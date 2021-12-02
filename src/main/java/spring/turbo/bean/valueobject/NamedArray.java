/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.beans.MutablePropertyValues;
import spring.turbo.lang.Mutable;
import spring.turbo.util.Asserts;

import java.util.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Mutable
public class NamedArray<T> implements Iterable<T> {

    private final List<T> array;
    private final List<String> names;

    NamedArray(List<T> array, List<String> names) {
        Asserts.notNull(array);
        Asserts.noNullElements(array, "array is null or has null element(s)");
        Asserts.notNull(names, "names is null or has null element(s)");
        Asserts.noNullElements(names, (String) null);
        Asserts.isTrue(array.size() == names.size());

        this.array = Collections.unmodifiableList(array);
        this.names = Collections.unmodifiableList(names);
    }

    public static <T> NamedArrayBuilder<T> builder() {
        return new NamedArrayBuilder<>();
    }

    @Override
    public Iterator<T> iterator() {
        return array.listIterator();
    }

    public int size() {
        return array.size();
    }

    public Map<String, T> zip() {
        final Map<String, T> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            map.put(names.get(i), array.get(i));
        }
        return Collections.unmodifiableMap(map);
    }

    public MutablePropertyValues toMutablePropertyValues() {
        final MutablePropertyValues mpv = new MutablePropertyValues();
        for (int i = 0; i < array.size(); i++) {
            mpv.add(names.get(i), array.get(i));
        }
        return mpv;
    }

}
