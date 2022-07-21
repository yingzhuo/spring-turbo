/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.function;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.13
 */
public final class RequestPredicateSet extends HashSet<RequestPredicate> implements Set<RequestPredicate> {

    public boolean anyMatches(HttpServletRequest request) {
        for (RequestPredicate predicate : this) {
            if (predicate.test(request)) {
                return true;
            }
        }
        return false;
    }

    public boolean allMatches(HttpServletRequest request) {
        for (RequestPredicate predicate : this) {
            if (!predicate.test(request)) {
                return false;
            }
        }
        return true;
    }

    public boolean noneMatches(HttpServletRequest request) {
        for (RequestPredicate predicate : this) {
            if (predicate.test(request)) {
                return false;
            }
        }
        return true;
    }

}
