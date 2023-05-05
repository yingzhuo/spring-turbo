/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.io.CloseUtils;
import spring.turbo.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
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

    public static void saveKeys(KeyPair keyPair, OutputStream publicKeyPathToSave, OutputStream privateKeyPathToSave)
            throws IOException {
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

    public static KeyPair loadKeys(String algorithm, InputStream publicKeyPathToLoad, InputStream privateKeyPathToLoad)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
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

}
