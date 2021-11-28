/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class TokenResolverBuilder {

    private final List<TokenResolver> list = new ArrayList<>();

    TokenResolverBuilder() {
    }

    public TokenResolverBuilder add(TokenResolver... resolvers) {
        if (resolvers != null && resolvers.length != 0) {
            list.addAll(Arrays.asList(resolvers));
        }
        return this;
    }

    public TokenResolverBuilder add(Collection<TokenResolver> resolvers) {
        if (resolvers != null && !resolvers.isEmpty()) {
            list.addAll(resolvers);
        }
        return this;
    }

    public TokenResolverBuilder fromHttpHeader(String headName) {
        list.add(new HeaderTokenResolver(headName));
        return this;
    }

    public TokenResolverBuilder fromHttpHeader(String headName, String prefix) {
        list.add(new HeaderTokenResolver(headName, prefix));
        return this;
    }

    public TokenResolverBuilder fromQuery(String paramName) {
        list.add(new QueryTokenResolver(paramName));
        return this;
    }

    public TokenResolverBuilder fromQuery(String paramName, String prefix) {
        list.add(new QueryTokenResolver(paramName, prefix));
        return this;
    }

    public TokenResolverBuilder bearerToken() {
        list.add(new HeaderTokenResolver(HttpHeaders.AUTHORIZATION, "Bearer "));
        return this;
    }

    public TokenResolverBuilder basicToken() {
        list.add(new HeaderTokenResolver(HttpHeaders.AUTHORIZATION, "Basic "));
        return this;
    }

    public TokenResolverBuilder fixed(String token) {
        list.add(new FixedStringTokenResolver(token));
        return this;
    }

    public TokenResolver build() {
        if (list.isEmpty()) {
            return NullTokenResolver.getInstance();
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return new CompositeTokenResolver(list);
    }

}
