package spring.turbo.util.reflection;

import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;

/**
 * @author 应卓
 * @see MethodUtils
 * @since 1.2.1
 */
public final class MethodPredicateFactories {

    /**
     * 私有构造方法
     */
    private MethodPredicateFactories() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static Predicate<Method> alwaysTrue() {
        return m -> true;
    }

    public static Predicate<Method> alwaysFalse() {
        return m -> false;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static Predicate<Method> not(Predicate<Method> predicate) {
        Assert.notNull(predicate, "predicate is required");
        return m -> !predicate.test(m);
    }

    @SafeVarargs
    public static Predicate<Method> any(Predicate<Method>... predicates) {
        Assert.notNull(predicates, "predicate is required");
        Assert.isTrue(predicates.length >= 1, "predicate must greater than 1");
        return m -> {
            for (final Predicate<Method> predicate : predicates) {
                if (predicate.test(m)) {
                    return true;
                }
            }
            return false;
        };
    }

    @SafeVarargs
    public static Predicate<Method> all(Predicate<Method>... predicates) {
        Assert.notNull(predicates, "predicate is required");
        Assert.isTrue(predicates.length >= 1, "predicate must greater than 1");
        return m -> {
            for (final Predicate<Method> predicate : predicates) {
                if (!predicate.test(m)) {
                    return false;
                }
            }
            return true;
        };
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static Predicate<Method> isUserDeclaredMethod() {
        return m -> !m.isBridge() && !m.isSynthetic() && (m.getDeclaringClass() != Object.class);
    }

    public static Predicate<Method> isNotUserDeclaredMethod() {
        return not(isUserDeclaredMethod());
    }

    public static Predicate<Method> withAnnotation(Class<? extends Annotation> annotationClass) {
        return m -> m.getAnnotation(annotationClass) != null;
    }

    public static Predicate<Method> withoutAnnotation(Class<? extends Annotation> annotationClass) {
        return not(withAnnotation(annotationClass));
    }

    public static Predicate<Method> isPublic() {
        return m -> Modifier.isPublic(m.getModifiers());
    }

    public static Predicate<Method> isNotPublic() {
        return not(isPublic());
    }

    public static Predicate<Method> isPrivate() {
        return m -> Modifier.isPrivate(m.getModifiers());
    }

    public static Predicate<Method> isNotPrivate() {
        return not(isPrivate());
    }

    public static Predicate<Method> isProtected() {
        return m -> Modifier.isProtected(m.getModifiers());
    }

    public static Predicate<Method> isNotProtected() {
        return not(isProtected());
    }

    public static Predicate<Method> isStatic() {
        return m -> Modifier.isStatic(m.getModifiers());
    }

    public static Predicate<Method> isNotStatic() {
        return not(isStatic());
    }

    public static Predicate<Method> isFinal() {
        return m -> Modifier.isFinal(m.getModifiers());
    }

    public static Predicate<Method> isNotFinal() {
        return not(isFinal());
    }

    public static Predicate<Method> isAbstract() {
        return m -> Modifier.isAbstract(m.getModifiers());
    }

    public static Predicate<Method> isNotAbstract() {
        return not(isAbstract());
    }

    public static Predicate<Method> isNative() {
        return m -> Modifier.isNative(m.getModifiers());
    }

    public static Predicate<Method> isNotNative() {
        return not(isNative());
    }

}
