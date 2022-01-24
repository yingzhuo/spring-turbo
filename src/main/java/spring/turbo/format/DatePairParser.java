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
import org.springframework.lang.Nullable;
import spring.turbo.bean.DatePair;
import spring.turbo.util.DateParseUtils;

import java.text.ParseException;
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
    private final String primaryPattern;
    private final String[] fallbackPatterns;

    public DatePairParser(String delimiter, String primaryPattern, String[] fallbackPatterns) {
        this.delimiter = delimiter;
        this.primaryPattern = primaryPattern;
        this.fallbackPatterns = fallbackPatterns;
    }

    @Override
    public DatePair parse(String text, @Nullable Locale locale) throws ParseException {
        final String string = text.trim();
        final String[] parts = string.split(delimiter, 2);
        final Date left = DateParseUtils.parse(parts[0], primaryPattern, fallbackPatterns);
        final Date right = DateParseUtils.parse(parts[1], primaryPattern, fallbackPatterns);
        return new DatePair(left, right);
    }

}
