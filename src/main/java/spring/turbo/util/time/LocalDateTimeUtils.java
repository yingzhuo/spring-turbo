/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
 *
 * @since 3.2.6
 */
public final class LocalDateTimeUtils {

    /**
     * 私有构造方法
     */
    private LocalDateTimeUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@link Date} 转换成 {@link LocalDateTime}
     *
     * @param date
     *            日期
     *
     * @return 转换结果
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        Asserts.notNull(date);
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
