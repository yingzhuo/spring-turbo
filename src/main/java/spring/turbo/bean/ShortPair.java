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
public final class ShortPair extends NumberPair {

    public static final ShortPair MIN_TO_MAX =
            new ShortPair(BigDecimal.valueOf(Short.MIN_VALUE), BigDecimal.valueOf(Short.MAX_VALUE));

    public ShortPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    public Short getLeft() {
        return super.getLeft(Short.class);
    }

    public Short getRight() {
        return super.getRight(Short.class);
    }

    public ShortPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new ShortPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

}
