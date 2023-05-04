/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.format.datetime.DateFormatter;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author 应卓
 *
 * @since 1.0.10
 */
public final class DateUtils {

    /**
     * Number of milliseconds in a standard second.
     */
    public static final long MILLIS_PER_SECOND = 1000;

    /**
     * Number of milliseconds in a standard minute.
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    /**
     * Number of milliseconds in a standard hour.
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    /**
     * Number of milliseconds in a standard day.
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    /**
     * This is half a month, so this represents whether a date is in the top or bottom half of the month.
     */
    public static final int SEMI_MONTH = 1001;

    private static final int[][] fields = { { Calendar.MILLISECOND }, { Calendar.SECOND }, { Calendar.MINUTE },
            { Calendar.HOUR_OF_DAY, Calendar.HOUR }, { Calendar.DATE, Calendar.DAY_OF_MONTH, Calendar.AM_PM
            /* Calendar.DAY_OF_YEAR, Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK_IN_MONTH */
            }, { Calendar.MONTH, SEMI_MONTH }, { Calendar.YEAR }, { Calendar.ERA } };

    /**
     * 私有构造方法
     */
    private DateUtils() {
        super();
    }

    public static boolean isSameDay(final Date date1, final Date date2) {
        Asserts.notNull(date1);
        Asserts.notNull(date2);
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        Asserts.notNull(cal1);
        Asserts.notNull(cal2);
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isSameInstant(final Date date1, final Date date2) {
        Asserts.notNull(date1);
        Asserts.notNull(date2);
        return date1.getTime() == date2.getTime();
    }

    public static boolean isSameInstant(final Calendar cal1, final Calendar cal2) {
        Asserts.notNull(cal1);
        Asserts.notNull(cal2);
        return cal1.getTime().getTime() == cal2.getTime().getTime();
    }

    public static boolean isSameLocalTime(final Calendar cal1, final Calendar cal2) {
        Asserts.notNull(cal1);
        Asserts.notNull(cal2);
        return cal1.get(Calendar.MILLISECOND) == cal2.get(Calendar.MILLISECOND)
                && cal1.get(Calendar.SECOND) == cal2.get(Calendar.SECOND)
                && cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE)
                && cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.getClass() == cal2.getClass();
    }

    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date addMilliseconds(final Date date, final int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    private static Date add(final Date date, final int calendarField, final int amount) {
        Asserts.notNull(date);
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date setYears(final Date date, final int amount) {
        return set(date, Calendar.YEAR, amount);
    }

    public static Date setMonths(final Date date, final int amount) {
        return set(date, Calendar.MONTH, amount);
    }

    public static Date setDays(final Date date, final int amount) {
        return set(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date setHours(final Date date, final int amount) {
        return set(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date setMinutes(final Date date, final int amount) {
        return set(date, Calendar.MINUTE, amount);
    }

    public static Date setSeconds(final Date date, final int amount) {
        return set(date, Calendar.SECOND, amount);
    }

    public static Date setMilliseconds(final Date date, final int amount) {
        return set(date, Calendar.MILLISECOND, amount);
    }

    private static Date set(final Date date, final int calendarField, final int amount) {
        Asserts.notNull(date);
        final Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(calendarField, amount);
        return c.getTime();
    }

    public static Calendar toCalendar(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static Calendar toCalendar(final Date date, final TimeZone tz) {
        final Calendar c = Calendar.getInstance(tz);
        c.setTime(date);
        return c;
    }

    public static LocalDate toLocalDate(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Year toYear(final Date date) {
        return Year.from(toLocalDate(date));
    }

    public static YearMonth toYearMonth(final Date date) {
        return YearMonth.from(toLocalDate(date));
    }

    public static Instant toInstant(final Date date) {
        return date.toInstant();
    }

    public static Date round(final Date date, final int field) {
        Asserts.notNull(date);
        final Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval, field, ModifyType.ROUND);
        return gval.getTime();
    }

    public static Calendar round(final Calendar date, final int field) {
        Asserts.notNull(date);
        final Calendar rounded = (Calendar) date.clone();
        modify(rounded, field, ModifyType.ROUND);
        return rounded;
    }

    public static Date truncate(final Date date, final int field) {
        Asserts.notNull(date);
        final Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval, field, ModifyType.TRUNCATE);
        return gval.getTime();
    }

    public static Calendar truncate(final Calendar date, final int field) {
        Asserts.notNull(date);
        final Calendar truncated = (Calendar) date.clone();
        modify(truncated, field, ModifyType.TRUNCATE);
        return truncated;
    }

    public static Date ceiling(final Date date, final int field) {
        Asserts.notNull(date);
        final Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval, field, ModifyType.CEILING);
        return gval.getTime();
    }

    public static Calendar ceiling(final Calendar date, final int field) {
        Asserts.notNull(date);
        final Calendar ceiled = (Calendar) date.clone();
        modify(ceiled, field, ModifyType.CEILING);
        return ceiled;
    }

    // -----------------------------------------------------------------------
    private static void modify(final Calendar val, final int field, final ModifyType modType) {
        if (val.get(Calendar.YEAR) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        }

        if (field == Calendar.MILLISECOND) {
            return;
        }

        // ----------------- Fix for LANG-59 ---------------------- START ---------------
        // see https://issues.apache.org/jira/browse/LANG-59
        //
        // Manually truncate milliseconds, seconds and minutes, rather than using
        // Calendar methods.

        final Date date = val.getTime();
        long time = date.getTime();
        boolean done = false;

        // truncate milliseconds
        final int millisecs = val.get(Calendar.MILLISECOND);
        if (ModifyType.TRUNCATE == modType || millisecs < 500) {
            time = time - millisecs;
        }
        if (field == Calendar.SECOND) {
            done = true;
        }

        // truncate seconds
        final int seconds = val.get(Calendar.SECOND);
        if (!done && (ModifyType.TRUNCATE == modType || seconds < 30)) {
            time = time - (seconds * 1000L);
        }
        if (field == Calendar.MINUTE) {
            done = true;
        }

        // truncate minutes
        final int minutes = val.get(Calendar.MINUTE);
        if (!done && (ModifyType.TRUNCATE == modType || minutes < 30)) {
            time = time - (minutes * 60000L);
        }

        // reset time
        if (date.getTime() != time) {
            date.setTime(time);
            val.setTime(date);
        }
        // ----------------- Fix for LANG-59 ----------------------- END ----------------

        boolean roundUp = false;
        for (final int[] aField : fields) {
            for (final int element : aField) {
                if (element == field) {
                    // This is our field... we stop looping
                    if (modType == ModifyType.CEILING || modType == ModifyType.ROUND && roundUp) {
                        if (field == SEMI_MONTH) {
                            // This is a special case that's hard to generalize
                            // If the date is 1, we round up to 16, otherwise
                            // we subtract 15 days and add 1 month
                            if (val.get(Calendar.DATE) == 1) {
                                val.add(Calendar.DATE, 15);
                            } else {
                                val.add(Calendar.DATE, -15);
                                val.add(Calendar.MONTH, 1);
                            }
                            // ----------------- Fix for LANG-440 ---------------------- START ---------------
                        } else if (field == Calendar.AM_PM) {
                            // This is a special case
                            // If the time is 0, we round up to 12, otherwise
                            // we subtract 12 hours and add 1 day
                            if (val.get(Calendar.HOUR_OF_DAY) == 0) {
                                val.add(Calendar.HOUR_OF_DAY, 12);
                            } else {
                                val.add(Calendar.HOUR_OF_DAY, -12);
                                val.add(Calendar.DATE, 1);
                            }
                            // ----------------- Fix for LANG-440 ---------------------- END ---------------
                        } else {
                            // We need at add one to this field since the
                            // last number causes us to round up
                            val.add(aField[0], 1);
                        }
                    }
                    return;
                }
            }
            // We have various fields that are not easy roundings
            int offset = 0;
            boolean offsetSet = false;
            // These are special types of fields that require different rounding rules
            switch (field) {
            case SEMI_MONTH:
                if (aField[0] == Calendar.DATE) {
                    // If we're going to drop the DATE field's value,
                    // we want to do this our own way.
                    // We need to subtract 1 since the date has a minimum of 1
                    offset = val.get(Calendar.DATE) - 1;
                    // If we're above 15 days adjustment, that means we're in the
                    // bottom half of the month and should stay accordingly.
                    if (offset >= 15) {
                        offset -= 15;
                    }
                    // Record whether we're in the top or bottom half of that range
                    roundUp = offset > 7;
                    offsetSet = true;
                }
                break;
            case Calendar.AM_PM:
                if (aField[0] == Calendar.HOUR_OF_DAY) {
                    // If we're going to drop the HOUR field's value,
                    // we want to do this our own way.
                    offset = val.get(Calendar.HOUR_OF_DAY);
                    if (offset >= 12) {
                        offset -= 12;
                    }
                    roundUp = offset >= 6;
                    offsetSet = true;
                }
                break;
            default:
                break;
            }
            if (!offsetSet) {
                final int min = val.getActualMinimum(aField[0]);
                final int max = val.getActualMaximum(aField[0]);
                // Calculate the offset from the minimum allowed value
                offset = val.get(aField[0]) - min;
                // Set roundUp if this is more than half way between the minimum and maximum
                roundUp = offset > ((max - min) / 2);
            }
            // We need to remove this field
            if (offset != 0) {
                val.set(aField[0], val.get(aField[0]) - offset);
            }
        }
        throw new IllegalArgumentException("The field " + field + " is not supported");

    }

    public static boolean truncatedEquals(final Calendar cal1, final Calendar cal2, final int field) {
        return truncatedCompareTo(cal1, cal2, field) == 0;
    }

    public static boolean truncatedEquals(final Date date1, final Date date2, final int field) {
        return truncatedCompareTo(date1, date2, field) == 0;
    }

    public static int truncatedCompareTo(final Calendar cal1, final Calendar cal2, final int field) {
        final Calendar truncatedCal1 = truncate(cal1, field);
        final Calendar truncatedCal2 = truncate(cal2, field);
        return truncatedCal1.compareTo(truncatedCal2);
    }

    public static int truncatedCompareTo(final Date date1, final Date date2, final int field) {
        final Date truncatedDate1 = truncate(date1, field);
        final Date truncatedDate2 = truncate(date2, field);
        return truncatedDate1.compareTo(truncatedDate2);
    }

    public static String format(final Date date, final String pattern) {
        return new DateFormatter(pattern).print(date, Locale.getDefault());
    }

    public static String getYearWeek(final Date date) {
        return getYearWeek(date, Calendar.SUNDAY, 4);
    }

    public static String getYearWeek(final Date date, final int firstDayOfWeek, final int minimalDaysInFirstWeek) {
        final Calendar c = toCalendar(date);
        c.setFirstDayOfWeek(firstDayOfWeek);
        c.setMinimalDaysInFirstWeek(minimalDaysInFirstWeek);
        final String year = String.format("%d", c.getWeekYear());
        final String weekOfYear = String.format("%02d", c.get(Calendar.WEEK_OF_YEAR));
        return StringFormatter.format("{}-{}", year, weekOfYear);
    }

    /**
     * Calendar modification types.
     */
    private enum ModifyType {
        /**
         * Truncation.
         */
        TRUNCATE,

        /**
         * Rounding.
         */
        ROUND,

        /**
         * Ceiling.
         */
        CEILING
    }

}
