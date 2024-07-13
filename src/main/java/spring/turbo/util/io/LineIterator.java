package spring.turbo.util.io;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static spring.turbo.util.io.IOExceptionUtils.toUnchecked;

/**
 * @author 应卓
 * @since 1.0.8
 */
public class LineIterator implements Iterator<String>, Closeable {

    @NonNull
    private final BufferedReader bufferedReader;

    @Nullable
    private String cachedLine;

    private boolean finished;

    public LineIterator(Reader reader) {
        Assert.notNull(reader, "reader is required");

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
            throw new NoSuchElementException("no more lines");
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
