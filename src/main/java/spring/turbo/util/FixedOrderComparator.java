/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.Comparator;
import java.util.Objects;

/**
 * 固定顺序比较器
 *
 * @param <T> 泛型
 * @author 应卓
 */
@SuppressWarnings("unchecked")
public class FixedOrderComparator<T> implements Comparator<T> {

    private final boolean atEndIfMiss;
    private final T[] array;

    /**
     * 构造方法
     *
     * @param objs 参与排序的数组，数组的元素位置决定了对象的排序先后
     */
    public FixedOrderComparator(T... objs) {
        this(false, objs);
    }

    /**
     * 构造方法
     *
     * @param atEndIfMiss 如果不在列表中是否排在后边
     * @param objs        参与排序的数组，数组的元素位置决定了对象的排序先后
     */
    public FixedOrderComparator(boolean atEndIfMiss, T... objs) {
        Asserts.notNull(objs);
        this.atEndIfMiss = atEndIfMiss;
        this.array = objs;
    }

    @Override
    public int compare(T o1, T o2) {
        final int index1 = getOrder(o1);
        final int index2 = getOrder(o2);
        return Integer.compare(index1, index2);
    }

    /**
     * 查找对象类型所在列表的位置
     *
     * @param object 对象
     * @return 位置，未找到位置根据{@link #atEndIfMiss}取不同值，false返回-1，否则返回列表长度
     */
    private int getOrder(T object) {
        int order = -1;

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], object)) {
                order = i;
                break;
            }
        }

        if (order < 0) {
            order = this.atEndIfMiss ? this.array.length : -1;
        }
        return order;
    }
}
