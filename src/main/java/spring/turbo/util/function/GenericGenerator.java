package spring.turbo.util.function;

/**
 * @author 应卓
 * @since 3.3.1
 */
@FunctionalInterface
@SuppressWarnings("rawtypes")
public interface GenericGenerator extends java.util.function.Supplier {

    @Override
    public default Object get() {
        return generate();
    }

    public Object generate();

}
