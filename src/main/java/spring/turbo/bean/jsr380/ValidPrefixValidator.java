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

import static spring.turbo.util.StringUtils.startsWithIgnoreCase;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class ValidPrefixValidator implements ConstraintValidator<ValidPrefix, String> {

    @Nullable
    private String[] permitPrefix;

    private boolean ignoreCase = false;

    @Override
    public void initialize(ValidPrefix annotation) {
        this.permitPrefix = annotation.value();
        this.ignoreCase = annotation.ignoreCase();
    }

    @Override
    public boolean isValid(@Nullable String value, ConstraintValidatorContext context) {
        if (value == null || permitPrefix == null || permitPrefix.length == 0) {
            return true;
        }

        if (value.isBlank()) {
            return false;
        }

        for (String prefix : this.permitPrefix) {
            if (this.ignoreCase) {
                if (startsWithIgnoreCase(value, prefix)) {
                    return true;
                }
            } else {
                if (value.startsWith(prefix)) {
                    return true;
                }
            }
        }
        return false;
    }

}
