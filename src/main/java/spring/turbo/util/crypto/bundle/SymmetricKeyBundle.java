package spring.turbo.util.crypto.bundle;

import java.io.Serializable;
import java.security.Key;

/**
 * 对秘钥的封装
 *
 * @author 应卓
 * @see KeyStoreSymmetricKeyBundleFactoryBean
 * @since 3.3.1
 */
@FunctionalInterface
public interface SymmetricKeyBundle extends Serializable {

    /**
     * 获取秘钥
     *
     * @param <T> 秘钥类型泛型
     * @return 秘钥
     */
    public <T extends Key> T getKey();

    /**
     * 获取算法名称
     *
     * @return 算法名称 如: AES
     */
    public default String getAlgorithm() {
        return getKey().getAlgorithm();
    }

}
