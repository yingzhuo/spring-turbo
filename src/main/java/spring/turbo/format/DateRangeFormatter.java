/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;
import spring.turbo.bean.DateDescriptor;
import spring.turbo.bean.DateRange;
import spring.turbo.bean.WeekOption;
import spring.turbo.util.DateParseUtils;
import spring.turbo.util.DateUtils;
import spring.turbo.util.StringFormatter;
import spring.turbo.util.ZoneIdPool;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * @author 应卓
 * @since 1.3.1
 */
public class DateRangeFormatter implements Formatter<DateRange> {

    private String delimiter = " @@ ";
    private String datePattern = "yyyy-MM-dd";
    private String timezone = "";
    private WeekOption weekOption = WeekOption.SUNDAY_START;

    /**
     * 构造方法
     */
    public DateRangeFormatter() {
        super();
    }

    @Override
    public DateRange parse(String text, Locale locale) throws ParseException {
        final String error = "text is not a valid DateRange.";
        final String[] parts = StringUtils.split(text, delimiter);
        if (parts == null || parts.length != 2) {
            throw new ParseException(error, 0);
        }

        final String leftDateString = parts[0];
        final String rightDateString = parts[1];

        try {
            final Date left = DateParseUtils.parse(leftDateString, datePattern);
            final Date right = DateParseUtils.parse(rightDateString, datePattern);

            return new DateRange(
                    DateDescriptor.of(left, ZoneIdPool.toZoneIdOrDefault(timezone), weekOption),
                    DateDescriptor.of(right, ZoneIdPool.toZoneIdOrDefault(timezone), weekOption)
            );
        } catch (Exception e) {
            throw new ParseException(error, 0);
        }
    }

    @Override
    public String print(DateRange object, Locale locale) {
        return StringFormatter.format(
                "{}{}{}",
                DateUtils.format(object.getLeftInclude(), this.datePattern),
                this.delimiter,
                DateUtils.format(object.getLeftInclude(), this.datePattern)
        );
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setWeekOption(WeekOption weekOption) {
        this.weekOption = weekOption;
    }

}
