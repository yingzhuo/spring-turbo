/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

/**
 * 一直返回empty-optional的令牌解析器实现
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class NullTokenResolver implements TokenResolver {

    /**
     * 单例
     */
    private static final NullTokenResolver INSTANCE = new NullTokenResolver();

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
        return INSTANCE;
    }

    /**
     * 解析令牌
     *
     * @param request HTTP请求
     * @return empty-optional
     */
    @Override
    public Optional<Token> resolve(WebRequest request) {
        return Optional.empty();
    }

}
