package spring.turbo.util.time;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * {@link LocalDate} 相关工具
 *
 * @author 应卓
 * @see java.time.LocalDate
 * @since 3.2.6
 */
public final class LocalDateUtils {

    /**
     * 私有构造方法
     */
    private LocalDateUtils() {
    }

    /**
     * 转换成{@link Date} 实例
     *
     * @param date {@link LocalDate} 实例
     * @return {@link Date} 实例
     */
    public static Date toDate(LocalDate date) {
        return toDate(date, null);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 转换成{@link Date} 实例
     *
     * @param date   {@link LocalDate} 实例
     * @param zoneId timezone
     * @return {@link Date} 实例
     * @see ZoneIdUtils
     */
    public static Date toDate(LocalDate date, @Nullable ZoneId zoneId) {
        zoneId = Objects.requireNonNullElse(zoneId, ZoneId.systemDefault());
        Instant instant = Instant.from(date.atStartOfDay(zoneId));
        return Date.from(instant);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@link Date} 转换成 {@link LocalDate}
     *
     * @param date 日期
     * @return 转换结果
     */
    public static LocalDate toLocalDate(Date date) {
        Assert.notNull(date, "date is required");
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 判断两个日期是否为同一天
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 判断结果
     */
    public static boolean isSameDay(LocalDate date1, LocalDate date2) {
        Assert.notNull(date1, "date1 is required");
        Assert.notNull(date2, "date2 is required");
        return date1.compareTo(date2) == 0;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 计算两个日期之间的日期差
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return 两个日期的日期差
     */
    public static int distanceDays(LocalDate date1, LocalDate date2) {
        Assert.notNull(date1, "date1 is required");
        Assert.notNull(date2, "date2 is required");
        return (int) Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 计算黄道十二宫
     *
     * @param date 日期
     * @return 黄道十二宫
     */
    public static Zodiac zodiac(LocalDate date) {
        Assert.notNull(date, "date is required");
        return Zodiac.getZodiac(date.getMonthValue(), date.getDayOfMonth());
    }

}
