package spring.turbo.bean.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 在trace或debug模式下生效
 *
 * @author 应卓
 * @see spring.turbo.bean.injection.IsTraceOrDebugMode
 * @see org.springframework.core.env.Environment
 * @see org.springframework.boot.ApplicationArguments
 * @since 1.3.0
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionalOnDebugModeCondition.class)
public @interface ConditionalOnDebugMode {

    public boolean traceAsDebug() default true;

}
