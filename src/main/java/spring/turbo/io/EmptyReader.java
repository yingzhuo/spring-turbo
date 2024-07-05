package spring.turbo.io;

import java.io.Reader;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.8
 */
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
        // nop
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final EmptyReader INSTANCE = new EmptyReader();
    }

}
