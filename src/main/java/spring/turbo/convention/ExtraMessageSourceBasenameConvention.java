/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.convention;

import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * 扩展 basename 规约
 *
 * @author 应卓
 * @see spring.turbo.autoconfiguration.properties.MessageSourceProps
 * @see org.springframework.boot.autoconfigure.context.MessageSourceProperties
 * @see spring.turbo.util.ServiceLoaderUtils
 * @since 2.0.3
 */
@FunctionalInterface
public interface ExtraMessageSourceBasenameConvention extends Ordered {

    @Nullable
    public Collection<String> getExtraMessageSourceBasename();

    @Override
    public default int getOrder() {
        return 0;
    }

}
