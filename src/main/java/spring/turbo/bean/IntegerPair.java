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
public final class IntegerPair extends NumberPair {

    public static final IntegerPair MIN_TO_MAX
            = new IntegerPair(BigDecimal.valueOf(Integer.MIN_VALUE), BigDecimal.valueOf(Integer.MAX_VALUE));

    public IntegerPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    public Integer getLeft() {
        return super.getLeft(Integer.class);
    }

    public Integer getRight() {
        return super.getRight(Integer.class);
    }

    public IntegerPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new IntegerPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }
}