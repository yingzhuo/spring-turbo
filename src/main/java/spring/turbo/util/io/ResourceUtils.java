package spring.turbo.util.io;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static spring.turbo.util.io.CloseUtils.closeQuietly;

/**
 * {@link Resource} 相关工具
 *
 * @author 应卓
 * @see Resource
 * @see ClassPathResourceUtils
 * @since 1.1.0
 */
public final class ResourceUtils {

    /**
     * 私有构造方法
     */
    private ResourceUtils() {
    }

    /**
     * 读取文本
     *
     * @param resource 资源
     * @return 文本内容
     */
    public static String readText(Resource resource) {
        return readText(resource, UTF_8);
    }

    /**
     * 读取文本
     *
     * @param resource 资源
     * @param charset  编码
     * @return 文本内容
     */
    public static String readText(Resource resource, @Nullable Charset charset) {
        Assert.notNull(resource, "resource is required");

        charset = Objects.requireNonNullElse(charset, UTF_8);

        try {
            return IOUtils.copyToString(resource.getInputStream(), charset);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } finally {
            closeQuietly(resource);
        }
    }

    /**
     * 读取字节码
     *
     * @param resource 资源
     * @return 字节码内容
     */
    public static byte[] readByteArray(Resource resource) {
        Assert.notNull(resource, "resource is required");

        try {
            return IOUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } finally {
            closeQuietly(resource);
        }
    }

}
