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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author 应卓
 * @since 1.0.7
 */
public final class BigIntegerPair extends NumberPair {

    public static final BigIntegerPair DEFAULT =
            new BigIntegerPair(BigDecimal.valueOf(Double.MIN_VALUE), BigDecimal.valueOf(Double.MAX_VALUE));

    public BigIntegerPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    @NonNull
    public BigInteger getLeft() {
        return super.getLeft(BigInteger.class);
    }

    @NonNull
    public BigInteger getRight() {
        return super.getRight(BigInteger.class);
    }

    @NonNull
    public BigIntegerPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new BigIntegerPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

}
