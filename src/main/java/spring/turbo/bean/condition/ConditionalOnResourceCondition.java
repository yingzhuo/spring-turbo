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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.CollectionUtils;
import spring.turbo.core.Logic;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @see ConditionalOnResource
 * @since 1.0.0
 */
final class ConditionalOnResourceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        final AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnResource.class.getName()));

        if (CollectionUtils.isEmpty(annotationAttributes)) {
            return false;
        }

        final String[] locations = annotationAttributes.getStringArray("value");

        if (locations.length == 0) {
            return false;
        }

        final Logic logic = annotationAttributes.getEnum("logic");
        final ResourceLoader resourceLoader = context.getResourceLoader();
        final List<Resource> resources = Stream.of(locations).map(resourceLoader::getResource).collect(Collectors.toList());
        final Predicate<Resource> discriminator = resource -> resource != null && resource.exists();

        if (logic == Logic.ANY) {
            return resources.stream().anyMatch(discriminator);
        } else {
            return resources.stream().allMatch(discriminator);
        }
    }

}
