package spring.turbo.util.reflection;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具 - Field
 *
 * @author 应卓
 * @see FieldPredicateFactories
 * @see ConstructorUtils
 * @see MethodUtils
 * @since 1.2.1
 */
public final class FieldUtils {

    /**
     * 私有构造方法
     */
    private FieldUtils() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static void makeAccessible(Field field) {
        ReflectionUtils.makeAccessible(field);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static List<Field> find(Class<?> clazz) {
        final List<Field> list = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz, list::add);
        return list;
    }

    public static List<Field> find(Class<?> clazz, String name) {
        final List<Field> list = new ArrayList<>();
        final Field field = ReflectionUtils.findField(clazz, name);
        if (field != null) {
            list.add(field);
        }
        return list;
    }

    public static List<Field> find(Class<?> clazz, String name, Class<?> fieldType) {
        final List<Field> list = new ArrayList<>();
        final Field field = ReflectionUtils.findField(clazz, name, fieldType);
        if (field != null) {
            list.add(field);
        }
        return list;
    }

}
