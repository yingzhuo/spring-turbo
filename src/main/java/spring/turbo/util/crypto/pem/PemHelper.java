package spring.turbo.util.crypto.pem;

import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 这个小工具用于读取PEM格式文件内容 <br>
 * 此工具依赖 <a href="https://search.maven.org/search?q=bcprov-jdk18oon">Bouncy Castle</a>
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class PemHelper {

    /**
     * 私有构造方法
     */
    private PemHelper() {
    }

    /**
     * 读取文件内容
     *
     * @param inputStream 资源
     * @return 文件内容
     * @throws UncheckedIOException IO错误
     */
    public static byte[] readPemBytes(Resource inputStream) {
        try {
            return readPemBytes(inputStream.getInputStream());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 从文件系统中读取PEM格式文件内容
     *
     * @param path 文件
     * @return 文件内容
     * @throws UncheckedIOException IO错误
     */
    public static byte[] readPemBytes(Path path) {
        try (var inputStream = Files.newInputStream(path)) {
            return readPemBytes(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 从文件系统中读取PEM格式文件内容
     *
     * @param file 文件
     * @return 文件内容
     * @throws UncheckedIOException IO错误
     */
    public static byte[] readPemBytes(File file) {
        try (var inputStream = new FileInputStream(file)) {
            return readPemBytes(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 读取文件内容
     *
     * @param inputStream 文件
     * @return 文件内容
     * @throws UncheckedIOException IO错误
     */
    public static byte[] readPemBytes(InputStream inputStream) {
        try (var keyReader = new InputStreamReader(inputStream); var pemReader = new PemReader(keyReader)) {
            var pemObject = pemReader.readPemObject();
            return pemObject.getContent();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
