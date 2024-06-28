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
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.util.Locale;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * 业务异常
 *
 * @author 应卓
 * @see #builder()
 * @see org.springframework.context.MessageSourceResolvable
 * @see org.springframework.context.MessageSource
 * @see org.springframework.context.support.MessageSourceAccessor
 * @see org.springframework.context.support.ApplicationObjectSupport
 * @see spring.turbo.core.MessageUtils
 * @since 2.0.1
 */
public final class BusinessException extends RuntimeException implements MessageSourceResolvable {

    @Nullable
    private final String[] codes;

    @Nullable
    private final Object[] arguments;

    @Nullable
    private final String defaultMessage;

    /**
     * 创建器用构造方法
     *
     * @param codes          I18n codes
     * @param arguments      I18n arguments
     * @param defaultMessage I18n defaultMessage
     * @see org.springframework.context.MessageSource
     * @see MessageSourceResolvable
     * @see BusinessExceptionBuilder
     */
    public BusinessException(@Nullable String[] codes, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(defaultMessage);
        this.codes = codes;
        this.arguments = arguments;
        this.defaultMessage = defaultMessage;
    }

    /**
     * 构造方法
     */
    public BusinessException() {
        this(null);
    }

    /**
     * 构造方法
     *
     * @param message 异常信息
     */
    public BusinessException(@Nullable String message) {
        super(message);
        this.codes = null;
        this.arguments = null;
        this.defaultMessage = message;
    }

    public static BusinessExceptionBuilder builder() {
        return new BusinessExceptionBuilder();
    }

    public static BusinessException of(@Nullable String msg) {
        return new BusinessException(msg);
    }

    public static BusinessException of(@Nullable Throwable ex) {
        return of(ofNullable(ex).map(Throwable::getMessage).orElse(null));
    }

    @Override
    @Nullable
    public String[] getCodes() {
        return this.codes;
    }

    @Override
    @Nullable
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public String getDefaultMessage() {
        return Objects.requireNonNullElseGet(this.defaultMessage,
                () -> Objects.requireNonNullElse(super.getMessage(), null));
    }

    public String getMessage(MessageSource messageSource, @Nullable Locale locale) {
        Asserts.notNull(messageSource, "messageSource is required");
        return messageSource.getMessage(this, Objects.requireNonNullElseGet(locale, Locale::getDefault));
    }

    public String getMessage(MessageSourceAccessor messageSourceAccessor) {
        Asserts.notNull(messageSourceAccessor, "messageSourceAccessor is required");
        return messageSourceAccessor.getMessage(this);
    }

}
