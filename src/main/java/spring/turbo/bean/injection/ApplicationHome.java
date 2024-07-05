package spring.turbo.bean.injection;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * {@code @Value("${spring.application.home}")} 的快捷方式
 *
 * @author 应卓
 * @see Value
 * @since 2.0.8
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Value("${spring.application.home}")
public @interface ApplicationHome {
}
