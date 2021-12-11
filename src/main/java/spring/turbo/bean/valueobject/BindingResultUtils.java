/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import spring.turbo.util.Asserts;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static spring.turbo.util.StringPool.COMMA;
import static spring.turbo.util.StringPool.EMPTY;

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

    public static String getJoinedDefaultErrorMessages(BindingResult bindingResult, String separator) {
        separator = !Objects.isNull(separator) ? separator : EMPTY;
        return String.join(separator, getDefaultErrorMessages(bindingResult));
    }

}
