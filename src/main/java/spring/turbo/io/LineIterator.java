/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.lang.NonNull;
import spring.turbo.util.Asserts;
import spring.turbo.util.CloseUtils;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class LineIterator implements Iterator<String>, Closeable {

    private final BufferedReader bufferedReader;
    private String cachedLine;
    private boolean finished;

    public LineIterator(@NonNull Reader reader) {
        Asserts.notNull(reader);

        if (reader instanceof BufferedReader) {
            this.bufferedReader = (BufferedReader) reader;
        } else {
            this.bufferedReader = new BufferedReader(reader);
        }
    }

    @Override
    public boolean hasNext() {
        if (cachedLine != null) {
            return true;
        }
        if (finished) {
            return false;
        }
        try {
            while (true) {
                final String line = bufferedReader.readLine();
                if (line == null) {
                    finished = true;
                    return false;
                }
                if (isValidLine(line)) {
                    cachedLine = line;
                    return true;
                }
            }
        } catch (final IOException ioe) {
            CloseUtils.closeQuietly(bufferedReader);
            throw new UncheckedIOException(ioe);
        }
    }

    protected boolean isValidLine(final String line) {
        return true;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        final String currentLine = cachedLine;
        cachedLine = null;
        return currentLine;
    }

    @Override
    public void close() throws IOException {
        finished = true;
        cachedLine = null;
        CloseUtils.closeQuietly(bufferedReader);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }

}
