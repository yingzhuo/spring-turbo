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
import org.springframework.lang.Nullable;
import spring.turbo.core.MessageSourceUtils;
import spring.turbo.util.ArrayUtils;
import spring.turbo.util.StringUtils;

import java.util.Locale;

/**
 * 实现了 {@link MessageSourceResolvable} 的异常类
 *
 * @author 应卓
 * @see MessageSource
 * @see MessageSourceResolvable
 * @see Locale
 * @see org.springframework.context.i18n.LocaleContextHolder
 * @since 3.3.1
 */
public abstract class AbstractMessageResolvableException extends IllegalArgumentException implements MessageSourceResolvable {

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
    public AbstractMessageResolvableException(@Nullable String code, @Nullable Object[] arguments) {
        this(code, arguments, null);
    }

    /**
     * 构造方法
     *
     * @param defaultMessage 默认错误信息
     * @see MessageSourceResolvable
     */
    public AbstractMessageResolvableException(@Nullable String defaultMessage) {
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
    public AbstractMessageResolvableException(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(StringUtils.blankToNull(defaultMessage));
        this.code = StringUtils.blankToNull(code);
        this.arguments = ArrayUtils.emptyToNull(arguments);
        this.defaultMessage = StringUtils.blankToNull(defaultMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public final String[] getCodes() {
        return code != null ? new String[]{code} : null;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public final Object[] getArguments() {
        return this.arguments;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public final String getDefaultMessage() {
        return this.defaultMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        try {
            return toString((MessageSource) null, null);
        } catch (Throwable e) {
            return super.toString();
        }
    }

    /**
     * 从{@link MessageSource} 解析出错误信息
     *
     * @param messageSource 信息源
     * @return 错误信息
     */
    public final String toString(@Nullable MessageSource messageSource) {
        return MessageSourceUtils.getMessage(messageSource, this);
    }

    /**
     * 从{@link MessageSource} 解析出错误信息
     *
     * @param messageSource 信息源
     * @param locale        locale
     * @return 错误信息
     */
    public final String toString(@Nullable MessageSource messageSource, @Nullable Locale locale) {
        return MessageSourceUtils.getMessage(messageSource, this, locale);
    }

}
