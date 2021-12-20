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
import spring.turbo.util.SocketUtils;

/**
 * @author 应卓
 * @since 1.0.0
 */
final class ConditionalOnReachableCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        AnnotationAttributes annotationAttributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(ConditionalOnReachable.class.getName()));

        if (CollectionUtils.isEmpty(annotationAttributes)) {
            return false;
        }

        String address = annotationAttributes.getString("address");
        int port = annotationAttributes.getNumber("port");
        int timeoutInMilliseconds = annotationAttributes.getNumber("timeoutInMilliseconds");
        return SocketUtils.isReachable(address, port, timeoutInMilliseconds);
    }

}
