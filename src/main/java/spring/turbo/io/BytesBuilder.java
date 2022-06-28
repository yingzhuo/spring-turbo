/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import spring.turbo.util.Asserts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;

/**
 * 此工具将多个对象合成一个字节数组
 *
 * @author 应卓
 * @see #newInstance() 创建实例
 * @since 1.0.0
 */
public final class BytesBuilder {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(64);

    /**
     * 私有构造方法
     *
     * @see #newInstance()
     */
    private BytesBuilder() {
        super();
    }

    /**
     * 创建实例
     *
     * @return 实例
     */
    public static BytesBuilder newInstance() {
        return new BytesBuilder();
    }

    /**
     * 添加对象
     *
     * @param object 任何一个非空对象
     * @return 创建器
     */
    public BytesBuilder append(Object object) {
        Asserts.notNull(object);

        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(object);
            oos.flush();
        } catch (IOException ex) {
            throw new UncheckedIOException("Failed to serialize object of type: " + object.getClass(), ex);
        }
        return this;
    }

    /**
     * 创建字节数组
     *
     * @return 字节数组
     */
    public byte[] build() {
        return outputStream.toByteArray();
    }

}
