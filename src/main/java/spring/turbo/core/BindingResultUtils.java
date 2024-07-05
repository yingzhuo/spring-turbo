package spring.turbo.core;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringPool;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@link BindingResult} 相关工具
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class BindingResultUtils {

    /**
     * 私有构造方法
     */
    private BindingResultUtils() {
    }

    public static List<String> getDefaultErrorMessages(@Nullable BindingResult bindingResult) {
        // null 或 没有错误
        if (bindingResult == null || !bindingResult.hasErrors()) {
            return Collections.emptyList();
        }

        return bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull).distinct().sorted().collect(Collectors.toList());
    }

    public static String getJoinedDefaultErrorMessages(@Nullable BindingResult bindingResult) {
        return getJoinedDefaultErrorMessages(bindingResult, StringPool.COMMA);
    }

    public static String getJoinedDefaultErrorMessages(@Nullable BindingResult bindingResult,
                                                       @Nullable String separator) {
        separator = separator == null ? StringPool.EMPTY : separator;
        return String.join(separator, getDefaultErrorMessages(bindingResult));
    }

    public static String getJoinedErrorMessages(@Nullable BindingResult bindingResult,
                                                final MessageSource messageSource) {
        return getJoinedErrorMessages(bindingResult, messageSource, null);
    }

    public static String getJoinedErrorMessages(@Nullable BindingResult bindingResult,
                                                final MessageSource messageSource, final @Nullable Locale locale) {
        return getJoinedErrorMessages(bindingResult, messageSource, locale, StringPool.COMMA);
    }

    public static String getJoinedErrorMessages(@Nullable BindingResult bindingResult,
                                                final MessageSource messageSource, final @Nullable Locale locale, @Nullable String separator) {
        separator = separator == null ? StringPool.EMPTY : separator;
        return String.join(separator, getErrorMessages(bindingResult, messageSource, locale));
    }

    public static List<String> getErrorMessages(@Nullable BindingResult bindingResult,
                                                final MessageSource messageSource) {
        return getErrorMessages(bindingResult, messageSource, Locale.getDefault());
    }

    public static List<String> getErrorMessages(@Nullable BindingResult bindingResult,
                                                final MessageSource messageSource, final @Nullable Locale locale) {
        Asserts.notNull(messageSource);

        // null 或 没有错误
        if (bindingResult == null || !bindingResult.hasErrors()) {
            return Collections.emptyList();
        }

        return bindingResult.getAllErrors().stream()
                .map(e -> messageSource.getMessage(e, locale != null ? locale : Locale.getDefault())).distinct()
                .sorted().collect(Collectors.toList());
    }

}
