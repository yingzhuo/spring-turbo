/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.jsr380;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.Nullable;

import static spring.turbo.util.StringUtils.endsWithIgnoreCase;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class ValidSuffixValidator implements ConstraintValidator<ValidSuffix, String> {

    @Nullable
    private String[] permitSuffix;

    private boolean ignoreCases;

    @Override
    public void initialize(ValidSuffix annotation) {
        this.permitSuffix = annotation.value();
        this.ignoreCases = annotation.ignoreCases();
    }

    @Override
    public boolean isValid(@Nullable String value, ConstraintValidatorContext context) {
        if (value == null || permitSuffix == null || permitSuffix.length == 0) {
            return true;
        }

        if (value.isBlank()) {
            return false;
        }

        for (String prefix : this.permitSuffix) {
            if (this.ignoreCases) {
                if (endsWithIgnoreCase(value, prefix)) {
                    return true;
                }
            } else {
                if (value.endsWith(prefix)) {
                    return true;
                }
            }
        }
        return false;
    }

}
