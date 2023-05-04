/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.NonNull;

import java.util.function.Supplier;

/**
 * 类型加载异常提供器
 *
 * @author 应卓
 *
 * @see ClassUtils
 * @see ClassLoadingException
 *
 * @since 1.0.2
 */
public class ClassLoadingExceptionSupplier implements Supplier<ClassLoadingException> {

    private final String className;

    public ClassLoadingExceptionSupplier(@NonNull String className) {
        Asserts.hasText(className);
        this.className = className;
    }

    @Override
    public ClassLoadingException get() {
        final String msg = StringFormatter.format("not able to load class. class name: '{}'", className);
        return new ClassLoadingException(msg);
    }

}
