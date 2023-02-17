/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.collection;

import java.util.Comparator;

/**
 * 自然顺序比较器
 *
 * @param <E> 泛型
 * @author 应卓
 * @since 2.0.12
 */
public class ComparableComparator<E extends Comparable<? super E>> implements Comparator<E> {

    /**
     * 私有构造方法
     */
    public ComparableComparator() {
        super();
    }

    @Override
    public int compare(final E obj1, final E obj2) {
        return obj1.compareTo(obj2);
    }

}
