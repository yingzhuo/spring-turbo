
package spring.turbo.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import spring.turbo.core.MessageSourceUtils;
import spring.turbo.databinding.MultiMessageSourceResolvable;
import spring.turbo.messagesource.StringMessageSourceResolvable;
import spring.turbo.util.StringPool;
import spring.turbo.util.StringUtils;
import spring.turbo.util.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
public final class DataBindingException extends RuntimeException implements MultiMessageSourceResolvable {

    private final List<MessageSourceResolvable> innerList = new ArrayList<>();

    public static void raiseIfNecessary(Errors errors) {
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

    /**
     * 私有构造方法
     */
    private DataBindingException() {
        super();
    }

    @Override
    public Iterator<MessageSourceResolvable> iterator() {
        return innerList.iterator();
    }

    public List<String> toStringMessages(MessageSource messageSource) {
        return toStringMessages(messageSource, null);
    }

    public List<String> toStringMessages(MessageSource messageSource, @Nullable Locale locale) {
        return innerList
                .stream()
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
