/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.databinding;

import org.springframework.context.MessageSourceResolvable;
import spring.turbo.util.collection.StreamFactories;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * 包含多个 {@link MessageSourceResolvable} 对象的类型
 *
 * @author 应卓
 * @since 3.3.1
 */
@FunctionalInterface
public interface MultiMessageSourceResolvable extends Iterable<MessageSourceResolvable> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<MessageSourceResolvable> iterator();

    /**
     * 转换成 {@link Stream}
     *
     * @return stream
     */
    public default Stream<MessageSourceResolvable> stream() {
        return StreamFactories.newStream(iterator(), false);
    }

    /**
     * 转换成 {@link Stream} (parallel)
     *
     * @return stream
     */
    public default Stream<MessageSourceResolvable> parallelStream() {
        return StreamFactories.newStream(iterator(), true);
    }

}
