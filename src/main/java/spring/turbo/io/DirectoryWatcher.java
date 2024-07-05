package spring.turbo.io;

import spring.turbo.util.Asserts;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * @author 应卓
 * @see #builder()
 * @since 2.1.0
 */
public final class DirectoryWatcher {

    private final Path directory;
    private final Listener listener;

    private DirectoryWatcher(Path directory, Listener listener) {
        Asserts.notNull(directory);
        Asserts.notNull(listener);
        this.directory = directory;
        this.listener = listener;
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static Builder builder() {
        return new Builder();
    }

    public void start() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    listener.execute("" + event.kind(), "" + event.context());
                }
                key.reset();
            }

        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    @FunctionalInterface
    public static interface Listener {
        public void execute(String kind, String file);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 创建器
     */
    public static final class Builder {

        private Path dir;
        private Listener listener;

        /**
         * 私有构造方法
         */
        private Builder() {
        }

        public Builder directory(String first, String... more) {
            this.dir = Paths.get(first, more);
            return this;
        }

        public Builder directory(Path dir) {
            this.dir = dir;
            return this;
        }

        public Builder directory(File dir) {
            this.dir = FileUtils.toPath(dir);
            return this;
        }

        public DirectoryWatcher build() {
            return new DirectoryWatcher(dir, listener);
        }
    }

}
