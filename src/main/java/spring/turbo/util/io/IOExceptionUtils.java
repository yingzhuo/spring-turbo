package spring.turbo.util.io;

import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * {@link IOException}相关工具
 *
 * @author 应卓
 * @since 1.0.10
 */
public final class IOExceptionUtils {

    /**
     * 私有构造方法
     */
    private IOExceptionUtils() {
    }

    /**
     * 转换成未检查异常
     *
     * @param e IOException实例
     * @return 未检查异常
     */
    public static UncheckedIOException toUnchecked(IOException e) {
        return new UncheckedIOException(e.getMessage(), e);
    }

    /**
     * 转换成未检查异常
     *
     * @param message 消息文本
     * @return 未检查异常
     */
    public static UncheckedIOException toUnchecked(@Nullable String message) {
        return toUnchecked(new IOException(message));
    }

}
