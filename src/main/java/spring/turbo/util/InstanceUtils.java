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
 * @author 应卓
 * @see UncheckedInstantiationException
 * @since 1.0.0
 */
public final class InstanceUtils {

    private InstanceUtils() {
        super();
    }

    public static <T> T newInstanceOrThrow(Class<T> valueObjectType) {
        Asserts.notNull(valueObjectType);
        try {
            return ReflectionUtils
                    .accessibleConstructor(valueObjectType)
                    .newInstance();
        } catch (Exception e) {
            throw new UncheckedInstantiationException(e.getMessage());
        }
    }

    public static <T> T newInstanceOrThrow(Class<T> valueObjectType, Supplier<? extends RuntimeException> exceptionSupplier) {
        Asserts.notNull(exceptionSupplier);
        return newInstance(valueObjectType)
                .orElseThrow(exceptionSupplier);
    }

    public static <T> Optional<T> newInstance(Class<T> valueObjectType) {
        Asserts.notNull(valueObjectType);
        try {
            return Optional.of(
                    ReflectionUtils
                            .accessibleConstructor(valueObjectType)
                            .newInstance()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
