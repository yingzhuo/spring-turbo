/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import spring.turbo.io.CloseUtils;
import spring.turbo.io.IOUtils;
import spring.turbo.util.Asserts;

import java.io.*;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 秘钥存储与加载工具
 *
 * @author 应卓
 *
 * @since 2.2.4
 */
public final class KeyStorage {

    /**
     * 私有构造方法
     */
    private KeyStorage() {
        super();
    }

    @SneakyThrows
    public static void saveKeys(KeyPair keyPair, OutputStream publicKeyPathToSave, OutputStream privateKeyPathToSave) {
        Asserts.notNull(keyPair, "keyPair is null");
        Asserts.notNull(publicKeyPathToSave, "publicKeyPathToSave is null");
        Asserts.notNull(privateKeyPathToSave, "privateKeyPathToSave is null");

        var privateKey = keyPair.getPrivate();
        var publicKey = keyPair.getPublic();

        // save public key
        var x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        var fos = publicKeyPathToSave;
        fos.write(x509EncodedKeySpec.getEncoded());
        fos.close();

        // save private Key
        var pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        fos = privateKeyPathToSave;
        fos.write(pkcs8EncodedKeySpec.getEncoded());
        fos.close();
    }

    @SneakyThrows
    public static KeyPair loadKeys(String algorithm, InputStream publicKeyPathToLoad,
            InputStream privateKeyPathToLoad) {
        Asserts.hasText(algorithm, "algorithm is null or blank");
        Asserts.notNull(publicKeyPathToLoad, "publicKeyPathToLoad is null");
        Asserts.notNull(privateKeyPathToLoad, "privateKeyPathToLoad is null");

        // read public key
        byte[] encodedPublicKey = IOUtils.copyToByteArray(publicKeyPathToLoad);
        CloseUtils.closeQuietly(publicKeyPathToLoad);

        // read private key
        byte[] encodedPrivateKey = IOUtils.copyToByteArray(privateKeyPathToLoad);
        CloseUtils.closeQuietly(privateKeyPathToLoad);

        // generate KeyPair
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        var publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
        var publicKey = keyFactory.generatePublic(publicKeySpec);

        var privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        var privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @SneakyThrows
    public static void saveKeys(KeyPair keyPair, File publicKeyPathToSave, File privateKeyPathToSave) {
        Asserts.notNull(publicKeyPathToSave, "publicKeyPathToSave is null");
        Asserts.notNull(privateKeyPathToSave, "privateKeyPathToSave is null");
        saveKeys(keyPair, new FileOutputStream(publicKeyPathToSave), new FileOutputStream(privateKeyPathToSave));
    }

    @SneakyThrows
    public static KeyPair loadKeys(String algorithm, File publicKeyPathToLoad, File privateKeyPathToLoad) {
        Asserts.notNull(publicKeyPathToLoad, "publicKeyPathToLoad is null");
        Asserts.notNull(privateKeyPathToLoad, "privateKeyPathToLoad is null");
        return loadKeys(algorithm, new FileInputStream(publicKeyPathToLoad), new FileInputStream(privateKeyPathToLoad));
    }

    // -----------------------------------------------------------------------------------------------------------------

    @SneakyThrows
    public static void saveKeys(KeyPair keyPair, Path publicKeyPathToSave, Path privateKeyPathToSave) {
        Asserts.notNull(publicKeyPathToSave, "publicKeyPathToSave is null");
        Asserts.notNull(privateKeyPathToSave, "privateKeyPathToSave is null");
        saveKeys(keyPair, new FileOutputStream(publicKeyPathToSave.toFile()),
                new FileOutputStream(privateKeyPathToSave.toFile()));
    }

    @SneakyThrows
    public static KeyPair loadKeys(String algorithm, Path publicKeyPathToLoad, Path privateKeyPathToLoad) {
        Asserts.notNull(publicKeyPathToLoad, "publicKeyPathToLoad is null");
        Asserts.notNull(privateKeyPathToLoad, "privateKeyPathToLoad is null");
        return loadKeys(algorithm, new FileInputStream(publicKeyPathToLoad.toFile()),
                new FileInputStream(privateKeyPathToLoad.toFile()));
    }

    // -----------------------------------------------------------------------------------------------------------------

    @SneakyThrows
    public static void saveKeys(KeyPair keyPair, WritableResource publicKeyPathToSave,
            WritableResource privateKeyPathToSave) {
        Asserts.notNull(publicKeyPathToSave, "publicKeyPathToSave is null");
        Asserts.notNull(privateKeyPathToSave, "privateKeyPathToSave is null");
        saveKeys(keyPair, publicKeyPathToSave.getOutputStream(), privateKeyPathToSave.getOutputStream());
    }

    @SneakyThrows
    public static KeyPair loadKeys(String algorithm, Resource publicKeyPathToLoad, Resource privateKeyPathToLoad) {
        Asserts.notNull(publicKeyPathToLoad, "publicKeyPathToLoad is null");
        Asserts.notNull(privateKeyPathToLoad, "privateKeyPathToLoad is null");
        return loadKeys(algorithm, publicKeyPathToLoad.getInputStream(), privateKeyPathToLoad.getInputStream());
    }

}
