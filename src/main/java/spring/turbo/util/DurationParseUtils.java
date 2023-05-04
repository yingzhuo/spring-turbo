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
 * {@link Duration} 解析工具
 *
 * @author 应卓
 *
 * @see DurationFormatUtils
 *
 * @since 1.0.1
 */
public final class DurationParseUtils {

    /**
     * 私有构造方法
     */
    private DurationParseUtils() {
        super();
    }

    /**
     * 从字符串解析 {@link Duration}
     * <p>
     * 用法:
     * <ul>
     * <li>{@code DurationParseUtils.parse("200ms")}</li>
     * <li>{@code DurationParseUtils.parse("1h")}</li>
     * <li>{@code DurationParseUtils.parse("P21D")}</li>
     * <li>{@code DurationParseUtils.parse("P3Y6M")}</li>
     * </ul>
     *
     * @param string
     *            待解析的字符串
     *
     * @return 解析结果
     *
     * @throws IllegalArgumentException
     *             字符串格式不正确
     */
    public static Duration parse(String string) {
        return parse(string, null);
    }

    /**
     * 从字符串解析 {@link Duration}
     * <p>
     * 用法:
     * <ul>
     * <li>{@code DurationParseUtils.parse("200ms", ChronoUnit.MILLIS)}</li>
     * <li>{@code DurationParseUtils.parse("1h", ChronoUnit.MILLIS)}</li>
     * <li>{@code DurationParseUtils.parse("P21D", null)}</li>
     * <li>{@code DurationParseUtils.parse("P3Y6M", null)}</li>
     * </ul>
     *
     * @param string
     *            待解析的字符串
     * @param unit
     *            默认时间单位，可为空，为空时为毫秒
     *
     * @return 解析结果
     *
     * @throws IllegalArgumentException
     *             字符串格式不正确
     */
    public static Duration parse(@NonNull String string, @Nullable ChronoUnit unit) {
        Asserts.notNull(string);

        Duration duration = parseFromSimpleStyle(string, unit);
        if (duration == null) {
            duration = parseFromISO8601Style(string, unit);
        }

        if (duration == null) {
            throw new IllegalArgumentException("'" + string + "' is not a valid duration");
        }

        return duration;
    }

    @Nullable
    private static Duration parseFromSimpleStyle(String string, @Nullable ChronoUnit unit) {
        try {
            return DurationStyle.SIMPLE.parse(string, unit);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private static Duration parseFromISO8601Style(String string, @Nullable ChronoUnit unit) {
        try {
            return DurationStyle.ISO8601.parse(string, unit);
        } catch (Exception e) {
            return null;
        }
    }

}
