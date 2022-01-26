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
import java.util.stream.Stream;

/**
 * @author 应卓
 * @since 1.0.12
 */
public final class PathWalker {

    /**
     * 私有构造方法
     */
    private PathWalker() {
        super();
    }

    public static Stream<Path> list(Path path, int maxDepth, @Nullable PathPredicate predicate) {
        Asserts.notNull(path);

        if (predicate == null) {
            predicate = PathPredicateFactories.alwaysTrue();
        }

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
