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
import spring.turbo.bean.DateRange;
import spring.turbo.bean.DateZones;
import spring.turbo.util.StringPool;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author 应卓
 * @see DateZones
 * @since 1.3.1
 */
public class DateZonesFormatter implements Formatter<DateZones> {

    private String delimiter = StringPool.SEMICOLON;
    private DateRangeFormatter dateRangeFormatter = new DateRangeFormatter();

    /**
     * 构造方法
     */
    public DateZonesFormatter() {
        super();
    }

    @Override
    public DateZones parse(String text, Locale locale) throws ParseException {
        final String error = "text is not a valid NumberZones.";
        final String[] parts = StringUtils.delimitedListToStringArray(text, delimiter);

        if (parts.length == 0) {
            throw new ParseException(error, 0);
        }

        final List<DateRange> list = new ArrayList<>(parts.length);
        for (String part : parts) {
            final DateRange dateRange = dateRangeFormatter.parse(part, locale);
            list.add(dateRange);
        }

        return new DateZones(list);
    }

    @Override
    public String print(DateZones object, Locale locale) {
        return object.toString();
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setDateRangeFormatter(DateRangeFormatter dateRangeFormatter) {
        this.dateRangeFormatter = dateRangeFormatter;
    }

}
