/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;
import spring.turbo.core.AnnotationUtils;
import spring.turbo.util.Asserts;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static spring.turbo.util.StringPool.ANNOTATION_STRING_NULL;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ValueObjectUtils {

    /**
     * 私有构造方法
     */
    private ValueObjectUtils() {
        super();
    }

    /**
     * 获取VO类各个property的别名
     *
     * @param valueObjectType VO类型
     * @return 别名
     */
    @NonNull
    public static Map<String, String> getAliases(@NonNull Class<?> valueObjectType) {
        Asserts.notNull(valueObjectType);

        final Map<String, String> map = new HashMap<>();

        // 先从type上查找
        final Alias.List listAnnotation = AnnotationUtils.findAnnotation(valueObjectType, Alias.List.class);
        if (listAnnotation != null) {
            for (Alias annotation : listAnnotation.value()) {
                final String from = annotation.from();
                final String to = annotation.to();
                if (!ANNOTATION_STRING_NULL.equals(from) && !ANNOTATION_STRING_NULL.equals(to)) {
                    map.put(from, to);
                }
            }
        } else {
            final Alias annotation = AnnotationUtils.findAnnotation(valueObjectType, Alias.class);
            if (annotation != null) {
                final String from = annotation.from();
                final String to = annotation.to();
                if (!ANNOTATION_STRING_NULL.equals(from) && !ANNOTATION_STRING_NULL.equals(to)) {
                    map.put(from, to);
                }
            }
        }

        // 后从field上查找
        ReflectionUtils.doWithFields(valueObjectType, field -> {
            final AnnotationAttributes attributes = AnnotationUtils.findAnnotationAttributes(field, Alias.class);

            if (attributes.containsKey("value")) {
                final String from = attributes.getString("value");
                if (!ANNOTATION_STRING_NULL.equals(from)) {
                    map.put(from, field.getName());
                }
            }
        });

        return Collections.unmodifiableMap(map);
    }

    /**
     * 将ValueObject转换为 {@link ValueObjectGetter} 实例
     *
     * @param valueObject ValueObject实例 不可为 {@code null}
     * @return {@link ValueObjectGetter} 实例
     * @since 1.0.6
     */
    public static ValueObjectGetter toValueObjectGetter(@NonNull Object valueObject) {
        return new ValueObjectGetter(valueObject);
    }

}
