/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.util.Locale;
import java.util.Objects;

/**
 * 未实现的功能
 *
 * @author 应卓
 * @since 3.2.2
 */
public final class NotImplementedException extends UnsupportedOperationException implements MessageSourceResolvable {

    @Nullable
    private final String code;

    @Nullable
    private final Object[] arguments;

    @Nullable
    private final String defaultMessage;

    /**
     * 构造方法
     *
     * @param code      解析错误信息用code
     * @param arguments 解析错误信息用参数
     * @see MessageSourceResolvable
     */
    public NotImplementedException(@Nullable String code, @Nullable Object... arguments) {
        this(code, arguments, null);
    }

    /**
     * 构造方法
     *
     * @param defaultMessage 默认错误信息
     * @see MessageSourceResolvable
     */
    public NotImplementedException(String defaultMessage) {
        this(null, null, defaultMessage);
    }

    /**
     * 构造方法
     *
     * @param code           解析错误信息用code
     * @param arguments      解析错误信息用参数
     * @param defaultMessage 默认错误信息
     * @see MessageSourceResolvable
     */
    public NotImplementedException(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(defaultMessage);
        this.code = (code == null || code.isBlank() ? null : code);
        this.arguments = arguments;
        this.defaultMessage = (defaultMessage == null || defaultMessage.isBlank() ? null : code);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String[] getCodes() {
        return code != null ? new String[]{code} : null;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    public String toString(MessageSource messageSource) {
        return toString(messageSource, null);
    }

    public String toString(MessageSource messageSource, @Nullable Locale locale) {
        Asserts.notNull(messageSource, "messageSource is required");
        var localeToUse = Objects.requireNonNullElseGet(locale, LocaleContextHolder::getLocale);
        return messageSource.getMessage(this, localeToUse);
    }

}
