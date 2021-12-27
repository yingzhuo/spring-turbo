/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.util.ClassUtils;
import spring.turbo.util.StringFormatter;

import java.util.*;

/**
 * @author 应卓
 * @see ScannedResult
 * @since 1.0.0
 */
public final class ScannedResultSet extends TreeSet<ScannedResult> implements Set<ScannedResult> {

    public ScannedResultSet() {
        super();
    }

    public ScannedResultSet(Collection<? extends ScannedResult> c) {
        super(c);
    }

    // since 1.0.2
    public Set<Class<?>> toClassSet() {
        return toClassSet(false);
    }

    // since 1.0.2
    public Set<Class<?>> toClassSet(boolean skipIfNotAbleToLoad) {
        final Set<Class<?>> set = new HashSet<>();

        for (ScannedResult scannedResult : this) {
            Optional<Class<?>> clzOpt = ClassUtils.forName(scannedResult.getClassName());
            if (clzOpt.isPresent()) {
                set.add(clzOpt.get());
            } else {
                if (!skipIfNotAbleToLoad) {
                    final String msg = StringFormatter.format("cannot load class: {}", scannedResult.getClassName());
                    throw new IllegalStateException(msg);
                }
            }
        }

        return Collections.unmodifiableSet(set);
    }

}
