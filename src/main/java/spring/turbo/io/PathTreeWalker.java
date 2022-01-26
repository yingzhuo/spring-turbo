/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.lang.Nullable;
import spring.turbo.io.function.PathPredicate;
import spring.turbo.io.function.PathPredicateFactories;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @see PathPredicate
 * @see PathPredicateFactories
 * @since 1.0.12
 */
public final class PathTreeWalker {

    private static final PathPredicate DEFAULT_PREDICATE = PathPredicateFactories.alwaysTrue();

    /**
     * 私有构造方法
     */
    private PathTreeWalker() {
        super();
    }

    public static Stream<Path> list(Path path) {
        return list(path, Integer.MAX_VALUE, DEFAULT_PREDICATE);
    }

    public static Stream<Path> list(Path path, @Nullable PathPredicate predicate) {
        return list(path, Integer.MAX_VALUE, predicate);
    }

    public static Stream<Path> list(Path path, int maxDepth, @Nullable PathPredicate predicate) {
        Asserts.notNull(path);
        Asserts.isTrue(maxDepth >= 0);

        predicate = Optional.ofNullable(predicate).orElse(DEFAULT_PREDICATE);

        if (!PathUtils.isExists(path)) {
            final String msg = StringFormatter.format("'{}' not exists", path);
            throw IOExceptionUtils.toUnchecked(msg);
        }

        try {
            return Files.walk(path, maxDepth, FileVisitOption.FOLLOW_LINKS).filter(predicate);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

}
