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

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * {@link StringTokenizer}相关工具
 *
 * @author 应卓
 * @see StringTokenizer
 * @since 2.0.2
 */
public final class StringTokenizerUtils {

    /**
     * 默认分隔符
     */
    private static final String DEFAULT_DELIMITER = " \t\n\r\f";

    /**
     * 私有构造方法
     */
    private StringTokenizerUtils() {
        super();
    }

    /**
     * 字符串转换为字符串数组
     *
     * @param string 字符串
     * @return 结果
     */
    public static String[] getTokensAsArray(String string) {
        return getTokensAsArray(string, null);
    }

    /**
     * 字符串转换为字符串数组
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 结果
     */
    public static String[] getTokensAsArray(String string, @Nullable String delimiter) {
        return getTokensAsList(string, delimiter).toArray(new String[0]);
    }

    /**
     * 字符串转换为字符串列表
     *
     * @param string 字符串
     * @return 结果
     */
    public static List<String> getTokensAsList(String string) {
        return getTokensAsList(string, null);
    }

    /**
     * 字符串转换为字符串列表
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 结果
     */
    public static List<String> getTokensAsList(String string, @Nullable String delimiter) {
        Asserts.notNull(string);
        delimiter = delimiter == null ? DEFAULT_DELIMITER : delimiter;
        return Collections.list(new StringTokenizer(string, delimiter))
                .stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    /**
     * 字符串转换为字符串集合
     *
     * @param string 字符串
     * @return 结果
     */
    public static Set<String> getTokensAsSet(String string) {
        return getTokensAsSet(string, null);
    }

    /**
     * 字符串转换为字符串集合
     *
     * @param string    字符串
     * @param delimiter 分隔符
     * @return 结果
     */
    public static Set<String> getTokensAsSet(String string, @Nullable String delimiter) {
        Asserts.notNull(string);
        delimiter = delimiter == null ? DEFAULT_DELIMITER : delimiter;
        return Collections.list(new StringTokenizer(string, delimiter))
                .stream()
                .map(token -> (String) token)
                .collect(Collectors.toSet());
    }

}
