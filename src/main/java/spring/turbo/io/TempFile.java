/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static spring.turbo.util.StringPool.DOT;
import static spring.turbo.util.StringPool.EMPTY;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class TempFile implements Serializable {

    private final Path path;

    private TempFile(Path path) {
        super();
        Assert.notNull(path, "path is null");
        this.path = path;
    }

    public static String getDirPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static Builder builder() {
        return new Builder();
    }

    public File getFile() {
        return getPath().toFile();
    }

    public Path getPath() {
        return this.path;
    }

    public InputStream getInputStream() {
        try {
            return new FileInputStream(getFile());
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    public OutputStream getOutputStream() {
        try {
            return new FileOutputStream(getFile());
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    public void delete() {
        try {
            Files.delete(getPath());
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return path.toString();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static final class Builder {

        private String prefix = EMPTY;
        private File file;
        private byte[] bytes;
        private InputStream inputStream;
        private Reader reader;

        private Builder() {
            super();
        }

        public Builder prefix(String prefix) {
            Assert.notNull(prefix, "prefix is null");
            this.prefix = prefix;
            return this;
        }

        public Builder set(File file) {
            Assert.notNull(file, "file is null");
            alreadySet();
            this.file = file;
            return this;
        }

        public Builder set(byte[] file) {
            Assert.notNull(file, "file is null");
            alreadySet();
            this.bytes = file;
            return this;
        }

        public Builder set(InputStream file) {
            Assert.notNull(file, "file is null");
            alreadySet();
            this.inputStream = file;
            return this;
        }

        public Builder set(Reader file) {
            Assert.notNull(file, "file is null");
            alreadySet();
            this.reader = file;
            return this;
        }

        public Builder set(Path file) {
            Assert.notNull(file, "file is null");
            alreadySet();
            this.file = file.toFile();
            return this;
        }

        public TempFile create() {
            notSet();

            try {
                String ext = getFilenameExtension();
                Path tmp = Files.createTempFile(prefix, ext).normalize();
                doCopy(tmp);
                return new TempFile(tmp);
            } catch (IOException e) {
                throw new UncheckedIOException(e.getMessage(), e);
            }
        }

        private void doCopy(Path tmp) throws IOException {
            if (file != null) {
                FileCopyUtils.copy(file, tmp.toFile());
            }

            if (bytes != null) {
                FileCopyUtils.copy(bytes, tmp.toFile());
            }

            if (inputStream != null) {
                FileCopyUtils.copy(inputStream, new FileOutputStream(tmp.toFile()));
            }

            if (reader != null) {
                FileCopyUtils.copy(reader, new FileWriter(tmp.toFile()));
            }
        }

        private String getFilenameExtension() {
            String ext;
            if (file != null) {
                ext = Optional.ofNullable(StringUtils.getFilenameExtension(file.getPath())).orElse(EMPTY);
            } else {
                ext = EMPTY;
            }
            return EMPTY.equals(ext) ? ext : DOT + ext;
        }

        private void alreadySet() {
            boolean c =
                    this.file != null
                            || this.bytes != null
                            || this.inputStream != null
                            || this.reader != null;
            if (c) {
                throw new IllegalArgumentException("file already set");
            }
        }

        private void notSet() {
            boolean c =
                    this.file == null
                            && this.bytes == null
                            && this.inputStream == null
                            && this.reader == null;

            if (c) {
                throw new IllegalArgumentException("file not set yet");
            }
        }
    }

}
