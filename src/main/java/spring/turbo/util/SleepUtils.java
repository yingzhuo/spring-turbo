/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.time.Duration;

/**
 * 线程暂停工具
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class SleepUtils {

    /**
     * 私有构造方法
     */
    private SleepUtils() {
        super();
    }

    /**
     * 暂停线程
     *
     * @param duration 暂停时间
     * @throws UncheckedInterruptedException 线程暂停被打断时抛出此异常
     */
    public static void sleep(Duration duration) {
        Asserts.notNull(duration);

        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new UncheckedInterruptedException(e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 线程暂停被中断异常
     *
     * @author 应卓
     * @since 1.0.0
     */
    public static class UncheckedInterruptedException extends RuntimeException {
        public UncheckedInterruptedException(InterruptedException e) {
            super(e.getMessage(), e);
        }
    }

}
