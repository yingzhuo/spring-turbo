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
import org.springframework.util.CollectionUtils;
import spring.turbo.core.Logic;
import spring.turbo.integration.Modules;

import java.util.stream.Stream;

/**
 * @author 应卓
 * @see ConditionalOnModule
 * @since 1.0.0
 */
final class ConditionalOnModuleCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnModule.class.getName()));

        if (CollectionUtils.isEmpty(annotationAttributes)) {
            return false;
        }

        final Logic logic = annotationAttributes.getEnum("logic");
        final Modules[] modules = (Modules[]) annotationAttributes.get("value");

        if (modules.length == 0) {
            return false;
        }

        if (logic == Logic.ANY) {
            return Stream.of(modules).anyMatch(Modules::isPresent);
        } else {
            return Stream.of(modules).allMatch(Modules::isPresent);
        }
    }

}
