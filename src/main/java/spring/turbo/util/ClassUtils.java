/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 类加载工具
 *
 * @author 应卓
 * @see InstanceUtils
 * @since 1.0.2
 */
public final class ClassUtils {

    private static final ClassLoader DEFAULT_CLASSLOADER = org.springframework.util.ClassUtils.getDefaultClassLoader();

    /**
     * 私有构造方法
     */
    private ClassUtils() {
        super();
    }

    /**
     * 获取默认的{@link ClassLoader}
     *
     * @return {@link ClassLoader}实例
     */
    @NonNull
    public static ClassLoader getDefaultClassloader() {
        // (only null if even the system ClassLoader isn't accessible)
        return Optional.ofNullable(DEFAULT_CLASSLOADER)
                .orElse(Thread.currentThread().getContextClassLoader());
    }

    /**
     * 尝试加载类型或抛出异常
     *
     * @param className 类型全名，不可为{@code null}
     * @return 加载结果
     * @see #forName(String)
     * @see #forNameOrThrow(String, Supplier)
     */
    @NonNull
    public static Class<?> forNameOrThrow(@NonNull String className) {
        return forNameOrThrow(className, new ClassLoadingExceptionSupplier(className));
    }

    /**
     * 尝试加载类型或抛出异常
     *
     * @param className             类型全名，不可为{@code null}
     * @param exceptionIfCannotLoad 异常提供者，不可为{@code null}
     * @return 加载结果
     */
    @NonNull
    public static Class<?> forNameOrThrow(@NonNull String className, @NonNull Supplier<? extends RuntimeException> exceptionIfCannotLoad) {
        Asserts.notNull(exceptionIfCannotLoad);
        return forName(className).orElseThrow(exceptionIfCannotLoad);
    }

    /**
     * 尝试加载类型
     * <p>
     * 例子:
     * {@code ClassUtils.forName("int")}
     * {@code ClassUtils.forName("int[]")}
     * {@code ClassUtils.forName("[[Ljava.lang.String;")}
     * {@code ClassUtils.forName("foo.Bar")}
     * {@code ClassUtils.forName("foo.Bar[]")}
     * {@code ClassUtils.forName("foo.Bar.InnerClass")}
     * {@code ClassUtils.forName("foo.Bar$InnerClass")}
     *
     * @param className 类型全名
     * @return 加载结果
     * @see #forNameOrThrow(String)
     * @see #forNameOrThrow(String, Supplier)
     */
    @NonNull
    public static Optional<Class<?>> forName(@NonNull String className) {
        try {
            final Class<?> clazz = org.springframework.util.ClassUtils.forName(className, DEFAULT_CLASSLOADER);
            return Optional.of(clazz);
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    /**
     * 判断类型是否存在
     *
     * @param className 类型全名
     * @return 存在时返回true，否则返回false
     */
    public static boolean isPresent(@NonNull String className) {
        try {
            return org.springframework.util.ClassUtils.isPresent(className, DEFAULT_CLASSLOADER);
        } catch (IllegalAccessError e) {
            // 典型情况，该类型的依赖有缺失
            return false;
        }
    }

}
