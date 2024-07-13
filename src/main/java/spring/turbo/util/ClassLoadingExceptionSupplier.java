package spring.turbo.util;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.function.Supplier;

/**
 * 类型加载异常提供器
 *
 * @author 应卓
 * @see ClassUtils
 * @see ClassLoadingException
 * @since 1.0.2
 */
public class ClassLoadingExceptionSupplier implements Supplier<ClassLoadingException> {

    private final String className;

    public ClassLoadingExceptionSupplier(@NonNull String className) {
        Assert.hasText(className, "className is required");
        this.className = className;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClassLoadingException get() {
        final String msg = StringFormatter.format("not able to load class. class name: '{}'", className);
        return new ClassLoadingException(msg);
    }

}
