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
import java.util.Optional;

/**
 * @author 应卓
 * @see org.springframework.context.MessageSourceResolvable
 * @since 3.3.2
 */
public abstract class AbstractMessageResolvableException extends RuntimeException implements MessageSourceResolvable {

    @Nullable
    private final String code;

    @Nullable
    private final Object[] arguments;

    @Nullable
    private final String defaultMessage;

    public AbstractMessageResolvableException(String code, @Nullable Object[] arguments) {
        this(code, arguments, null);
    }

    public AbstractMessageResolvableException(String defaultMessage) {
        this(null, null, defaultMessage);
    }

    public AbstractMessageResolvableException(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(defaultMessage);
        this.code = blankToNull(code);
        this.arguments = arguments;
        this.defaultMessage = blankToNull(defaultMessage);

        if (this.code == null && this.defaultMessage == null) {
            throw new AssertionError("code/defaultMessage at least one required");
        }
    }

    @Nullable
    @Override
    public final String[] getCodes() {
        return Optional.ofNullable(this.code)
                .map(code -> new String[]{code})
                .orElse(null);
    }

    @Nullable
    @Override
    public final Object[] getArguments() {
        return this.arguments;
    }

    @Nullable
    @Override
    public final String getDefaultMessage() {
        return this.defaultMessage;
    }

    public final String toString(MessageSource messageSource) {
        return toString(messageSource, null);
    }

    public final String toString(MessageSource messageSource, @Nullable Locale locale) {
        Asserts.notNull(messageSource, "messageSource is required");
        return messageSource.getMessage(this, Objects.requireNonNullElseGet(locale, Locale::getDefault));
    }

    public final String toString(MessageSourceAccessor messageSourceAccessor) {
        Asserts.notNull(messageSourceAccessor, "messageSourceAccessor is required");
        return messageSourceAccessor.getMessage(this);
    }

    @Nullable
    private String blankToNull(@Nullable String s) {
        return s == null || s.isBlank() ? null : s;
    }

}
