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
import org.springframework.validation.Errors;
import spring.turbo.core.MessageSourceUtils;
import spring.turbo.databinding.MultiMessageSourceResolvable;
import spring.turbo.messagesource.SimpleMessageSourceResolvable;
import spring.turbo.messagesource.StringMessageSourceResolvable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringPool;
import spring.turbo.util.StringUtils;
import spring.turbo.util.collection.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据绑定异常
 *
 * <ul>
 *     <li>数据转换错误</li>
 *     <li>数据不合法</li>
 * </ul>
 *
 * @author 应卓
 * @since 3.3.1
 */
public class DataBindingException extends IllegalArgumentException implements MultiMessageSourceResolvable {

    protected final List<MessageSourceResolvable> innerList = new ArrayList<>();

    /**
     * 私有构造方法
     */
    private DataBindingException() {
        super();
    }

    public static void raiseIfNecessary(@Nullable Errors errors) {
        if (errors != null && errors.hasErrors()) {
            var ex = new DataBindingException();

            CollectionUtils.nullSafeAddAll(
                    ex.innerList,
                    errors.getAllErrors()
                            .stream()
                            .map(objectError -> (MessageSourceResolvable) objectError)
                            .toList()
            );

            throw ex;
        }
    }

    public static DataBindingException of(String firstMessage, String... moreMessages) {
        List<String> messages = new ArrayList<>();
        StringUtils.blankSafeAdd(messages, firstMessage);
        StringUtils.blankSafeAddAll(messages, moreMessages);

        var ex = new DataBindingException();
        ex.innerList.addAll(
                messages.stream()
                        .map(StringMessageSourceResolvable::new)
                        .toList()
        );
        return ex;
    }

    public static DataBindingException of(MessageSourceResolvable firstMessage, MessageSourceResolvable... moreMessages) {
        List<MessageSourceResolvable> messages = new ArrayList<>();
        CollectionUtils.nullSafeAdd(messages, firstMessage);
        CollectionUtils.nullSafeAddAll(messages, moreMessages);

        var ex = new DataBindingException();
        ex.innerList.addAll(messages);
        return ex;
    }

    public static DataBindingException of(Collection<MessageSourceResolvable> messageSourceResolvableCollection) {
        var ex = new DataBindingException();
        CollectionUtils.nullSafeAddAll(ex.innerList, messageSourceResolvableCollection);
        return ex;
    }

    public static DataBindingException ofCode(String code) {
        return ofCode(code, null, null);
    }

    public static DataBindingException ofCode(String code, @Nullable Object... arguments) {
        return ofCode(code, arguments, null);
    }

    public static DataBindingException ofCode(String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        Asserts.hasText(code, "code is required");
        var ex = new DataBindingException();
        ex.innerList.add(new SimpleMessageSourceResolvable(code, arguments, defaultMessage));
        return ex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<MessageSourceResolvable> iterator() {
        return innerList.iterator();
    }

    public int size() {
        return innerList.size();
    }

    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    public boolean isNotEmpty() {
        return !innerList.isEmpty();
    }

    public List<String> toStringMessages(MessageSource messageSource) {
        return toStringMessages(messageSource, null);
    }

    public List<String> toStringMessages(MessageSource messageSource, @Nullable Locale locale) {
        return stream()
                .map(msr -> MessageSourceUtils.getMessage(messageSource, msr, locale))
                .collect(Collectors.toList());
    }

    public String toCommaDelimitedMessage(MessageSource messageSource) {
        return toCommaDelimitedMessage(messageSource, null);
    }

    public String toCommaDelimitedMessage(MessageSource messageSource, @Nullable Locale locale) {
        return StringUtils.join(toStringMessages(messageSource, locale), StringPool.COMMA);
    }

}
