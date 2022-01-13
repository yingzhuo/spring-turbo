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

import java.text.ParseException;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class CharSequenceToDatePairConverter implements GenericConverter {

    private static final DatePairParser DATE_PAIR_PARSER = new DatePairParser(" @@ ", "yyyy-MM-dd HH:mm:ss");

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(CharSequence.class, DatePair.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        try {
            return DATE_PAIR_PARSER.parse(source.toString(), Locale.getDefault());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
