/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
        super();
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
