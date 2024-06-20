/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import java.io.InputStream;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.8
 */
public final class EmptyInputStream extends InputStream {

    /**
     * 私有构造方法
     */
    private EmptyInputStream() {
        super();
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
