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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import spring.turbo.messagesource.StringMessageSourceResolvable;
import spring.turbo.util.Asserts;

import java.util.*;

import static spring.turbo.util.StringPool.COMMA;

/**
 * 错误的参数异常
 *
 * @author 应卓
 * @see Errors
 * @see BindingResult
 * @see MessageSource
 * @see MessageSourceAccessor
 * @see Locale
 * @see org.springframework.context.i18n.LocaleContextHolder
 * @since 3.3.2
 */
public final class BadParametersException extends RuntimeException {

    private final Collection<MessageSourceResolvable> messageSourceResolvableCollection;

    /**
     * 构造方法
     *
     * @param errors 数据绑定错误
     */
    public BadParametersException(Errors errors) {
        Asserts.notNull(errors, "errors is required");
        Asserts.isTrue(errors.hasErrors(), "errors has no errors");

        messageSourceResolvableCollection =
                errors.getAllErrors()
                        .stream()
                        .map(e -> (MessageSourceResolvable) e)
                        .toList();
    }

    /**
     * 构造方法
     *
     * @param bindingResult 数据绑定错误
     */
    public BadParametersException(BindingResult bindingResult) {
        this((Errors) bindingResult);
    }

    /**
     * 构造方法
     *
     * @param errorMessage 错误信息
     * @param moreMessages 更多错误信息
     */
    public BadParametersException(String errorMessage, String... moreMessages) {
        var list = new ArrayList<MessageSourceResolvable>();
        list.add(StringMessageSourceResolvable.of(errorMessage));

        list.addAll(
                Arrays.stream(moreMessages)
                        .map(StringMessageSourceResolvable::of)
                        .toList()
        );

        this.messageSourceResolvableCollection = Collections.unmodifiableCollection(list);
    }

    /**
     * 获取 {@link MessageSourceResolvable} 集合
     *
     * @return {@link MessageSourceResolvable} 集合
     */
    public Collection<MessageSourceResolvable> getMessageSourceResolvableCollection() {
        return messageSourceResolvableCollection;
    }

    /**
     * 获取错误数量
     *
     * @return 错误数量
     */
    public int errorCount() {
        return messageSourceResolvableCollection.size();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 通过 {@link MessageSource} 解析出错误文本
     *
     * @param messageSource {@link MessageSource} 实例
     * @return 错误文本
     */
    public List<String> getMessages(MessageSource messageSource) {
        return getMessages(messageSource, null);
    }

    /**
     * 通过 {@link MessageSource} 解析出错误文本
     *
     * @param messageSource {@link MessageSource} 实例
     * @param locale        locale
     * @return 错误文本
     */
    public List<String> getMessages(MessageSource messageSource, @Nullable Locale locale) {
        Asserts.notNull(messageSource, "messageSource is required");

        final var localeToUse = Objects.requireNonNullElseGet(locale, Locale::getDefault);
        return messageSourceResolvableCollection
                .stream()
                .map(messageSourceResolvable -> messageSource.getMessage(messageSourceResolvable, localeToUse))
                .toList();
    }

    /**
     * 通过 {@link MessageSourceAccessor} 解析出错误文本并合成一个字符串 (由逗号分隔)
     *
     * @param messageSource {@link MessageSource} 实例
     * @return 错误文本
     */
    public String toCommaDelimitedString(MessageSource messageSource) {
        return toCommaDelimitedString(messageSource, null);
    }

    /**
     * 通过 {@link MessageSource} 解析出错误文本并合成一个字符串 (由逗号分隔)
     *
     * @param messageSource {@link MessageSource} 实例
     * @param locale        locale
     * @return 错误文本
     */
    public String toCommaDelimitedString(MessageSource messageSource, @Nullable Locale locale) {
        return String.join(COMMA, getMessages(messageSource, locale));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 通过 {@link MessageSourceAccessor} 解析出错误文本
     *
     * @param messageSourceAccessor {@link MessageSourceAccessor} 实例
     * @return 错误文本
     */
    public List<String> getMessages(MessageSourceAccessor messageSourceAccessor) {
        Asserts.notNull(messageSourceAccessor, "messageSourceAccessor is required");

        return messageSourceResolvableCollection
                .stream()
                .map(messageSourceAccessor::getMessage)
                .toList();
    }

    /**
     * 通过 {@link MessageSourceAccessor} 解析出错误文本并合成一个字符串 (由逗号分隔)
     *
     * @param messageSourceAccessor {@link MessageSourceAccessor} 实例
     * @return 错误文本
     */
    public String toCommaDelimitedString(MessageSourceAccessor messageSourceAccessor) {
        return String.join(COMMA, getMessages(messageSourceAccessor));
    }

}
