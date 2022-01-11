/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.format.Parser;
import org.springframework.lang.NonNull;
import spring.turbo.bean.DatePair;
import spring.turbo.util.Asserts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * (内部使用)
 *
 * @author 应卓
 * @since 1.0.8
 */
class DatePairParser implements Parser<DatePair> {

    private final String delimiter;
    private final DateFormat dateFormat;

    DatePairParser(@NonNull String delimiter, @NonNull String pattern) {
        Asserts.hasText(delimiter);
        Asserts.hasText(pattern);
        this.delimiter = delimiter;
        this.dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public DatePair parse(String text, Locale locale) throws ParseException {
        final String string = text.trim();
        final String[] parts = string.split(delimiter, 2);
        final Date left = dateFormat.parse(parts[0]);
        final Date right = dateFormat.parse(parts[1]);
        return new DatePair(left, right);
    }

}
