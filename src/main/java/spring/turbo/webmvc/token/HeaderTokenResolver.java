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
import spring.turbo.util.StringPool;

import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
public class HeaderTokenResolver implements TokenResolver {

    protected final String headerName;
    protected final String prefix;
    protected final int prefixLen;

    public HeaderTokenResolver(String headerName) {
        this(headerName, StringPool.EMPTY);
    }

    public HeaderTokenResolver(String headerName, String prefix) {
        if (prefix == null) prefix = StringPool.EMPTY;
        this.headerName = headerName;
        this.prefix = prefix;
        this.prefixLen = prefix.length();
    }

    @Override
    public Optional<Token> resolve(WebRequest request) {

        String headerValue = request.getHeader(headerName);

        if (headerValue == null || !headerValue.startsWith(prefix)) {
            return Optional.empty();
        }

        headerValue = headerValue.substring(prefixLen);

        if (headerValue.split("\\.").length == 2 && !headerValue.endsWith(".")) {
            headerValue += ".";
        }

        return Optional.of(StringToken.of(headerValue));
    }

}
