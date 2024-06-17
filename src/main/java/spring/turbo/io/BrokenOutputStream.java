/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 1.1.1
 */
public class BrokenOutputStream extends OutputStream {

    /**
     * 私有构造方法
     */
    private BrokenOutputStream() {
        super();
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
