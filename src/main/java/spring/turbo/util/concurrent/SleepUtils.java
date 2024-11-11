package spring.turbo.util.concurrent;

import org.springframework.util.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
    }

    /**
     * 暂停线程
     *
     * @param duration 暂停时间
     * @throws UncheckedInterruptedException 线程暂停被打断时抛出此异常
     */
    public static void sleep(Duration duration) {
        Assert.notNull(duration, "duration is required");

        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new UncheckedInterruptedException(e);
        }
    }

    /**
     * 暂停线程
     *
     * @param time     暂停时间
     * @param timeUnit 时间单位
     * @throws UncheckedInterruptedException 线程暂停被打断时抛出此异常
     */
    public static void sleep(long time, TimeUnit timeUnit) {
        if (time <= 0) {
            return;
        }

        Assert.notNull(timeUnit, "timeUnit is required");

        try {
            Thread.sleep(timeUnit.toMillis(time));
        } catch (InterruptedException e) {
            throw new UncheckedInterruptedException(e);
        }
    }

    /**
     * 暂停现成
     *
     * @param seconds 暂停时间(秒)
     * @throws UncheckedInterruptedException 线程暂停被打断时抛出此异常
     */
    public static void sleepInSeconds(long seconds) {
        sleep(seconds, TimeUnit.SECONDS);
    }

}
