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
import spring.turbo.util.Asserts;
import spring.turbo.util.CloseUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * @author 应卓
 * @since 1.1.0
 */
public final class ResourceUtils {

    /**
     * 私有构造方法
     */
    private ResourceUtils() {
        super();
    }

    public static String toString(Resource resource) {
        return toString(resource, UTF_8);
    }

    public static String toString(Resource resource, Charset charset) {
        Asserts.notNull(resource);
        Asserts.notNull(charset);

        try {
            return IOUtils.copyToString(resource.getInputStream(), charset);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } finally {
            CloseUtils.closeQuietly(resource);
        }
    }

}
