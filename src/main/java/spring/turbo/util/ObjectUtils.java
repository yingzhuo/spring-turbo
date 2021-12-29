/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ObjectUtils {

    private ObjectUtils() {
        super();
    }

    public static <T> T defaultIfNull(@Nullable T obj, T defaultValue) {
        return Optional.ofNullable(obj).orElse(defaultValue);
    }

    public static <T> T defaultIfNull(@Nullable T obj, Supplier<T> defaultValue) {
        return Optional.ofNullable(obj).orElseGet(defaultValue);
    }

    @Nullable
    @SafeVarargs
    public static <T> T firstNonNull(final T... values) {
        if (values != null) {
            for (final T val : values) {
                if (val != null) {
                    return val;
                }
            }
        }
        return null;
    }

    @Nullable
    @SafeVarargs
    public static <T> T getFirstNonNull(final Supplier<T>... suppliers) {
        if (suppliers != null) {
            for (final Supplier<T> supplier : suppliers) {
                if (supplier != null) {
                    final T value = supplier.get();
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
        return null;
    }

}
