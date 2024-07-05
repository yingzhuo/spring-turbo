package spring.turbo.util;

import javax.annotation.Nullable;

/**
 * @author 应卓
 * @see JsonStringUtils
 * @since 3.3.0
 */
public final class XmlStringUtils {

    /**
     * 私有构造方法
     */
    private XmlStringUtils() {
    }

    @Nullable
    public static String removeExtraWhitespaces(@Nullable String xmlString) {
        if (xmlString == null) {
            return null;
        }
        return xmlString.replaceAll(">[\\s\r\n]*<", "><").trim();
    }

}
