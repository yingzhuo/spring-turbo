package spring.turbo.util.concurrent;

/**
 * 当前线程相关简易工具
 *
 * @author 应卓
 * @since 3.4.0
 */
public final class CurrentThreads {

    /**
     * 私有构造方法
     */
    private CurrentThreads() {
    }

    /**
     * 获取当前线程实例
     *
     * @return 当前线程实例
     */
    public static Thread get() {
        return Thread.currentThread();
    }

    /**
     * 获取当前线程名称
     *
     * @return 当前线程名称
     */
    public static String getName() {
        return get().getName();
    }

    /**
     * 获取当前线程ID
     *
     * @return 当前线程ID
     */
    public static long getId() {
        try {
            return get().getId();
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
        return get().getState();
    }

    /**
     * 获取当前线程中断标志位
     *
     * @return 当前线程中断标志位
     */
    public static boolean isInterrupted() {
        return get().isInterrupted();
    }

}
