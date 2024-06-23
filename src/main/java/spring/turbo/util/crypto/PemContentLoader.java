/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import spring.turbo.io.IOExceptionUtils;
import spring.turbo.util.Asserts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;

import static spring.turbo.io.IOUtils.copyToString;
import static spring.turbo.util.CharsetPool.UTF_8;
import static spring.turbo.util.StringPool.EMPTY;

/**
 * PEM格式文件内容读取工具
 *
 * @author 应卓
 * @see #builder()
 * @since 3.3.0
 */
public interface PemContentLoader {

    public static Builder builder() {
        return new Builder();
    }

    @Nullable
    public String loadEncodedPublicKey();

    @Nullable
    public String loadEncodedPrivateKey();

    // -----------------------------------------------------------------------------------------------------------------

    public static class Builder {

        private String publicKeyBeginLine = "-----BEGIN PUBLIC KEY-----";
        private String publicKeyEndLine = "-----END PUBLIC KEY-----";
        private String privateKeyBeginLine = "-----BEGIN PRIVATE KEY-----";
        private String privateKeyEndLine = "-----END PRIVATE KEY-----";
        private String text = "";

        Builder() {
            super();
        }

        public Builder publicKeyBeginLine(String publicKeyBeginLine) {
            Asserts.hasText(publicKeyBeginLine);
            this.publicKeyBeginLine = publicKeyBeginLine;
            return this;
        }

        public Builder publicKeyEndLine(String publicKeyEndLine) {
            Asserts.hasText(publicKeyEndLine);
            this.publicKeyEndLine = publicKeyEndLine;
            return this;
        }

        public Builder privateKeyBeginLine(String privateKeyBeginLine) {
            Asserts.hasText(privateKeyBeginLine);
            this.privateKeyBeginLine = privateKeyBeginLine;
            return this;
        }

        public Builder privateKeyEndLine(String privateKeyEndLine) {
            Asserts.hasText(privateKeyEndLine);
            this.privateKeyEndLine = privateKeyEndLine;
            return this;
        }

        public Builder resource(File file) {
            return resource(file, null);
        }

        public Builder resource(File file, @Nullable Charset charset) {
            try {
                this.text = copyToString(
                        new FileInputStream(file),
                        Objects.requireNonNullElse(charset, UTF_8)
                );
                return this;
            } catch (FileNotFoundException e) {
                throw IOExceptionUtils.toUnchecked(e);
            }
        }

        public Builder resource(Path path) {
            return resource(path, null);
        }

        public Builder resource(Path path, @Nullable Charset charset) {
            return resource(path.toFile(), charset);
        }

        public Builder resource(Resource resource) {
            return resource(resource, null);
        }

        public Builder resource(Resource resource, @Nullable Charset charset) {
            try {
                return resource(resource.getFile(), charset);
            } catch (IOException e) {
                throw IOExceptionUtils.toUnchecked(e);
            }
        }

        public PemContentLoader build() {
            return new DefaultPemContentLoader(text, privateKeyBeginLine, privateKeyEndLine, publicKeyBeginLine, publicKeyEndLine);
        }
    }

    public static class DefaultPemContentLoader implements PemContentLoader {

        private final String text;
        private final String privateKeyBeginLine;
        private final String privateKeyEndLine;
        private final String publicKeyBeginLine;
        private final String publicKeyEndLine;

        public DefaultPemContentLoader(String text, String privateKeyBeginLine, String privateKeyEndLine, String publicKeyBeginLine, String publicKeyEndLine) {
            this.text = text;
            this.privateKeyBeginLine = privateKeyBeginLine;
            this.privateKeyEndLine = privateKeyEndLine;
            this.publicKeyBeginLine = publicKeyBeginLine;
            this.publicKeyEndLine = publicKeyEndLine;
        }

        @Override
        public String loadEncodedPublicKey() {
            return load(publicKeyBeginLine, publicKeyEndLine);
        }

        @Override
        public String loadEncodedPrivateKey() {
            return load(privateKeyBeginLine, privateKeyEndLine);
        }

        @Nullable
        private String load(String startLine, String endLine) {
            var regex = String.format(".*%s(.+)%s.*", startLine, endLine);
            var matcher = Pattern.compile(regex, Pattern.DOTALL | Pattern.MULTILINE).matcher(text);
            if (matcher.matches()) {
                var key = matcher.replaceAll("$1");
                key = key.replaceAll("\\s", EMPTY);
                return key;
            } else {
                return null;
            }
        }
    }

}
