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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 应卓
 * @see FieldPredicateFactories
 * @since 1.2.1
 */
public final class FieldUtils {

    /**
     * 私有构造方法
     */
    private FieldUtils() {
        super();
    }

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
