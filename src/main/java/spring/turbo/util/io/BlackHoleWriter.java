package spring.turbo.util.io;

import java.io.Writer;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 2.0.0
 */
public final class BlackHoleWriter extends Writer {

    /**
     * 私有构造方法
     */
    private BlackHoleWriter() {
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static BlackHoleWriter getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public void write(char[] buf, int off, int len) {
        // nop
    }

    @Override
    public void flush() {
        // nop
    }

    @Override
    public void close() {
        // nop
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final BlackHoleWriter INSTANCE = new BlackHoleWriter();
    }

}
