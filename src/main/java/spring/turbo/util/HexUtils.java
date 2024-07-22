package spring.turbo.util;

import java.util.HexFormat;

/**
 * HEX相关工具
 *
 * @author 应卓
 * @see Base64Utils
 * @since 1.0.0
 */
public final class HexUtils {

    /**
     * 私有构造方法
     */
    private HexUtils() {
    }

    /**
     * bytes用hex方式表示
     *
     * @param bytes bytes
     * @return bytes
     */
    public static String encodeToString(byte[] bytes) {
        var format = HexFormat.of();
        return format.formatHex(bytes);
    }

    /**
     * hex字符串转换成 bytes
     *
     * @param hexString hex字符串
     * @return bytes
     */
    public static byte[] decodeToBytes(String hexString) {
        var format = HexFormat.of();
        return format.parseHex(hexString);
    }

}
