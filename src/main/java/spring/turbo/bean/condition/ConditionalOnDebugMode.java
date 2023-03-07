/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
