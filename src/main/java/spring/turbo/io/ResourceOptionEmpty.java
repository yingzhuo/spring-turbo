/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.Resource;
import spring.turbo.lang.Singleton;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Singleton
final class ResourceOptionEmpty implements ResourceOption {

    /**
     * 私有构造方法
     */
    private ResourceOptionEmpty() {
        super();
    }

    public static ResourceOptionEmpty getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public Optional<Resource> toOptional() {
        return Optional.empty();
    }

    @Override
    public boolean isAbsent() {
        return true;
    }

    @Override
    public String toString(Charset charset) {
        throw SyncAvoid.EX;
    }

    @Override
    public String toString() {
        throw SyncAvoid.EX;
    }

    @Override
    public InputStream toInputStream() {
        throw SyncAvoid.EX;
    }

    @Override
    public byte[] toByteArray() {
        throw SyncAvoid.EX;
    }

    @Override
    public File toFile() {
        throw SyncAvoid.EX;
    }

    @Override
    public Path toPath() {
        throw SyncAvoid.EX;
    }

    @Override
    public long getChecksumCRC32(int buffSize) {
        throw SyncAvoid.EX;
    }

    @Override
    public long getChecksumCRC32() {
        throw SyncAvoid.EX;
    }

    @Override
    public LineIterator getLineIterator(Charset charset) {
        throw SyncAvoid.EX;
    }

    @Override
    public LineIterator getLineIterator() {
        throw SyncAvoid.EX;
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final ResourceOptionEmpty INSTANCE = new ResourceOptionEmpty();
        private static final NoSuchElementException EX = new NoSuchElementException("No resource present");
    }

}
