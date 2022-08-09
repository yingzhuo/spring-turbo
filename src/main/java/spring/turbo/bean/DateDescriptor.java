/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.Nullable;
import spring.turbo.util.ObjectUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间维度
 *
 * @author 应卓
 * @see java.util.Date
 * @see java.time.LocalDate
 * @see #of(Date)
 * @see #of(String, WeekOption)
 * @see #of(Date, ZoneId)
 * @since 1.1.4
 */
public interface DateDescriptor extends Comparable<DateDescriptor>, Serializable {

    public static DateDescriptor of(String text) {
        return of(text, null);
    }

    public static DateDescriptor of(String text, @Nullable WeekOption weekOption) {
        return new DateDescriptorImpl(text, ObjectUtils.defaultIfNull(weekOption, WeekOption.SUNDAY_START));
    }

    public static DateDescriptor of(LocalDate date) {
        return of(date, null);
    }

    public static DateDescriptor of(Date date) {
        return of(date, null);
    }

    public static DateDescriptor of(Date date, @Nullable ZoneId zone) {
        return of(date, zone, null);
    }

    public static DateDescriptor of(Date date, @Nullable ZoneId zone, @Nullable WeekOption weekOption) {
        return of(date.toInstant().atZone(ObjectUtils.defaultIfNull(zone, ZoneId.systemDefault())).toLocalDate(), weekOption);
    }

    public static DateDescriptor of(LocalDate date, @Nullable WeekOption weekOption) {
        return new DateDescriptorImpl(date, ObjectUtils.defaultIfNull(weekOption, WeekOption.SUNDAY_START));
    }

    public String getCenturyString();

    public String getDayString();

    public String getPrevDayString();

    public String getNextDayString();

    public String getYearString();

    public boolean isLeapYear();

    public String getPrevYearString();

    public String getNextYearString();

    public String getYearMonthString();

    public String getPrevYearMonthString();

    public String getNextYearMonthString();

    public String getPrev12YearMonthString();

    public String getNext12YearMonthString();

    public String getYearQuarterString();

    public String getPrevYearQuarterString();

    public String getNextYearQuarterString();

    public String getPrev4YearQuarterString();

    public String getNext4YearQuarterString();

    public String getWeekString();

    public String getPrevWeekString();

    public String getNextWeekString();

    public WeekOption getWeekOption();

    public default boolean sameDate(DateDescriptor other) {
        return equals(other);
    }

    public default boolean before(DateDescriptor other) {
        return this.compareTo(other) < 0;
    }

    public default boolean after(DateDescriptor other) {
        return this.compareTo(other) > 0;
    }

    public default boolean beforeOrSameDate(DateDescriptor other) {
        return this.compareTo(other) <= 0;
    }

    public default boolean afterOrSameDate(DateDescriptor other) {
        return this.compareTo(other) >= 0;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public LocalDate toLocalDate();

    public Date toDate(@Nullable ZoneId zone);

    public default Date toDate() {
        return toDate(ZoneId.systemDefault());
    }

    public Calendar toCalendar(@Nullable ZoneId zone);

    public default Calendar toCalendar() {
        return toCalendar(ZoneId.systemDefault());
    }

}
