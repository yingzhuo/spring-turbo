/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.Nullable;

import java.util.Locale;

/**
 * @author 应卓
 *
 * @since 1.0.11
 */
public final class MessageUtils {

    /**
     * 私有构造方法
     */
    private MessageUtils() {
        super();
    }

    public static String getMessage(MessageSourceResolvable resolvable) {
        return SpringUtils.getRequiredBean(MessageSourceAccessor.class).getMessage(resolvable);
    }

    public static String getMessage(String code, @Nullable Object[] args, Locale locale) {
        return SpringUtils.getRequiredBean(MessageSourceAccessor.class).getMessage(code, args, locale);
    }

    public static String getMessage(String code, @Nullable Object[] args, String defaultMessage, Locale locale) {
        return SpringUtils.getRequiredBean(MessageSourceAccessor.class).getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(String code, String defaultMessage) {
        return SpringUtils.getRequiredBean(MessageSourceAccessor.class).getMessage(code, defaultMessage);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale) {
        return SpringUtils.getRequiredBean(MessageSourceAccessor.class).getMessage(code, defaultMessage, locale);
    }

    public static String getMessage(String code, @Nullable Object[] args, String defaultMessage) {
        return SpringUtils.getRequiredBean(MessageSourceAccessor.class).getMessage(code, args, defaultMessage);
    }

}
