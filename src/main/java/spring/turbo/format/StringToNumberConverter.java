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
import spring.turbo.util.NumberParseUtils;
import spring.turbo.util.StringFormatter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class StringToNumberConverter implements GenericConverter {

    private static final Set<ConvertiblePair> CONVERTIBLE_PAIRS;

    static {
        final Set<ConvertiblePair> set = new HashSet<>();
        set.add(new ConvertiblePair(String.class, Byte.class));
        set.add(new ConvertiblePair(String.class, Short.class));
        set.add(new ConvertiblePair(String.class, Integer.class));
        set.add(new ConvertiblePair(String.class, Long.class));
        set.add(new ConvertiblePair(String.class, Float.class));
        set.add(new ConvertiblePair(String.class, Double.class));
        set.add(new ConvertiblePair(String.class, BigInteger.class));
        set.add(new ConvertiblePair(String.class, BigDecimal.class));
        CONVERTIBLE_PAIRS = Collections.unmodifiableSet(set);
    }

    public StringToNumberConverter() {
        super();
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_PAIRS;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (source == null) {
            return null;
        }

        final String text = source.toString();

        if (targetType.getObjectType() == Byte.class) {
            return NumberParseUtils.parse(text, Byte.class);
        }

        if (targetType.getObjectType() == Short.class) {
            return NumberParseUtils.parse(text, Short.class);
        }

        if (targetType.getObjectType() == Integer.class) {
            return NumberParseUtils.parse(text, Integer.class);
        }

        if (targetType.getObjectType() == Long.class) {
            return NumberParseUtils.parse(text, Long.class);
        }

        if (targetType.getObjectType() == Float.class) {
            return NumberParseUtils.parse(text, Float.class);
        }

        if (targetType.getObjectType() == Double.class) {
            return NumberParseUtils.parse(text, Double.class);
        }

        if (targetType.getObjectType() == BigInteger.class) {
            return NumberParseUtils.parse(text, BigInteger.class);
        }

        if (targetType.getObjectType() == BigDecimal.class) {
            return NumberParseUtils.parse(text, BigDecimal.class);
        }

        // 实际不会发生
        throw new IllegalArgumentException(StringFormatter.format("unsupported number type: {}", targetType.getObjectType().getName()));
    }

}
