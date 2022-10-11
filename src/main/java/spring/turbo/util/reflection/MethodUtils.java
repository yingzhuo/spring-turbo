/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.reflection;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 反射工具 - Method
 *
 * @author 应卓
 * @see MethodPredicateFactories
 * @see ConstructorUtils
 * @see FieldUtils
 * @since 1.2.1
 */
public final class MethodUtils {

    /**
     * 私有构造方法
     */
    private MethodUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static void makeAccessible(Method method) {
        ReflectionUtils.makeAccessible(method);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static List<Method> find(Class<?> clazz) {
        final List<Method> list = new ArrayList<>();
        ReflectionUtils.doWithMethods(clazz, list::add);
        return list;
    }

    public static List<Method> find(Class<?> clazz, String name) {
        final List<Method> list = new ArrayList<>();
        Optional.ofNullable(ReflectionUtils.findMethod(clazz, name)).ifPresent(list::add);
        return list;
    }

    public static List<Method> find(Class<?> clazz, String name, Class<?>... paramTypes) {
        final List<Method> list = new ArrayList<>();
        Optional.ofNullable(ReflectionUtils.findMethod(clazz, name, paramTypes)).ifPresent(list::add);
        return list;
    }

}
