package spring.turbo.autoconfiguration.properties;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import spring.turbo.util.StringUtils;

import java.io.Serializable;

/**
 * @author 应卓
 * @see org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration
 * @see spring.turbo.autoconfiguration.MessageSourceAutoConfiguration
 * @since 2.0.3
 */
@ConfigurationProperties(prefix = "spring.messages")
public class MessageSourceProps extends MessageSourceProperties implements Serializable {

    /**
     * 默认构造方法
     */
    public MessageSourceProps() {
        super.setBasename(null);
    }

    @Nullable
    @Override
    public String getBasename() {
        return super.getBasename();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public final String[] getBasenameArray() {
        var basename = getBasename();
        if (basename == null) {
            return new String[0];
        }
        basename = StringUtils.deleteWhitespace(basename);
        if (StringUtils.isBlank(basename)) {
            return new String[0];
        }
        return StringUtils.commaDelimitedListToStringArray(basename, true);
    }

}
