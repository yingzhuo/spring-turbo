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
import java.math.BigInteger;

/**
 * {@link BigInteger}对
 *
 * @author 应卓
 *
 * @see LongPair
 * @see DoublePair
 * @see BigDecimalPair
 *
 * @since 1.0.7
 */
public final class BigIntegerPair extends NumberPair {

    public BigIntegerPair(BigDecimal left, BigDecimal right) {
        super(left, right);
    }

    public BigInteger getLeft() {
        return super.getLeft(BigInteger.class);
    }

    public BigInteger getRight() {
        return super.getRight(BigInteger.class);
    }

    public BigIntegerPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new BigIntegerPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String toString() {
        return StringFormatter.format("{} - {}", getLeft(), getRight());
    }

}
