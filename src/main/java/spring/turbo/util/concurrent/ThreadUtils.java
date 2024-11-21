package spring.turbo.util.concurrent;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * {@link Thread} 相关工具
 *
 * @author 应卓
 * @see CurrentThreadUtils
 * @since 3.3.5
 */
public final class ThreadUtils {

    /**
     * 私有构造方法
     */
    private ThreadUtils() {
    }

    public static Set<Thread> getAllNonDaemonThreads() {
        return getAllThreads(Predicate.not(Thread::isDaemon));
    }

    public static Set<Thread> getAllDaemonThreads() {
        return getAllThreads(Thread::isDaemon);
    }

    public static Set<Thread> getAllThreads() {
        return getAllThreads(__ -> true);
    }

    public static Set<Thread> getAllThreads(@Nullable Predicate<Thread> threadPredicate) {
        final var predicate = Objects.requireNonNullElse(threadPredicate, __ -> true);

        // @formatter:off
        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableSet());
        // @formatter:on
    }

}
