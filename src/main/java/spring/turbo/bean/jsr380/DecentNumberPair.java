/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.jsr380;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import spring.turbo.bean.DateRange;
import spring.turbo.bean.NumberPair;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * {@link DateRange} 检查用元注释
 *
 * @author 应卓
 * @see NumberPair
 * @since 2.0.3
 */
@Inherited
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DecentNumberPairValidator.class)
public @interface DecentNumberPair {

    public double min() default Double.MIN_VALUE;

    public double max() default Double.MAX_VALUE;

    public String message() default "{spring.turbo.bean.jsr380.DecentNumberPair.message}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
