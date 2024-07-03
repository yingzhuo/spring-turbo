/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import spring.turbo.util.Asserts;

import java.io.*;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author 应卓
 * @since 1.0.12
 */
public final class IOUtils {

    /**
     * 私有构造方法
     */
    private IOUtils() {
        super();
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
        Asserts.notNull(in);
        Asserts.notNull(out);

        try {
            return FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
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
        Asserts.notNull(in);
        Asserts.notNull(out);

        try {
            return FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
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
        Asserts.notNull(in);
        Asserts.notNull(out);

        try {
            StreamUtils.copy(in, out);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
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
        copy(in, UTF_8, out);
    }

    /**
     * 拷贝
     *
     * @param in      in
     * @param charset 编码
     * @param out     out
     * @throws UncheckedIOException IO错误
     */
    public static void copy(String in, Charset charset, OutputStream out) {
        Asserts.notNull(in);
        Asserts.notNull(charset);
        Asserts.notNull(out);

        try {
            StreamUtils.copy(in, charset, out);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
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
        Asserts.notNull(in);

        try {
            return StreamUtils.copyToByteArray(in);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
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
        return copyToString(in, UTF_8);
    }

    /**
     * 拷贝
     *
     * @param in      in
     * @param charset 字符编码
     * @return 字符串
     * @throws UncheckedIOException IO错误
     */
    public static String copyToString(InputStream in, Charset charset) {
        Asserts.notNull(in);
        Asserts.notNull(charset);

        try {
            return StreamUtils.copyToString(in, charset);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    /**
     * 排空一个{@link InputStream}
     *
     * @param in in
     * @return 排空的字节数
     */
    public static int drain(InputStream in) {
        Asserts.notNull(in);
        try {
            return StreamUtils.drain(in);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

}
