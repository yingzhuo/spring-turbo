package spring.turbo.util.time;

import org.springframework.boot.convert.DurationStyle;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

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
    }

    /**
     * 格式化 {@link Duration}实例
     *
     * @param duration {@link Duration}实例
     * @return 格式化字符串
     */
    public static String format(Duration duration) {
        return format(duration, null);
    }

    /**
     * 格式化 {@link Duration}实例
     *
     * @param duration {@link Duration}实例
     * @param unit     默认时间单位，可为空，为空时为毫秒
     * @return 格式化字符串
     */
    public static String format(Duration duration, @Nullable ChronoUnit unit) {
        Asserts.notNull(duration);
        return DurationStyle.ISO8601.print(duration, unit);
    }

}
