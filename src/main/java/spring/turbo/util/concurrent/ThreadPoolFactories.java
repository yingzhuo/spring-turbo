package spring.turbo.util.concurrent;

import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.concurrent.*;

/**
 * 简单工具用于创建线程池
 *
 * @author 应卓
 * @see Thread
 * @see Runnable
 * @see Callable
 * @see FutureTask
 * @see CompletableFuture
 * @see org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean
 * @see org.springframework.scheduling.concurrent.ForkJoinPoolFactoryBean
 * @since 3.3.5
 */
public final class ThreadPoolFactories {

    /**
     * 私有构造方法
     */
    private ThreadPoolFactories() {
    }

    /**
     * 创建线程数固定的线程池 <br>
     * 本方法不适合用在严肃的生产环境
     *
     * @param n 　线程数
     * @return 线程池实例
     */
    public static ExecutorService createFixed(int n) {
        return Executors.newFixedThreadPool(n);
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
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAlive.toMillis(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(blockingQueueSize),
                Executors.defaultThreadFactory(),
                rejectedExecutionHandler != null ? rejectedExecutionHandler : new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
