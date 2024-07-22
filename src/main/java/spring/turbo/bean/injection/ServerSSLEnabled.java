package spring.turbo.bean.injection;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * {@code @Value("${server.ssl.enabled:false}")} 的快捷方式
 *
 * @author 应卓
 * @see Value
 * @since 2.0.5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Value("${server.ssl.enabled:false}")
public @interface ServerSSLEnabled {
}
