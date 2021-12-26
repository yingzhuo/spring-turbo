/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.boot.convert.DurationStyle;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author 应卓
 * @see DurationFormatUtils
 * @since 1.0.1
 */
public final class DurationParseUtils {

    private DurationParseUtils() {
        super();
    }

    public static Duration parse(@Nullable String string) {
        return parse(string, null);
    }

    public static Duration parse(@Nullable String string, @Nullable ChronoUnit unit) {
        if (string == null) {
            return null;
        }

        Duration duration = parseFromSimpleStyle(string, unit);
        if (duration == null) {
            duration = parseFromISO8601Style(string, unit);
        }

        if (duration == null) {
            throw new IllegalArgumentException("'" + string + "' is not a valid duration");
        }

        return duration;
    }

    private static Duration parseFromSimpleStyle(String string, ChronoUnit unit) {
        try {
            return DurationStyle.SIMPLE.parse(string, unit);
        } catch (Exception e) {
            return null;
        }
    }

    private static Duration parseFromISO8601Style(String string, ChronoUnit unit) {
        try {
            return DurationStyle.ISO8601.parse(string, unit);
        } catch (Exception e) {
            return null;
        }
    }

}
