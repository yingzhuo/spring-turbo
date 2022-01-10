/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import java.math.BigDecimal;

/**
 * @author 应卓
 * @since 1.0.7
 */
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

}
