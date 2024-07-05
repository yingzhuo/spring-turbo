package spring.turbo.convention;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.core.Ordered;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.Nullable;
import spring.turbo.autoconfiguration.properties.MessageSourceProps;

import java.util.Collection;

/**
 * 扩展 basename 规约
 *
 * @author 应卓
 * @see MessageSourceProps
 * @see MessageSourceProperties
 * @see SpringFactoriesLoader
 * @since 2.0.3
 */
@FunctionalInterface
@Deprecated(forRemoval = true, since = "3.3.1")
public interface ExtraMessageSourceBasenameConvention extends Ordered {

    @Nullable
    public Collection<String> getExtraMessageSourceBasename();

    @Override
    public default int getOrder() {
        return 0;
    }

}
