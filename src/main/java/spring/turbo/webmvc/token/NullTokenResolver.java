/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

/**
 * 一直返回empty-optional的令牌解析器实现
 *
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.0
 */
public final class NullTokenResolver implements TokenResolver {

    /**
     * 私有构造方法
     */
    private NullTokenResolver() {
        super();
    }

    /**
     * 获取本类单例
     *
     * @return 单例实例
     */
    public static NullTokenResolver getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * 解析令牌
     *
     * @param request HTTP请求
     * @return empty-optional
     */
    @NonNull
    @Override
    public Optional<Token> resolve(WebRequest request) {
        return Optional.empty();
    }

    /**
     * 排序参数
     *
     * @return 排序值 (最低)
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final NullTokenResolver INSTANCE = new NullTokenResolver();
    }

}
