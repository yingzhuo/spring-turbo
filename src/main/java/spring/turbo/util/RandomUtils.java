/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.Random;

/**
 * 随机数生成工具
 *
 * @author 应卓
 * @see RandomStringUtils
 * @since 1.0.0
 */
public final class RandomUtils {

    private static final Random RANDOM = new Random();

    /**
     * 私有构造方法
     */
    private RandomUtils() {
        super();
    }

    /**
     * 随机生成布尔值
     *
     * @return 随机布尔值
     */
    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * 随机生成字节数组
     *
     * @param count 字符数组长度
     * @return 随机字节数组
     */
    public static byte[] nextBytes(final int count) {
        Asserts.isTrue(count >= 0);

        final byte[] result = new byte[count];
        RANDOM.nextBytes(result);
        return result;
    }

    /**
     * 随机生成整数
     *
     * @param startInclusive 开始 (包含)
     * @param endExclusive   结束 (不包含)
     * @return 随机整数
     */
    public static int nextInt(final int startInclusive, final int endExclusive) {
        Asserts.isTrue(endExclusive >= startInclusive);
        Asserts.isTrue(startInclusive >= 0);

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }

    /**
     * 随机生成整数
     *
     * @return 随机整数
     */
    public static int nextInt() {
        return nextInt(0, Integer.MAX_VALUE);
    }

    /**
     * 随机生成长整数
     *
     * @param startInclusive 开始 (包含)
     * @param endExclusive   结束 (不包含)
     * @return 随机长整数
     */
    public static long nextLong(final long startInclusive, final long endExclusive) {
        Asserts.isTrue(endExclusive >= startInclusive);
        Asserts.isTrue(startInclusive >= 0);

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + nextLong(endExclusive - startInclusive);
    }

    /**
     * 随机生成长整数
     *
     * @return 随机长整数
     */
    public static long nextLong() {
        return nextLong(Long.MAX_VALUE);
    }

    private static long nextLong(final long n) {
        long bits;
        long val;
        do {
            bits = RANDOM.nextLong() >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0);

        return val;
    }

    /**
     * 随机生成双精度数
     *
     * @param startInclusive 开始 (包含)
     * @param endExclusive   结束 (不包含)
     * @return 随机双精度数
     */
    public static double nextDouble(final double startInclusive, final double endExclusive) {
        Asserts.isTrue(endExclusive >= startInclusive);
        Asserts.isTrue(startInclusive >= 0);

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextDouble());
    }

    /**
     * 随机生成双精度数
     *
     * @return 随机双精度数
     */
    public static double nextDouble() {
        return nextDouble(0, Double.MAX_VALUE);
    }

    /**
     * 随机生成浮点数数
     *
     * @param startInclusive 开始 (包含)
     * @param endExclusive   结束 (不包含)
     * @return 随机浮点数
     */
    public static float nextFloat(final float startInclusive, final float endExclusive) {
        Asserts.isTrue(endExclusive >= startInclusive);
        Asserts.isTrue(startInclusive >= 0);

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextFloat());
    }

    /**
     * 随机生成浮点数数
     *
     * @return 随机浮点数
     */
    public static float nextFloat() {
        return nextFloat(0, Float.MAX_VALUE);
    }

}
