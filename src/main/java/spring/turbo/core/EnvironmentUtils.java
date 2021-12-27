/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import spring.turbo.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 应卓
 * @see SpringUtils
 * @since 1.0.0
 */
public final class EnvironmentUtils {

    private EnvironmentUtils() {
        super();
    }

    public static String resolvePlaceholders(String text) {
        if (text == null) return null;
        return SpringUtils.getEnvironment().resolvePlaceholders(text);
    }

    public static String resolveRequiredPlaceholders(String text) {
        return SpringUtils.getEnvironment().resolveRequiredPlaceholders(text);
    }

    public static String getPropertyValue(String propertyName) {
        return getPropertyValue(propertyName, String.class);
    }

    public static <T> T getPropertyValue(String propertyName, Class<T> targetType) {
        return getPropertyValue(propertyName, targetType, null);
    }

    public static <T> T getPropertyValue(String propertyName, Class<T> targetType, T defaultIfNull) {
        T result = SpringUtils.getEnvironment().getProperty(propertyName, targetType);
        return result != null ? result : defaultIfNull;
    }

    public static List<String> getCommaDelimitedPropertyValue(String propertyName) {
        final String value = getPropertyValue(propertyName);

        if (value == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(Arrays.asList(StringUtils.commaDelimitedListToStringArray(value, true)));
        }
    }

}
