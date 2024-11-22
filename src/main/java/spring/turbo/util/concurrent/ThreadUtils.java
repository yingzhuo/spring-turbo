package spring.turbo.util.concurrent;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Optional;
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

    /**
     * 获取虚拟机实例中所有非守护线程
     *
     * @return 虚拟机实例中所有非守护线程
     */
    public static Set<Thread> getNonDaemonThreads() {
        return getThreads(AsyncVoid.NON_DAEMON);
    }

    /**
     * 获取虚拟机实例中所有守护线程
     *
     * @return 虚拟机实例中所有守护线程
     */
    public static Set<Thread> getDaemonThreads() {
        return getThreads(AsyncVoid.IS_DAEMON);
    }

    /**
     * 获取虚拟机实例中所有线程
     *
     * @return 虚拟机实例中所有守护线程
     */
    public static Set<Thread> getAllThreads() {
        return getThreads(AsyncVoid.ANY);
    }

    /**
     * 获取虚拟机实例中的线程
     *
     * @param filter 过滤器
     * @return 虚拟机实例中的线程
     */
    public static Set<Thread> getThreads(@Nullable Predicate<Thread> filter) {
        filter = Objects.requireNonNullElse(filter, AsyncVoid.ANY);

        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .filter(filter)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * 通过ID获取线程 (不包含守护线程)
     *
     * @param id 线程ID
     * @return 结果
     */
    public static Optional<Thread> getById(long id) {
        return getById(id, false);
    }

    /**
     * 通过ID获取线程
     *
     * @param id                    线程ID
     * @param includingDaemonThread 是否包含守护线程
     * @return 结果
     */
    public static Optional<Thread> getById(long id, boolean includingDaemonThread) {
        if (id < 0) {
            return Optional.empty();
        }

        var p = includingDaemonThread ? AsyncVoid.ANY : AsyncVoid.NON_DAEMON;

        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .filter(p)
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    /**
     * 通过名称获取线程 (不包含守护线程)
     *
     * @param name 线程名称
     * @return 结果
     */
    public static Optional<Thread> getByName(String name) {
        return getByName(name, false);
    }

    /**
     * 通过名称获取线程
     *
     * @param name                  线程名称
     * @param includingDaemonThread 是否包含守护线程
     * @return 结果
     */
    public static Optional<Thread> getByName(String name, boolean includingDaemonThread) {
        Assert.hasText(name, "name is null or blank");

        var p = includingDaemonThread ? AsyncVoid.ANY : AsyncVoid.NON_DAEMON;

        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .filter(p)
                .filter(t -> t.getName().equals(name))
                .findFirst();
    }

    // 延迟加载
    private static class AsyncVoid {
        private static final Predicate<Thread> ANY = t -> true;
        private static final Predicate<Thread> IS_DAEMON = Thread::isDaemon;
        private static final Predicate<Thread> NON_DAEMON = Predicate.not(IS_DAEMON);
    }

}
