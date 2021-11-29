/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.util.Assert;
import spring.turbo.lang.Immutable;

import java.util.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Immutable
public class NamedArray<T> implements Iterable<T> {

    public static <T> NamedArrayBuilder<T> builder() {
        return new NamedArrayBuilder<>();
    }

    private final List<T> array;
    private final List<String> names;

    NamedArray(List<T> array, List<String> names) {
        Assert.noNullElements(array, "array is null or has null element(s)");
        Assert.noNullElements(names, "names is null or has null element(s)");
        Assert.state(array.size() == names.size(), "array and names have different size");

        this.array = Collections.unmodifiableList(array);
        this.names = Collections.unmodifiableList(names);
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

}
