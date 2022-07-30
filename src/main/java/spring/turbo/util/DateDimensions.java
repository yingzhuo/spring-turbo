/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.temporal.WeekFields;

/**
 * 时间维度
 *
 * @author 应卓
 * @see java.util.Date
 * @see java.time.LocalDate
 * @see #of(String, WeekOption)
 * @since 1.1.1
 */
public interface DateDimensions extends Serializable {

    public static DateDimensions of(String text, WeekOption weekOption) {
        return new DateDimensionsImpl(text, weekOption);
    }

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

    // -----------------------------------------------------------------------------------------------------------------

    public enum WeekOption {

        IOS(WeekFields.ISO),
        SUNDAY_START(WeekFields.of(DayOfWeek.SUNDAY, 1)),
        MONDAY_START(WeekFields.of(DayOfWeek.MONDAY, 1));

        private final WeekFields weekFields;

        WeekOption(WeekFields weekFields) {
            this.weekFields = weekFields;
        }

        public WeekFields getWeekFields() {
            return weekFields;
        }
    }

}
