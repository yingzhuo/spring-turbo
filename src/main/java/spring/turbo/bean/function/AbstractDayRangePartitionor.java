/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.function;

import spring.turbo.util.DateUtils;
import spring.turbo.util.StringFormatter;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 应卓
 * @since 1.0.14
 */
public abstract class AbstractDayRangePartitionor implements DayRangePartitionor {

    /**
     * 构造方法
     */
    public AbstractDayRangePartitionor() {
        super();
    }

    protected final int getCentury(Date date) {
        final LocalDate localDate = DateUtils.toLocalDate(date);
        return (localDate.getYear() / 100) + 1;
    }

    protected final String getYear(Date date) {
        final LocalDate localDate = DateUtils.toLocalDate(date);
        return StringFormatter.format("{}", localDate.getYear());
    }

    protected final String getYearMonth(Date date) {
        final LocalDate localDate = DateUtils.toLocalDate(date);
        final String year = String.format("%d", localDate.getYear());
        final String month = String.format("%02d", localDate.getMonthValue());
        return StringFormatter.format("{}-{}", year, month);
    }

    protected final String getYearWeek(Date date, int firstDayOfWeek) {
        final Calendar calendar = DateUtils.toCalendar(date);
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        final String year = String.format("%d", calendar.getWeekYear());
        final String weekOfYear = String.format("%02d", calendar.get(Calendar.WEEK_OF_YEAR));
        return StringFormatter.format("{}-{}", year, weekOfYear);
    }

}
