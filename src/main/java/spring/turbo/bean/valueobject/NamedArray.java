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
import org.springframework.lang.Nullable;
import spring.turbo.lang.Mutable;
import spring.turbo.util.Asserts;

import java.util.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Mutable
public class NamedArray<T> implements Iterable<T> {

    // 数据
    private final List<T> array;

    // 名字
    private final List<String> names;

    // 别名
    private final Map<String, String> aliases;

    /**
     * 构造方法
     *
     * @param array 数据
     * @param names 名称
     */
    NamedArray(List<T> array, List<String> names, @Nullable Map<String, String> aliases) {
        Asserts.notNull(array);
        Asserts.notNull(names);
        Asserts.noNullElements(names, (String) null);
        Asserts.isTrue(array.size() == names.size());

        this.array = Collections.unmodifiableList(array);
        this.names = Collections.unmodifiableList(names);
        this.aliases = aliases == null ? Collections.emptyMap() : Collections.unmodifiableMap(aliases);
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
            map.put(toAliasIfNecessary(names.get(i)), array.get(i));
        }
        return Collections.unmodifiableMap(map);
    }

    public MutablePropertyValues toMutablePropertyValues() {
        final MutablePropertyValues mpv = new MutablePropertyValues();
        for (int i = 0; i < array.size(); i++) {
            mpv.add(toAliasIfNecessary(names.get(i)), array.get(i));
        }
        return mpv;
    }

    private String toAliasIfNecessary(String name) {
        return this.aliases.getOrDefault(name, name);
    }

}
