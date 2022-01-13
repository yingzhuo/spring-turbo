/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import java.util.NoSuchElementException;

/**
 * @author 应卓
 * @since 1.0.8
 */
public final class EmptyLineIterator extends LineIterator {

    private static final EmptyLineIterator INSTANCE = new EmptyLineIterator();

    private EmptyLineIterator() {
        super(EmptyReader.getInstance());
    }

    public static EmptyLineIterator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        throw new NoSuchElementException("No more lines");
    }

}
