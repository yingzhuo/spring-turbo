/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.function;

import spring.turbo.util.Asserts;
import spring.turbo.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 应卓
 * @since 1.1.2
 */
public final class ResponsePredicateFactories {

    /**
     * 私有构造方法
     */
    private ResponsePredicateFactories() {
        super();
    }

    public static ResponsePredicate alwaysTrue() {
        return response -> true;
    }

    public static ResponsePredicate alwaysFalse() {
        return response -> false;
    }

    public static ResponsePredicate not(ResponsePredicate predicate) {
        return new Not(predicate);
    }

    public static ResponsePredicate any(ResponsePredicate... predicates) {
        return new Any(predicates);
    }

    public static ResponsePredicate all(ResponsePredicate... predicates) {
        return new All(predicates);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static class Not implements ResponsePredicate {

        private final ResponsePredicate predicate;

        public Not(ResponsePredicate predicate) {
            Asserts.notNull(predicate);
            this.predicate = predicate;
        }

        @Override
        public boolean test(HttpServletResponse response) {
            return !predicate.test(response);
        }
    }

    private static class Any implements ResponsePredicate {

        private final List<ResponsePredicate> list = new LinkedList<>();

        public Any(ResponsePredicate... list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean test(HttpServletResponse response) {
            for (ResponsePredicate predicate : list) {
                if (predicate.test(response)) return true;
            }
            return false;
        }
    }

    private static class All implements ResponsePredicate {
        private final List<ResponsePredicate> list = new LinkedList<>();

        public All(ResponsePredicate... list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean test(HttpServletResponse response) {
            for (ResponsePredicate predicate : list) {
                if (!predicate.test(response)) return false;
            }
            return true;
        }
    }

}
