package spring.turbo.util.reflection;

import spring.turbo.util.ClassLoadingException;

/**
 * 实例化失败异常
 *
 * @author 应卓
 * @see ClassLoadingException
 * @see InstanceUtils
 * @since 1.0.0
 */
public class InstantiationException extends IllegalStateException {

    public InstantiationException() {
        super();
    }

    public InstantiationException(String message) {
        super(message);
    }

}
