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

import static spring.turbo.util.StringPool.*;

/**
 * @author 应卓
 *
 * @see ConditionalOnDebugMode
 *
 * @since 1.3.0
 */
public final class ConditionalOnDebugModeCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (doMatches(context, metadata)) {
            return ConditionOutcome.match("trace/debug mode enabled");
        } else {
            return ConditionOutcome.noMatch("trace/debug mode not enabled");
        }
    }

    private boolean doMatches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            final var env = context.getEnvironment();
            final var attributes = AnnotationAttributes
                    .fromMap(metadata.getAnnotationAttributes(ConditionalOnDebugMode.class.getName()));

            if (attributes == null) {
                return true;
            }

            final var traceAsDebug = attributes.getBoolean("traceAsDebug");

            if (traceAsDebug) {
                return (env.getProperty(DEBUG) != null && !FALSE.equalsIgnoreCase(env.getProperty(DEBUG)))
                        || (env.getProperty(TRACE) != null && !FALSE.equalsIgnoreCase(env.getProperty(TRACE)));
            } else {
                return env.getProperty(DEBUG) != null && !FALSE.equalsIgnoreCase(env.getProperty(DEBUG));
            }
        } catch (Exception e) {
            return false;
        }
    }

}
