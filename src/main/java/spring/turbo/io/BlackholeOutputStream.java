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
 * @since 1.0.8
 */
public final class BlackholeOutputStream extends OutputStream {

    private BlackholeOutputStream() {
        super();
    }

    public static BlackholeOutputStream getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public void write(int b) throws IOException {
        // nop
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final BlackholeOutputStream INSTANCE = new BlackholeOutputStream();
    }

}
