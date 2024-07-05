package spring.turbo.bean.jsr380;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 判断字符串是不是合法的JSON
 *
 * @author 应卓
 * @since 2.0.4
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = ValidJsonValidator.class)
public @interface ValidJson {

    public String message() default "{spring.turbo.bean.jsr380.ValidJson.message}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
