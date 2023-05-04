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
import java.io.InputStream;

/**
 * @author 应卓
 *
 * @since 1.0.9
 */
public final class BrokenInputStream extends InputStream {

    /**
     * 私有构造方法
     */
    private BrokenInputStream() {
        super();
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
