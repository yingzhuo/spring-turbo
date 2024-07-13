package spring.turbo.util.collection.iterator;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 组合型比较器
 *
 * @param <T> 泛型
 * @author 应卓
 */
public class CompositeComparator<T> implements Comparator<T> {

    private final List<Comparator<T>> comparators;

    @SafeVarargs
    public CompositeComparator(Comparator<T>... comparators) {
        Assert.notNull(comparators, "comparators is required");
        Assert.notEmpty(comparators, "comparators is required");
        Assert.noNullElements(comparators, "comparators has null element(s)");
        this.comparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(T o1, T o2) {
        for (Comparator<T> comparator : this.comparators) {
            int result = comparator.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

}
