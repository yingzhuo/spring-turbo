package spring.turbo.util.concurrent;

import org.springframework.util.Assert;

import java.util.concurrent.Semaphore;

/**
 * {@link Semaphore} 相关工具
 *
 * @author 应卓
 * @see CountDownLatchUtils
 * @since 3.3.5
 */
public final class SemaphoreUtils {

    /**
     * 私有构造方法
     */
    private SemaphoreUtils() {
    }

    /**
     * 尝试获取信号量
     *
     * @param semaphore 信号量
     */
    public static void acquire(Semaphore semaphore) {
        acquire(semaphore, false);
    }

    /**
     * 尝试获取信号量
     *
     * @param semaphore                         信号量
     * @param resetInterruptFlagWhenInterrupted 线程暂停被打断时是否重置中断标志位
     */
    public static void acquire(Semaphore semaphore, boolean resetInterruptFlagWhenInterrupted) {
        Assert.notNull(semaphore, "semaphore is null");

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            if (resetInterruptFlagWhenInterrupted) {
                Thread.currentThread().interrupt();
            } else {
                throw new UncheckedInterruptedException(e);
            }
        }
    }

}
