package spring.turbo.databinding;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.util.StringUtils;
import spring.turbo.exception.DataBindingException;

/**
 * 内部工具
 *
 * @author 应卓
 * @since 3.3.1
 */
final class InternalConverterUtils {

    /**
     * 私有构造方法
     */
    private InternalConverterUtils() {
    }

    public static <T extends RuntimeException> RuntimeException transform(T e) {

        if ((e instanceof MessageSourceResolvable) || (e instanceof MultiMessageSourceResolvable)) {
            return e;
        }

        var msg = e.getMessage();
        if (!StringUtils.hasText(msg)) {
            var rootEx = NestedExceptionUtils.getRootCause(e);
            if (rootEx != null) {
                msg = rootEx.getMessage();
            }
        }

        if (StringUtils.hasText(msg)) {
            return DataBindingException.of(msg);
        }

        return e;
    }

}
