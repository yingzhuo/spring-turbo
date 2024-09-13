package spring.turbo.util.io;

import java.io.InputStream;

/**
 * 空的 {@link InputStream}
 *
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.8
 * @deprecated 使用 {@link InputStream#nullInputStream()} 替代
 */
@Deprecated(since = "3.4.0")
public final class EmptyInputStream extends InputStream {

    /**
     * 私有构造方法
     */
    private EmptyInputStream() {
    }

    public static EmptyInputStream getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public int read() {
        return -1;
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final EmptyInputStream INSTANCE = new EmptyInputStream();
    }

}
