package spring.turbo.util.concurrent;

import org.springframework.util.Assert;

import java.util.concurrent.CountDownLatch;

/**
 * {@link CountDownLatchUtils}
 *
 * @author 应卓
 * @since 3.3.5
 */
public final class CountDownLatchUtils {

    /**
     * 私有构造方法
     */
    private CountDownLatchUtils() {
    }

    /**
     * 等待倒计数结束
     *
     * @param countDownLatch countDownLatch 实例
     */
    public static void await(CountDownLatch countDownLatch) {
        await(countDownLatch, false);
    }

    /**
     * 等待倒计数结束
     *
     * @param countDownLatch                    countDownLatch实例
     * @param resetInterruptFlagWhenInterrupted 线程暂停被打断时是否重置中断标志位
     */
    public static void await(CountDownLatch countDownLatch, boolean resetInterruptFlagWhenInterrupted) {
        Assert.notNull(countDownLatch, "countDownLatch is null");

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            if (resetInterruptFlagWhenInterrupted) {
                Thread.currentThread().interrupt();
            } else {
                throw new UncheckedInterruptedException(e);
            }
        }
    }

    /**
     * 倒计数
     *
     * @param countDownLatch countDownLatch实例
     */
    public static void countDown(CountDownLatch countDownLatch) {
        Assert.notNull(countDownLatch, "countDownLatch is null");
        countDownLatch.countDown();
    }

}
