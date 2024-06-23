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
import spring.turbo.io.IOUtils;
import spring.turbo.util.Asserts;
import spring.turbo.util.CharsetPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;

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
        private boolean stripWhitespace = true;
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

        public Builder stripWhitespace(boolean stripWhitespace) {
            this.stripWhitespace = stripWhitespace;
            return this;
        }

        public Builder resource(File file) {
            return resource(file, null);
        }

        public Builder resource(File file, @Nullable Charset charset) {
            try {
                this.text = IOUtils.copyToString(new FileInputStream(file),
                        Objects.requireNonNullElse(charset, CharsetPool.UTF_8));
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
            return new PemContentLoader() {
                @Override
                public String loadEncodedPublicKey() {
                    Asserts.hasText(text);

                    var regex = String.format(".*%s(.+)%s.*", privateKeyBeginLine, privateKeyEndLine);
                    var matcher = Pattern.compile(regex, Pattern.DOTALL | Pattern.MULTILINE).matcher(text);
                    if (matcher.matches()) {
                        var key = matcher.replaceAll("$1");
                        if (stripWhitespace) {
                            key = key.replaceAll("\\s", "");
                        }
                        return key;
                    } else {
                        return null;
                    }
                }

                @Override
                public String loadEncodedPrivateKey() {
                    Asserts.hasText(text);

                    var regex = String.format(".*%s(.+)%s.*", publicKeyBeginLine, publicKeyEndLine);
                    var matcher = Pattern.compile(regex, Pattern.DOTALL | Pattern.MULTILINE).matcher(text);
                    if (matcher.matches()) {
                        var key = matcher.replaceAll("$1");
                        if (stripWhitespace) {
                            key = key.replaceAll("\\s", "");
                        }
                        return key;
                    } else {
                        return null;
                    }
                }
            };
        }
    }
}
