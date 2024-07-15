package spring.turbo.util;

import org.springframework.lang.Nullable;
import spring.turbo.util.text.StringMatcher;
import spring.turbo.util.text.StringTokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link StringTokenizer} 相关工具
 *
 * @author 应卓
 * @see StringTokenizer
 * @since 3.3.1
 */
public final class StringTokenizerUtils {

    /**
     * 私有构造方法
     */
    private StringTokenizerUtils() {
    }

    /**
     * 拆分字符串
     *
     * @param text  字符串
     * @param delim 分隔逻辑
     * @return 拆分结果
     */
    public static List<String> split(@Nullable String text, StringMatcher delim) {
        return split(text, delim, true, true);
    }

    /**
     * 拆分字符串
     *
     * @param text             字符串
     * @param delim            分隔逻辑
     * @param trimToken        是否去掉每个token前后的白字符
     * @param ignoreBlankToken 是否忽略空白的token
     * @return 拆分结果
     */
    public static List<String> split(@Nullable String text, StringMatcher delim, boolean trimToken, boolean ignoreBlankToken) {
        if (text == null) {
            return new ArrayList<>(0);
        }

        var list = new ArrayList<String>();
        var tokens = new StringTokenizer(text, delim);
        while (tokens.hasNext()) {
            var token = tokens.next();
            if (ignoreBlankToken) {
                if (StringUtils.isBlank(token)) {
                    continue;
                }
            }

            if (trimToken) {
                token = token.trim();
            }

            list.add(token);
        }
        return list;
    }

}
