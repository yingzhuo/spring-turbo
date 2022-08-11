/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.Nullable;
import spring.turbo.lang.Immutable;
import spring.turbo.util.StringPool;
import spring.turbo.util.StringUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author 应卓
 * @since 1.1.4
 */
@Immutable
public class NumberZones implements Iterable<NumberPair> {

    private final List<NumberPair> list;

    /**
     * 构造方法
     */
    public NumberZones() {
        this(null);
    }

    /**
     * 构造方法
     *
     * @param list NumberPair
     */
    public NumberZones(@Nullable List<NumberPair> list) {
        this.list = list != null ?
                Collections.unmodifiableList(list) :
                Collections.emptyList();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        return StringUtils.nullSafeJoin(this, StringPool.COMMA);
    }

    @Override
    public Iterator<NumberPair> iterator() {
        return list.iterator();
    }

}
