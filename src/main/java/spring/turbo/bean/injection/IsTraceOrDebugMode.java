package spring.turbo.bean.injection;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * 注入{@code boolean}值表达应用程序是否处于debug或trace模式
 *
 * @author 应卓
 * @see Value
 * @since 1.2.3
 */
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Value("#{(environment.getProperty('debug') != null && environment.getProperty('debug') != 'false') || (environment.getProperty('trace') != null && environment.getProperty('trace') != 'false')}")
public @interface IsTraceOrDebugMode {
}
