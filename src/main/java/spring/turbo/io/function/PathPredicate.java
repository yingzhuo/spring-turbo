/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io.function;

import spring.turbo.util.Asserts;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * @author 应卓
 * @since 1.0.12
 */
@FunctionalInterface
public interface PathPredicate extends Predicate<Path> {

    public static PathPredicate from(Predicate<Path> p) {
        Asserts.notNull(p);
        return p::test;
    }

    @Override
    public boolean test(Path path);

}
