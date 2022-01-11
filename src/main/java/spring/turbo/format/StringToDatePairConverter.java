/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import spring.turbo.bean.DatePair;
import spring.turbo.util.StringFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class StringToDatePairConverter implements GenericConverter {

    private static final String DEFAULT_DELIMITER = " @@ ";
    private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(CharSequence.class, DatePair.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        final String string = source.toString().trim();

        try {
            final String[] parts = string.split(DEFAULT_DELIMITER, 2);
            final Date left = DEFAULT_DATE_FORMAT.parse(parts[0]);
            final Date right = DEFAULT_DATE_FORMAT.parse(parts[1]);
            return new DatePair(left, right);
        } catch (ParseException e) {
            final String exceptionMsg = StringFormatter.format("'{}' is invalid DatePair", string);
            throw new IllegalArgumentException(exceptionMsg);
        }
    }

}
