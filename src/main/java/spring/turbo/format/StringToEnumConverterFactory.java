/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringPool;
import spring.turbo.util.StringUtils;
import spring.turbo.util.reflection.MethodUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import static spring.turbo.util.reflection.MethodPredicateFactories.*;

/**
 * 替代Spring原厂的 String -&gt; Enum转换器
 *
 * @author 应卓
 *
 * @see EnumConvertingMethod
 *
 * @since 1.2.1
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    /**
     * 构造方法
     */
    public StringToEnumConverterFactory() {
        super();
    }

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter(getEnumType(targetType));
    }

    private Class<?> getEnumType(Class<?> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        Asserts.notNull(enumType, () -> "The target type " + targetType.getName() + " does not refer to an enum");
        return enumType;
    }

    private static class StringToEnumConverter<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        private StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Nullable
        @Override
        public T convert(String source) {

            final String oriSource = source;

            if (StringUtils.isBlank(source)) {
                return null;
            }

            source = source.replaceAll(StringPool.HYPHEN, StringPool.UNDERSCORE).trim();

            try {
                // 一般性尝试
                return (T) Enum.valueOf(this.enumType, source);
            } catch (Exception e) {
                // 尝试转成大写再试一次。
                source = source.toUpperCase();
                try {
                    return (T) Enum.valueOf(this.enumType, source);
                } catch (Exception ex) {
                    // 都不行的话动用反射
                    T ret = convertViaReflection(oriSource);
                    if (ret != null) {
                        return ret;
                    } else {
                        throw ex;
                    }
                }
            }
        }

        @Nullable
        private T convertViaReflection(String source) {

            final Method method = this.findConvertingMethod();
            if (method == null) {
                return null;
            }

            // 反射调用方法
            try {
                return (T) method.invoke(null, source);
            } catch (Throwable e) {
                return null;
            }
        }

        @Nullable
        private Method findConvertingMethod() {
            List<Method> ret = MethodUtils.find(enumType);
            ret = ret.stream().filter(
                    all(isUserDeclaredMethod(), isPublic(), isStatic(), withAnnotation(EnumConvertingMethod.class)))
                    .collect(Collectors.toList());

            // 找到多个形同没有找到
            if (ret.size() != 1) {
                return null;
            }

            return ret.get(0);
        }
    }

}
