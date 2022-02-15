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
import org.springframework.lang.Nullable;
import spring.turbo.bean.DayRange;
import spring.turbo.util.SetFactories;

import java.text.ParseException;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.13
 */
public class StringToDayPairConverter implements GenericConverter {

    private static final Set<ConvertiblePair> CONVERTIBLE_TYPES = SetFactories.newUnmodifiableSet(
            new ConvertiblePair(String.class, DayRange.class)
    );

    private static final DatePairParser DATE_PAIR_PARSER = new DatePairParser(" @@ ", "yyyy-MM-dd", new String[0]);

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_TYPES;
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        try {
            return DATE_PAIR_PARSER.parse(source.toString(), null)
                    .toDayRange();
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}