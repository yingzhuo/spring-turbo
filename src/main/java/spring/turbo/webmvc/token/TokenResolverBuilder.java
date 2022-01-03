/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import spring.turbo.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 令牌解析器的创建器
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class TokenResolverBuilder {

    private final List<TokenResolver> list = new ArrayList<>();

    /**
     * 构造方法
     */
    TokenResolverBuilder() {
        super();
    }

    /**
     * 添加其他令牌解析器的实现
     *
     * @param resolvers 要添加的解析器
     * @return 创建器本身
     */
    public TokenResolverBuilder add(TokenResolver... resolvers) {
        CollectionUtils.nullSafeAddAll(this.list, resolvers);
        return this;
    }

    /**
     * 添加其他令牌解析器的实现
     *
     * @param resolvers 要添加的解析器
     * @return 创建器本身
     */
    public TokenResolverBuilder add(Collection<TokenResolver> resolvers) {
        CollectionUtils.nullSafeAddAll(this.list, resolvers);
        return this;
    }

    /**
     * 从HTTP请求头中解析令牌
     *
     * @param headName 请求头名称
     * @return 创建器本身
     * @see HeaderTokenResolver
     */
    public TokenResolverBuilder fromHttpHeader(String headName) {
        list.add(new HeaderTokenResolver(headName));
        return this;
    }

    /**
     * 从HTTP请求头中解析令牌
     *
     * @param headName 请求头名称
     * @param prefix   前缀
     * @return 创建器本身
     * @see HeaderTokenResolver
     */
    public TokenResolverBuilder fromHttpHeader(String headName, String prefix) {
        list.add(new HeaderTokenResolver(headName, prefix));
        return this;
    }

    /**
     * 从HTTP Query中解析令牌
     *
     * @param paramName query名称
     * @return 创建器本身
     */
    public TokenResolverBuilder fromHttpQuery(String paramName) {
        list.add(new QueryTokenResolver(paramName));
        return this;
    }

    /**
     * 从HTTP Query中解析令牌
     *
     * @param paramName query名称
     * @param prefix    前缀
     * @return 创建器本身
     */
    public TokenResolverBuilder fromHttpQuery(String paramName, String prefix) {
        list.add(new QueryTokenResolver(paramName, prefix));
        return this;
    }

    /**
     * Bearer 令牌解析器
     *
     * @return 创建器本身
     */
    public TokenResolverBuilder bearerToken() {
        list.add(new BearerTokenResolver());
        return this;
    }

    /**
     * Basic 令牌解析器
     *
     * @return 创建器本身
     */
    public TokenResolverBuilder basicToken() {
        list.add(new BasicTokenResolver());
        return this;
    }

    /**
     * 创建令牌解析器
     *
     * @return 解析器实例
     */
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
