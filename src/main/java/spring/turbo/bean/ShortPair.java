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

import java.math.BigDecimal;

/**
 * @author 应卓
 * @see spring.turbo.format.StringToNumberPairConverter
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
 * @see DatePair
 * @since 1.0.7
 */
@Immutable
public final class ShortPair extends NumberPair {

    public static final ShortPair MIN_TO_MAX =
            new ShortPair(BigDecimal.valueOf(Short.MIN_VALUE), BigDecimal.valueOf(Short.MAX_VALUE));

    public static final ShortPair DEFAULT = MIN_TO_MAX;

    public ShortPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    @NonNull
    public Short getLeft() {
        return super.getLeft(Short.class);
    }

    @NonNull
    public Short getRight() {
        return super.getRight(Short.class);
    }

    @NonNull
    public ShortPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new ShortPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

}
