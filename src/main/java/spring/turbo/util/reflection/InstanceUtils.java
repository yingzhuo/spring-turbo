package spring.turbo.util.reflection;

import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;
import spring.turbo.util.ClassUtils;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 实例创建工具
 *
 * @author 应卓
 * @see ClassUtils
 * @see InstantiationException
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
     * @param type 类型
     * @param <T>  实例类型泛型
     * @return 实例
     * @throws InstantiationException 创建实例无法成功
     */
    public static <T> T newInstanceElseThrow(Class<T> type) {
        return newInstanceElseThrow(type, new InstantiationExceptionSupplier(type));
    }

    /**
     * 创建实例，不成功时抛出异常
     *
     * @param type                            类型
     * @param exceptionIfCannotCreateInstance 异常提供器
     * @param <T>                             实例类型泛型
     * @return 实例
     */
    public static <T> T newInstanceElseThrow(Class<T> type,
                                             Supplier<? extends RuntimeException> exceptionIfCannotCreateInstance) {
        return newInstance(type).orElseThrow(exceptionIfCannotCreateInstance);
    }

    /**
     * 尝试创建实例
     *
     * @param type 类型
     * @param <T>  实例类型泛型
     * @return 实例Optional，不成功时返回空的Optional
     */
    public static <T> Optional<T> newInstance(Class<T> type) {
        try {
            var constructor = ReflectionUtils.accessibleConstructor(type);
            ReflectionUtils.makeAccessible(constructor);
            return Optional.of(constructor.newInstance());
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    /**
     * 尝试加载类型并创建实例
     *
     * @param className 类型名称
     * @param <T>       实例类型泛型
     * @return 实例Optional，不成功时返回空的Optional
     * @see ClassUtils#forName(String) 加载类型
     */
    public static <T> Optional<T> newInstance(String className) {
        try {
            final Class<?> type = ClassUtils.forNameElseThrow(className);
            return (Optional<T>) newInstance(type);
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    /**
     * 尝试创建实例
     *
     * @param type            类型
     * @param defaultInstance 创建失败时返回的默认实例
     * @param <T>             实例类型泛型
     * @return 实例
     */
    @Nullable
    public static <T> T newInstanceElse(Class<T> type, @Nullable T defaultInstance) {
        return newInstance(type).orElse(defaultInstance);
    }

    /**
     * 尝试创建实例
     *
     * @param className       类型名称
     * @param defaultInstance 创建失败时返回的默认实例
     * @param <T>             实例类型泛型
     * @return 实例
     */
    @Nullable
    public static <T> T newInstanceElse(String className, @Nullable T defaultInstance) {
        return (T) newInstance(className).orElse(defaultInstance);
    }

    /**
     * 尝试创建实例
     *
     * @param type            类型
     * @param defaultSupplier 创建失败时返回的默认实例的Supplier
     * @param <T>             实例类型泛型
     * @return 实例
     */
    @Nullable
    public static <T> T newInstanceElseGet(Class<T> type, Supplier<? extends T> defaultSupplier) {
        return newInstance(type).orElseGet(defaultSupplier);
    }

    /**
     * 尝试创建实例
     *
     * @param className       类型名称
     * @param defaultSupplier 创建失败时返回的默认实例的Supplier
     * @param <T>             实例类型泛型
     * @return 实例
     */
    @Nullable
    public static <T> T newInstanceElseGet(String className, Supplier<? extends T> defaultSupplier) {
        return (T) newInstance(className).orElseGet(defaultSupplier);
    }

}
