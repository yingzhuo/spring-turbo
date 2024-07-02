/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto.pem;

import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

/**
 * 这个小工具用于读取PEM格式文件内容<br>
 * 此工具依赖 <a href="https://search.maven.org/search?q=bcprov-jdk15to18">BouncyCastle</a>
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class PemHelper {

    /**
     * 私有构造方法
     */
    private PemHelper() {
        super();
    }

    /**
     * 读取文件内容
     *
     * @param inputStream 资源
     * @return 文件内容
     */
    public static byte[] readPemBytes(Resource inputStream) {
        try {
            return readPemBytes(inputStream.getInputStream());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 读取文件内容
     *
     * @param inputStream 文件
     * @return 文件内容
     */
    public static byte[] readPemBytes(InputStream inputStream) {
        try (var keyReader = new InputStreamReader(inputStream); var pemReader = new PemReader(keyReader)) {
            var pemObject = pemReader.readPemObject();
            return pemObject.getContent();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

}
