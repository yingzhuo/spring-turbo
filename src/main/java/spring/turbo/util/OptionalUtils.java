/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class OptionalUtils {

    private OptionalUtils() {
        super();
    }

    public static <T> T getQuietly(Optional optional) {
        if (optional == null) {
            return null;
        }
        try {
            Object obj = optional.get();
            if (!(obj instanceof Optional)) {
                return (T) obj;
            } else {
                return getQuietly((Optional) obj);
            }
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static <T> T getOrDefaultQuietly(Optional optional, T defaultValue) {
        T obj = getQuietly(optional);
        return Optional.ofNullable(obj).orElse(defaultValue);
    }

}
