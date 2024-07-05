package spring.turbo.bean.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import spring.turbo.util.OS;
import spring.turbo.util.collection.CollectionUtils;

import java.util.HashSet;

/**
 * @author 应卓
 * @see ConditionalOnOS
 * @since 2.0.1
 */
public final class ConditionalOnOSCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var attributes = AnnotationAttributes
                .fromMap(metadata.getAnnotationAttributes(ConditionalOnOS.class.getName()));
        if (attributes == null) {
            return ConditionOutcome.noMatch("not match on current operation-system");
        }

        var set = new HashSet<OS>();
        CollectionUtils.nullSafeAddAll(set, (OS[]) attributes.get("value"));

        if (set.contains(OS.get())) {
            return ConditionOutcome.match();
        } else {
            return ConditionOutcome.noMatch("not match on current operation-system");
        }
    }

}
