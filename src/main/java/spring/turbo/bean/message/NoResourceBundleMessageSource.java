/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.message;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import spring.turbo.util.StringFormatter;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author 应卓
 * @since 1.0.11
 */
@Deprecated
public class NoResourceBundleMessageSource implements MessageSource {

    // 优先级:
    // 1. defaultMessage
    // 2. StringFormat(code + args)
    // 3. code (first code)
    // 4. throw NoSuchMessageException

    @Override
    public String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {

        if (defaultMessage != null) {
            return defaultMessage;
        }

        if (args != null) {
            return StringFormatter.format(code, args);
        } else {
            return code;
        }
    }

    @Override
    public String getMessage(String code, @Nullable Object[] args, Locale locale) throws NoSuchMessageException {
        if (args != null) {
            return StringFormatter.format(code, args);
        } else {
            return code;
        }
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        final String defaultMessage = resolvable.getDefaultMessage();
        if (defaultMessage != null) {
            return defaultMessage;
        }

        final String[] codes = resolvable.getCodes();
        final String firstCode = getFirstCode(resolvable.getCodes());
        final Object[] arguments = resolvable.getArguments();

        if (firstCode != null) {
            if (arguments != null) {
                return StringFormatter.format(firstCode, arguments);
            } else {
                return firstCode;
            }
        }

        throw new NoSuchMessageException(Arrays.toString(resolvable.getCodes()));
    }

    @Nullable
    private String getFirstCode(@Nullable String[] codes) {
        if (codes == null) {
            return null;
        }
        if (codes.length >= 1) {
            return codes[0];
        }
        return null;
    }

}
