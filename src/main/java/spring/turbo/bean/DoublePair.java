/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.util.StringFormatter;

import java.math.BigDecimal;

/**
 * {@link Double}对
 *
 * @author 应卓
 *
 * @see LongPair
 * @see BigIntegerPair
 * @see BigDecimalPair
 *
 * @since 1.0.7
 */
public final class DoublePair extends NumberPair {

    public DoublePair(BigDecimal left, BigDecimal right) {
        super(left, right);
    }

    public Double getLeft() {
        return super.getLeft(Double.class);
    }

    public Double getRight() {
        return super.getRight(Double.class);
    }

    public DoublePair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new DoublePair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
