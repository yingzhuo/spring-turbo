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

    public static Thread get() {
        return Thread.currentThread();
    }

    public static String getName() {
        return get().getName();
    }

    public static long getId() {
        try {
            return get().getId();
        } catch (Exception e) {
            return -1L;
        }
    }

}
