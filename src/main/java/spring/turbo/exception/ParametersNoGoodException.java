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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import spring.turbo.core.MessageSourceUtils;
import spring.turbo.messagesource.StringMessageSourceResolvable;
import spring.turbo.util.Asserts;

import java.util.*;
import java.util.stream.Collectors;

import static spring.turbo.util.StringPool.COMMA;

/**
 * 错误的参数异常
 *
 * @author 应卓
 * @see Errors
 * @see BindingResult
 * @see MessageSource
 * @see Locale
 * @see org.springframework.context.i18n.LocaleContextHolder
 * @since 3.3.2
 */
public final class ParametersNoGoodException extends RuntimeException implements Iterable<MessageSourceResolvable> {

    private final List<MessageSourceResolvable> messageSourceResolvableList;

    /**
     * 构造方法
     *
     * @param errors 数据绑定错误
     */
    public ParametersNoGoodException(Errors errors) {
        Asserts.notNull(errors, "errors is required");
        Asserts.isTrue(errors.hasErrors(), "errors has no errors");

        messageSourceResolvableList =
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
    public ParametersNoGoodException(BindingResult bindingResult) {
        this((Errors) bindingResult);
    }

    /**
     * 构造方法
     *
     * @param errorMessage 错误信息
     * @param moreMessages 更多错误信息
     */
    public ParametersNoGoodException(String errorMessage, String... moreMessages) {
        var list = new ArrayList<MessageSourceResolvable>();
        list.add(new StringMessageSourceResolvable(errorMessage));

        list.addAll(
                Arrays.stream(moreMessages)
                        .map(StringMessageSourceResolvable::new)
                        .toList()
        );

        this.messageSourceResolvableList = Collections.unmodifiableList(list);
    }

    /**
     * 获取 {@link MessageSourceResolvable} 集合
     *
     * @return {@link MessageSourceResolvable} 集合
     */
    public Collection<MessageSourceResolvable> getMessageSourceResolvableList() {
        return messageSourceResolvableList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<MessageSourceResolvable> iterator() {
        return messageSourceResolvableList.iterator();
    }

    /**
     * 获取错误数量
     *
     * @return 错误数量
     */
    public int errorCount() {
        return messageSourceResolvableList.size();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 通过 {@link MessageSource} 解析出错误文本
     *
     * @param messageSource {@link MessageSource} 实例
     * @return 错误文本
     */
    public List<String> toStringList(MessageSource messageSource) {
        return toStringList(messageSource, null);
    }

    /**
     * 通过 {@link MessageSource} 解析出错误文本
     *
     * @param messageSource {@link MessageSource} 实例
     * @param locale        locale
     * @return 错误文本
     */
    public List<String> toStringList(MessageSource messageSource, @Nullable Locale locale) {
        Asserts.notNull(messageSource, "messageSource is required");

        return messageSourceResolvableList
                .stream()
                .map(resolvable -> MessageSourceUtils.getMessage(messageSource, resolvable, locale))
                .collect(Collectors.toList());
    }

    /**
     * 通过 {@link MessageSource} 解析出错误文本并合成一个字符串 (由逗号分隔)
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
        return String.join(COMMA, toStringList(messageSource, locale));
    }

}
