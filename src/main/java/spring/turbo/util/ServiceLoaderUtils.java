/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.core.OrderComparator;
import org.springframework.util.ClassUtils;

import java.util.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ServiceLoaderUtils {

    private ServiceLoaderUtils() {
        super();
    }

    public static <T> List<T> load(Class<T> targetType) {
        return load(targetType, ClassUtils.getDefaultClassLoader());
    }

    public static <T> List<T> load(Class<T> targetType, ClassLoader classLoader) {
        Asserts.notNull(targetType);
        Asserts.notNull(classLoader);

        final ServiceLoader<T> loader = ServiceLoader.load(targetType, classLoader);
        List<T> list = new LinkedList<>();
        for (T it : loader) {
            list.add(it);
        }
        OrderComparator.sort(list);
        return Collections.unmodifiableList(list);
    }

    public static <T> List<T> loadQuietly(Class<T> targetType) {
        return loadQuietly(targetType, ClassUtils.getDefaultClassLoader());
    }

    public static <T> List<T> loadQuietly(Class<T> targetType, ClassLoader classLoader) {
        try {
            return load(targetType, classLoader);
        } catch (ServiceConfigurationError e) {
            return Collections.emptyList();
        }
    }

}
