/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io.function;

import spring.turbo.io.LocalFileDescriptor;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * @author 应卓
 * @see LocalFilePredicateFactories
 * @since 1.1.1
 */
@FunctionalInterface
public interface LocalFilePredicate extends Predicate<LocalFileDescriptor> {

    public default Predicate<File> toFilePredicate() {
        return file -> this.test(LocalFileDescriptor.of(file));
    }

    public default Predicate<Path> toPathPredicate() {
        return path -> this.test(LocalFileDescriptor.of(path));
    }

}
