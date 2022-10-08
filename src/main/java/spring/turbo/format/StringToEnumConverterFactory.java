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

/**
 * 替代Spring原厂的 String -&gt; Enum转换器
 *
 * @author 应卓
 * @since 1.2.1
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum(getEnumType(targetType));
    }

    private Class<?> getEnumType(Class<?> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        Asserts.notNull(enumType, () -> "The target type " + targetType.getName() + " does not refer to an enum");
        return enumType;
    }

    private static class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (StringUtils.isBlank(source)) {
                return null;
            }

            source = source
                    .replaceAll(StringPool.HYPHEN, StringPool.UNDERSCORE)
                    .trim();

            try {
                return (T) Enum.valueOf(this.enumType, source);
            } catch (Exception e) {
                // 如果不行，尝试转成大写再试一次。
                source = source.toUpperCase();
                return (T) Enum.valueOf(this.enumType, source);
            }
        }
    }

}
