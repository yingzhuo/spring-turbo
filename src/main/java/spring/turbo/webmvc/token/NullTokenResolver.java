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
 * @author 应卓
 * @since 1.0.0
 */
public final class NullTokenResolver implements TokenResolver {

    private static final NullTokenResolver INSTANCE = new NullTokenResolver();

    private NullTokenResolver() {
        super();
    }

    public static NullTokenResolver getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Token> resolve(WebRequest webRequest) {
        return Optional.empty();
    }

}
