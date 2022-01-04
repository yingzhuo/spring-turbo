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
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * {@link Duration} 格式化工具
 *
 * @author 应卓
 * @see DurationParseUtils
 * @since 1.0.1
 */
public final class DurationFormatUtils {

    /**
     * 私有构造方法
     */
    private DurationFormatUtils() {
        super();
    }

    /**
     * 格式化 {@link Duration}实例
     *
     * @param duration {@link Duration}实例
     * @return 格式化字符串
     */
    @NonNull
    public static String format(@NonNull Duration duration) {
        return format(duration, null);
    }

    /**
     * 格式化 {@link Duration}实例
     *
     * @param duration {@link Duration}实例
     * @param unit     默认时间单位，可为空，为空时为毫秒
     * @return 格式化字符串
     */
    @NonNull
    public static String format(@NonNull Duration duration, @Nullable ChronoUnit unit) {
        Asserts.notNull(duration);
        return DurationStyle.ISO8601.print(duration, unit);
    }

}
