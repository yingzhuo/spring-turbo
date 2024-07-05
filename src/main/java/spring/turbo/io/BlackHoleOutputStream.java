package spring.turbo.io;

import java.io.OutputStream;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.8
 */
public final class BlackHoleOutputStream extends OutputStream {

    /**
     * 私有构造方法
     */
    private BlackHoleOutputStream() {
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static BlackHoleOutputStream getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public void write(int b) {
        // nop
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final BlackHoleOutputStream INSTANCE = new BlackHoleOutputStream();
    }

}
