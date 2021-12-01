/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.util.Assert;

import java.util.Random;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class RandomUtils {

    private static final Random RANDOM = new Random();

    public RandomUtils() {
        super();
    }

    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    public static byte[] nextBytes(final int count) {
        Assert.isTrue(count >= 0, "count cannot be negative");

        final byte[] result = new byte[count];
        RANDOM.nextBytes(result);
        return result;
    }

    public static int nextInt(final int startInclusive, final int endExclusive) {
        Assert.isTrue(endExclusive >= startInclusive,
                "Start value must be smaller or equal to end value.");
        Assert.isTrue(startInclusive >= 0, "both range values must be non-negative");

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }

    public static int nextInt() {
        return nextInt(0, Integer.MAX_VALUE);
    }

    public static long nextLong(final long startInclusive, final long endExclusive) {
        Assert.isTrue(endExclusive >= startInclusive,
                "start value must be smaller or equal to end value.");
        Assert.isTrue(startInclusive >= 0, "both range values must be non-negative.");

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + nextLong(endExclusive - startInclusive);
    }

    public static long nextLong() {
        return nextLong(Long.MAX_VALUE);
    }

    private static long nextLong(final long n) {
        // Extracted from o.a.c.rng.core.BaseProvider.nextLong(long)
        long bits;
        long val;
        do {
            bits = RANDOM.nextLong() >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0);

        return val;
    }

    public static double nextDouble(final double startInclusive, final double endExclusive) {
        Assert.isTrue(endExclusive >= startInclusive,
                "start value must be smaller or equal to end value.");
        Assert.isTrue(startInclusive >= 0, "both range values must be non-negative.");

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextDouble());
    }

    public static double nextDouble() {
        return nextDouble(0, Double.MAX_VALUE);
    }

    public static float nextFloat(final float startInclusive, final float endExclusive) {
        Assert.isTrue(endExclusive >= startInclusive,
                "start value must be smaller or equal to end value.");
        Assert.isTrue(startInclusive >= 0, "Both range values must be non-negative.");

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextFloat());
    }

    public static float nextFloat() {
        return nextFloat(0, Float.MAX_VALUE);
    }
}
