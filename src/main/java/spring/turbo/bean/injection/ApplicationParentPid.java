package spring.turbo.bean.injection;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

/**
 * {@code @Value("${spring.process.parent-pid}")} 的快捷方式
 *
 * @author 应卓
 * @see Value
 * @since 3.3.1
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Value("${spring.process.parent-pid:-1}")
public @interface ApplicationParentPid {
}
