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
import org.springframework.web.context.request.WebRequest;
import spring.turbo.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 组合型令牌解析器
 * <p>
 * 本类型解析器封装多个其他的解析器，如果之前的解析器不能解析出令牌，则尝试下一个。
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class CompositeTokenResolver implements TokenResolver {

    private final List<TokenResolver> resolvers = new ArrayList<>();

    public CompositeTokenResolver(TokenResolver... resolvers) {
        //添加并排序
        CollectionUtils.nullSafeAddAll(this.resolvers, resolvers);
        OrderComparator.sort(this.resolvers);
    }

    public CompositeTokenResolver(Collection<TokenResolver> resolvers) {
        //添加并排序
        CollectionUtils.nullSafeAddAll(this.resolvers, resolvers);
        OrderComparator.sort(this.resolvers);
    }

    public static CompositeTokenResolver of(TokenResolver... resolvers) {
        return new CompositeTokenResolver(resolvers);
    }

    @Override
    public Optional<Token> resolve(WebRequest request) {
        for (TokenResolver it : resolvers) {
            Optional<Token> op = doResolve(it, request);
            if (op.isPresent()) return op;
        }
        return Optional.empty();
    }

    // since 1.0.5
    private Optional<Token> doResolve(TokenResolver resolver, WebRequest request) {
        try {
            if (resolver != null) {
                return resolver.resolve(request);
            } else {
                return Optional.empty();
            }
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

}
