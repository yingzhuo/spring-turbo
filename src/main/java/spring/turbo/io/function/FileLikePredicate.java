/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io.function;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import spring.turbo.io.RichResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * @author 应卓
 * @see FileLikePredicateFactories
 * @since 2.0.8
 */
public interface FileLikePredicate extends Predicate<File> {

    @Override
    public boolean test(@Nullable File file);

    // -----------------------------------------------------------------------------------------------------------------

    public default Predicate<File> asFilePredicate() {
        return this;
    }

    public default Predicate<Path> asPathPredicate() {
        return path -> this.test(path != null ? path.toFile() : null);
    }

    public default Predicate<Resource> asResourcePredicate() {
        return resource -> {
            if (resource == null)
                return false;
            try {
                var file = resource.getFile();
                return this.test(file);
            } catch (IOException e) {
                return false;
            }
        };
    }

    public default Predicate<RichResource> asRichResourcePredicate() {
        return resource -> {
            if (resource == null)
                return false;
            try {
                var file = resource.getFile();
                return this.test(file);
            } catch (IOException e) {
                return false;
            }
        };
    }

}
