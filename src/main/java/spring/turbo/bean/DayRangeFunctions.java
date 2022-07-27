/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.util.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

/**
 * @author 应卓
 * @see DayRange
 * @since 1.1.3
 */
public final class DayRangeFunctions {

    /**
     * 私有构造方法
     */
    private DayRangeFunctions() {
        super();
    }

    public static Function<Date, Date> nop() {
        return d -> d;
    }

    public static Function<Date, Date> nextDayAndTruncate() {
        return d -> DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE), 1);
    }

}
