/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.function.Supplier;

/**
 * @author 应卓
 *
 * @see InstanceUtils
 * @see InstantiationException
 *
 * @since 1.0.0
 */
public final class InstantiationExceptionSupplier implements Supplier<InstantiationException> {

    private final Class<?> type;

    public InstantiationExceptionSupplier(Class<?> type) {
        Asserts.notNull(type);
        this.type = type;
    }

    @Override
    public InstantiationException get() {
        return new InstantiationException(
                StringFormatter.format("not able to create instance. type: '{}'", type.getName()));
    }

}
