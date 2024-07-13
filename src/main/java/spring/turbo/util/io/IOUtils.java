package spring.turbo.util.io;

import org.springframework.lang.Nullable;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author 应卓
 * @since 1.0.12
 */
public final class IOUtils {

    /**
     * 私有构造方法
     */
    private IOUtils() {
    }

    /**
     * 拷贝
     *
     * @param in  in
     * @param out out
     * @return 拷贝的字节数
     * @throws UncheckedIOException IO错误
     */
    public static int copy(InputStream in, OutputStream out) {
        try {
            return FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 拷贝
     *
     * @param in  in
     * @param out out
     * @return 拷贝的字节数
     * @throws UncheckedIOException IO错误
     */
    public static int copy(Reader in, Writer out) {
        try {
            return FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 拷贝
     *
     * @param in  in
     * @param out out
     * @throws UncheckedIOException IO错误
     */
    public static void copy(byte[] in, OutputStream out) {
        try {
            StreamUtils.copy(in, out);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 拷贝
     *
     * @param in  in
     * @param out out
     * @throws UncheckedIOException IO错误
     */
    public static void copy(String in, OutputStream out) {
        copy(in, null, out);
    }

    /**
     * 拷贝
     *
     * @param in      in
     * @param charset 编码
     * @param out     out
     * @throws UncheckedIOException IO错误
     */
    public static void copy(String in, @Nullable Charset charset, OutputStream out) {
        charset = Objects.requireNonNullElse(charset, StandardCharsets.UTF_8);

        try {
            StreamUtils.copy(in, charset, out);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 拷贝
     *
     * @param in in
     * @return 字节数组
     * @throws UncheckedIOException IO错误
     */
    public static byte[] copyToByteArray(InputStream in) {
        try {
            return StreamUtils.copyToByteArray(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 拷贝
     *
     * @param in in
     * @return 字符串
     * @throws UncheckedIOException IO错误
     */
    public static String copyToString(InputStream in) {
        return copyToString(in, null);
    }

    /**
     * 拷贝
     *
     * @param in      in
     * @param charset 字符编码
     * @return 字符串
     * @throws UncheckedIOException IO错误
     */
    public static String copyToString(InputStream in, @Nullable Charset charset) {
        charset = Objects.requireNonNullElse(charset, StandardCharsets.UTF_8);

        try {
            return StreamUtils.copyToString(in, charset);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 排空一个{@link InputStream}
     *
     * @param in in
     * @return 排空的字节数
     */
    public static int drain(InputStream in) {
        try {
            return StreamUtils.drain(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
