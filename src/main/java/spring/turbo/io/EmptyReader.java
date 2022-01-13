/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import java.io.IOException;
import java.io.Reader;

/**
 * @author 应卓
 * @since 1.0.8
 */
public final class EmptyReader extends Reader {

    private static final EmptyReader INSTANCE = new EmptyReader();

    private EmptyReader() {
        super();
    }

    public static EmptyReader getInstance() {
        return INSTANCE;
    }

    @Override
    public int read(char[] buf, int off, int len) throws IOException {
        return -1;
    }

    @Override
    public void close() throws IOException {
    }

}
