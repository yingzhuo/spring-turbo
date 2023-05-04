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
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static spring.turbo.io.IOExceptionUtils.toUnchecked;

/**
 * @author 应卓
 *
 * @since 1.0.8
 */
public class LineIterator implements Iterator<String>, Closeable {

    @NonNull
    private final BufferedReader bufferedReader;

    @Nullable
    private String cachedLine;

    private boolean finished;

    public LineIterator(Reader reader) {
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
            throw toUnchecked(ioe);
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
        return Objects.requireNonNull(currentLine);
    }

    @Override
    public void close() {
        finished = true;
        cachedLine = null;
        CloseUtils.closeQuietly(bufferedReader);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }

}
