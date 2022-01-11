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

/**
 * @author 应卓
 * @since 1.0.7
 */
public final class BigDecimalPair extends NumberPair {

    public static final BigDecimalPair DEFAULT =
            new BigDecimalPair(BigDecimal.valueOf(Double.MIN_VALUE), BigDecimal.valueOf(Double.MAX_VALUE));

    public BigDecimalPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    @NonNull
    public BigDecimal getLeft() {
        return super.getLeft(BigDecimal.class);
    }

    @NonNull
    public BigDecimal getRight() {
        return super.getRight(BigDecimal.class);
    }

    @NonNull
    public BigDecimalPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new BigDecimalPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

}
