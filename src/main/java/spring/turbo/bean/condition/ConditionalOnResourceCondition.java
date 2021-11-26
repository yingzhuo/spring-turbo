/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import spring.turbo.io.ResourceOptions;

/**
 * @author 应卓
 * @since 1.0.0
 */
class ConditionalOnResourceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        final AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnResource.class.getName()));

        if (annotationAttributes == null) {
            return false;
        }

        final String[] locations = annotationAttributes.getStringArray("value");

        if (locations.length == 0) {
            return false;
        }

        return ResourceOptions.builder()
                .resourceLoader(context.getResourceLoader())
                .add(locations)
                .build()
                .isPresent();
    }

}
