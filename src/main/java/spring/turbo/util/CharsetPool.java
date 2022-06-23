/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 常用字符集
 *
 * @author 应卓
 * @since 1.0.7
 */
public final class CharsetPool {

    // UTF-8
    public static final String UTF_8_VALUE = "UTF-8";
    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    // ISO-8859-1
    public static final String ISO_8859_1_VALUE = "ISO-8859-1";
    public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;

    // GBK
    public static final String GBK_VALUE = "GBK";
    public static final Charset GBK = Charset.forName(GBK_VALUE);

    // US-ASCII
    public static final String US_ASCII_VALUE = "US-ASCII";
    public static final Charset US_ASCII = StandardCharsets.US_ASCII;

    /**
     * 私有构造方法
     */
    private CharsetPool() {
        super();
    }

}
