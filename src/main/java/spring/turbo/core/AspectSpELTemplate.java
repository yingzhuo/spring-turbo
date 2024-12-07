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
 * @see SpEL
 * @since 3.4.0
 */
public final class AspectSpELTemplate<T> implements Serializable {

    private final String expression;
    private final Map<String, Object> expressionVariables;

    public static AspectSpELTemplate<String> newInstance(String expression, ProceedingJoinPoint joinPoint) {
        return newInstance(expression, joinPoint, String.class);
    }

    public static <R> AspectSpELTemplate<R> newInstance(String expression, ProceedingJoinPoint joinPoint, Class<R> returnType) {
        Assert.notNull(returnType, "returnType is required");
        return new AspectSpELTemplate<>(expression, joinPoint);
    }

    private AspectSpELTemplate(String expression, ProceedingJoinPoint joinPoint) {
        Assert.hasText(expression, "expression is required");
        Assert.notNull(joinPoint, "joinPoint is required");
        this.expression = expression;

        var map = new HashMap<String, Object>();
        map.put("args", joinPoint.getArgs());
        map.put("argsLength", joinPoint.getArgs().length);
        map.put("method", ((MethodSignature) joinPoint.getSignature()).getMethod());
        map.put("methodName", ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
        map.put("target", joinPoint.getTarget());
        map.put("targetType", joinPoint.getTarget().getClass().getName());

        this.expressionVariables = Collections.unmodifiableMap(map);
    }

    public T getValue() {
        return SpEL.getValue(expression, null, expressionVariables);
    }

    public Map<String, Object> getExpressionVariables() {
        return expressionVariables;
    }

}
