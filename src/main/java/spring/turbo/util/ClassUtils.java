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
 * @author 应卓
 * @see InstanceUtils
 * @since 1.0.2
 */
public final class ClassUtils {

    private static final ClassLoader DEFAULT_CLASSLOADER = org.springframework.util.ClassUtils.getDefaultClassLoader();

    private ClassUtils() {
        super();
    }

    public static ClassLoader getDefaultClassloader() {
        return DEFAULT_CLASSLOADER;
    }

    @NonNull
    public static Class<?> forNameOrThrow(@NonNull String className) {
        return forNameOrThrow(className, new ClassLoadingExceptionSupplier(className));
    }

    @NonNull
    public static Class<?> forNameOrThrow(@NonNull String className, Supplier<? extends RuntimeException> exceptionIfCannotLoad) {
        return forName(className).orElseThrow(exceptionIfCannotLoad);
    }

    public static Optional<Class<?>> forName(@NonNull String className) {
        try {
            final Class<?> clazz = org.springframework.util.ClassUtils.forName(className, DEFAULT_CLASSLOADER);
            return Optional.of(clazz);
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    public static boolean isPresent(@NonNull String className) {
        return org.springframework.util.ClassUtils.isPresent(className, DEFAULT_CLASSLOADER);
    }

}
