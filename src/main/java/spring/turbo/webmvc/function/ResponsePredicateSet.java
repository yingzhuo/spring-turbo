/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.function;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.1.2
 */
public class ResponsePredicateSet extends HashSet<ResponsePredicate> implements Set<ResponsePredicate> {

    public boolean anyMatches(HttpServletResponse response) {
        for (ResponsePredicate predicate : this) {
            if (predicate.test(response)) {
                return true;
            }
        }
        return false;
    }

    public boolean allMatches(HttpServletResponse response) {
        for (ResponsePredicate predicate : this) {
            if (!predicate.test(response)) {
                return false;
            }
        }
        return true;
    }

    public boolean noneMatches(HttpServletResponse response) {
        for (ResponsePredicate predicate : this) {
            if (predicate.test(response)) {
                return false;
            }
        }
        return true;
    }

}
