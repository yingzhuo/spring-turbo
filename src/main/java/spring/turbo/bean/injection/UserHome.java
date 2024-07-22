package spring.turbo.bean.injection;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * {@code @Value("${user.home}")} 的快捷方式
 *
 * @author 应卓
 * @see Value
 * @since 3.3.2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Value("${user.home}")
public @interface UserHome {
}
