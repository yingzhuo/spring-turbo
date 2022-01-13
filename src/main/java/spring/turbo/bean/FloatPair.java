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
import spring.turbo.format.StringToNumberPairConverter;
import spring.turbo.lang.Immutable;
import spring.turbo.util.StringFormatter;

import java.math.BigDecimal;

/**
 * @author 应卓
 * @see StringToNumberPairConverter
 * @see spring.turbo.bean.jsr380.OrderedNumberPair
 * @see spring.turbo.bean.jsr380.NumberPairLeft
 * @see spring.turbo.bean.jsr380.NumberPairRight
 * @see BytePair
 * @see ShortPair
 * @see IntegerPair
 * @see LongPair
 * @see BigIntegerPair
 * @see DoublePair
 * @see BigDecimalPair
 * @see DatePair
 * @since 1.0.7
 */
@Immutable
public final class FloatPair extends NumberPair {

    public static final FloatPair MIN_TO_MAX =
            new FloatPair(BigDecimal.valueOf(Float.MIN_VALUE), BigDecimal.valueOf(Float.MAX_VALUE));

    public static final FloatPair DEFAULT = MIN_TO_MAX;

    public FloatPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    @NonNull
    public Float getLeft() {
        return super.getLeft(Float.class);
    }

    @NonNull
    public Float getRight() {
        return super.getRight(Float.class);
    }

    @NonNull
    public FloatPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new FloatPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
