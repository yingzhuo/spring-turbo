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
import spring.turbo.bean.NumberPair;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 检查 {@link NumberPair} 是否有序
 *
 * @author 应卓
 * @see NumberPair
 * @see NumberPair#isOrdered()
 * @since 1.0.8
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = OrderedNumberPairValidator.class)
@Deprecated(forRemoval = true) // 逻辑过于混乱，不好用
public @interface OrderedNumberPair {

    public String message();

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
