package spring.turbo.util.collection;

import java.util.Comparator;

/**
 * 实际不排序的排序器
 *
 * @param <T> 排序元素泛型
 * @author 应卓
 * @see #getInstance()
 * @since 3.3.1
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public final class NoopComparator<T> implements Comparator<T> {

    /**
     * 私有构造方法
     */
    private NoopComparator() {
    }

    /**
     * 获取单例对象
     *
     * @param <T> 排序元素泛型
     * @return 实例
     */
    public static <T> NoopComparator<T> getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(T o1, T o2) {
        return 0;
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final NoopComparator INSTANCE = new NoopComparator();
    }

}
