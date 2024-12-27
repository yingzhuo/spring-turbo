package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * 随机字符串生成工具
 *
 * @author 应卓
 * @see RandomUtils
 * @see UUIDGenerators
 * @see StringUtils
 * @see StringDefaults
 * @since 1.0.0
 */
public final class RandomStringUtils {

    /**
     * 私有构造方法
     */
    private RandomStringUtils() {
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
    public static String randomAscii(int count) {
        return random(count, 32, 127, false, false);
    }

    /**
     * 生成随机字符串 (只包含ascii)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomAscii(int minLengthInclusive, int maxLengthExclusive) {
        return randomAscii(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含字母)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomAlphabetic(int count) {
        return random(count, true, false);
    }

    /**
     * 生成随机字符串 (只包含字母)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomAlphabetic(int minLengthInclusive, int maxLengthExclusive) {
        return randomAlphabetic(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含字母和数字)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomAlphanumeric(int count) {
        return random(count, true, true);
    }

    /**
     * 生成随机字符串 (只包含字母和数字)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomAlphanumeric(int minLengthInclusive, int maxLengthExclusive) {
        return randomAlphanumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含图形支付)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomGraph(int count) {
        return random(count, 33, 126, false, false);
    }

    /**
     * 生成随机字符串 (只包含图形支付)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomGraph(int minLengthInclusive, int maxLengthExclusive) {
        return randomGraph(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含数字)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomNumeric(int count) {
        return random(count, false, true);
    }

    /**
     * 生成随机字符串 (只包含数字)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomNumeric(int minLengthInclusive, int maxLengthExclusive) {
        return randomNumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 生成随机字符串 (只包含可显示字符)
     *
     * @param count 字符串长度
     * @return 随机字符串
     */
    public static String randomPrint(int count) {
        return random(count, 32, 126, false, false);
    }

    /**
     * 生成随机字符串 (只包含可显示字符)
     *
     * @param minLengthInclusive 可能的长度最小值 (包含)
     * @param maxLengthExclusive 可能的长度最大值 (不包含)
     * @return 随机字符串
     */
    public static String randomPrint(int minLengthInclusive, int maxLengthExclusive) {
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
    public static String random(int count, boolean letters, boolean numbers) {
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
    public static String random(int count, int start, int end, boolean letters,
                                boolean numbers) {
        return random(count, start, end, letters, numbers, null, ThreadLocalRandom.current());
    }

    private static String random(int count, int start, int end, boolean letters, boolean numbers,
                                 char[] chars, Random random) {
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

        int zero_digit_ascii = 48;
        int first_letter_ascii = 65;

        if (chars == null && (numbers && end <= zero_digit_ascii || letters && end <= first_letter_ascii)) {
            throw new IllegalArgumentException(
                    "Parameter end (" + end + ") must be greater then (" + zero_digit_ascii + ") for generating digits "
                            + "or greater then (" + first_letter_ascii + ") for generating letters.");
        }

        StringBuilder builder = new StringBuilder(count);
        int gap = end - start;

        while (count-- != 0) {
            int codePoint;
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

            int numberOfChars = Character.charCount(codePoint);
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
    public static String random(int count, @Nullable String chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, ThreadLocalRandom.current());
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
    public static String random(int count, @Nullable char... chars) {
        if (chars == null) {
            return random(count, 0, 0, false, false, null, ThreadLocalRandom.current());
        }
        return random(count, 0, chars.length, false, false, chars, ThreadLocalRandom.current());
    }

}
