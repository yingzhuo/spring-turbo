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
public class QueryTokenResolver implements TokenResolver {

    protected final String paramName;
    protected final String prefix;
    protected final int prefixLen;

    public QueryTokenResolver(String paramName) {
        this(paramName, StringPool.EMPTY);
    }

    public QueryTokenResolver(String paramName, String prefix) {
        if (prefix == null) prefix = StringPool.EMPTY;
        this.paramName = paramName;
        this.prefix = prefix;
        this.prefixLen = prefix.length();
    }

    @Override
    public Optional<Token> resolve(WebRequest request) {
        String paramValue = request.getParameter(paramName);

        if (paramValue == null || !paramValue.startsWith(prefix)) {
            return Optional.empty();
        }

        paramValue = paramValue.substring(prefixLen);

        if (paramValue.split("\\.").length == 2 && !paramValue.endsWith(".")) {
            paramValue += ".";
        }

        return Optional.of(StringToken.of(paramValue));
    }

}
