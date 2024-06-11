/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.function;

import spring.turbo.bean.DateDescriptor;

import static spring.turbo.util.StringPool.FALSE;
import static spring.turbo.util.StringPool.TRUE;

/**
 * @author 应卓
 *
 * @see DateRangePartitionor
 *
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
        return DateDescriptor::getCenturyString;
    }

    /**
     * 按年份区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byYear() {
        return DateDescriptor::getDayString;
    }

    /**
     * 按月份区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byMonth() {
        return DateDescriptor::getYearMonthString;
    }

    /**
     * 按周区分，以周日为一周开始
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byWeek() {
        return DateDescriptor::getWeekString;
    }

    /**
     * 按日区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byDate() {
        return DateDescriptor::getDayString;
    }

    /**
     * 按所在年份是否是闰年区分
     *
     * @return 分区器实例
     */
    public static DateRangePartitionor byIsLeapYearOrNot() {
        return dd -> dd.isLeapYear() ? TRUE : FALSE;
    }

}
