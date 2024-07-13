package spring.turbo.util;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * {@link CharSequence} 相关工具
 *
 * @author 应卓
 * @since 2.0.1
 */
public final class CharSequenceUtils {

    /**
     * 私有构造方法
     */
    private CharSequenceUtils() {
        super();
    }

    // ------------------------------------------------------------------------------------------------------------------

    /**
     * 获取字符序列的长度
     *
     * @param cs 字符序列或 {@code null}
     * @return 结果
     */
    public static int length(@Nullable CharSequence cs) {
        return cs != null ? cs.length() : 0;
    }

    // ------------------------------------------------------------------------------------------------------------------

    /**
     * 获取子字符序列
     *
     * @param cs    指定的字符序列
     * @param start 切割开始位置 (包含)
     * @return a new subsequence, may be null
     * @throws IndexOutOfBoundsException start 参数不合法
     */
    public static CharSequence subSequence(CharSequence cs, int start) {
        Assert.notNull(cs, "cs is required");
        return cs.subSequence(start, cs.length());
    }

    // ------------------------------------------------------------------------------------------------------------------

    /**
     * {@link CharSequence} 转换成 char[]
     *
     * @param source 指定 {@code CharSequence} 的实例.
     * @return 结果
     */
    public static char[] toCharArray(@Nullable CharSequence source) {
        if (source == null) {
            return new char[0];
        }

        final int len = StringUtils.length(source.toString());
        if (len == 0) {
            return new char[0];
        }
        if (source instanceof String) {
            return ((String) source).toCharArray();
        }
        final char[] array = new char[len];
        for (int i = 0; i < len; i++) {
            array[i] = source.charAt(i);
        }
        return array;
    }

}
