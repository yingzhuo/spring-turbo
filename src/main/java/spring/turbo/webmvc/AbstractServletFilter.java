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
import spring.turbo.webmvc.function.RequestPredicate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

import static spring.turbo.util.CharsetPool.UTF_8_VALUE;

/**
 * @author 应卓
 * @since 1.0.0
 */
public abstract class AbstractServletFilter extends OncePerRequestFilter {

    private final SkipPredicateSet skipPredicates = new SkipPredicateSet();

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
        return skipPredicates.shouldSkip(request);
    }

    public final void addSkipPredicates(@Nullable RequestPredicate predicate, @Nullable RequestPredicate... others) {
        if (predicate != null) {
            skipPredicates.add(predicate);
        }

        if (others != null) {
            for (RequestPredicate other : others) {
                if (other != null) {
                    skipPredicates.add(other);
                }
            }
        }
    }

    protected final void writeJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding(UTF_8_VALUE);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    protected final void writeText(HttpServletResponse response, String text) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding(UTF_8_VALUE);
        response.getWriter().print(text);
        response.getWriter().flush();
    }

    protected final void writeHtml(HttpServletResponse response, String html) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(UTF_8_VALUE);
        response.getWriter().print(html);
        response.getWriter().flush();
    }

    private static class SkipPredicateSet extends HashSet<RequestPredicate> {
        boolean shouldSkip(HttpServletRequest request) {
            for (RequestPredicate predicate : this) {
                if (predicate.test(request)) {
                    return true;
                }
            }
            return false;
        }
    }

}
