package spring.turbo.bean.jsr380;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Repeatable(FieldsValueMatch.List.class)
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsValueMatchValidator.class)
public @interface FieldsValueMatch {

    public String message() default "";

    public String field();

    public String fieldMatch();

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

    @Inherited
    @Documented
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        public FieldsValueMatch[] value();
    }

}
