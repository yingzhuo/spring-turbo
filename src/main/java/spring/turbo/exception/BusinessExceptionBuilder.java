/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.lang.Nullable;
import spring.turbo.util.collection.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务异常的创建器
 *
 * @author 应卓
 *
 * @since 2.0.1
 */
public final class BusinessExceptionBuilder {

    private final List<String> codes = new ArrayList<>();
    private final List<Object> arguments = new ArrayList<>();

    @Nullable
    private String defaultMessage = null;

    /**
     * 构造方法
     */
    BusinessExceptionBuilder() {
        super();
    }

    public BusinessExceptionBuilder codes(@Nullable String... codes) {
        CollectionUtils.nullSafeAddAll(this.codes, codes);
        return this;
    }

    public BusinessExceptionBuilder arguments(@Nullable Object... arguments) {
        CollectionUtils.nullSafeAddAll(this.arguments, arguments);
        return this;
    }

    public BusinessExceptionBuilder defaultMessage(@Nullable String message) {
        this.defaultMessage = message;
        return this;
    }

    public BusinessException build() {
        if (codes.isEmpty() && arguments.isEmpty() && defaultMessage != null) {
            return BusinessException.of(defaultMessage);
        }

        String[] codesInUse = codes.isEmpty() ? null : codes.toArray(new String[0]);
        Object[] argumentInUse = arguments.isEmpty() ? null : arguments.toArray(new Object[0]);

        return new BusinessException(codesInUse, argumentInUse, this.defaultMessage);
    }

}
