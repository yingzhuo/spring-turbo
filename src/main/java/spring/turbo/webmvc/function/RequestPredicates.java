/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.function;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class RequestPredicates {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 私有构造方法
     */
    private RequestPredicates() {
        super();
    }

    public static RequestPredicate method(final HttpMethod... methods) {
        Assert.noNullElements(methods, "methods is null or has null element(s)");

        final Set<HttpMethod> methodSet = Stream.of(methods)
                .collect(Collectors.toSet());
        return request -> methodSet.stream().anyMatch(method -> method.matches(request.getMethod()));
    }

    public static RequestPredicate antStylePathMatches(final String pattern) {
        Assert.hasText(pattern, "pattern is blank");
        return request -> ANT_PATH_MATCHER.match(pattern, request.getRequestURI());
    }

    public static RequestPredicate antStylePathMatches(final String pattern, final HttpMethod... methods) {
        Assert.hasText(pattern, "pattern is blank");
        Assert.noNullElements(methods, "methods is null or has null element(s)");

        final Set<HttpMethod> methodSet = Stream.of(methods)
                .collect(Collectors.toSet());

        return request -> ANT_PATH_MATCHER.match(pattern, request.getRequestURI())
                &&
                methodSet.stream().anyMatch(method -> method.matches(request.getMethod()));
    }

    public static RequestPredicate regexPathMatches(final String regex) {
        Assert.hasText(regex, "regex is blank");
        return request -> request.getRequestURI().matches(regex);
    }

    public static RequestPredicate regexPathMatches(final String regex, final HttpMethod... methods) {
        Assert.hasText(regex, "regex is blank");
        Assert.noNullElements(methods, "methods is null or has null element(s)");

        final Set<HttpMethod> methodSet = Stream.of(methods)
                .collect(Collectors.toSet());

        return request -> request.getRequestURI().matches(regex)
                &&
                methodSet.stream().anyMatch(method -> method.matches(request.getMethod()));
    }

    public static RequestPredicate hasHeader(final String headerName) {
        Assert.hasText(headerName, "headerName is blank");
        return request -> StringUtils.hasText(request.getHeader(headerName));
    }

    public static RequestPredicate headerValueRegexMatches(final String headerName, final String headerValueRegex) {
        Assert.hasText(headerName, "headerName is blank");
        Assert.hasText(headerValueRegex, "headerValueRegex is blank");

        return request -> {
            String value = request.getHeader(headerName);
            if (!StringUtils.hasText(value)) return false;
            return value.matches(headerValueRegex);
        };
    }

    public static RequestPredicate delegating(final Predicate<HttpServletRequest> predicate) {
        Assert.notNull(predicate, "predicate is null");
        return new Delegating(predicate);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestPredicate not(final RequestPredicate predicate) {
        Assert.notNull(predicate, "predicate is null");
        return new Not(predicate);
    }

    public static RequestPredicate any(final RequestPredicate... predicates) {
        Assert.noNullElements(predicates, "predicates is null or has null element");
        return new Any(predicates);
    }

    public static RequestPredicate all(final RequestPredicate... predicates) {
        Assert.noNullElements(predicates, "predicates is null or has null element");
        return new All(predicates);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static class Not implements RequestPredicate {
        private final Predicate<HttpServletRequest> predicate;

        public Not(Predicate<HttpServletRequest> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean test(HttpServletRequest request) {
            return !predicate.test(request);
        }
    }

    private static class Any implements RequestPredicate {
        private final List<RequestPredicate> list;

        public Any(RequestPredicate... list) {
            this.list = Arrays.asList(list);
        }

        @Override
        public boolean test(HttpServletRequest request) {
            for (RequestPredicate predicate : list) {
                if (predicate.test(request)) return true;
            }
            return false;
        }
    }

    private static class All implements RequestPredicate {
        private final List<RequestPredicate> list;

        public All(RequestPredicate... list) {
            this.list = Arrays.asList(list);
        }

        @Override
        public boolean test(HttpServletRequest request) {
            for (RequestPredicate predicate : list) {
                if (!predicate.test(request)) return false;
            }
            return true;
        }
    }

    private static class Delegating implements RequestPredicate {
        private final Predicate<HttpServletRequest> predicate;

        public Delegating(Predicate<HttpServletRequest> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean test(HttpServletRequest request) {
            return predicate.test(request);
        }
    }

}
