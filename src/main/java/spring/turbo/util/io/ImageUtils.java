package spring.turbo.util.io;

import spring.turbo.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static spring.turbo.util.io.IOExceptionUtils.toUnchecked;

/**
 * {@code BufferedImage}相关工具
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class ImageUtils {

    /**
     * 私有构造方法
     */
    private ImageUtils() {
    }

    /**
     * 将图片转换成字节数组
     *
     * @param image  图片实例
     * @param format 格式，如: {@code "png"}
     * @return 字节数组
     */
    public static byte[] toByteArray(BufferedImage image, String format) {
        try {
            var os = new ByteArrayOutputStream();
            ImageIO.write(image, format, os);
            return os.toByteArray();
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    /**
     * 将图片转换成Base64哈希之后的字符串
     *
     * @param image  图片实例
     * @param format 格式，如: {@code "png"}
     * @return Base64字符串
     */
    public static String encodeToBase64(BufferedImage image, String format) {
        var bytes = toByteArray(image, format);
        var base64Bytes = Base64Utils.encode(bytes, false, true);
        return new String(base64Bytes, StandardCharsets.UTF_8);
    }

}
