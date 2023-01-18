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
import spring.turbo.util.Asserts;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static spring.turbo.util.ArrayUtils.size;

/**
 * @author 应卓
 * @since 2.0.9
 */
public final class RequestLikePredicateFactories {

    /**
     * 私有构造方法
     */
    private RequestLikePredicateFactories() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestLikePredicate alwaysTrue() {
        return request -> true;
    }

    public static RequestLikePredicate alwaysFalse() {
        return request -> false;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestLikePredicate not(RequestLikePredicate predicate) {
        Asserts.notNull(predicate);
        return request -> !predicate.test(request);
    }

    public static RequestLikePredicate or(RequestLikePredicate... predicates) {
        Asserts.notNull(predicates);
        Asserts.notEmpty(predicates);
        Asserts.noNullElements(predicates);

        if (size(predicates) == 1) {
            return predicates[0];
        } else {
            return request -> {
                for (var predicate : predicates) {
                    if (predicate.test(request)) {
                        return true;
                    }
                }
                return false;
            };
        }
    }

    public static RequestLikePredicate and(RequestLikePredicate... predicates) {
        Asserts.notNull(predicates);
        Asserts.notEmpty(predicates);
        Asserts.noNullElements(predicates);

        if (size(predicates) == 1) {
            return predicates[0];
        } else {
            return request -> {
                for (var predicate : predicates) {
                    if (!predicate.test(request)) {
                        return false;
                    }
                }
                return true;
            };
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestLikePredicate isSecure() {
        return request -> request != null && request.isSecure();
    }

    public static RequestLikePredicate isNotSecure() {
        return request -> request != null && !request.isSecure();
    }

    public static RequestLikePredicate method(HttpMethod method) {
        Asserts.notNull(method);
        return request -> {
            if (request == null) {
                return false;
            }
            return method.matches(request.getMethod());
        };
    }

    public static RequestLikePredicate hasQueryParameter(String parameter) {
        Asserts.hasText(parameter);
        return request -> {
            if (request == null) {
                return false;
            }
            return request.getParameter(parameter) != null;
        };
    }

    public static RequestLikePredicate hasNotQueryParameter(String parameter) {
        Asserts.hasText(parameter);
        return request -> {
            if (request == null) {
                return false;
            }
            return request.getParameter(parameter) == null;
        };
    }

    public static RequestLikePredicate queryParameterMatchRegex(String parameterName, String regex) {
        Asserts.hasText(parameterName);
        Asserts.hasText(regex);
        return request -> {
            if (request == null) {
                return false;
            }
            final String parameterValue = request.getParameter(parameterName);
            if (parameterValue == null) {
                return false;
            }
            return Pattern.matches(regex, parameterValue);
        };
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestLikePredicate hasHttpHeader(String headerName) {
        Asserts.hasText(headerName);
        return request -> {
            if (request == null) {
                return false;
            }
            return request.getHeader(headerName) != null;
        };
    }

    public static RequestLikePredicate hasNotHttpHeader(String headerName) {
        Asserts.hasText(headerName);
        return request -> {
            if (request == null) {
                return false;
            }
            return request.getHeader(headerName) == null;
        };
    }

    public static RequestLikePredicate httpHeaderMatchRegex(String headerName, String regex) {
        Asserts.hasText(headerName);
        Asserts.hasText(regex);

        return request -> {
            if (request == null) {
                return false;
            }
            final String headerValue = request.getHeader(headerName);
            if (headerValue == null) {
                return false;
            }
            return Pattern.matches(regex, headerValue);
        };
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static RequestLikePredicate matchAntPatterns(HttpMethod method, String... patterns) {
        return and(
                method(method),
                matchAntPatterns(patterns)
        );
    }

    public static RequestLikePredicate matchAntPatterns(String... patterns) {
        Asserts.notNull(patterns);
        Asserts.notEmpty(patterns);
        Asserts.noNullElements(patterns);

        final var patternMatcher = new AntPathMatcher();

        if (size(patterns) == 1) {
            return request -> {
                if (request == null) {
                    return false;
                }
                return patternMatcher.match(patterns[0], request.getRequestURI());
            };
        } else {
            return or(
                    Stream.of(patterns)
                            .map(pattern -> (RequestLikePredicate) request -> {
                                if (request == null) {
                                    return false;
                                }
                                return patternMatcher.match(pattern, request.getRequestURI());
                            })
                            .toList()
                            .toArray(new RequestLikePredicate[0])
            );
        }
    }

}
