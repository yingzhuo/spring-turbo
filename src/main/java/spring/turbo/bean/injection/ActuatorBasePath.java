package spring.turbo.bean.injection;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * {@code @Value("${management.endpoints.web.base-path:/actuator}")} 的快捷方式
 *
 * @author 应卓
 * @see Value
 * @since 1.1.3
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Value("${management.endpoints.web.base-path:/actuator}")
public @interface ActuatorBasePath {
}
