package spring.turbo.util.concurrent;

import org.springframework.util.Assert;

import java.time.Duration;

import static spring.turbo.util.RandomUtils.nextLong;

/**
 * 当前线程相关小工具
 *
 * @author 应卓
 * @see Thread
 * @see java.util.concurrent.ThreadPoolExecutor
 * @since 3.3.5
 */
public final class CurrentThreadUtils {

    /**
     * 私有构造方法
     */
    private CurrentThreadUtils() {
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
     * 判断线程状态
     *
     * @return 判断结果
     * @see Thread.State#NEW
     */
    public static boolean isNew() {
        return getState() == Thread.State.NEW;
    }

    /**
     * 判断线程状态
     *
     * @return 判断结果
     * @see Thread.State#RUNNABLE
     */
    public static boolean isRunnable() {
        return getState() == Thread.State.RUNNABLE;
    }

    /**
     * 判断线程状态
     *
     * @return 判断结果
     * @see Thread.State#WAITING
     */
    public static boolean isWaiting() {
        return getState() == Thread.State.WAITING;
    }

    /**
     * 判断线程状态
     *
     * @return 判断结果
     * @see Thread.State#BLOCKED
     */
    public static boolean isBlocked() {
        return getState() == Thread.State.BLOCKED;
    }

    /**
     * 判断线程状态
     *
     * @return 判断结果
     * @see Thread.State#TIMED_WAITING
     */
    public static boolean isTimedWaiting() {
        return getState() == Thread.State.TIMED_WAITING;
    }

    /**
     * 判断线程状态
     *
     * @return 判断结果
     * @see Thread.State#TERMINATED
     */
    public static boolean isTerminated() {
        return getState() == Thread.State.TERMINATED;
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
     */
    public static void sleep(Duration duration) {
        sleep(duration, false);
    }

    /**
     * 暂停当前线程
     *
     * @param duration                          暂停时间
     * @param resetInterruptFlagWhenInterrupted 线程暂停被打断时是否重置中断标志位
     */
    public static void sleep(Duration duration, boolean resetInterruptFlagWhenInterrupted) {
        Assert.notNull(duration, "duration is required");

        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            if (resetInterruptFlagWhenInterrupted) {
                Thread.currentThread().interrupt();
            } else {
                throw new UncheckedInterruptedException(e);
            }
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
        sleep(Duration.ofSeconds(nextLong(startInclusive, endExclusive)), false);
    }

    /**
     * 暂停当前线程随机n秒
     *
     * @param startInclusive                    最小休息秒数 (包含)
     * @param endExclusive                      最大休息秒数 (不包含)
     * @param resetInterruptFlagWhenInterrupted 线程暂停被打断时是否重置中断标志位
     * @see spring.turbo.util.RandomUtils
     */
    public static void sleepRandomSeconds(long startInclusive, long endExclusive, boolean resetInterruptFlagWhenInterrupted) {
        sleep(Duration.ofSeconds(nextLong(startInclusive, endExclusive)), resetInterruptFlagWhenInterrupted);
    }

}
