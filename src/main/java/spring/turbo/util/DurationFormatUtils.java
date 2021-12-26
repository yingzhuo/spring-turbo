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
 * @see DurationParseUtils
 * @since 1.0.1
 */
public final class DurationFormatUtils {

    private DurationFormatUtils() {
        super();
    }

    public static String format(@Nullable Duration duration) {
        return format(duration, null);
    }

    public static String format(@Nullable Duration duration, @Nullable ChronoUnit unit) {
        if (duration == null) {
            return null;
        }
        return DurationStyle.ISO8601.print(duration, unit);
    }

}
