package spring.turbo.util.time;

import spring.turbo.util.Asserts;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * {@link LocalDateTime} 相关工具
 *
 * @author 应卓
 * @since 3.2.6
 */
public final class LocalDateTimeUtils {

    /**
     * 私有构造方法
     */
    private LocalDateTimeUtils() {
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@link Date} 转换成 {@link LocalDateTime}
     *
     * @param date 日期
     * @return 转换结果
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        Asserts.notNull(date);
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
