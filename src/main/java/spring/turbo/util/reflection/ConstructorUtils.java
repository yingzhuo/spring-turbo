package spring.turbo.util.reflection;

import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;

/**
 * 反射工具 - 构造方法
 *
 * @author 应卓
 * @see MethodUtils
 * @see FieldUtils
 * @since 1.2.1
 */
public final class ConstructorUtils {

    /**
     * 私有构造方法
     */
    private ConstructorUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static <T> void makeAccessible(Constructor<T> constructor) {
        ReflectionUtils.makeAccessible(constructor);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nullable
    public static <T> Constructor<T> accessibleConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        try {
            return ReflectionUtils.accessibleConstructor(clazz, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
