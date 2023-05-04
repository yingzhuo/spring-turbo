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

import java.io.Closeable;
import java.io.IOException;

/**
 * 本类封装资源关闭操作
 *
 * @author 应卓
 *
 * @since 1.0.0
 */
public final class CloseUtils {

    /**
     * 私有构造方法
     */
    private CloseUtils() {
        super();
    }

    /**
     * 关闭资源
     *
     * @param closeable
     *            资源
     */
    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
                // nop
            }
        }
    }

    /**
     * 关闭资源
     *
     * @param resource
     *            资源
     */
    public static void closeQuietly(@Nullable Resource resource) {
        if (resource != null) {
            try {
                closeQuietly(resource.getInputStream());
            } catch (IOException ignored) {
                // nop
            }
        }
    }

}
