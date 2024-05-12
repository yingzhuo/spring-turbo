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
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import spring.turbo.bean.DateDescriptor;
import spring.turbo.bean.DateRange;
import spring.turbo.bean.WeekOption;
import spring.turbo.util.*;
import spring.turbo.util.collection.CollectionUtils;
import spring.turbo.util.time.ZoneIdUtils;

import java.text.ParseException;
import java.util.*;

/**
 * @author 应卓
 *
 * @since 1.3.1
 */
public class DateRangeFormatter implements Formatter<DateRange> {

    private String delimiter = " @@ ";
    private String datePattern = "yyyy-MM-dd";
    private String timezone = "";
    private WeekOption weekOption = WeekOption.SUNDAY_START;
    private List<String> backupDelimiters = List.of("@@");

    /**
     * 构造方法
     */
    public DateRangeFormatter() {
        super();
    }

    @Override
    public DateRange parse(String text, Locale locale) throws ParseException {
        final List<String> delimiterList = new ArrayList<>();
        CollectionUtils.nullSafeAdd(delimiterList, this.delimiter);
        CollectionUtils.nullSafeAddAll(delimiterList, this.backupDelimiters);

        for (String delimiter : delimiterList) {
            final var ret = this.doParse(text, locale, delimiter);
            if (ret != null) {
                return ret;
            }
        }

        throw new ParseException("text is not a valid DateRange.", 0);
    }

    @Nullable
    private DateRange doParse(String text, Locale locale, String delimiterToUse) {
        final String[] parts = StringUtils.split(text, delimiterToUse);
        if (parts == null || parts.length != 2) {
            return null;
        }

        final String leftDateString = parts[0];
        final String rightDateString = parts[1];

        try {
            final Date left = DateParseUtils.parse(leftDateString, datePattern);
            final Date right = DateParseUtils.parse(rightDateString, datePattern);

            return new DateRange(DateDescriptor.of(left, ZoneIdUtils.toZoneIdOrDefault(timezone), weekOption),
                    DateDescriptor.of(right, ZoneIdUtils.toZoneIdOrDefault(timezone), weekOption));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String print(DateRange object, Locale locale) {
        return StringFormatter.format("{}{}{}", DateUtils.format(object.getLeftInclude(), this.datePattern),
                this.delimiter, DateUtils.format(object.getLeftInclude(), this.datePattern));
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

    public final void setBackupDelimiters(String... backupDelimiters) {
        final List<String> list = new ArrayList<>();
        CollectionUtils.nullSafeAddAll(list, backupDelimiters);
        this.backupDelimiters = Collections.unmodifiableList(list);
    }

}
