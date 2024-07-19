package spring.turbo.util.io;

import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import spring.turbo.util.collection.StreamFactories;
import spring.turbo.util.text.StringMatcher;
import spring.turbo.util.text.StringTokenizer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 有更丰富功能的 {@link Resource}。<br>
 * 显而易见，这是一个装饰器。
 *
 * @author 应卓
 * @see RichResourceEditor
 * @since 3.3.2
 */
public interface RichResource extends Resource, Serializable, Closeable {

    /**
     * 判断代理的对象是否是一个文件或物理设备
     *
     * @return 判断结果
     */
    public default boolean isPhysicalResource() {
        try {
            getFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 获取{@link Path}
     *
     * @return path
     * @throws IOException I/O错误
     */
    public default Path getPath() throws IOException {
        return getFile().toPath().toAbsolutePath();
    }

    /**
     * 获取一个{@link Reader}
     *
     * @return reader
     * @throws IOException I/O错误
     */
    public default Reader getReader() throws IOException {
        return getReader(null);
    }

    /**
     * 获取一个{@link Reader}
     *
     * @param charset 编码
     * @return reader
     * @throws IOException I/O错误
     */
    public default Reader getReader(@Nullable Charset charset) throws IOException {
        return new InputStreamReader(getInputStream(), Objects.requireNonNullElse(charset, UTF_8));
    }

    /**
     * 将资源转换成一个文本
     *
     * @return 文本
     * @throws IOException I/O错误
     */
    public default String getAsText() throws IOException {
        return getAsText(null);
    }

    /**
     * 将资源转换成一个文本
     *
     * @param charset 编码
     * @return 文本
     * @throws IOException I/O错误
     */
    public default String getAsText(@Nullable Charset charset) throws IOException {
        return getContentAsString(Objects.requireNonNullElse(charset, UTF_8));
    }

    /**
     * 将资源文本逐行读取
     *
     * @return 逐行的文本
     * @throws IOException I/O错误
     */
    public default Stream<String> getLinesAsStream() throws IOException {
        return getLinesAsStream(null);
    }

    /**
     * 将资源文本逐行读取
     *
     * @param charset 编码
     * @return 逐行的文本
     * @throws IOException I/O错误
     */
    public default Stream<String> getLinesAsStream(@Nullable Charset charset) throws IOException {
        var tokenizer = new StringTokenizer(getAsText(charset), StringMatcher.charSetMatcher('\n'));
        return StreamFactories.newStream(tokenizer, false);
    }

    /**
     * 判断资源是否是一个文件而非目录
     *
     * @return 判断结果
     */
    public default boolean isRegularFile() {
        try {
            return PathUtils.isRegularFile(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断资源是否是一个目录
     *
     * @return 判断结果
     */
    public default boolean isDirectory() {
        try {
            return PathUtils.isDirectory(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断资源是否是一个空目录
     *
     * @return 判断结果
     */
    public default boolean isEmptyDirectory() {
        try {
            return PathUtils.isEmptyDirectory(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断资源是否是一个连接
     *
     * @return 判断结果
     */
    public default boolean isSymbolicLink() {
        try {
            return PathUtils.isSymbolicLink(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 判断资源是否是一个隐藏文件或目录
     *
     * @return 判断结果
     */
    public default boolean isHidden() {
        try {
            return PathUtils.isHidden(getPath());
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 获取代理的对象
     *
     * @return 代理的 {@link Resource} 对象
     */
    public Resource delegating();

    /**
     * 获取代理的对象的类型
     *
     * @return 代理的对象的类型
     */
    public default Class<? extends Resource> getDelegatingType() {
        return delegating().getClass();
    }

}
