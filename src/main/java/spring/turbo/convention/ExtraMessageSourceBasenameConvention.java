/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.convention;

import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * 扩展 basename 规约
 *
 * @author 应卓
 * @see spring.turbo.autoconfiguration.properties.MessageSourceProps
 * @see org.springframework.boot.autoconfigure.context.MessageSourceProperties
 * @since 2.0.3
 */
@FunctionalInterface
public interface ExtraMessageSourceBasenameConvention {

    @Nullable
    public Collection<String> getExtraMessageSourceBasename();

}
