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
import spring.turbo.io.ResourceOptions;

/**
 * @author 应卓
 * @since 2.0.1
 */
public final class ConditionalOnResourceOptionCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final var attributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnResourceOption.class.getName()));

        if (attributes == null) {
            return ConditionOutcome.noMatch("resources absent");
        }

        final var resources = attributes.getStringArray("resources");
        if (resources.length == 0) {
            return ConditionOutcome.noMatch("resources absent");
        }

        final var match = ResourceOptions.builder()
                .add(resources)
                .build()
                .isPresent();

        if (match) {
            return ConditionOutcome.match("resources at least 1 absent");
        } else {
            return ConditionOutcome.noMatch("resources absent");
        }
    }

}
