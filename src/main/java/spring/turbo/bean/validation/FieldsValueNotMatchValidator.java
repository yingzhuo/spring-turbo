package spring.turbo.bean.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author 应卓
 * @see FieldsValueNotMatch
 * @since 1.0.0
 */
public class FieldsValueNotMatchValidator implements ConstraintValidator<FieldsValueNotMatch, Object> {

    @Nullable
    private String field;

    @Nullable
    private String fieldMatch;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(FieldsValueNotMatch annotation) {
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

        Assert.notNull(field, "field is null");
        Assert.notNull(fieldMatch, "fieldMatch is null");

        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        return !Objects.equals(fieldValue, fieldMatchValue);
    }

}
