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
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import spring.turbo.bean.*;
import spring.turbo.util.RegexUtils;
import spring.turbo.util.StringFormatter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author 应卓
 * @see CharSequence
 * @see NumberPair
 * @since 1.0.7
 */
public class StringToNumberPairConverter implements GenericConverter {

    private static final Pattern REGEX =
            Pattern.compile("^([+\\-]?[a-fA-F0-9.xX]+)-([+\\-]?[a-fA-F0-9.xX]+)$");

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        final Set<ConvertiblePair> set = new HashSet<>(9);
        set.add(new ConvertiblePair(CharSequence.class, NumberPair.class));
        set.add(new ConvertiblePair(CharSequence.class, BytePair.class));
        set.add(new ConvertiblePair(CharSequence.class, ShortPair.class));
        set.add(new ConvertiblePair(CharSequence.class, IntegerPair.class));
        set.add(new ConvertiblePair(CharSequence.class, LongPair.class));
        set.add(new ConvertiblePair(CharSequence.class, BigIntegerPair.class));
        set.add(new ConvertiblePair(CharSequence.class, FloatPair.class));
        set.add(new ConvertiblePair(CharSequence.class, DoublePair.class));
        set.add(new ConvertiblePair(CharSequence.class, BigDecimalPair.class));
        return Collections.unmodifiableSet(set);
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

        final String exceptionMsg = StringFormatter.format("'{}' is invalid NumberPair", originalString);
        final BigDecimal left = parse(leftString).orElseThrow(() -> new IllegalArgumentException(exceptionMsg));
        final BigDecimal right = parse(rightString).orElseThrow(() -> new IllegalArgumentException(exceptionMsg));

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

    private Optional<BigDecimal> parse(String text) {

        // Spring工具不可以将十六进数直接转换成BigDecimal
        if (text.startsWith("0x") ||
                text.startsWith("0X") ||
                text.startsWith("-0X") ||
                text.startsWith("-0x")) {

            final BigInteger bigInt = NumberUtils.parseNumber(text, BigInteger.class);
            return Optional.of(new BigDecimal(bigInt));
        }

        try {
            return Optional.of(NumberUtils.parseNumber(text, BigDecimal.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
