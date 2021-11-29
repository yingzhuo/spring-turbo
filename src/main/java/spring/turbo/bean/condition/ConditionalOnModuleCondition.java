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
import spring.turbo.core.Logic;
import spring.turbo.integration.Modules;

import java.util.Arrays;
import java.util.Objects;

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

        if (annotationAttributes == null) {
            return false;
        }

        final String[] moduleNames = annotationAttributes.getStringArray("value");
        if (moduleNames.length == 0) {
            return false;
        }

        final Logic logic = annotationAttributes.getEnum("logic");

        if (logic == Logic.ANY) {
            for (String actual : Modules.ALL_MODULE_NAMES) {
                for (String moduleName : moduleNames) {
                    if (Objects.equals(actual, moduleName)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            return Modules.ALL_MODULE_NAMES.containsAll(Arrays.asList(moduleNames));
        }
    }

}
