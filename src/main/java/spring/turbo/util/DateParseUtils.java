/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * @author 应卓
 * @since 1.0.10
 */
public final class DateParseUtils {

    /**
     * 私有构造方法
     */
    private DateParseUtils() {
        super();
    }

    public static Date parse(String string, String pattern, String... fallbackPatterns) {
        try {
            final DateFormatter formatter = new DateFormatter();
            formatter.setIso(DateTimeFormat.ISO.NONE);
            formatter.setPattern(pattern);
            formatter.setFallbackPatterns(fallbackPatterns);
            return formatter.parse(string, Locale.getDefault());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
