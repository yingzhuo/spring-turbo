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

    public static ExecutorService create(int corePoolSize, int maximumPoolSize, Duration keepAlive, int queueSize) {
        return create(corePoolSize, maximumPoolSize, keepAlive, queueSize, new ThreadPoolExecutor.AbortPolicy());
    }

    public static ExecutorService create(int corePoolSize, int maximumPoolSize, Duration keepAlive, int queueSize, @Nullable RejectedExecutionHandler rejectedExecutionHandler) {
        var pool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAlive.toMillis(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                Executors.defaultThreadFactory()
        );
        Optional.ofNullable(rejectedExecutionHandler).ifPresent(pool::setRejectedExecutionHandler);
        return pool;
    }

}
