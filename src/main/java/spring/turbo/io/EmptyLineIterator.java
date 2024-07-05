package spring.turbo.io;

import java.util.NoSuchElementException;

/**
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.8
 */
public final class EmptyLineIterator extends LineIterator {

    /**
     * 私有构造方法
     */
    private EmptyLineIterator() {
        super(EmptyReader.getInstance());
    }

    public static EmptyLineIterator getInstance() {
        return SyncAvoid.INSTANCE;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        throw new NoSuchElementException("no more lines");
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final EmptyLineIterator INSTANCE = new EmptyLineIterator();
    }

}
