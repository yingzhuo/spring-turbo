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
import spring.turbo.util.Asserts;

import java.util.Locale;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.11
 */
@Deprecated
public class DelegatingMessageSource implements MessageSource {

    private final MessageSource primaryMessageSource;
    private final MessageSource secondaryMessageSource;

    public DelegatingMessageSource(MessageSource primaryMessageSource) {
        this(primaryMessageSource, null);
    }

    public DelegatingMessageSource(MessageSource primaryMessageSource, @Nullable MessageSource secondaryMessageSource) {
        Asserts.notNull(primaryMessageSource);
        this.primaryMessageSource = primaryMessageSource;
        this.secondaryMessageSource = Optional.ofNullable(secondaryMessageSource).orElseGet(NoResourceBundleMessageSource::new);
    }

    @Nullable
    @Override
    public String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
        String msg = primaryMessageSource.getMessage(code, args, defaultMessage, locale);

        if (msg == null) {
            msg = secondaryMessageSource.getMessage(code, args, defaultMessage, locale);
        }
        return msg;
    }

    @Override
    public String getMessage(String code, @Nullable Object[] args, Locale locale) throws NoSuchMessageException {
        try {
            return primaryMessageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            return secondaryMessageSource.getMessage(code, args, locale);
        }
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        try {
            return primaryMessageSource.getMessage(resolvable, locale);
        } catch (NoSuchMessageException e) {
            return secondaryMessageSource.getMessage(resolvable, locale);
        }
    }

}
