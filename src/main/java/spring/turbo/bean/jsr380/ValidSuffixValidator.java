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

    private boolean ignoreCase;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(ValidSuffix annotation) {
        this.permitSuffix = annotation.value();
        this.ignoreCase = annotation.ignoreCase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(@Nullable String value, ConstraintValidatorContext context) {
        if (value == null || permitSuffix == null || permitSuffix.length == 0) {
            return true;
        }

        if (value.isBlank()) {
            return false;
        }

        for (String prefix : this.permitSuffix) {
            if (this.ignoreCase) {
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
