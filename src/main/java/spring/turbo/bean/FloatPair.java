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
public final class FloatPair extends NumberPair {

    public static final FloatPair MIN_TO_MAX =
            new FloatPair(BigDecimal.valueOf(Float.MIN_VALUE), BigDecimal.valueOf(Float.MAX_VALUE));

    public FloatPair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    public Float getLeft() {
        return super.getLeft(Float.class);
    }

    public Float getRight() {
        return super.getRight(Float.class);
    }

    public FloatPair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new FloatPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

}
