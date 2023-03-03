/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * {@link Resource} 相关工具
 *
 * @author 应卓
 * @see Resource
 * @since 1.1.0
 */
public final class ResourceUtils {

    /**
     * 私有构造方法
     */
    private ResourceUtils() {
        super();
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
        Asserts.notNull(resource);
        charset = Objects.requireNonNullElse(charset, UTF_8);

        try {
            return IOUtils.copyToString(resource.getInputStream(), charset);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } finally {
            CloseUtils.closeQuietly(resource);
        }
    }

    /**
     * 读取字节码
     *
     * @param resource 资源
     * @return 字节码内容
     */
    public static byte[] readByteArray(Resource resource) {
        Asserts.notNull(resource);

        try {
            return IOUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } finally {
            CloseUtils.closeQuietly(resource);
        }
    }

}
