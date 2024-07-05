package spring.turbo.bean.jsr380;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.Nullable;
import spring.turbo.util.RegexUtils;

/**
 * @author 应卓
 * @see ValidRegex
 * @since 1.0.6
 */
public class ValidRegexValidator implements ConstraintValidator<ValidRegex, CharSequence> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(@Nullable CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return RegexUtils.isValidRegex(value.toString());
    }

}
