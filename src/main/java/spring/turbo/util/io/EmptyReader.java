package spring.turbo.util.io;

import java.io.Reader;

/**
 * 空的 {@link Reader}
 *
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.8
 * @deprecated 使用 {@link Reader#nullReader()} 替代
 */
@Deprecated(since = "3.4.0")
public final class EmptyReader extends Reader {

    /**
     * 私有构造方法
     */
    private EmptyReader() {
    }

    public static EmptyReader getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public int read(char[] buf, int off, int len) {
        return -1;
    }

    @Override
    public void close() {
        // noop
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final EmptyReader INSTANCE = new EmptyReader();
    }

}
