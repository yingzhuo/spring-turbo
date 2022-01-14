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
import org.springframework.util.StringUtils;
import spring.turbo.bean.*;
import spring.turbo.util.NumberParseUtils;
import spring.turbo.util.RegexUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author 应卓
 * @see CharSequence
 * @see NumberPair
 * @see NumberParseUtils
 * @see org.springframework.util.NumberUtils
 * @since 1.0.7
 */
public class StringToNumberPairConverter implements GenericConverter {

    private static final Pattern REGEX =
            Pattern.compile("^([+\\-]?[#a-fA-F0-9.xX]+)-([+\\-]?[#a-fA-F0-9.xX]+)$");

    private static final Set<ConvertiblePair> CONVERTIBLE_PAIRS;

    static {
        final Set<ConvertiblePair> set = new HashSet<>();
        set.add(new ConvertiblePair(String.class, NumberPair.class));
        set.add(new ConvertiblePair(String.class, BytePair.class));
        set.add(new ConvertiblePair(String.class, ShortPair.class));
        set.add(new ConvertiblePair(String.class, IntegerPair.class));
        set.add(new ConvertiblePair(String.class, LongPair.class));
        set.add(new ConvertiblePair(String.class, BigIntegerPair.class));
        set.add(new ConvertiblePair(String.class, FloatPair.class));
        set.add(new ConvertiblePair(String.class, DoublePair.class));
        set.add(new ConvertiblePair(String.class, BigDecimalPair.class));
        CONVERTIBLE_PAIRS = Collections.unmodifiableSet(set);
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

        final String originalString = source.toString();
        final String trimmedString = StringUtils.trimAllWhitespace(originalString);

        String leftString = RegexUtils.replaceFirst(trimmedString, REGEX, "$1");
        String rightString = RegexUtils.replaceFirst(trimmedString, REGEX, "$2");

        // 去除前面的+号
        if (leftString.startsWith("+")) {
            leftString = leftString.substring(1);
        }

        // 去除前面的+号
        if (rightString.startsWith("+")) {
            rightString = rightString.substring(1);
        }

        final BigDecimal left = NumberParseUtils.parse(leftString, BigDecimal.class);
        final BigDecimal right = NumberParseUtils.parse(rightString, BigDecimal.class);

        if (targetType.getObjectType() == BytePair.class) {
            return new BytePair(left, right);
        } else if (targetType.getObjectType() == ShortPair.class) {
            return new ShortPair(left, right);
        } else if (targetType.getObjectType() == IntegerPair.class) {
            return new IntegerPair(left, right);
        } else if (targetType.getObjectType() == LongPair.class) {
            return new LongPair(left, right);
        } else if (targetType.getObjectType() == BigIntegerPair.class) {
            return new BigIntegerPair(left, right);
        } else if (targetType.getObjectType() == FloatPair.class) {
            return new FloatPair(left, right);
        } else if (targetType.getObjectType() == DoublePair.class) {
            return new DoublePair(left, right);
        } else if (targetType.getObjectType() == BigDecimalPair.class) {
            return new BigDecimalPair(left, right);
        } else {
            return new NumberPair(left, right);
        }
    }

}
