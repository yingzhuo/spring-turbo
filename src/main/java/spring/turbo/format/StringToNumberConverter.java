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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.0
 */
public class StringToNumberConverter implements GenericConverter {

    public StringToNumberConverter() {
        super();
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        final Set<ConvertiblePair> set = new HashSet<>();
        set.add(new ConvertiblePair(String.class, Byte.class));
        set.add(new ConvertiblePair(String.class, Short.class));
        set.add(new ConvertiblePair(String.class, Integer.class));
        set.add(new ConvertiblePair(String.class, Long.class));
        set.add(new ConvertiblePair(String.class, Float.class));
        set.add(new ConvertiblePair(String.class, Double.class));
        set.add(new ConvertiblePair(String.class, BigInteger.class));
        set.add(new ConvertiblePair(String.class, BigDecimal.class));
        return Collections.unmodifiableSet(set);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        String text = (String) source;

        final BigDecimal decimal = new BigDecimal(text);

        if (targetType.getObjectType() == Byte.class) {
            return decimal.byteValue();
        }

        if (targetType.getObjectType() == Short.class) {
            return decimal.shortValue();
        }

        if (targetType.getObjectType() == Integer.class) {
            return decimal.intValue();
        }

        if (targetType.getObjectType() == Long.class) {
            return decimal.longValue();
        }

        if (targetType.getObjectType() == Float.class) {
            return decimal.floatValue();
        }

        if (targetType.getObjectType() == Double.class) {
            return decimal.doubleValue();
        }

        if (targetType.getObjectType() == BigInteger.class) {
            return decimal.toBigInteger();
        }

        if (targetType.getObjectType() == BigDecimal.class) {
            return decimal;
        }

        return null;
    }

}
