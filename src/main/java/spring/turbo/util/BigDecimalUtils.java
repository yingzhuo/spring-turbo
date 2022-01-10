/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;

/**
 * @author 应卓
 * @since 1.0.8
 */
public final class BigDecimalUtils {

    /**
     * 私有构造方法
     */
    private BigDecimalUtils() {
        super();
    }

    public static BigDecimal min(@NonNull BigDecimal number1, @NonNull BigDecimal number2) {
        Asserts.notNull(number1);
        Asserts.notNull(number2);
        return number1.compareTo(number2) < 0 ? number1 : number2;
    }

    public static BigDecimal max(@NonNull BigDecimal number1, @NonNull BigDecimal number2) {
        Asserts.notNull(number1);
        Asserts.notNull(number2);
        return number1.compareTo(number2) > 0 ? number1 : number2;
    }

}
