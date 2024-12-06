package spring.turbo.core;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Map;

/**
 * SpEL所欲实体
 *
 * @author 应卓
 * @since 3.4.0
 */
@FunctionalInterface
@SuppressWarnings("unchecked")
public interface SpELResolvable<T> extends Serializable {

    public String getExpression();

    @Nullable
    public default Object getRootObject() {
        return null;
    }

    @Nullable
    public default Map<String, ?> getVariables() {
        return null;
    }

    public default T getValue() {
        return (T) SpEL.getValue(getExpression(), getRootObject(), getVariables());
    }

}
