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
import org.springframework.util.ReflectionUtils;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @see ClassUtils
 * @see InstantiationException
 * @since 1.0.0
 */
public final class InstanceUtils {

    private InstanceUtils() {
        super();
    }

    @NonNull
    public static <T> T newInstanceOrThrow(@NonNull Class<T> type) {
        return newInstanceOrThrow(type, new InstantiationExceptionSupplier(type));
    }

    @NonNull
    public static <T> T newInstanceOrThrow(@NonNull Class<T> type, Supplier<? extends RuntimeException> exceptionIfCannotCreateInstance) {
        Asserts.notNull(exceptionIfCannotCreateInstance);
        return newInstance(type)
                .orElseThrow(exceptionIfCannotCreateInstance);
    }

    @NonNull
    public static <T> Optional<T> newInstance(@NonNull Class<T> type) {
        Asserts.notNull(type);
        try {
            return Optional.of(
                    ReflectionUtils
                            .accessibleConstructor(type)
                            .newInstance()
            );
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> newInstance(@NonNull String className) {
        Asserts.hasText(className);
        try {
            final Class<?> type = ClassUtils.forNameOrThrow(className);
            return (Optional<T>) newInstance(type);
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
