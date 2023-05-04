/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.util.ReflectionUtils;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 实例创建工具
 *
 * @author 应卓
 *
 * @see ClassUtils
 * @see InstantiationException
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public final class InstanceUtils {

    /**
     * 私有构造方法
     */
    private InstanceUtils() {
        super();
    }

    /**
     * 创建实例，不成功时抛出默认异常
     *
     * @param type
     *            类型
     * @param <T>
     *            实例类型泛型
     *
     * @return 实例
     *
     * @throws InstantiationException
     *             创建实例无法成功
     */
    public static <T> T newInstanceElseThrow(Class<T> type) {
        return newInstanceElseThrow(type, new InstantiationExceptionSupplier(type));
    }

    /**
     * 创建实例，不成功时抛出异常
     *
     * @param type
     *            类型
     * @param exceptionIfCannotCreateInstance
     *            异常提供器
     * @param <T>
     *            实例类型泛型
     *
     * @return 实例
     */
    public static <T> T newInstanceElseThrow(Class<T> type,
            Supplier<? extends RuntimeException> exceptionIfCannotCreateInstance) {
        Asserts.notNull(exceptionIfCannotCreateInstance);
        return newInstance(type).orElseThrow(exceptionIfCannotCreateInstance);
    }

    /**
     * 尝试创建实例
     *
     * @param type
     *            类型
     * @param <T>
     *            实例类型泛型
     *
     * @return 实例Optional，不成功时返回空的Optional
     */
    public static <T> Optional<T> newInstance(Class<T> type) {
        Asserts.notNull(type);
        try {
            return Optional.of(ReflectionUtils.accessibleConstructor(type).newInstance());
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    /**
     * 尝试加载类型并创建实例
     *
     * @param className
     *            类型名称
     * @param <T>
     *            实例类型泛型
     *
     * @return 实例Optional，不成功时返回空的Optional
     *
     * @see ClassUtils#forName(String) 加载类型
     */
    public static <T> Optional<T> newInstance(String className) {
        Asserts.hasText(className);
        try {
            final Class<?> type = ClassUtils.forNameElseThrow(className);
            return (Optional<T>) newInstance(type);
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
