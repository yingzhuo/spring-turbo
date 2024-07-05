package spring.turbo.util.collection;

import java.util.Comparator;

/**
 * 自然顺序比较器
 *
 * @param <E> 泛型
 * @author 应卓
 * @since 2.0.12
 * @deprecated see {@link Comparator#naturalOrder()}
 */
@Deprecated(since = "3.3.1")
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
