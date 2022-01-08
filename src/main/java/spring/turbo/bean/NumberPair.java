/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.NonNull;
import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * @author 应卓
 * @see spring.turbo.format.StringToNumberPairConverter
 * @see BytePair
 * @see ShortPair
 * @see IntegerPair
 * @see LongPair
 * @see BigIntegerPair
 * @see FloatPair
 * @see DoublePair
 * @see BigDecimalPair
 * @since 1.0.7
 */
@Immutable
@SuppressWarnings("unchecked")
public class NumberPair implements Serializable {

    private final BigDecimal left;
    private final BigDecimal right;

    public NumberPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        Asserts.notNull(left);
        Asserts.notNull(right);
        this.left = left;
        this.right = right;
    }

    @NonNull
    public NumberPair toOrdered() {
        return new NumberPair(min(left, right), max(left, right));
    }

    @NonNull
    public <T extends Number> Pair<T, T> toPair(Class<T> type) {
        return Pair.ofNonNull(getLeft(type), getRight(type));
    }

    public <T extends Number> T getLeft(Class<T> type) {
        if (type == Byte.class) {
            Byte result = left.byteValue();
            return (T) result;
        }

        if (type == Short.class) {
            Short result = left.shortValue();
            return (T) result;
        }

        if (type == Integer.class) {
            Integer result = left.intValue();
            return (T) result;
        }

        if (type == Long.class) {
            Long result = left.longValue();
            return (T) result;
        }

        if (type == BigInteger.class) {
            BigInteger result = left.toBigInteger();
            return (T) result;
        }

        if (type == Float.class) {
            Float result = left.floatValue();
            return (T) result;
        }

        if (type == Double.class) {
            Double result = left.doubleValue();
            return (T) result;
        }

        if (type == BigDecimal.class) {
            return (T) left;
        }

        throw new UnsupportedOperationException(StringFormatter.format("'{}' type is not supported", type.getSimpleName()));
    }

    public <T extends Number> T getRight(Class<T> type) {
        if (type == Byte.class) {
            Byte result = right.byteValue();
            return (T) result;
        }

        if (type == Short.class) {
            Short result = right.shortValue();
            return (T) result;
        }

        if (type == Integer.class) {
            Integer result = right.intValue();
            return (T) result;
        }

        if (type == Long.class) {
            Long result = right.longValue();
            return (T) result;
        }

        if (type == BigInteger.class) {
            BigInteger result = right.toBigInteger();
            return (T) result;
        }

        if (type == Float.class) {
            Float result = right.floatValue();
            return (T) result;
        }

        if (type == Double.class) {
            Double result = right.doubleValue();
            return (T) result;
        }

        if (type == BigDecimal.class) {
            return (T) right;
        }

        throw new UnsupportedOperationException(StringFormatter.format("'{}' type is not supported", type.getSimpleName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberPair that = (NumberPair) o;
        return left.equals(that.left) && right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", left, right);
    }

    private BigDecimal min(@NonNull BigDecimal number1, @NonNull BigDecimal number2) {
        return number1.compareTo(number2) < 0 ? number1 : number2;
    }

    private BigDecimal max(@NonNull BigDecimal number1, @NonNull BigDecimal number2) {
        return number1.compareTo(number2) > 0 ? number1 : number2;
    }

}
