package spring.turbo.util.concurrent;

import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author 应卓
 * @since 3.4.0
 */
public final class ThreadPoolFactories {

    /**
     * 私有构造方法
     */
    private ThreadPoolFactories() {
    }

    /**
     * 创建新的线程池
     *
     * @param corePoolSize      核心池容量
     * @param maximumPoolSize   最大容量
     * @param keepAlive         现成最大空闲时间
     * @param blockingQueueSize 阻塞队列容量
     * @return 线程池实例
     */
    public static ExecutorService create(int corePoolSize, int maximumPoolSize, Duration keepAlive, int blockingQueueSize) {
        return create(corePoolSize, maximumPoolSize, keepAlive, blockingQueueSize, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 创建新的线程池
     *
     * @param corePoolSize             核心池容量
     * @param maximumPoolSize          最大容量
     * @param keepAlive                现成最大空闲时间
     * @param blockingQueueSize        阻塞队列容量
     * @param rejectedExecutionHandler 拒绝策略
     * @return 线程池实例
     */
    public static ExecutorService create(int corePoolSize, int maximumPoolSize, Duration keepAlive, int blockingQueueSize, @Nullable RejectedExecutionHandler rejectedExecutionHandler) {
        var pool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAlive.toMillis(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(blockingQueueSize),
                Executors.defaultThreadFactory()
        );
        Optional.ofNullable(rejectedExecutionHandler).ifPresent(pool::setRejectedExecutionHandler);
        return pool;
    }

}
