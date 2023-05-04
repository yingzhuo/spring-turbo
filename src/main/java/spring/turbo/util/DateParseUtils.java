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
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * @author 应卓
 *
 * @since 1.0.10
 */
public final class DateParseUtils {

    public static final String PRIMARY_PATTERN = "yyyy-MM-dd";

    public static final String[] BACKUP_PATTERNS = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS", "yyyy-MM-dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss.SSS", "yyyy/MM/dd", "yyyy-M-d HH:mm:ss", "yyyy-M-d HH:mm:ss.SSS", "yyyy-M-d",
            "yyyy/M/d HH:mm:ss", "yyyy/M/d HH:mm:ss.SSS", "yyyy/M/d", "yyyy", "yyyy-MM", "yyyy-M", };

    /**
     * 私有构造方法
     */
    private DateParseUtils() {
        super();
    }

    public static Date parse(String string, String pattern, String... fallbackPatterns) {
        Asserts.notNull(string);
        Asserts.notNull(pattern);

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

    public static Date parse(String string, String pattern, @Nullable Collection<String> fallbackPatterns) {
        if (fallbackPatterns != null && !fallbackPatterns.isEmpty()) {
            return parse(string, pattern, fallbackPatterns.toArray(new String[0]));
        } else {
            return parse(string, pattern);
        }
    }

    public static Date parseWildly(String string) {
        return parse(string, PRIMARY_PATTERN, BACKUP_PATTERNS);
    }

}
