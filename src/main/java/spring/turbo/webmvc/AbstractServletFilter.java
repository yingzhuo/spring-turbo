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
import org.springframework.web.filter.OncePerRequestFilter;
import spring.turbo.util.CollectionUtils;
import spring.turbo.webmvc.function.RequestPredicate;
import spring.turbo.webmvc.function.RequestPredicateSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 应卓
 * @since 1.0.0
 */
public abstract class AbstractServletFilter extends OncePerRequestFilter {

    private final RequestPredicateSet skipPredicates = new RequestPredicateSet();

    @Override
    protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean next = true;
        if (!shouldSkip(request)) {
            next = doFilter(request, response);
        }
        if (next) {
            filterChain.doFilter(request, response);
        }
    }

    protected abstract boolean doFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    private boolean shouldSkip(HttpServletRequest request) {
        return skipPredicates.anyMatches(request);
    }

    public final void addSkipPredicates(@Nullable RequestPredicate predicate, @Nullable RequestPredicate... others) {
        CollectionUtils.nullSafeAdd(skipPredicates, predicate);
        CollectionUtils.nullSafeAddAll(skipPredicates, others);
    }

    public final void addSkipPredicates(@Nullable RequestPredicateSet predicates) {
        CollectionUtils.nullSafeAddAll(skipPredicates, predicates);
    }

}
