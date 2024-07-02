/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.databinding;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.util.StringUtils;
import spring.turbo.exception.DataBindingException;

/**
 * 内部工具
 *
 * @author 应卓
 * @since 3.3.1
 */
final class InternalConverterUtils {

    public static <T extends RuntimeException> RuntimeException transform(T e) {

        if (e instanceof MessageSourceResolvable) {
            return e;
        }

        if (e instanceof MultiMessageSourceResolvable) {
            return e;
        }

        var msg = e.getMessage();
        if (!StringUtils.hasText(msg)) {
            var rootEx = NestedExceptionUtils.getRootCause(e);
            if (rootEx != null) {
                msg = rootEx.getMessage();
            }
        }

        if (StringUtils.hasText(msg)) {
            return DataBindingException.of(msg);
        }

        return e;
    }

}
