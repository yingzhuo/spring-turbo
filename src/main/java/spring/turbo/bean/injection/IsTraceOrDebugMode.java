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
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Value("#{(environment.getProperty('debug') != null && environment.getProperty('debug') != 'false') || (environment.getProperty('trace') != null && environment.getProperty('trace') != 'false')}")
@Deprecated(since = "3.4.0", forRemoval = false) // 太不常用，也未严格测试
public @interface IsTraceOrDebugMode {
}
