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
import org.springframework.format.Parser;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import spring.turbo.bean.NumberPair;
import spring.turbo.bean.NumberZones;
import spring.turbo.util.collection.SetFactories;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.1.4
 */
public class NumberZonesParser implements Parser<NumberZones>, GenericConverter {

    private static final StringToNumberPairConverter STRING_TO_NUMBER_PAIR_CONVERTER = new StringToNumberPairConverter();

    private final NumberZonesFormat annotation;

    public NumberZonesParser() {
        this.annotation = new NumberZonesFormat() {
            @Override
            public String delimiter() {
                return ";";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return NumberZonesFormat.class;
            }
        };
    }

    public NumberZonesParser(NumberZonesFormat annotation) {
        this.annotation = annotation;
    }

    @Nullable
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return SetFactories.newUnmodifiableSet(
                new ConvertiblePair(String.class, NumberZones.class)
        );
    }

    @Nullable
    @Override
    public Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }

        try {
            return parse((String) source, Locale.getDefault());
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public NumberZones parse(String text, Locale locale) throws ParseException {
        final String error = "text is not a valid NumberZones.";
        final String[] parts = StringUtils.delimitedListToStringArray(text, annotation.delimiter());

        if (parts.length == 0) {
            throw new ParseException(error, 0);
        }

        final List<NumberPair> list = new ArrayList<>(parts.length);
        for (String part : parts) {

            final NumberPair numberPair = (NumberPair) STRING_TO_NUMBER_PAIR_CONVERTER.convert(
                    part,
                    TypeDescriptor.valueOf(String.class),
                    TypeDescriptor.valueOf(NumberPair.class)
            );

            list.add(numberPair);
        }

        return new NumberZones(list);
    }

}
