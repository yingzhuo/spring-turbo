package spring.turbo.bean.jsr380;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 判断正则表达式本身是否合法
 *
 * @author 应卓
 * @since 1.0.6
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = ValidRegexValidator.class)
public @interface ValidRegex {

    public String message() default "{spring.turbo.bean.jsr380.ValidRegex.message}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
