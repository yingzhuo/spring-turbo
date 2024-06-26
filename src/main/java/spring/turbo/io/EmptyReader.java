/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
        super();
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
