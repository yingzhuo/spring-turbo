/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.format.StringToNumberPairConverter;
import spring.turbo.lang.Immutable;
import spring.turbo.util.StringFormatter;

import java.math.BigDecimal;

/**
 * {@link Integer}对
 *
 * @author 应卓
 * @see StringToNumberPairConverter
 * @see spring.turbo.bean.jsr380.OrderedNumberPair
 * @see spring.turbo.bean.jsr380.NumberPairLeft
 * @see spring.turbo.bean.jsr380.NumberPairRight
 * @see BytePair
 * @see ShortPair
 * @see LongPair
 * @see BigIntegerPair
 * @see FloatPair
 * @see DoublePair
 * @see BigDecimalPair
 * @since 1.0.7
 */
@Immutable
public final class IntegerPair extends NumberPair {

    public IntegerPair(BigDecimal left, BigDecimal right) {
        super(left, right);
    }

    public Integer getLeft() {
        return super.getLeft(Integer.class);
    }

    public Integer getRight() {
        return super.getRight(Integer.class);
    }

    public IntegerPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new IntegerPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
