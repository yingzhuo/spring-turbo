/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class BytesBuilder {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(64);

    private BytesBuilder() {
        super();
    }

    public static BytesBuilder newInstance() {
        return new BytesBuilder();
    }

    public BytesBuilder append(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(object);
            oos.flush();
        } catch (IOException ex) {
            throw new UncheckedIOException("Failed to serialize object of type: " + object.getClass(), ex);
        }
        return this;
    }

    public byte[] build() {
        return outputStream.toByteArray();
    }

}
