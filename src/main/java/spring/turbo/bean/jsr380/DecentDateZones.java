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
import spring.turbo.bean.DateZones;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * {@link DateZones} 检查用元注释
 *
 * @author 应卓
 *
 * @see DateZones
 *
 * @since 2.0.1
 */
@Inherited
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DecentDateZonesConstraintValidator.class)
public @interface DecentDateZones {

    public String message() default "{spring.turbo.bean.jsr380.DecentDateZones.message}";

    public int mixSize() default Integer.MIN_VALUE;

    public int maxSize() default Integer.MAX_VALUE;

    public boolean mustBeContinuous() default true;

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
