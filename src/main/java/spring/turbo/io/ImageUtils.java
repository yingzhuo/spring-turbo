/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.lang.NonNull;
import spring.turbo.util.Asserts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ImageUtils {

    private ImageUtils() {
        super();
    }

    public static byte[] toByteArray(@NonNull BufferedImage image, @NonNull String format) {
        Asserts.notNull(image);
        Asserts.hasText(format);

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, format, os);
            return os.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static String encodeToBase64(@NonNull BufferedImage image, @NonNull String format) {
        Asserts.notNull(image);
        Asserts.hasText(format);
        return Base64.getEncoder().encodeToString(toByteArray(image, format));
    }

}
