/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.autoconfiguration.properties;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import spring.turbo.util.StringUtils;

import java.io.Serializable;

/**
 * @author 应卓
 * @since 2.0.3
 */
@ConfigurationProperties(prefix = "spring.messages")
public class MessageSourceProps extends MessageSourceProperties implements Serializable {

    /**
     * 默认构造方法
     */
    public MessageSourceProps() {
        // spring boot 官方提供的默认值个人觉得无厘头
        super.setBasename(null);
    }

    public final String[] getBasenameArray() {
        final var basenameStr = StringUtils.deleteWhitespace(getBasename());
        if (StringUtils.isBlank(basenameStr)) {
            return new String[0];
        }
        return StringUtils.commaDelimitedListToStringArray(basenameStr, true);
    }

}
