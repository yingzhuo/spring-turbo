/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc;

import org.springframework.lang.Nullable;
import spring.turbo.util.CollectionUtils;
import spring.turbo.webmvc.function.RequestPredicate;
import spring.turbo.webmvc.function.RequestPredicateSet;

/**
 * @author 应卓
 * @since 1.1.3
 */
@FunctionalInterface
public interface SkippableFilter {

    public void addSkipPredicates(@Nullable RequestPredicateSet predicates);

    public default void addSkipPredicates(@Nullable RequestPredicate predicate, @Nullable RequestPredicate... others) {
        final RequestPredicateSet set = new RequestPredicateSet();
        CollectionUtils.nullSafeAdd(set, predicate);
        CollectionUtils.nullSafeAddAll(set, others);
        if (!set.isEmpty()) {
            addSkipPredicates(set);
        }
    }

}
