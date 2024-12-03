package spring.turbo.util.concurrent;

import spring.turbo.util.UUIDUtils;

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

    /**
     * 获取当前线程的一个特征字符串
     *
     * @return 当前线程的一个特征字符串
     */
    public static String getTrait() {
        return String.format("%s:%06d", SyncAvoid.PSEUDO_VM_ID, getId());
    }

    // 延迟加载
    private static class SyncAvoid {

        /**
         * 伪Java虚拟机ID
         */
        private static final String PSEUDO_VM_ID = UUIDUtils.randomUUID(true);
    }

}
