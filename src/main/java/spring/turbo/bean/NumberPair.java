/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;
import spring.turbo.util.BigDecimalUtils;
import spring.turbo.util.StringFormatter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 数字对
 *
 * @author 应卓
 * @see spring.turbo.bean.jsr380.OrderedNumberPair
 * @see spring.turbo.bean.jsr380.NumberPairLeft
 * @see spring.turbo.bean.jsr380.NumberPairRight
 * @see LongPair
 * @see BigIntegerPair
 * @see DoublePair
 * @see BigDecimalPair
 * @since 1.0.7
 */
@Immutable
public class NumberPair implements Serializable {

    private final BigDecimal left;
    private final BigDecimal right;

    public NumberPair(BigDecimal left, BigDecimal right) {
        Asserts.notNull(left);
        Asserts.notNull(right);
        this.left = left;
        this.right = right;
    }

    public final boolean isOrdered() {
        return left.compareTo(right) <= 0;
    }

    public final NumberPair toOrdered() {
        return isOrdered() ? this : new NumberPair(right, left);
    }

    public <T extends Number> Pair<T, T> toPair(Class<T> type) {
        return Pair.ofNonNull(getLeft(type), getRight(type));
    }

    public <T extends Number> T getLeft(Class<T> type) {
        return BigDecimalUtils.getValue(left, type);
    }

    public <T extends Number> T getRight(Class<T> type) {
        return BigDecimalUtils.getValue(right, type);
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

}
