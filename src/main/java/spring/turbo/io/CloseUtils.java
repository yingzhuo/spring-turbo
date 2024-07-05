package spring.turbo.io;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

import java.io.Closeable;
import java.io.IOException;

/**
 * 本类封装资源关闭操作
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class CloseUtils {

    /**
     * 私有构造方法
     */
    private CloseUtils() {
    }

    /**
     * 关闭资源
     *
     * @param closeable 资源
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
     * @param closeable 资源
     */
    public static void closeQuietly(@Nullable AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ignored) {
                // nop
            }
        }
    }

    /**
     * 关闭资源
     *
     * @param resource 资源
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
