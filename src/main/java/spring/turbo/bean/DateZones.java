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
import spring.turbo.util.StringPool;
import spring.turbo.util.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 日期范围组
 *
 * @author 应卓
 * @since 1.3.1
 */
public class DateZones implements Iterable<DateRange>, Serializable {

    private final List<DateRange> list;

    public DateZones() {
        this(null);
    }

    public DateZones(@Nullable List<DateRange> list) {
        this.list = list != null ? Collections.unmodifiableList(list) : Collections.emptyList();
    }

    @Override
    public Iterator<DateRange> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return StringUtils.nullSafeJoin(this, StringPool.SEMICOLON);
    }

}
