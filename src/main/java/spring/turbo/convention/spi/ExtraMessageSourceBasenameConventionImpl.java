package spring.turbo.convention.spi;

import spring.turbo.convention.ExtraMessageSourceBasenameConvention;

import java.util.Collection;
import java.util.List;

/**
 * @author 应卓
 * @since 2.0.3
 */
@Deprecated(forRemoval = true, since = "3.3.1")
public final class ExtraMessageSourceBasenameConventionImpl implements ExtraMessageSourceBasenameConvention {

    @Override
    public Collection<String> getExtraMessageSourceBasename() {
        return List.of("spring.turbo.ValidationMessages");
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
