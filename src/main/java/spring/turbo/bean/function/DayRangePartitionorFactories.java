/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.function;

import org.springframework.format.datetime.DateFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author 应卓
 * @see DayRangePartitionor
 * @see spring.turbo.bean.DayRange
 * @see DayRangePartitionor
 * @since 1.0.14
 */
public final class DayRangePartitionorFactories {

    public final static int SUNDAY = Calendar.SUNDAY;
    public final static int MONDAY = Calendar.MONDAY;
    public final static int TUESDAY = Calendar.TUESDAY;
    public final static int WEDNESDAY = Calendar.WEDNESDAY;
    public final static int THURSDAY = Calendar.THURSDAY;
    public final static int FRIDAY = Calendar.FRIDAY;
    public final static int SATURDAY = Calendar.SATURDAY;

    /**
     * 私有构造方法
     */
    private DayRangePartitionorFactories() {
        super();
    }

    /**
     * 按世纪区分
     *
     * @return 分区器实例
     */
    public static DayRangePartitionor byCentury() {
        return new AbstractDayRangePartitionor() {
            @Override
            public String test(Date date) {
                return String.valueOf(getCentury(date));
            }
        };
    }

    /**
     * 按年份区分
     *
     * @return 分区器实例
     */
    public static DayRangePartitionor byYear() {
        return new AbstractDayRangePartitionor() {
            @Override
            public String test(Date date) {
                return super.getYear(date);
            }
        };
    }

    /**
     * 按月份区分
     *
     * @return 分区器实例
     */
    public static DayRangePartitionor byMonth() {
        return new AbstractDayRangePartitionor() {
            @Override
            public String test(Date date) {
                return super.getYearMonth(date);
            }
        };
    }

    /**
     * 按周区分，已周日为一周开始
     *
     * @return 分区器实例
     */
    public static DayRangePartitionor byWeek() {
        return byWeek(SUNDAY, 4);
    }

    /**
     * 按周区分
     *
     * @param firstDayOfWeek         一周的开始
     * @param minimalDaysInFirstWeek 第一周最小天数
     * @return 分区器实例
     * @see #SUNDAY
     * @see #MONDAY
     * @see #TUESDAY
     * @see #WEDNESDAY
     * @see #THURSDAY
     * @see #FRIDAY
     * @see #SATURDAY
     */
    public static DayRangePartitionor byWeek(final int firstDayOfWeek, final int minimalDaysInFirstWeek) {
        return new AbstractDayRangePartitionor() {
            @Override
            public String test(Date date) {
                return super.getYearWeek(date, firstDayOfWeek);
            }
        };
    }

    /**
     * 按日区分
     *
     * @return 分区器实例
     */
    public static DayRangePartitionor byDate() {
        return date -> new DateFormatter("yyyy-MM-dd").print(date, Locale.getDefault());
    }

}
