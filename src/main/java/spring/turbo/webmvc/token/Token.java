/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import java.io.Serializable;

/**
 * 令牌
 *
 * @author 应卓
 * @see #ofString(String)
 * @since 1.0.0
 */
@FunctionalInterface
public interface Token extends Serializable {

    public static Token ofString(String string) {
        return StringToken.of(string);
    }

    /**
     * @return 将令牌转换为 {@link java.lang.String}
     * @since 1.0.5
     */
    public String asString();

    @Override
    public String toString();

}
