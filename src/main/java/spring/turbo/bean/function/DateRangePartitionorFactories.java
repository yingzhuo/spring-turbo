/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.function;

import spring.turbo.bean.DateDim;
import spring.turbo.bean.DateRange;

/**
 * @author 应卓
 * @see DateRangePartitionor
 * @see DateRange
 * @since 1.1.4
 */
public final class DateRangePartitionorFactories {

    /**
     * 私有构造方法
     */
    private DateRangePartitionorFactories() {
        super();
    }

    /**
     * 按世纪区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byCentury() {
        return DateDim::getCenturyString;
    }

    /**
     * 按年份区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byYear() {
        return DateDim::getDayString;
    }

    /**
     * 按月份区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byMonth() {
        return DateDim::getYearMonthString;
    }

    /**
     * 按周区分，已周日为一周开始
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byWeek() {
        return DateDim::getWeekString;
    }

    /**
     * 按日区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byDate() {
        return DateDim::getDayString;
    }

    /**
     * 按所在年份是否是闰年区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byIsLeapYearOrNot() {
        return dd -> dd.isLeapYear() ? "true" : "false";
    }

}
