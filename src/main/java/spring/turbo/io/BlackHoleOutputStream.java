/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import spring.turbo.lang.Singleton;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.8
 */
@Singleton
public final class BlackHoleOutputStream extends OutputStream {

    private BlackHoleOutputStream() {
        super();
    }

    public static BlackHoleOutputStream getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public void write(int b) throws IOException {
        // nop
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final BlackHoleOutputStream INSTANCE = new BlackHoleOutputStream();
    }

}
