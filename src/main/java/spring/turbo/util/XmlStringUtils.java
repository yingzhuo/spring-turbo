/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import javax.annotation.Nullable;

/**
 * @author 应卓
 *
 * @see JsonStringUtils
 *
 * @since 3.3.0
 */
public final class XmlStringUtils {

    /**
     * 私有构造方法
     */
    private XmlStringUtils() {
        super();
    }

    @Nullable
    public static String removeExtraWhitespaces(@Nullable String xmlString) {
        if (xmlString == null) {
            return null;
        }
        return xmlString.replaceAll(">[\\s\r\n]*<", "><").trim();
    }
}
