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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author 应卓
 *
 * @see java.time.LocalDate
 *
 * @since 3.2.6
 */
public final class LocalDateUtils {

    public static int betweenDays(LocalDate date1, LocalDate date2) {
        Asserts.notNull(date1);
        Asserts.notNull(date2);
        return (int) Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }

    /**
     * 私有构造方法
     */
    private LocalDateUtils() {
        super();
    }

}
