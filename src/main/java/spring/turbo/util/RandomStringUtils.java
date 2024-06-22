/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.util.Random;
import java.util.UUID;

import static spring.turbo.util.StringPool.EMPTY;
import static spring.turbo.util.StringPool.HYPHEN;

/**
 * 随机字符串生成工具
 *
 * @author 应卓
 * @see RandomUtils
 * @see StringUtils
 * @see StringDefaults
 * @since 1.0.0
 */
public final class RandomStringUtils {

    /**
     * 随机子
     */
    private static final Random RANDOM = new Random();

    /**
     * 私有构造方法
     */
    private RandomStringUtils() {
        super();
    }

    /**
     * 生成随机字符串
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String random(final int count) {
        return random(count, false, false);
    }

    /**
     * 生成随机字符串 (只包含ascii)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomAscii(final int count) {
        return random(count, 32, 127, false, false);
    }

    /**
     * 生成随机字符串 (只包含ascii)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomAscii(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomAscii(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含字母)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomAlphabetic(final int count) {
        return random(count, true, false);
    }

    /**
     * 生成随机字符串 (只包含字母)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomAlphabetic(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomAlphabetic(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含字母和数字)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomAlphanumeric(final int count) {
        return random(count, true, true);
    }

    /**
     * 生成随机字符串 (只包含字母和数字)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomAlphanumeric(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomAlphanumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含图形支付)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomGraph(final int count) {
        return random(count, 33, 126, false, false);
    }

    /**
     * 生成随机字符串 (只包含图形支付)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomGraph(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomGraph(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含数字)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomNumeric(final int count) {
        return random(count, false, true);
    }

    /**
     * 生成随机字符串 (只包含数字)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomNumeric(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomNumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含可显示字符)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomPrint(final int count) {
        return random(count, 32, 126, false, false);
    }

    /**
     * 生成随机字符串 (只包含可显示字符)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomPrint(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomPrint(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串
     *
     * @param count   字符串长度
     * @param letters 是否可包含字母
     * @param numbers 是否可包含数字
     * @return 随机字符串
     */
    public static String random(final int count, final boolean letters, final boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    /**
     * 生成随机字符串
     *
     * @param count   字符串长度
     * @param start   开始字符
     * @param end     结束字符
     * @param letters 是否可包含字母
     * @param numbers 是否可包含数字
     * @return 随机字符串
     */
    public static String random(final int count, final int start, final int end, final boolean letters,
                                final boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    private static String random(int count, int start, int end, final boolean letters, final boolean numbers,
                                 final char[] chars, final Random random) {
        if (count == 0) {
            return EMPTY;
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if (chars != null && chars.length == 0) {
            throw new IllegalArgumentException("The chars array must not be empty");
        }

        if (start == 0 && end == 0) {
            if (chars != null) {
                end = chars.length;
            } else if (!letters && !numbers) {
                end = Character.MAX_CODE_POINT;
            } else {
                end = 'z' + 1;
                start = ' ';
            }
        } else if (end <= start) {
            throw new IllegalArgumentException(
                    "Parameter end (" + end + ") must be greater than start (" + start + ")");
        }

        final int zero_digit_ascii = 48;
        final int first_letter_ascii = 65;

        if (chars == null && (numbers && end <= zero_digit_ascii || letters && end <= first_letter_ascii)) {
            throw new IllegalArgumentException(
                    "Parameter end (" + end + ") must be greater then (" + zero_digit_ascii + ") for generating digits "
                            + "or greater then (" + first_letter_ascii + ") for generating letters.");
        }

        final StringBuilder builder = new StringBuilder(count);
        final int gap = end - start;

        while (count-- != 0) {
            final int codePoint;
            if (chars == null) {
                codePoint = random.nextInt(gap) + start;

                switch (Character.getType(codePoint)) {
                    case Character.UNASSIGNED:
                    case Character.PRIVATE_USE:
                    case Character.SURROGATE:
                        count++;
                        continue;
                }

            } else {
                codePoint = chars[random.nextInt(gap) + start];
            }

            final int numberOfChars = Character.charCount(codePoint);
            if (count == 0 && numberOfChars > 1) {
                count++;
                continue;
            }

            if (letters && Character.isLetter(codePoint) || numbers && Character.isDigit(codePoint)
                    || !letters && !numbers) {
                builder.appendCodePoint(codePoint);

                if (numberOfChars == 2) {
                    count--;
                }

            } else {
                count++;
            }
        }
        return builder.toString();
    }

    /**
     * 生成随机字符串
     *
     * @param count 随机字符串长度
     * @param chars 随机字符串的可能的字符从此参数中获得
     * @return 随机字符串
     */
    public static String random(final int count, final @Nullable String chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, chars.toCharArray());
    }

    /**
     * 生成随机字符串
     *
     * @param count 随机字符串长度
     * @param chars 随机字符串的可能的字符从此参数中获得
     * @return 随机字符串
     */
    public static String random(final int count, @Nullable final char... chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, RANDOM);
        }
        return random(count, 0, chars.length, false, false, chars, RANDOM);
    }

    /**
     * 生成随机UUID字符串
     *
     * @return 随机UUID字符串
     * @deprecated 使用 {@link UUIDUtils} 代替
     */
    @Deprecated(since = "3.3.1")
    public static String randomUUID() {
        return randomUUID(false);
    }

    /**
     * 生成随机UUID字符串
     *
     * @param removeHyphen 结果中是否要移除字符{@code "-"}
     * @return 随机UUID字符串
     * @deprecated 使用 {@link UUIDUtils} 代替
     */
    @Deprecated(since = "3.3.1")
    public static String randomUUID(boolean removeHyphen) {
        final var uuid = UUID.randomUUID().toString();
        return removeHyphen ? uuid.replaceAll(HYPHEN, EMPTY) : uuid;
    }

}
