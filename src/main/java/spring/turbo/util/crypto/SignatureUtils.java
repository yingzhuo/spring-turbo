package spring.turbo.util.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;

/**
 * 签名工具
 *
 * @author 应卓
 * @see CipherUtils
 * @since 3.3.1
 */
public final class SignatureUtils {

    /**
     * 私有构造方法
     */
    private SignatureUtils() {
    }

    /**
     * 签名
     *
     * @param data       原始数据
     * @param sigAlgName 签名算法 如: SHA256WithRSA
     * @param privateKey 私钥
     * @return 签名
     */
    public static byte[] sign(byte[] data, String sigAlgName, PrivateKey privateKey) {
        try {
            var signature = Signature.getInstance(sigAlgName);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 验证签名是否正确
     *
     * @param data       原始数据
     * @param sign       签名
     * @param sigAlgName 签名算法 如: SHA256WithRSA
     * @param publicKey  公钥
     * @return 验证结果
     */
    public static boolean verify(byte[] data, byte[] sign, String sigAlgName, PublicKey publicKey) {
        try {
            var signature = Signature.getInstance(sigAlgName);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 验证签名是否正确
     *
     * @param data        原始数据
     * @param sign        签名
     * @param certificate 证书
     * @return 验证结果
     */
    public static boolean verify(byte[] data, byte[] sign, X509Certificate certificate) {
        return verify(data, sign, certificate.getSigAlgName(), certificate.getPublicKey());
    }

}
