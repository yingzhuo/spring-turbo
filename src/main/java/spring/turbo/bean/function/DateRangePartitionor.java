/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.function;

import org.springframework.lang.Nullable;
import spring.turbo.bean.DateDescriptor;

import java.util.function.Function;

/**
 * @author 应卓
 * @see DateRangePartitionorFactories
 * @since 1.1.4
 */
@FunctionalInterface
public interface DateRangePartitionor extends Function<DateDescriptor, String> {

    @Nullable
    public String test(DateDescriptor dateDescriptor);

    @Override
    @Nullable
    public default String apply(DateDescriptor dateDescriptor) {
        return test(dateDescriptor);
    }

}
