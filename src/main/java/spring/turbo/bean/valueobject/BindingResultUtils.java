/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static spring.turbo.util.StringPool.COMMA;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class BindingResultUtils {

    private BindingResultUtils() {
        super();
    }

    public static List<String> getDefaultErrorMessages(BindingResult bindingResult) {
        Asserts.notNull(bindingResult);
        return bindingResult
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static String getJoinedDefaultErrorMessages(BindingResult bindingResult) {
        return getJoinedDefaultErrorMessages(bindingResult, COMMA);
    }

    public static String getJoinedDefaultErrorMessages(BindingResult bindingResult, @Nullable String separator) {
        separator = StringUtils.nullToEmpty(separator);
        return String.join(separator, getDefaultErrorMessages(bindingResult));
    }

    public static String getJoinedErrorMessages(BindingResult bindingResult, final MessageSource messageSource) {
        return getJoinedErrorMessages(bindingResult, messageSource, null);
    }

    public static String getJoinedErrorMessages(BindingResult bindingResult, final MessageSource messageSource, final @Nullable Locale locale) {
        return getJoinedErrorMessages(bindingResult, messageSource, locale, COMMA);
    }

    public static String getJoinedErrorMessages(BindingResult bindingResult, final MessageSource messageSource, final @Nullable Locale locale, @Nullable String separator) {
        separator = StringUtils.nullToEmpty(separator);
        return String.join(separator, getErrorMessages(bindingResult, messageSource, locale));
    }

    public static List<String> getErrorMessages(BindingResult bindingResult, final MessageSource messageSource) {
        return getErrorMessages(bindingResult, messageSource, null);
    }

    public static List<String> getErrorMessages(BindingResult bindingResult, final MessageSource messageSource, final @Nullable Locale locale) {
        Asserts.notNull(bindingResult);
        Asserts.notNull(messageSource);

        final Locale localeToUse = locale != null ? locale : Locale.getDefault();
        if (bindingResult.hasErrors()) {
            return bindingResult
                    .getAllErrors()
                    .stream()
                    .map(e -> messageSource.getMessage(e, localeToUse))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
