/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import spring.turbo.messagesource.StringMessageSourceResolvable;
import spring.turbo.util.Asserts;

import java.util.Locale;

/**
 * {@link MessageSource} 相关工具
 *
 * @author 应卓
 * @since 3.3.2
 */
public final class MessageSourceUtils {

    /**
     * 私有构造方法
     */
    private MessageSourceUtils() {
        super();
    }

    public static String getMessage(@Nullable MessageSource messageSource, MessageSourceResolvable messageSourceResolvable) {
        return getMessage(messageSource, messageSourceResolvable, null);
    }

    public static String getMessage(@Nullable MessageSource messageSource, MessageSourceResolvable messageSourceResolvable, @Nullable Locale locale) {
        Asserts.notNull(messageSourceResolvable, "messageSourceResolvable is required");

        if (messageSourceResolvable instanceof StringMessageSourceResolvable stringMessageSourceResolvable) {
            return stringMessageSourceResolvable.getDefaultMessage();
        }

        messageSource = messageSource != null ? messageSource : SpringUtils.getMessageSource();
        locale = locale != null ? locale : LocaleUtils.getLocale();

        try {
            return messageSource.getMessage(messageSourceResolvable, locale);
        } catch (NoSuchMessageException e) {
            var defaultMsg = messageSourceResolvable.getDefaultMessage();
            if (defaultMsg != null) {
                return defaultMsg;
            }
            throw e;
        }
    }

}
