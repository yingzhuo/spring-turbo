/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.Nullable;
import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;

/**
 * 自洽性错误
 *
 * @author 应卓
 * @see java.text.MessageFormat
 * @see org.springframework.context.MessageSource
 * @since 1.0.0
 */
@Immutable
@Deprecated(since = "2.0.1", forRemoval = true)
public final class SelfConsistentException extends IllegalStateException implements MessageSourceResolvable {

    @Nullable
    private final String[] codes;

    @Nullable
    private final Object[] arguments;

    @Nullable
    private final String defaultMessage;

    public SelfConsistentException(@Nullable String[] codes, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        this.codes = codes;
        this.arguments = arguments;
        this.defaultMessage = defaultMessage;
    }

    @Nullable
    @Override
    public String[] getCodes() {
        return this.codes;
    }

    @Nullable
    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Nullable
    @Override
    public String getDefaultMessage() {
        return this.defaultMessage;
    }

    public String getRequiredFirstCode() {
        Asserts.notNull(codes);
        Asserts.isTrue(codes.length >= 1);
        return codes[0];
    }

    public Object[] getRequiredArguments() {
        Asserts.notNull(arguments);
        return arguments;
    }

}
