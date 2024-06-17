/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class NullValidator implements Validator {

    /**
     * 私有构造方法
     */
    private NullValidator() {
        super();
    }

    public static NullValidator getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        // nop
    }

    // 延迟加载
    private static class SyncAvoid {
        private final static NullValidator INSTANCE = new NullValidator();
    }

}
