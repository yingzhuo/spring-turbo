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
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * 字符串到数字解析工具
 *
 * @author 应卓
 * @since 1.0.8
 */
public final class NumberParseUtils {

    /**
     * 私有构造方法
     */
    private NumberParseUtils() {
        super();
    }

    /**
     * 从字符串中解析数字
     * <p>
     *    <ul>
     *        <li>支持科学计数法</li>
     *        <li>支持十六进制数</li>
     *    </ul>
     * <p>
     * 请正确使用类型，否则会有精度损失
     *
     * @param text 字符串
     * @param type 具体类型
     * @param <T>  数字的具体类型泛型
     * @return 结果
     * @throws IllegalArgumentException 解析失败
     */
    @NonNull
    public static <T extends Number> T parse(@NonNull String text, @NonNull Class<T> type) {
        Asserts.notNull(text);
        Asserts.notNull(type);

        // 移除白字符和逗号
        text = text.replaceAll("[\\s,]", EMPTY);

        // 十六进制数特殊处理
        if (text.startsWith("#") || text.startsWith("-#") || text.startsWith("0x") || text.startsWith("0X") || text.startsWith("-0x") || text.startsWith("-0X")) {
            final BigInteger bigInteger = NumberUtils.parseNumber(text, BigInteger.class);
            return BigDecimalUtils.getValue(new BigDecimal(bigInteger), type);
        }

        // 科学计数法特殊处理
        if (text.contains("E") || text.contains("e")) {
            final BigDecimal bigDecimal = NumberUtils.parseNumber(text, BigDecimal.class);
            return BigDecimalUtils.getValue(bigDecimal, type);
        }

        try {
            return NumberUtils.parseNumber(text, type);
        } catch (IllegalArgumentException e) {
            return fallback(text, type);
        }
    }

    private static <T extends Number> T fallback(@NonNull String text, @NonNull Class<T> type) {
        BigDecimal big;

        try {
            big = new BigDecimal(text);
        } catch (Throwable e) {
            throw new IllegalArgumentException(getErrorMessage(text));
        }

        return BigDecimalUtils.getValue(big, type);
    }

    private static String getErrorMessage(String text) {
        return StringFormatter.format("{} is not a valid number", text);
    }
}
