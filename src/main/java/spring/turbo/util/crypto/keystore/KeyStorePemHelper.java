package spring.turbo.util.crypto.keystore;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import spring.turbo.core.ResourceUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNullElse;
import static spring.turbo.util.crypto.pem.PemReadingUtils.readPkcs8PrivateKey;
import static spring.turbo.util.crypto.pem.PemReadingUtils.readX509Certificate;

/**
 * @author 应卓
 * @since 3.3.2
 */
public final class KeyStorePemHelper {

    private static final String DEFAULT_PASSWORD = "changeit";

    /**
     * 私有构造方法
     */
    private KeyStorePemHelper() {
    }

    /**
     * 从PEM文件中加载私钥和证书生成 {@link KeyStore} 实例
     *
     * @param certificateLocation   证书位置
     * @param privateKeyLocation    私钥位置
     * @param pemPrivateKeyPassword 私钥密码
     * @param entryName             条目名称也就是 alias
     * @param storepass             生成对象的storepass
     * @param keypass               生成对象的keypass
     * @return keyStore实例
     */
    public static KeyStore loadFromPemFiles(String certificateLocation,
                                            String privateKeyLocation,
                                            @Nullable String pemPrivateKeyPassword,
                                            String entryName,
                                            @Nullable String storepass,
                                            @Nullable String keypass) {
        return loadFromPemFiles(
                ResourceUtils.loadResource(certificateLocation),
                ResourceUtils.loadResource(privateKeyLocation),
                pemPrivateKeyPassword,
                entryName,
                storepass,
                keypass
        );
    }

    /**
     * 从PEM文件中加载私钥和证书生成 {@link KeyStore} 实例
     *
     * @param certificateResource   证书
     * @param privateKeyResource    私钥
     * @param pemPrivateKeyPassword 私钥密码
     * @param entryName             条目名称也就是 alias
     * @param storepass             生成对象的storepass
     * @param keypass               生成对象的keypass
     * @return keyStore实例
     */
    public static KeyStore loadFromPemFiles(Resource certificateResource,
                                            Resource privateKeyResource,
                                            @Nullable String pemPrivateKeyPassword,
                                            String entryName,
                                            @Nullable String storepass,
                                            @Nullable String keypass) {
        try {
            var cert = readX509Certificate(certificateResource.getContentAsString(UTF_8));
            var privateKey = readPkcs8PrivateKey(privateKeyResource.getContentAsString(UTF_8), pemPrivateKeyPassword);

            storepass = requireNonNullElse(storepass, DEFAULT_PASSWORD);
            keypass = requireNonNullElse(keypass, DEFAULT_PASSWORD);

            var store = KeyStore.getInstance(KeyStore.getDefaultType());
            store.load(null, storepass.toCharArray());
            store.setKeyEntry(entryName, privateKey, keypass.toCharArray(), new Certificate[]{cert});
            return store;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
