package spring.turbo.io;

import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.nio.charset.Charset;

import static spring.turbo.util.StringPool.EMPTY;
import static spring.turbo.util.StringPool.LF;

/**
 * {@link ClassPathResource} 相关工具
 *
 * @author 应卓
 * @see ResourceUtils
 * @since 2.0.13
 */
public final class ClassPathResourceUtils {

    /**
     * 私有构造方法
     */
    private ClassPathResourceUtils() {
    }

    /**
     * 读取为文本
     *
     * @param location 资源地址 如: {@code "META-INF/spring.txt"}
     * @return 文本
     */
    public static String readText(String location) {
        return readText(location, null);
    }

    /**
     * 读取为文本
     *
     * @param location 资源地址 如: {@code "META-INF/spring.txt"}
     * @param charset  字符集
     * @return 文本
     */
    public static String readText(String location, @Nullable Charset charset) {
        Asserts.hasText(location);
        return ResourceUtils.readText(new ClassPathResource(location), charset);
    }

    /**
     * 读取为单行文本
     *
     * @param location 资源地址 如: {@code "META-INF/spring.txt"}
     * @return 当行文本
     */
    public static String readTextAsOneLine(String location) {
        return readTextAsOneLine(location, null);
    }

    /**
     * 读取为单行文本
     *
     * @param location 资源地址 如: {@code "META-INF/spring.txt"}
     * @param charset  字符集
     * @return 当行文本
     */
    public static String readTextAsOneLine(String location, @Nullable Charset charset) {
        Asserts.hasText(location);
        return readText(location, charset).replaceAll(LF, EMPTY);
    }

    /**
     * 读取为二进制数据
     *
     * @param location 资源地址 如: {@code "META-INF/spring.data"}
     * @return 二进制数据
     */
    public static byte[] readByteArray(String location) {
        Asserts.hasText(location);
        return ResourceUtils.readByteArray(new ClassPathResource(location));
    }

}
