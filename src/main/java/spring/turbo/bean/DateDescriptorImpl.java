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
import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;
import spring.turbo.util.DateUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 时间维度的具体实现
 *
 * @author 应卓
 * @since 1.1.4
 */
@Immutable
public class DateDescriptorImpl implements DateDescriptor {

    private final static DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter YYYY_MM = DateTimeFormatter.ofPattern("yyyy-MM");
    private final static DateTimeFormatter YYYY = DateTimeFormatter.ofPattern("yyyy");

    private final WeekOption weekOption;
    private final LocalDate date;
    private final String dayString;

    private final String centuryString;
    private final String yearString;
    private final boolean isLeapYear;
    private final String yearMonthString;
    private final String yearQuarterString;
    private final String weekString;

    private final String prevDayString;
    private final String nextDayString;

    private final String prevYearString;
    private final String nextYearString;

    private final String prevYearQuarterString;
    private final String nextYearQuarterString;
    private final String prev4YearQuarterString;
    private final String next4YearQuarterString;

    private final String prevYearMonthString;
    private final String nextYearMonthString;
    private final String prev12YearMonthString;
    private final String next12YearMonthString;

    private final String prevWeekString;
    private final String nextWeekString;

    public DateDescriptorImpl(String string) {
        this(string, WeekOption.SUNDAY_START);
    }

    public DateDescriptorImpl(String string, WeekOption weekOption) {
        this(LocalDate.from(YYYY_MM_DD.parse(string)), weekOption);
    }

    public DateDescriptorImpl(LocalDate date) {
        this(date, WeekOption.SUNDAY_START);
    }

    public DateDescriptorImpl(LocalDate date, WeekOption weekOption) {
        Asserts.notNull(date);
        Asserts.notNull(weekOption);

        this.date = date;
        this.weekOption = weekOption;
        this.dayString = YYYY_MM_DD.format(this.date);
        this.centuryString = String.format("%d", (date.getYear() + 99) / 100);
        this.yearString = YYYY.format(this.date);
        this.isLeapYear = this.date.isLeapYear();
        this.yearMonthString = YYYY_MM.format(this.date);
        this.yearQuarterString = calcQuarter(0);
        this.weekString = calcWeek(0, this.weekOption);

        this.prevDayString = YYYY_MM_DD.format(this.date.plusDays(-1));
        this.nextDayString = YYYY_MM_DD.format(this.date.plusDays(1));

        this.prevYearString = YYYY.format(this.date.plusYears(-1));
        this.nextYearString = YYYY.format(this.date.plusYears(1));

        this.prevYearQuarterString = calcQuarter(-1);
        this.nextYearQuarterString = calcQuarter(1);
        this.prev4YearQuarterString = calcQuarter(-4);
        this.next4YearQuarterString = calcQuarter(4);

        this.prevYearMonthString = YYYY_MM.format(this.date.plusMonths(-1));
        this.nextYearMonthString = YYYY_MM.format(this.date.plusMonths(1));
        this.prev12YearMonthString = YYYY_MM.format(this.date.plusMonths(-12));
        this.next12YearMonthString = YYYY_MM.format(this.date.plusMonths(12));

        this.prevWeekString = calcWeek(-1, this.weekOption);
        this.nextWeekString = calcWeek(1, this.weekOption);
    }

    @Override
    public String getCenturyString() {
        return this.centuryString;
    }

    @Override
    public String getDayString() {
        return this.dayString;
    }

    @Override
    public String getPrevDayString() {
        return this.prevDayString;
    }

    @Override
    public String getNextDayString() {
        return this.nextDayString;
    }

    @Override
    public String getYearString() {
        return this.yearString;
    }

    @Override
    public boolean isLeapYear() {
        return this.isLeapYear;
    }

    @Override
    public String getPrevYearString() {
        return this.prevYearString;
    }

    @Override
    public String getNextYearString() {
        return this.nextYearString;
    }

    @Override
    public String getYearMonthString() {
        return this.yearMonthString;
    }

    @Override
    public String getYearQuarterString() {
        return this.yearQuarterString;
    }

    @Override
    public String getPrevYearQuarterString() {
        return this.prevYearQuarterString;
    }

    @Override
    public String getNextYearQuarterString() {
        return this.nextYearQuarterString;
    }

    @Override
    public String getPrev4YearQuarterString() {
        return this.prev4YearQuarterString;
    }

    @Override
    public String getNext4YearQuarterString() {
        return this.next4YearQuarterString;
    }

    @Override
    public String getPrevYearMonthString() {
        return this.prevYearMonthString;
    }

    @Override
    public String getNextYearMonthString() {
        return this.nextYearMonthString;
    }

    @Override
    public String getPrev12YearMonthString() {
        return prev12YearMonthString;
    }

    @Override
    public String getNext12YearMonthString() {
        return next12YearMonthString;
    }

    @Override
    public String getPrevWeekString() {
        return prevWeekString;
    }

    @Override
    public String getNextWeekString() {
        return nextWeekString;
    }

    @Override
    public String getWeekString() {
        return this.weekString;
    }

    @Override
    public LocalDate toLocalDate() {
        return this.date;
    }

    @Override
    public Date toDate(@Nullable ZoneId zone) {
        zone = zone != null ? zone : ZoneId.systemDefault();
        return Date.from(this.date.atStartOfDay().atZone(zone).toInstant());
    }

    @Override
    public Calendar toCalendar(@Nullable ZoneId zone) {
        zone = zone != null ? zone : ZoneId.systemDefault();
        return DateUtils.toCalendar(toDate(zone));
    }

    @Override
    public WeekOption getWeekOption() {
        return this.weekOption;
    }

    @Override
    public Zodiac getZodiac() {
        return Zodiac.getZodiac(this.date.getMonthValue(), this.date.getDayOfMonth());
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateDescriptorImpl that = (DateDescriptorImpl) o;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return this.dayString;
    }

    @Override
    public int compareTo(DateDescriptor o) {
        Asserts.notNull(o);
        return this.getDayString().compareTo(o.getDayString());
    }

    // -----------------------------------------------------------------------------------------------------------------

    private String calcQuarter(int offset) {
        LocalDate localDate = this.date;

        if (offset != 0) {
            localDate = localDate.plusMonths(offset * 3);
        }

        int q = localDate.get(IsoFields.QUARTER_OF_YEAR);
        return String.format("%s-Q%d", YYYY.format(localDate), q);
    }

    private String calcWeek(int offset, WeekOption weekOption) {
        LocalDate localDate = this.date;

        if (offset != 0) {
            localDate = localDate.plusWeeks(offset);
        }

        DayOfWeek firstDayOfWeek = weekOption.getWeekFields().getFirstDayOfWeek();
        LocalDate left = localDate.with(TemporalAdjusters.previous(firstDayOfWeek));
        LocalDate right = left.plusDays(6);

        return String.format(
                "%s@@%s",
                left.format(YYYY_MM_DD),
                right.format(YYYY_MM_DD)
        );
    }

}
