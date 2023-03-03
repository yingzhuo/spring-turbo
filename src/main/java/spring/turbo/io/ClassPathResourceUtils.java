/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.nio.charset.Charset;

import static spring.turbo.util.StringPool.EMPTY;
import static spring.turbo.util.StringPool.LF;

/**
 * @author 应卓
 * @since 2.0.13
 */
public final class ClassPathResourceUtils {

    /**
     * 私有构造方法
     */
    private ClassPathResourceUtils() {
        super();
    }

    public static String readText(String location) {
        return readText(location, null);
    }

    public static String readText(String location, @Nullable Charset charset) {
        Asserts.hasText(location);
        return ResourceUtils.readText(new ClassPathResource(location), charset);
    }

    public static String readTextAsOneLine(String location) {
        return readTextAsOneLine(location, null);
    }

    public static String readTextAsOneLine(String location, @Nullable Charset charset) {
        Asserts.hasText(location);
        return readText(location, charset).replaceAll(LF, EMPTY);
    }

    public static byte[] readByteArray(String location) {
        Asserts.hasText(location);
        return ResourceUtils.readByteArray(new ClassPathResource(location));
    }

}
