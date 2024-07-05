package spring.turbo.bean.jsr380;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 验证字符串必须有的前缀
 *
 * @author 应卓
 * @see ValidSuffix
 * @since 2.0.7
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = ValidPrefixValidator.class)
public @interface ValidPrefix {

    public String[] value() default {};

    public boolean ignoreCase() default false;

    public String message() default "{spring.turbo.bean.jsr380.ValidPrefix.message}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
