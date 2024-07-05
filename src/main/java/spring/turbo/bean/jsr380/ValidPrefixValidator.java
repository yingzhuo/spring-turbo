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

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(ValidPrefix annotation) {
        this.permitPrefix = annotation.value();
        this.ignoreCase = annotation.ignoreCase();
    }

    /**
     * {@inheritDoc}
     */
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
