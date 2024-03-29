/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import spring.turbo.SpringTurboModules;

/**
 * @author 应卓
 *
 * @since 2.0.13
 */
public final class ConditionalOnSpringTurboModulesCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var attributes = AnnotationAttributes
                .fromMap(metadata.getAnnotationAttributes(ConditionalOnSpringTurboModules.class.getName()));

        if (attributes == null) {
            return ConditionOutcome.noMatch("no modules");
        }

        var modules = attributes.getStringArray("modules");
        var logic = (ConditionalOnSpringTurboModules.Logic) attributes.getEnum("logic");

        if (modules.length == 0) {
            return ConditionOutcome.noMatch("no modules");
        }

        var matches = logic == ConditionalOnSpringTurboModules.Logic.ANY ? SpringTurboModules.presentAny(modules)
                : SpringTurboModules.presentAll(modules);

        if (matches) {
            return ConditionOutcome.match();
        } else {
            return ConditionOutcome.noMatch("not match");
        }
    }

}
