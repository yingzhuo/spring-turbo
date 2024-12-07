package spring.turbo.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Aspect环绕式切面相关小工具
 *
 * @author 应卓
 * @since 3.4.0
 */
public final class SpELForAspectAround<T> implements Serializable {

    private final String expression;
    private final Map<String, Object> expressionVariables;

    public static SpELForAspectAround<String> newInstance(String expression, ProceedingJoinPoint joinPoint) {
        return newInstance(expression, joinPoint, String.class);
    }

    public static <R> SpELForAspectAround<R> newInstance(String expression, ProceedingJoinPoint joinPoint, Class<R> returnType) {
        return new SpELForAspectAround<>(expression, joinPoint);
    }

    private SpELForAspectAround(String expression, ProceedingJoinPoint joinPoint) {
        Assert.hasText(expression, "expression is required");
        Assert.notNull(joinPoint, "joinPoint is required");
        this.expression = expression;

        var map = new HashMap<String, Object>();
        map.put("args", joinPoint.getArgs());
        map.put("method", ((MethodSignature) joinPoint.getSignature()).getMethod());
        map.put("target", joinPoint.getTarget());
        map.put("targetType", joinPoint.getTarget().getClass().getName());

        this.expressionVariables = Collections.unmodifiableMap(map);
    }

    public T getValue() {
        return SpEL.getValue(expression, null, expressionVariables);
    }

}
