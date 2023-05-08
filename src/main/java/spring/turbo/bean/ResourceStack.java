/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.io.RichResource;
import spring.turbo.util.Asserts;

/**
 * 资源栈，建议配合 {@code SpEL} 使用
 *
 * @author 应卓
 *
 * @since 2.2.5
 */
public sealed interface ResourceStack permits ResourceStackImpl {

    @Nullable
    public RichResource find(@Nullable String environmentName, String... locations);

    @NonNull
    public default RichResource findRequired(@Nullable String environmentName, String... locations) {
        var resource = find(environmentName, locations);
        Asserts.notNull(resource);
        Asserts.isTrue(RichResource.Builder.DEFAULT_DISCRIMINATOR.test(resource));
        return resource;
    }

}
