package spring.turbo.bean.condition;

import org.springframework.context.annotation.Conditional;
import spring.turbo.util.OS;

import java.lang.annotation.*;

/**
 * 基于OS系统的条件
 *
 * @author 应卓
 * @since 2.0.1
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(ConditionalOnOSCondition.class)
public @interface ConditionalOnOS {

    public OS[] value() default {};

}
