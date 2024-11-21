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

    private static final Predicate<Thread> ANY = t -> true;
    private static final Predicate<Thread> IS_DAEMON = Thread::isDaemon;
    private static final Predicate<Thread> NON_DAEMON = Predicate.not(IS_DAEMON);

    /**
     * 私有构造方法
     */
    private ThreadUtils() {
    }

    public static Set<Thread> getNonDaemonThreads() {
        return getThreads(NON_DAEMON);
    }

    public static Set<Thread> getDaemonThreads() {
        return getThreads(IS_DAEMON);
    }

    public static Set<Thread> getAllThreads() {
        return getThreads(null);
    }

    public static Set<Thread> getThreads(@Nullable Predicate<Thread> threadPredicate) {
        var predicate = Objects.requireNonNullElse(threadPredicate, ANY);

        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableSet());
    }

    public static Optional<Thread> getById(long id) {
        return getById(id, false);
    }

    public static Optional<Thread> getById(long id, boolean includingDaemonThread) {
        if (id < 0) {
            return Optional.empty();
        }

        // @formatter:off
        var p = includingDaemonThread ? ANY : NON_DAEMON;

        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .filter(p)
                .filter(t -> t.getId() == id)
                .findFirst();
        // @formatter:on
    }

    public static Optional<Thread> getByName(String name) {
        return getByName(name, false);
    }

    public static Optional<Thread> getByName(String name, boolean includingDaemonThread) {
        Assert.hasText(name, "name is null or blank");

        // @formatter:off
        var p = includingDaemonThread ? ANY : NON_DAEMON;

        return Thread.getAllStackTraces()
                .keySet()
                .stream()
                .filter(p)
                .filter(t -> t.getName().equals(name))
                .findFirst();
        // @formatter:on
    }

}
