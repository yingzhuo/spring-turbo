package spring.turbo.util;

import java.util.Base64;

/**
 * {@link Base64} 相关工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class Base64Utils {

    /**
     * 私有构造方法
     */
    private Base64Utils() {
    }

    /**
     * 编码
     *
     * @param data 要编码的数据
     * @return 结果
     */
    public static byte[] encode(byte[] data) {
        return encode(data, true, true);
    }

    /**
     * 编码
     *
     * @param data           要编码的数据
     * @param withoutPadding 不需要padding
     * @return 结果
     */
    public static byte[] encode(byte[] data, boolean withoutPadding) {
        return encode(data, withoutPadding, true);
    }

    /**
     * 编码
     *
     * @param data           要编码的数据
     * @param withoutPadding 不需要padding
     * @param urlSafe        urlSafe
     * @return 结果
     */
    public static byte[] encode(byte[] data, boolean withoutPadding, boolean urlSafe) {
        Base64.Encoder encoder = urlSafe ? Base64.getUrlEncoder() : Base64.getEncoder();
        if (withoutPadding) {
            encoder = encoder.withoutPadding();
        }
        return encoder.encode(data);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 解码
     *
     * @param data 要解码的数据
     * @return 结果
     */
    public static byte[] decode(byte[] data) {
        return decode(data, true);
    }

    /**
     * 解码
     *
     * @param data    要解码的数据
     * @param urlSafe urlSafe
     * @return 结果
     */
    public static byte[] decode(byte[] data, boolean urlSafe) {
        var decoder = urlSafe ? Base64.getUrlDecoder() : Base64.getDecoder();
        return decoder.decode(data);
    }

}
