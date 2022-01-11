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
public final class DoublePair extends NumberPair {

    public static final DoublePair MIN_TO_MAX =
            new DoublePair(BigDecimal.valueOf(Double.MIN_VALUE), BigDecimal.valueOf(Double.MAX_VALUE));

    public DoublePair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
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

}
