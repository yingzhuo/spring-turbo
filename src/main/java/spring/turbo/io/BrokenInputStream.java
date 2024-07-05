package spring.turbo.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 应卓
 * @since 1.0.9
 */
public final class BrokenInputStream extends InputStream {

    /**
     * 私有构造方法
     */
    private BrokenInputStream() {
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static BrokenInputStream getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public int read() throws IOException {
        throw new IOException("broken");
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final BrokenInputStream INSTANCE = new BrokenInputStream();
    }

}
