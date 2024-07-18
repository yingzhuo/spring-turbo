package spring.turbo.util.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 1.1.1
 */
@Deprecated(since = "3.3.1")
public class BrokenOutputStream extends OutputStream {

    /**
     * 私有构造方法
     */
    private BrokenOutputStream() {
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static BrokenOutputStream getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public void write(int b) throws IOException {
        throw new IOException("broken");
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final BrokenOutputStream INSTANCE = new BrokenOutputStream();
    }

}
