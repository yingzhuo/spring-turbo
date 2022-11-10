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
 * {@link Short}对
 *
 * @author 应卓
 * @see StringToNumberPairConverter
 * @see spring.turbo.bean.jsr380.OrderedNumberPair
 * @see spring.turbo.bean.jsr380.NumberPairLeft
 * @see spring.turbo.bean.jsr380.NumberPairRight
 * @see BytePair
 * @see IntegerPair
 * @see LongPair
 * @see BigIntegerPair
 * @see FloatPair
 * @see DoublePair
 * @see BigDecimalPair
 * @since 1.0.7
 */
@Immutable
public final class ShortPair extends NumberPair {

    public ShortPair(BigDecimal left, BigDecimal right) {
        super(left, right);
    }

    public Short getLeft() {
        return super.getLeft(Short.class);
    }

    public Short getRight() {
        return super.getRight(Short.class);
    }

    public ShortPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new ShortPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
