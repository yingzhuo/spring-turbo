/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

/**
 * 字符池
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class CharPool {

    public static final char SPACE = ' ';
    public static final char TAB = '\t';
    public static final char DOT = '.';
    public static final char SLASH = '/';
    public static final char BACKSLASH = '\\';
    public static final char CR = '\r';
    public static final char LF = '\n';
    public static final char QUESTION_MARK = '?';
    public static final char HYPHEN = '-';
    public static final char UNDERSCORE = '_';
    public static final char COMMA = ',';
    public static final char SINGLE_QUOTE = '\'';
    public static final char DOUBLE_QUOTE = '"';

    /**
     * 私有构造方法
     */
    private CharPool() {
        super();
    }

}
