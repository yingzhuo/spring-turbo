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
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * {@link BigDecimal} 相关工具
 *
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

    /**
     * 找出两个数中较小的一个
     *
     * @param number1 数字1
     * @param number2 数字2
     * @return 参数中最小的一个
     */
    @NonNull
    public static BigDecimal min(@NonNull BigDecimal number1, @NonNull BigDecimal number2) {
        Asserts.notNull(number1);
        Asserts.notNull(number2);
        return number1.compareTo(number2) < 0 ? number1 : number2;
    }

    /**
     * 找出若干个数中最小的一个
     *
     * @param numbers 数字
     * @return 参数中最小的一个
     */
    @NonNull
    public static BigDecimal min(@NonNull BigDecimal... numbers) {
        Asserts.notNull(numbers);
        Asserts.notEmpty(numbers);
        Asserts.noNullElements(numbers);

        BigDecimal min = numbers[0];

        for (BigDecimal number : numbers) {
            min = min(min, number);
        }
        return min;
    }

    /**
     * 找出若干个数中最小的一个
     *
     * @param numbers 数字
     * @return 参数中最小的一个
     */
    @NonNull
    public static BigDecimal min(@NonNull Collection<BigDecimal> numbers) {
        Asserts.notNull(numbers);
        return min(numbers.toArray(new BigDecimal[0]));
    }

    /**
     * 找出两个数中较大的一个
     *
     * @param number1 数字1
     * @param number2 数字2
     * @return 参数中最小的一个
     */
    @NonNull
    public static BigDecimal max(@NonNull BigDecimal number1, @NonNull BigDecimal number2) {
        Asserts.notNull(number1);
        Asserts.notNull(number2);
        return number1.compareTo(number2) > 0 ? number1 : number2;
    }

    /**
     * 找出若干个数中最大的一个
     *
     * @param numbers 数字
     * @return 参数中最大的一个
     */
    @NonNull
    public static BigDecimal max(@NonNull BigDecimal... numbers) {
        Asserts.notNull(numbers);
        Asserts.notEmpty(numbers);
        Asserts.noNullElements(numbers);

        BigDecimal max = numbers[0];

        for (BigDecimal number : numbers) {
            max = min(max, number);
        }
        return max;
    }

    /**
     * 找出若干个数中最大的一个
     *
     * @param numbers 数字
     * @return 参数中最大的一个
     */
    @NonNull
    public static BigDecimal max(@NonNull Collection<BigDecimal> numbers) {
        Asserts.notNull(numbers);
        return max(numbers.toArray(new BigDecimal[0]));
    }

    /**
     * 求和 (空值不参与求和)
     *
     * @param numbers 若干个数
     * @return 和
     */
    @NonNull
    public static BigDecimal nullSafeAdd(@Nullable BigDecimal... numbers) {
        BigDecimal x = BigDecimal.ZERO;
        if (numbers != null) {
            for (BigDecimal number : numbers) {
                if (number != null) {
                    x = x.add(number);
                }
            }
        }
        return x;
    }

    /**
     * 求和 (空值不参与求和)
     *
     * @param numbers 若干个数
     * @return 和
     */
    @NonNull
    public static BigDecimal nullSafeAdd(@Nullable Collection<BigDecimal> numbers) {
        if (numbers == null) {
            return BigDecimal.ZERO;
        }
        return nullSafeAdd(numbers.toArray(new BigDecimal[0]));
    }

    /**
     * 求积 (空值不参与求积)
     *
     * @param numbers 若干个数
     * @return 积
     */
    @NonNull
    public static BigDecimal nullSafeMultiply(@Null BigDecimal... numbers) {
        BigDecimal x = BigDecimal.ONE;
        if (numbers != null) {
            for (BigDecimal number : numbers) {
                if (number != null) {
                    x = x.multiply(number);
                }
            }
        }
        return x;
    }

    /**
     * 求积 (空值不参与求积)
     *
     * @param numbers 若干个数
     * @return 积
     */
    @NonNull
    public static BigDecimal nullSafeMultiply(@Null Collection<BigDecimal> numbers) {
        if (numbers == null) {
            return BigDecimal.ONE;
        }
        return nullSafeMultiply(numbers.toArray(new BigDecimal[0]));
    }

}
