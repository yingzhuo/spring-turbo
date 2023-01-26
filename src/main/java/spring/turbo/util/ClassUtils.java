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
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link Class} 相关工具
 *
 * @author 应卓
 * @see InstanceUtils
 * @since 1.0.2
 */
public final class ClassUtils {

    @Nullable
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
    public static ClassLoader getDefaultClassLoader() {
        // (only null if even the system ClassLoader isn't accessible)
        return Optional.ofNullable(DEFAULT_CLASSLOADER)
                .orElse(Thread.currentThread().getContextClassLoader());
    }

    /**
     * 尝试加载类型或抛出异常
     *
     * @param className 类型全名，不可为 {@code null}
     * @return 加载结果
     * @see #forName(String)
     * @see #forNameElseThrow(String, Supplier)
     */
    public static Class<?> forNameElseThrow(@NonNull String className) {
        return forNameElseThrow(className, new ClassLoadingExceptionSupplier(className));
    }

    /**
     * 尝试加载类型或抛出异常
     *
     * @param className             类型全名，不可为 {@code null}
     * @param exceptionIfCannotLoad 异常提供者，不可为 {@code null}
     * @return 加载结果
     */
    public static Class<?> forNameElseThrow(@NonNull String className, @NonNull Supplier<? extends RuntimeException> exceptionIfCannotLoad) {
        Asserts.notNull(exceptionIfCannotLoad);
        return forName(className).orElseThrow(exceptionIfCannotLoad);
    }

    /**
     * 尝试加载类型
     * <p>
     * 例子:
     * <ul>
     * <li> {@code ClassUtils.forName("int")} </li>
     * <li> {@code ClassUtils.forName("int[]")} </li>
     * <li> {@code ClassUtils.forName("[[Ljava.lang.String;")} </li>
     * <li> {@code ClassUtils.forName("foo.Bar")} </li>
     * <li> {@code ClassUtils.forName("foo.Bar[]")} </li>
     * <li> {@code ClassUtils.forName("foo.Bar.InnerClass")} </li>
     * <li> {@code ClassUtils.forName("foo.Bar$InnerClass")} </li>
     * </ul>
     *
     * @param className 类型全名
     * @return 加载结果
     * @see #forNameElseThrow(String)
     * @see #forNameElseThrow(String, Supplier)
     */
    public static Optional<Class<?>> forName(String className) {
        try {
            Asserts.notNull(className);
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
    public static boolean isPresent(String className) {
        try {
            Asserts.notNull(className);
            return org.springframework.util.ClassUtils.isPresent(className, DEFAULT_CLASSLOADER);
        } catch (IllegalAccessError e) {
            // 典型情况，该类型的依赖有缺失
            return false;
        }
    }

    /**
     * 判断类型是否不存在
     *
     * @param className 类型全名
     * @return 存在时返回false，否则返回true
     */
    public static boolean isAbsent(String className) {
        return !isPresent(className);
    }

    /**
     * 获取包名
     *
     * @param clz 类型
     * @return 包名
     */
    public static String getPackageName(Class<?> clz) {
        return org.springframework.util.ClassUtils.getPackageName(clz);
    }

    /**
     * 获取包名
     *
     * @param className 类型
     * @return 包名
     */
    public static String getPackageName(String className) {
        return org.springframework.util.ClassUtils.getPackageName(className);
    }

}
