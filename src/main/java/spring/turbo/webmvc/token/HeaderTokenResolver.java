/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringUtils;

import java.util.Optional;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * 通过HTTP header解析令牌
 *
 * @author 应卓
 * @see org.springframework.http.HttpHeaders
 * @since 1.0.0
 */
public class HeaderTokenResolver implements TokenResolver {

    protected final String headerName;
    protected final String prefix;
    protected final int prefixLen;

    /**
     * 构造方法
     *
     * @param headerName 请求头名
     */
    public HeaderTokenResolver(@NonNull String headerName) {
        this(headerName, EMPTY);
    }

    /**
     * 构造方法
     *
     * @param headerName 请求头名
     * @param prefix     前缀
     */
    public HeaderTokenResolver(@NonNull String headerName, @Nullable String prefix) {
        Asserts.hasText(headerName);
        if (prefix == null) prefix = EMPTY;
        this.headerName = headerName;
        this.prefix = prefix;
        this.prefixLen = prefix.length();
    }

    /**
     * 解析令牌
     *
     * @param request HTTP请求
     * @return 令牌Optional，不能成功解析时返回empty-optional
     */
    @NonNull
    @Override
    public Optional<Token> resolve(WebRequest request) {
        String headerValue = request.getHeader(headerName);

        if (headerValue == null || !headerValue.startsWith(prefix)) {
            return Optional.empty();
        }

        headerValue = headerValue.substring(prefixLen);

        if (StringUtils.isBlank(headerValue)) {
            return Optional.empty();
        }

        return Optional.of(StringToken.of(headerValue));
    }

    /**
     * 排序参数
     *
     * @return 排序值
     */
    @Override
    public int getOrder() {
        return -100;
    }

    public String getHeaderName() {
        return headerName;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPrefixLen() {
        return prefixLen;
    }

}
