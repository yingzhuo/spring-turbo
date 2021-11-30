/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @param <T> 数据类型
 * @author 应卓
 * @since 1.0.0
 */
public final class NamedArrayBuilder<T> {

    private final List<T> array = new LinkedList<>();
    private final List<String> names = new LinkedList<>();

    NamedArrayBuilder() {
        super();
    }

    public final NamedArrayBuilder<T> addNames(String... names) {
        this.names.addAll(Arrays.asList(names));
        return this;
    }

    public final NamedArrayBuilder<T> addNames(Collection<String> names) {
        this.names.addAll(names);
        return this;
    }

    @SafeVarargs
    public final NamedArrayBuilder<T> addObjects(T... objects) {
        this.array.addAll(Arrays.asList(objects));
        return this;
    }

    public final NamedArrayBuilder<T> addObjects(Collection<T> objects) {
        this.array.addAll(objects);
        return this;
    }

    public NamedArray<T> build() {
        return build(false);
    }

    public NamedArray<T> build(boolean zipWithIndex) {
        if (zipWithIndex) {
            names.clear();
            for (int i = 0; i < array.size(); i++) {
                names.add(Integer.toString(i));
            }
        }
        return new NamedArray<>(array, names);
    }

}