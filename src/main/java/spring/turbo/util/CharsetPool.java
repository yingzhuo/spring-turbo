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
 * @author 应卓
 * @since 1.0.7
 */
public final class CharsetPool {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
    public static final String UTF_8_VALUE = "UTF-8";
    public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
    public static final String ISO_8859_1_VALUES = "ISO-8859-1";

    /**
     * 私有构造方法
     */
    private CharsetPool() {
        super();
    }

}
