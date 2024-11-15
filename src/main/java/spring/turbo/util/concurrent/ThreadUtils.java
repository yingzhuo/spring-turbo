package spring.turbo.util.concurrent;

import org.springframework.util.Assert;
import spring.turbo.util.RandomUtils;

import java.time.Duration;

import static spring.turbo.util.RandomUtils.nextLong;

/**
 * 线程暂停工具
 *
 * @author 应卓
 * @since 3.3.5
 */
public final class ThreadUtils {

    /**
     * 私有构造方法
     */
    private ThreadUtils() {
    }

    /**
     * 获取当前线程实例
     *
     * @return 当前线程实例
     */
    public static Thread getCurrent() {
        return Thread.currentThread();
    }

    /**
     * 获取当前线程名称
     *
     * @return 当前线程名称
     */
    public static String getName() {
        return getCurrent().getName();
    }

    /**
     * 获取当前线程ID
     *
     * @return 当前线程ID
     */
    public static long getId() {
        try {
            return getCurrent().getId();
        } catch (Exception e) {
            return -1L;
        }
    }

    /**
     * 获取当前线程状态
     *
     * @return 当前线程状态
     */
    public static Thread.State getState() {
        return getCurrent().getState();
    }

    /**
     * 获取当前线程中断标志位
     *
     * @return 当前线程中断标志位
     */
    public static boolean isInterrupted() {
        return getCurrent().isInterrupted();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 暂停当前线程
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
     * 暂停当前线程，如果在暂停期间被中断，则重置现成暂停标记为true
     *
     * @param duration 暂停时间
     */
    public static void sleepAndResetInterruptFlagWhenInterrupted(Duration duration) {
        Assert.notNull(duration, "duration is required");

        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 暂停当前线程随机n秒
     *
     * @param startInclusive 最小休息秒数 (包含)
     * @param endExclusive   最大休息秒数 (不包含)
     * @see spring.turbo.util.RandomUtils
     */
    public static void sleepRandomSeconds(long startInclusive, long endExclusive) {
        sleep(Duration.ofSeconds(nextLong(startInclusive, endExclusive)));
    }

    /**
     * 暂停当前线程随机n秒, 如果在暂停期间被中断, 则重置现成暂停标记为true
     *
     * @param startInclusive 最小休息秒数 (包含)
     * @param endExclusive   最大休息秒数 (不包含)
     * @see RandomUtils
     */
    public static void sleepRandomSecondsAndResetInterruptFlagWhenInterrupted(long startInclusive, long endExclusive) {
        try {
            Thread.sleep(nextLong(startInclusive, endExclusive) * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
