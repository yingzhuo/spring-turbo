/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.util.Comparator;

/**
 * @param <T> 泛型
 * @author 应卓
 */
public class InstanceComparator<T> implements Comparator<T> {

    private final Class<?>[] instanceOrder;

    public InstanceComparator(Class<?>... instanceOrder) {
        Asserts.notNull(instanceOrder);
        this.instanceOrder = instanceOrder;
    }

    @Override
    public int compare(T o1, T o2) {
        int i1 = getOrder(o1);
        int i2 = getOrder(o2);
        return (Integer.compare(i1, i2));
    }

    private int getOrder(@Nullable T object) {
        if (object != null) {
            for (int i = 0; i < this.instanceOrder.length; i++) {
                if (this.instanceOrder[i].isInstance(object)) {
                    return i;
                }
            }
        }
        return this.instanceOrder.length;
    }
}