/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import org.springframework.util.Assert;
import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptions;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class KeysReaders {

    private KeysReaders() {
        super();
    }

    public static RSAKeys readRSAKeys(String publicKeyLocation, String privateKeyLocation) {
        return RSAKeys.fromString(
                getText(publicKeyLocation),
                getText(privateKeyLocation)
        );
    }

    public static ECDSAKeys readECDSAKeys(String publicKeyLocation, String privateKeyLocation) {
        return ECDSAKeys.fromString(
                getText(publicKeyLocation),
                getText(privateKeyLocation)
        );
    }

    public static DSAKeys readDSAKeys(String publicKeyLocation, String privateKeyLocation) {
        return DSAKeys.fromString(
                getText(publicKeyLocation),
                getText(privateKeyLocation)
        );
    }

    private static String getText(String location) {
        Assert.hasText(location, "location is blank");
        ResourceOption option = ResourceOptions.builder()
                .add(location)
                .build();

        Assert.state(option.isPresent(), "location is not able to open");
        return option.toString(UTF_8);
    }

}
