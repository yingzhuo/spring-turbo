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
import org.springframework.util.ReflectionUtils;
import spring.turbo.core.AnnotationUtils;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringPool;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ValueObjectUtils {

    private ValueObjectUtils() {
        super();
    }

    public static <T> Optional<T> newInstance(Class<T> valueObjectType) {
        Asserts.notNull(valueObjectType);
        try {
            return Optional.of(
                    ReflectionUtils
                            .accessibleConstructor(valueObjectType)
                            .newInstance()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <T> Map<String, String> getAliases(Class<T> valueObjectType) {
        Asserts.notNull(valueObjectType);

        final Map<String, String> map = new HashMap<>();

        ReflectionUtils.doWithFields(valueObjectType, field -> {
            final AnnotationAttributes attributes = AnnotationUtils.findAnnotationAttributes(field, Alias.class);
            final String from = attributes.getString("value");
            if (!StringPool.ANNOTATION_STRING_NULL.equals(from)) {
                map.put(from, field.getName());
            }
        });

        return Collections.unmodifiableMap(map);
    }

}
