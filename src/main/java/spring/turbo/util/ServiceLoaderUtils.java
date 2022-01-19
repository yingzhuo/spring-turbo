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

import java.util.*;

/**
 * {@link ServiceLoader}相关工具
 *
 * @author 应卓
 * @see java.util.ServiceLoader
 * @since 1.0.0
 */
public final class ServiceLoaderUtils {

    /**
     * 私有构造方法
     */
    private ServiceLoaderUtils() {
        super();
    }

    /**
     * 加载Service
     *
     * @param targetType Service类型
     * @param <T>        Service类型泛型
     * @return Service实例
     * @throws ServiceConfigurationError 加载失败时抛出此错误
     */
    public static <T> List<T> load(Class<T> targetType) {
        return load(targetType, ClassUtils.getDefaultClassLoader());
    }

    /**
     * 加载Service
     *
     * @param targetType  Service类型
     * @param classLoader ClassLoader实例
     * @param <T>         Service类型泛型
     * @return Service实例
     * @throws ServiceConfigurationError 加载失败时抛出此错误
     */
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

    /**
     * 加载Service
     *
     * @param targetType Service类型
     * @param <T>        Service类型泛型
     * @return Service实例
     */
    public static <T> List<T> loadQuietly(Class<T> targetType) {
        return loadQuietly(targetType, ClassUtils.getDefaultClassLoader());
    }

    /**
     * 加载Service
     *
     * @param targetType  Service类型
     * @param classLoader ClassLoader实例
     * @param <T>         Service类型泛型
     * @return Service实例
     */
    public static <T> List<T> loadQuietly(Class<T> targetType, ClassLoader classLoader) {
        try {
            return load(targetType, classLoader);
        } catch (ServiceConfigurationError e) {
            return Collections.emptyList();
        }
    }

}
