package spring.turbo.bean.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import spring.turbo.io.RichResource;
import spring.turbo.util.reflection.InstanceUtils;

import java.util.function.Predicate;

/**
 * @author 应卓
 * @since 2.0.1
 */
@SuppressWarnings("unchecked")
public final class ConditionalOnResourceOptionCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var attributes = AnnotationAttributes
                .fromMap(metadata.getAnnotationAttributes(ConditionalOnResourceOption.class.getName()));

        if (attributes == null) {
            return ConditionOutcome.noMatch("resources absent");
        }

        var resources = attributes.getStringArray("value");
        var discriminatorType = (Class<? extends Predicate<Resource>>) attributes.getClass("discriminator");

        if (resources.length == 0) {
            return ConditionOutcome.noMatch("resources absent");
        }

        var match = RichResource.builder().blankSafeAddLocations(resources)
                .discriminator(InstanceUtils.newInstanceElseThrow(discriminatorType)).build().isPresent();

        if (match) {
            return ConditionOutcome.match();
        } else {
            return ConditionOutcome.noMatch("resources absent");
        }
    }

}
