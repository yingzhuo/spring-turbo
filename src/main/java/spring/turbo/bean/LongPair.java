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
import spring.turbo.util.StringFormatter;

import java.math.BigDecimal;

/**
 * {@link Long}对
 *
 * @author 应卓
 * @see spring.turbo.bean.jsr380.OrderedNumberPair
 * @see spring.turbo.bean.jsr380.NumberPairLeft
 * @see spring.turbo.bean.jsr380.NumberPairRight
 * @see BigIntegerPair
 * @see DoublePair
 * @see BigDecimalPair
 * @since 1.0.7
 */
@Immutable
public final class LongPair extends NumberPair {

    public LongPair(BigDecimal left, BigDecimal right) {
        super(left, right);
    }

    public Long getLeft() {
        return super.getLeft(Long.class);
    }

    public Long getRight() {
        return super.getRight(Long.class);
    }

    public LongPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new LongPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
