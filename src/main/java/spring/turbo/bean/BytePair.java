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
 * {@link Byte}对
 *
 * @author 应卓
 * @see StringToNumberPairConverter
 * @see spring.turbo.bean.jsr380.OrderedNumberPair
 * @see spring.turbo.bean.jsr380.NumberPairLeft
 * @see spring.turbo.bean.jsr380.NumberPairRight
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
public final class BytePair extends NumberPair {

    public static final BytePair MIN_TO_MAX =
            new BytePair(BigDecimal.valueOf(Byte.MIN_VALUE), BigDecimal.valueOf(Byte.MAX_VALUE));

    public static final BytePair DEFAULT = MIN_TO_MAX;

    public BytePair(BigDecimal left, BigDecimal right) {
        super(left, right);
    }

    public Byte getLeft() {
        return super.getLeft(Byte.class);
    }

    public Byte getRight() {
        return super.getRight(Byte.class);
    }

    public BytePair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new BytePair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
