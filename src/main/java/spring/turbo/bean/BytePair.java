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
public final class BytePair extends NumberPair {

    public static final BytePair MIN_TO_MAX =
            new BytePair(BigDecimal.valueOf(Byte.MIN_VALUE), BigDecimal.valueOf(Byte.MAX_VALUE));

    public static final BytePair DEFAULT = MIN_TO_MAX;

    public BytePair(@NonNull BigDecimal left, @NonNull BigDecimal right) {
        super(left, right);
    }

    @NonNull
    public Byte getLeft() {
        return super.getLeft(Byte.class);
    }

    @NonNull
    public Byte getRight() {
        return super.getRight(Byte.class);
    }

    @NonNull
    public BytePair toTypedOrdered() {
        final NumberPair np = super.toOrdered();
        return new BytePair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

}
