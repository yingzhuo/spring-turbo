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
import spring.turbo.bean.DatePair;
import spring.turbo.util.SetFactories;

import java.text.ParseException;
import java.util.Set;

import static spring.turbo.util.DateParseUtils.BACKUP_PATTERNS;

/**
 * @author 应卓
 * @since 1.0.8
 */
@Deprecated
public class StringToDatePairConverter implements GenericConverter {

    private static final String DELIMITER = " @@ ";
    private static final Set<ConvertiblePair> CONVERTIBLE_PAIRS
            = SetFactories.newUnmodifiableSet(new ConvertiblePair(String.class, DatePair.class));

    private static final DatePairParser DATE_PAIR_PARSER = new DatePairParser(DELIMITER, "yyyy-MM-dd", BACKUP_PATTERNS);

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_PAIRS;
    }

    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        try {
            return DATE_PAIR_PARSER.parse(source.toString(), null);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
