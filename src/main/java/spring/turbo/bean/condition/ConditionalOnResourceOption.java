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
import org.springframework.core.annotation.AliasFor;
import spring.turbo.io.ResourceOptionDiscriminator;

import java.lang.annotation.*;

/**
 * @author 应卓
 * @see spring.turbo.io.ResourceOption
 * @see ResourceOptionDiscriminator
 * @since 2.0.1
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionalOnResourceOptionCondition.class)
public @interface ConditionalOnResourceOption {

    @AliasFor("value")
    public String[] resources() default {};

    @AliasFor("resources")
    public String[] value() default {};

    public Class<? extends ResourceOptionDiscriminator> discriminator() default ResourceOptionDiscriminator.Default.class;

}
