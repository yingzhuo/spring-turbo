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
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class RequestPredicateFactories {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 私有构造方法
     */
    private RequestPredicateFactories() {
        super();
    }

    public static RequestPredicate methodMatches(final HttpMethod... methods) {
        Assert.noNullElements(methods, "methods is null or has null element(s)");
        return request -> RequestPredicateFactories.matchMethods(request, methods);
    }

    public static RequestPredicate hasParameter(final String parameterName) {
        Assert.hasText(parameterName, "parameterName is blank");
        return request -> request.getParameterMap().containsKey(parameterName);
    }

    public static RequestPredicate parameterValueRegexMatches(final String parameterName, final String parameterValueRegex) {
        Assert.hasText(parameterName, "parameterName is blank");
        Assert.hasText(parameterValueRegex, "parameterValueRegex is blank");

        return request -> {
            String value = request.getParameter(parameterName);
            if (!StringUtils.hasText(value)) return false;
            return value.matches(parameterValueRegex);
        };
    }

    public static RequestPredicate hasAttribute(final String attributeName) {
        Assert.hasText(attributeName, "attributeName is blank");
        return request -> request.getAttribute(attributeName) != null;
    }

    public static RequestPredicate attributeValueRegexMatches(final String attributeName, final String attributeValueRegex) {
        Assert.hasText(attributeName, "attributeName is blank");
        Assert.hasText(attributeValueRegex, "attributeValueRegex is blank");

        return request -> {
            Object valueObj = request.getAttribute(attributeName);
            if (valueObj == null) {
                return false;
            }
            if (!(valueObj instanceof CharSequence)) {
                return false;
            }
            return valueObj.toString().matches(attributeValueRegex);
        };
    }

    public static RequestPredicate pathAntStyleMatches(final String pattern) {
        Assert.hasText(pattern, "pattern is blank");
        return request -> ANT_PATH_MATCHER.match(pattern, request.getRequestURI());
    }

    public static RequestPredicate pathRegexMatches(final String regex) {
        Assert.hasText(regex, "regex is blank");
        return request -> request.getRequestURI().matches(regex);
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

    public static RequestPredicate contextPathRegexMatches(final String contextPathPattern) {
        Assert.hasText(contextPathPattern, "contextPathPattern is blank");
        return request -> {
            String contextPath = request.getContextPath();
            if (contextPath == null) return false;
            return contextPath.matches(contextPathPattern);
        };
    }

    public static RequestPredicate isSecure() {
        return HttpServletRequest::isSecure;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestPredicate delegating(final Predicate<HttpServletRequest> predicate) {
        Assert.notNull(predicate, "predicate is null");
        return new Delegating(predicate);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestPredicate alwaysTrue() {
        return request -> true;
    }

    public static RequestPredicate alwaysFalse() {
        return request -> false;
    }

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

    public static RequestPredicate or(final RequestPredicate p1, final RequestPredicate p2) {
        Assert.notNull(p1, "p1 is null");
        Assert.notNull(p2, "p2 is null");
        return any(p1, p2);
    }

    public static RequestPredicate and(final RequestPredicate p1, final RequestPredicate p2) {
        Assert.notNull(p1, "p1 is null");
        Assert.notNull(p2, "p2 is null");
        return all(p1, p2);
    }

    public static RequestPredicate xor(final RequestPredicate p1, final RequestPredicate p2) {
        Assert.notNull(p1, "p1 is null");
        Assert.notNull(p2, "p2 is null");
        return request -> p1.test(request) ^ p2.test(request);
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

    // -----------------------------------------------------------------------------------------------------------------

    private static boolean matchMethods(HttpServletRequest request, HttpMethod[] methodArray) {
        return Stream.of(methodArray).anyMatch(m -> m.matches(request.getMethod()));
    }

}
