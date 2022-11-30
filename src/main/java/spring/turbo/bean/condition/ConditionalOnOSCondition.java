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
import spring.turbo.util.CollectionUtils;
import spring.turbo.util.OS;

import java.util.HashSet;

/**
 * @author 应卓
 * @see ConditionalOnOS
 * @since 2.0.0
 */
final class ConditionalOnOSCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final var current = OS.get();
        final var attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnOS.class.getName()));
        if (attributes == null) {
            return ConditionOutcome.noMatch("not match on current operation-system");
        }

        final var stringSet = new HashSet<OS>();
        CollectionUtils.nullSafeAddAll(stringSet, (OS[]) attributes.get("value"));

        if (stringSet.contains(current)) {
            return ConditionOutcome.match();
        } else {
            return ConditionOutcome.noMatch("not match on current operation-system");
        }
    }

}
