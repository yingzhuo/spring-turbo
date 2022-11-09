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
import org.springframework.web.context.request.WebRequest;
import spring.turbo.util.CharsetPool;
import spring.turbo.util.StringPool;
import spring.turbo.util.crypto.Base64;

import java.util.Optional;

/**
 * HTTP Basic 令牌解析器
 *
 * @author 应卓
 * @see HeaderTokenResolver
 * @see BearerTokenResolver
 * @since 1.0.5
 */
public final class BasicTokenResolver extends HeaderTokenResolver {

    private static final String PREFIX = "Basic ";

    /**
     * 构造方法
     */
    public BasicTokenResolver() {
        super(HttpHeaders.AUTHORIZATION, PREFIX);
    }

    @Override
    public Optional<Token> resolve(WebRequest request) {
        final Optional<Token> tokenOption = super.resolve(request);

        if (!tokenOption.isPresent()) {
            return tokenOption;
        }

        final String tokenValue = tokenOption.get().asString();
        String headerValue = tokenValue;
        headerValue = new String(Base64.decode(headerValue), CharsetPool.UTF_8);

        final String[] parts = headerValue.split(StringPool.COLON);
        if (parts.length != 2) {
            return Optional.empty();
        } else {
            return Optional.of(new BasicToken(tokenValue, parts[0], parts[1]));
        }
    }

}
