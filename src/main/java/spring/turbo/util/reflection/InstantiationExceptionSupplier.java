package spring.turbo.util.reflection;

import org.springframework.util.Assert;
import spring.turbo.util.StringFormatter;

import java.util.function.Supplier;

/**
 * @author 应卓
 * @see InstanceUtils
 * @see InstantiationException
 * @since 1.0.0
 */
public final class InstantiationExceptionSupplier implements Supplier<InstantiationException> {

    private final Class<?> type;

    public InstantiationExceptionSupplier(Class<?> type) {
        Assert.notNull(type, "type is required");
        this.type = type;
    }

    @Override
    public InstantiationException get() {
        return new InstantiationException(
                StringFormatter.format("not able to create instance. type: '{}'", type.getName()));
    }

}
