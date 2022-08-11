/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import static spring.turbo.util.StringUtils.repeat;

/**
 * 字符串池
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class StringPool {

    public static final String NULL = "null";
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String SEMICOLON = ";";
    public static final String DOT = ".";
    public static final String DOT_DOT = "..";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String QUESTION_MARK = "?";
    public static final String HYPHEN = "-";
    public static final String UNDERSCORE = "_";
    public static final String COMMA = ",";
    public static final String COMMA_SPACE = COMMA + SPACE;

    public static final String QUESTION_MARK_X_3 = repeat(QUESTION_MARK, 3);
    public static final String HYPHEN_X_80 = repeat(HYPHEN, 80);

    public static final String ANNOTATION_STRING_NULL = "#<no value!!!>#";

    /**
     * 私有构造方法
     */
    private StringPool() {
        super();
    }

}
