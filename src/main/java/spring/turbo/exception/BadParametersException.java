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

/**
 * 错误的参数异常
 *
 * @author 应卓
 * @since 3.3.2
 */
public class BadParametersException extends RuntimeException {

    private final Collection<MessageSourceResolvable> messageSourceResolvableCollection;

    public BadParametersException(Errors errors) {
        Asserts.notNull(errors, "errors is required");
        Asserts.isTrue(errors.hasErrors(), "errors has no errors");

        messageSourceResolvableCollection =
                errors.getAllErrors()
                        .stream()
                        .map(e -> (MessageSourceResolvable) e)
                        .toList();
    }

    public BadParametersException(BindingResult bindingResult) {
        this((Errors) bindingResult);
    }

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

    public Collection<MessageSourceResolvable> getMessageSourceResolvableCollection() {
        return messageSourceResolvableCollection;
    }

    public int errorCount() {
        return messageSourceResolvableCollection.size();
    }

    public List<String> getMessages(MessageSource messageSource) {
        return getMessages(messageSource, null);
    }

    public List<String> getMessages(MessageSource messageSource, @Nullable Locale locale) {
        Asserts.notNull(messageSource, "messageSource is required");

        final var localeToUse = Objects.requireNonNullElseGet(locale, Locale::getDefault);
        return messageSourceResolvableCollection
                .stream()
                .map(messageSourceResolvable -> messageSource.getMessage(messageSourceResolvable, localeToUse))
                .toList();
    }

    public List<String> getMessage(MessageSourceAccessor messageSourceAccessor) {
        Asserts.notNull(messageSourceAccessor, "messageSourceAccessor is required");

        return messageSourceResolvableCollection
                .stream()
                .map(messageSourceAccessor::getMessage)
                .toList();
    }

}
