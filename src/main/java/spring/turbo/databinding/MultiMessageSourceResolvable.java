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

}
