package spring.turbo.util.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.OrderComparator;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;
import spring.turbo.util.ClassUtils;

import java.util.*;

/**
 * {@link ServiceLoader}相关工具
 *
 * @author 应卓
 * @see java.util.ServiceLoader
 * @see org.springframework.core.Ordered
 * @since 1.0.0
 */
public final class ServiceLoaderUtils {

    private static final Logger log = LoggerFactory.getLogger(ServiceLoaderUtils.class);

    /**
     * 私有构造方法
     */
    private ServiceLoaderUtils() {
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
        return load(targetType, null);
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
    public static <T> List<T> load(Class<T> targetType, @Nullable ClassLoader classLoader) {
        Asserts.notNull(targetType);

        try {
            classLoader = Objects.requireNonNullElseGet(classLoader, ClassUtils::getDefaultClassLoader);
            final ServiceLoader<T> loader = ServiceLoader.load(targetType, classLoader);
            List<T> list = new LinkedList<>();
            for (T it : loader) {
                list.add(it);
            }
            OrderComparator.sort(list);
            return Collections.unmodifiableList(list);
        } catch (ServiceConfigurationError error) {
            // 记录日志，但不吞此异常
            log.warn(error.getMessage(), error);
            throw error;
        }
    }

    /**
     * 加载Service
     *
     * @param targetType Service类型
     * @param <T>        Service类型泛型
     * @return Service实例
     */
    public static <T> List<T> loadQuietly(Class<T> targetType) {
        return loadQuietly(targetType, null);
    }

    /**
     * 加载Service
     *
     * @param targetType  Service类型
     * @param classLoader ClassLoader实例
     * @param <T>         Service类型泛型
     * @return Service实例
     */
    public static <T> List<T> loadQuietly(Class<T> targetType, @Nullable ClassLoader classLoader) {
        try {
            return load(targetType, classLoader);
        } catch (ServiceConfigurationError e) {
            return Collections.emptyList();
        }
    }

}
