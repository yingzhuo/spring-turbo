/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.api;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * @param <T>
 *            负载类型
 *
 * @author 应卓
 *
 * @since 1.0.0
 */
@Deprecated(since = "3.3.1")
public interface ApiResult<T> extends Serializable {

    public String getCode();

    @Nullable
    public String getErrorMessage();

    public T getPayload();

    public boolean isDeprecated();

}
