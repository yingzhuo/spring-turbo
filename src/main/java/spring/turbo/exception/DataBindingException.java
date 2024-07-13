package spring.turbo.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import spring.turbo.databinding.MultiMessageSourceResolvable;
import spring.turbo.messagesource.SimpleMessageSourceResolvable;
import spring.turbo.messagesource.StringMessageSourceResolvable;
import spring.turbo.util.StringFormatter;
import spring.turbo.util.StringUtils;
import spring.turbo.util.collection.CollectionUtils;
import spring.turbo.util.collection.iterator.NoopComparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据绑定异常。这个异常是 {@link MultiMessageSourceResolvable}的实现类。也就是说本对象包含了国际化所需要的信息。
 * 配合 {@link MessageSource} 或 {@link MessageSourceAccessor} 可以方便地得到错误文本。最终可以把错误文本用户暂时页面或输出到前端。
 *
 * <p>
 * 如下情形，推荐抛出本异常的实例。
 * <ul>
 *     <li>数据转换错误</li>
 *     <li>数据不合法</li>
 * </ul>
 *
 * @author 应卓
 * @see Errors
 * @see BindingResult
 * @see org.springframework.core.convert.converter.Converter
 * @see spring.turbo.util.function.GenericGenerator
 * @see org.springframework.core.convert.converter.ConverterFactory
 * @see org.springframework.format.Parser
 * @see org.springframework.format.Formatter
 * @see org.springframework.format.AnnotationFormatterFactory
 * @see java.beans.PropertyEditor
 * @since 3.3.1
 */
public final class DataBindingException extends IllegalArgumentException implements MultiMessageSourceResolvable {

    private final List<MessageSourceResolvable> innerList = new ArrayList<>();

    /**
     * 私有构造方法
     */
    private DataBindingException() {
    }

    /**
     * 如果数据绑定有错误则抛出异常
     *
     * @param errors 数据绑定结果
     */
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

    /**
     * 如果数据绑定有错误则抛出异常
     *
     * @param bindingResult 数据绑定结果
     */
    public static void raiseIfNecessary(@Nullable BindingResult bindingResult) {
        raiseIfNecessary((Errors) bindingResult);
    }

    /**
     * 创建非国际化的对象
     *
     * @param firstMessage 错误信息
     * @param moreMessages 多个错误信息
     * @return 实例
     */
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

    /**
     * 创建国际化的对象
     *
     * @param firstMessage 错误信息
     * @param moreMessages 多个错误信息
     * @return 实例
     */
    public static DataBindingException of(MessageSourceResolvable firstMessage, MessageSourceResolvable... moreMessages) {
        List<MessageSourceResolvable> messages = new ArrayList<>();
        CollectionUtils.nullSafeAdd(messages, firstMessage);
        CollectionUtils.nullSafeAddAll(messages, moreMessages);

        var ex = new DataBindingException();
        ex.innerList.addAll(messages);
        return ex;
    }

    /**
     * 创建国际化的对象
     *
     * @param messageSourceResolvableCollection 错误信息 (多个)
     * @return 实例
     */
    public static DataBindingException of(Collection<MessageSourceResolvable> messageSourceResolvableCollection) {
        var ex = new DataBindingException();
        CollectionUtils.nullSafeAddAll(ex.innerList, messageSourceResolvableCollection);
        return ex;
    }

    /**
     * 创建国际化的对象
     *
     * @param code 错误消息代码
     * @return 实例
     * @see MessageSource#getMessage(String, Object[], String, Locale)
     */
    public static DataBindingException ofCode(String code) {
        return ofCode(code, null, null);
    }

    /**
     * 创建国际化的对象
     *
     * @param code      错误消息代码
     * @param arguments 错误消息参数
     * @return 实例
     * @see MessageSource#getMessage(String, Object[], String, Locale)
     */
    public static DataBindingException ofCode(String code, @Nullable Object... arguments) {
        return ofCode(code, arguments, null);
    }

    /**
     * 创建国际化的对象
     *
     * @param code           错误消息代码
     * @param arguments      错误消息参数
     * @param defaultMessage 默认消息
     * @return 实例
     * @see MessageSource#getMessage(String, Object[], String, Locale)
     */
    public static DataBindingException ofCode(String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        Assert.hasText(code, "code is required");
        var ex = new DataBindingException();
        ex.innerList.add(new SimpleMessageSourceResolvable(code, arguments, defaultMessage));
        return ex;
    }

    /**
     * 创建非国际化的消息
     *
     * @param messageTemplate 默认消息模板
     * @param args            参数
     * @return 实例
     * @see StringFormatter
     */
    public static DataBindingException ofFormattedMessage(String messageTemplate, Object... args) {
        return of(StringFormatter.format(messageTemplate, args));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<MessageSourceResolvable> iterator() {
        return innerList.iterator();
    }

    /**
     * 获取保存的错误消息条目数量
     *
     * @return 错误消息条目数量
     */
    public int size() {
        return innerList.size();
    }

    /**
     * 判断本实例保存的错误消息条目数量是否为空
     *
     * @return 空时返回 {@code true}
     */
    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    /**
     * 判断本实例保存的错误消息条目数量是否不为空
     *
     * @return 空时返回 {@code false}
     */
    public boolean isNotEmpty() {
        return !innerList.isEmpty();
    }

    public List<String> getMessages(MessageSource messageSource) {
        return getMessages(messageSource, null, null);
    }

    public List<String> getMessages(MessageSource messageSource, @Nullable Locale locale) {
        return getMessages(messageSource, locale, null);
    }

    public List<String> getMessages(MessageSource messageSource, @Nullable Locale locale, @Nullable Comparator<String> ordering) {
        return stream()
                .map(msr -> AbstractMessageResolvableException.getMessage(messageSource, msr, locale))
                .sorted(Objects.requireNonNullElseGet(ordering, NoopComparator::getInstance))
                .collect(Collectors.toList());
    }

}
