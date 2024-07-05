package spring.turbo.bean.jsr380;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.util.Objects;

/**
 * @author 应卓
 * @see FieldsValueMatch
 * @since 1.0.0
 */
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    @Nullable
    private String field;

    @Nullable
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch annotation) {
        this.field = annotation.field();
        this.fieldMatch = annotation.fieldMatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(@Nullable Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Asserts.notNull(field);
        Asserts.notNull(fieldMatch);

        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        return Objects.equals(fieldValue, fieldMatchValue);
    }

}
