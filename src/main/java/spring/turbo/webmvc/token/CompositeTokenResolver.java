/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import org.springframework.core.OrderComparator;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

/**
 * 组合型令牌解析器
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class CompositeTokenResolver implements TokenResolver {

    private final List<TokenResolver> resolvers = new ArrayList<>();

    public CompositeTokenResolver(TokenResolver... resolvers) {
        if (resolvers != null) {
            this.resolvers.addAll(Arrays.asList(resolvers));
        }

        if (!CollectionUtils.isEmpty(this.resolvers)) {
            OrderComparator.sort(this.resolvers);
        }
    }

    public CompositeTokenResolver(Collection<TokenResolver> resolvers) {
        if (resolvers != null && !resolvers.isEmpty()) {
            this.resolvers.addAll(resolvers);
        }
    }

    public static CompositeTokenResolver of(TokenResolver... resolvers) {
        return new CompositeTokenResolver(resolvers);
    }

    @Override
    public Optional<Token> resolve(WebRequest request) {
        for (TokenResolver it : resolvers) {
            Optional<Token> op = it.resolve(request);
            if (op.isPresent()) return op;
        }
        return Optional.empty();
    }

}
