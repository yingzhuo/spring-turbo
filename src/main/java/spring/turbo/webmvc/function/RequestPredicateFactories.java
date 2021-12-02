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
import org.springframework.util.StringUtils;
import spring.turbo.util.Asserts;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
        Asserts.notNull(methods);
        Asserts.noNullElements(methods);
        return request -> RequestPredicateFactories.matchMethods(request, methods);
    }

    public static RequestPredicate hasParameter(final String parameterName) {
        Asserts.hasText(parameterName);
        return request -> request.getParameterMap().containsKey(parameterName);
    }

    public static RequestPredicate parameterValueRegexMatches(final String parameterName, final String parameterValueRegex) {
        Asserts.hasText(parameterName);
        Asserts.hasText(parameterValueRegex);

        return request -> {
            String value = request.getParameter(parameterName);
            if (!StringUtils.hasText(value)) return false;
            return value.matches(parameterValueRegex);
        };
    }

    public static RequestPredicate hasAttribute(final String attributeName) {
        Asserts.hasText(attributeName);
        return request -> request.getAttribute(attributeName) != null;
    }

    public static RequestPredicate attributeValueRegexMatches(final String attributeName, final String attributeValueRegex) {
        Asserts.hasText(attributeName);
        Asserts.hasText(attributeValueRegex);

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
        Asserts.hasText(pattern);
        return request -> ANT_PATH_MATCHER.match(pattern, request.getRequestURI());
    }

    public static RequestPredicate pathRegexMatches(final String regex) {
        Asserts.hasText(regex);
        return request -> request.getRequestURI().matches(regex);
    }

    public static RequestPredicate hasHeader(final String headerName) {
        Asserts.hasText(headerName);
        return request -> StringUtils.hasText(request.getHeader(headerName));
    }

    public static RequestPredicate headerValueRegexMatches(final String headerName, final String headerValueRegex) {
        Asserts.hasText(headerName);
        Asserts.hasText(headerValueRegex);

        return request -> {
            String value = request.getHeader(headerName);
            if (!StringUtils.hasText(value)) return false;
            return value.matches(headerValueRegex);
        };
    }

    public static RequestPredicate contextPathRegexMatches(final String contextPathPattern) {
        Asserts.hasText(contextPathPattern);
        return request -> {
            String contextPath = request.getContextPath();
            if (contextPath == null) return false;
            return contextPath.matches(contextPathPattern);
        };
    }

    public static RequestPredicate isSecure() {
        return HttpServletRequest::isSecure;
    }

    public static RequestPredicate localeMatches(final Locale locale) {
        return request -> request.getLocale() == locale;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestPredicate delegating(final Predicate<HttpServletRequest> predicate) {
        Asserts.notNull(predicate);
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
        Asserts.notNull(predicate);
        return new Not(predicate);
    }

    public static RequestPredicate any(final RequestPredicate... predicates) {
        Asserts.notEmpty(predicates);
        Asserts.noNullElements(predicates);
        return new Any(predicates);
    }

    public static RequestPredicate all(final RequestPredicate... predicates) {
        Asserts.notEmpty(predicates);
        Asserts.noNullElements(predicates);
        return new All(predicates);
    }

    public static RequestPredicate or(final RequestPredicate p1, final RequestPredicate p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);
        return any(p1, p2);
    }

    public static RequestPredicate and(final RequestPredicate p1, final RequestPredicate p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);
        return all(p1, p2);
    }

    public static RequestPredicate xor(final RequestPredicate p1, final RequestPredicate p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);
        return request -> p1.test(request) ^ p2.test(request);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static boolean matchMethods(HttpServletRequest request, HttpMethod[] methodArray) {
        return Stream.of(methodArray).anyMatch(m -> m.matches(request.getMethod()));
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

    // -----------------------------------------------------------------------------------------------------------------

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
