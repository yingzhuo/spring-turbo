/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * {@link java.util.StringTokenizer} 加强版
 *
 * @author 应卓
 *
 * @see java.util.StringTokenizer
 *
 * @since 2.0.2
 */
public class StringTokenizer implements ListIterator<String>, Serializable, Cloneable {

    private static final StringTokenizer CSV_TOKENIZER_PROTOTYPE;
    private static final StringTokenizer TSV_TOKENIZER_PROTOTYPE;

    static {
        CSV_TOKENIZER_PROTOTYPE = new StringTokenizer();
        CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StringMatcher.commaMatcher());
        CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StringMatcher.doubleQuoteMatcher());
        CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StringMatcher.noneMatcher());
        CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StringMatcher.whitespaceMatcher());
        CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);

        TSV_TOKENIZER_PROTOTYPE = new StringTokenizer();
        TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StringMatcher.tabMatcher());
        TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StringMatcher.doubleQuoteMatcher());
        TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StringMatcher.noneMatcher());
        TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StringMatcher.whitespaceMatcher());
        TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
    }

    @Nullable
    private char[] chars;

    @Nullable
    private String[] tokens;

    private int tokenPos = 0;

    private StringMatcher delimMatcher = StringMatcher.splitMatcher();

    private StringMatcher quoteMatcher = StringMatcher.noneMatcher();

    private StringMatcher ignoredMatcher = StringMatcher.noneMatcher();

    private StringMatcher trimmerMatcher = StringMatcher.noneMatcher();

    private boolean emptyAsNull = false;

    private boolean ignoreEmptyTokens = false;

    /**
     * 构造方法
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer() {
        this.chars = null;
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable char[] input) {
        this.chars = input != null ? input.clone() : null;
    }

    /**
     * 构造方法
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable char[] input, char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     * @param quote
     *            引号
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable char[] input, char delim, char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable char[] input, String delim) {
        this(input);
        setDelimiterString(delim);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable char[] input, StringMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     * @param quote
     *            引号
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable char[] input, final StringMatcher delim, final StringMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable String input) {
        this.chars = input != null ? input.toCharArray() : null;
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable String input, char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     * @param quote
     *            引号
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable String input, char delim, char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable String input, String delim) {
        this(input);
        setDelimiterString(delim);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable String input, StringMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    /**
     * 构造方法
     *
     * @param input
     *            初始化内容
     * @param delim
     *            分隔符
     * @param quote
     *            引号
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public StringTokenizer(@Nullable String input, StringMatcher delim, StringMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    private static StringTokenizer getCSVClone() {
        return (StringTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    /**
     * 返回CSV文件专用 {@link StringTokenizer}
     *
     * @return CSV文件专用 {@link StringTokenizer}
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public static StringTokenizer getCSVInstance() {
        return getCSVClone();
    }

    /**
     * 返回CSV文件专用 {@link StringTokenizer}
     *
     * @param input
     *            初始化内容
     *
     * @return CSV文件专用 {@link StringTokenizer}
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public static StringTokenizer getCSVInstance(final char[] input) {
        return getCSVClone().reset(input);
    }

    /**
     * 返回CSV文件专用 {@link StringTokenizer}
     *
     * @param input
     *            初始化内容
     *
     * @return CSV文件专用 {@link StringTokenizer}
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public static StringTokenizer getCSVInstance(final String input) {
        return getCSVClone().reset(input);
    }

    private static StringTokenizer getTSVClone() {
        return (StringTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }

    /**
     * 获取实例
     *
     * @param input
     *            初始化内容
     *
     * @return 实例
     */
    public static StringTokenizer newInstance(@Nullable char[] input) {
        return new StringTokenizer(input);
    }

    /**
     * 获取实例
     *
     * @param input
     *            初始化内容
     *
     * @return 实例
     */
    public static StringTokenizer newInstance(@Nullable String input) {
        return new StringTokenizer(input);
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static StringTokenizer newInstance() {
        return new StringTokenizer();
    }

    /**
     * 返回TSV文件专用 {@link StringTokenizer}
     *
     * @return TSV文件专用 {@link StringTokenizer}
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public static StringTokenizer getTSVInstance() {
        return getTSVClone();
    }

    /**
     * 返回TSV文件专用 {@link StringTokenizer}
     *
     * @param input
     *            初始化内容
     *
     * @return TSV文件专用 {@link StringTokenizer}
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public static StringTokenizer getTSVInstance(final char[] input) {
        return getTSVClone().reset(input);
    }

    /**
     * 返回TSV文件专用 {@link StringTokenizer}
     *
     * @param input
     *            初始化内容
     *
     * @return TSV文件专用 {@link StringTokenizer}
     *
     * @see #reset(char[])
     * @see #reset(String)
     */
    public static StringTokenizer getTSVInstance(final String input) {
        return getTSVClone().reset(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(String obj) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    private void addToken(List<String> list, @Nullable String tok) {
        if (tok == null || tok.isEmpty()) {
            if (isIgnoreEmptyTokens()) {
                return;
            }
            if (isEmptyTokenAsNull()) {
                tok = null;
            }
        }
        list.add(tok);
    }

    private void checkTokenized() {
        if (tokens == null) {
            final List<String> split;
            if (chars == null) {
                // still call tokenize as subclass may do some work
                split = tokenize(null, 0, 0);
            } else {
                split = tokenize(chars, 0, chars.length);
            }
            tokens = split.toArray(new String[0]);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object clone() {
        try {
            return cloneReset();
        } catch (final CloneNotSupportedException ex) {
            return null;
        }
    }

    Object cloneReset() throws CloneNotSupportedException {
        // this method exists to enable 100% test coverage
        final StringTokenizer cloned = (StringTokenizer) super.clone();
        if (cloned.chars != null) {
            cloned.chars = cloned.chars.clone();
        }
        cloned.reset();
        return cloned;
    }

    @Nullable
    public String getContent() {
        if (chars == null) {
            return null;
        }
        return new String(chars);
    }

    /**
     * 获取分隔符用匹配器
     *
     * @return 匹配器实例
     */
    public StringMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    /**
     * 设置分隔符用匹配器
     *
     * @param matcher
     *            匹配器
     *
     * @return this
     */
    public StringTokenizer setDelimiterMatcher(StringMatcher matcher) {
        Asserts.notNull(matcher);
        this.delimMatcher = matcher;
        return this;
    }

    /**
     * 获取忽略匹配器
     *
     * @return 匹配器实例
     */
    public StringMatcher getIgnoredMatcher() {
        return ignoredMatcher;
    }

    /**
     * 设置字符忽略匹配器
     *
     * @param matcher
     *            匹配器
     *
     * @return this
     */
    public StringTokenizer setIgnoredMatcher(StringMatcher matcher) {
        Asserts.notNull(matcher);
        this.ignoredMatcher = matcher;
        return this;
    }

    /**
     * 获取引号匹配器
     *
     * @return 匹配器实例
     */
    public StringMatcher getQuoteMatcher() {
        return quoteMatcher;
    }

    /**
     * 设置引号匹配器
     *
     * @param matcher
     *            匹配器
     *
     * @return this
     */
    public StringTokenizer setQuoteMatcher(StringMatcher matcher) {
        Asserts.notNull(matcher);
        this.quoteMatcher = matcher;
        return this;
    }

    /**
     * 获取 Trimmer字符匹配器
     *
     * @return 匹配器实例
     */
    public StringMatcher getTrimmerMatcher() {
        return trimmerMatcher;
    }

    /**
     * 设置 Trimmer字符匹配器
     *
     * @param matcher
     *            匹配器
     *
     * @return this
     */
    public StringTokenizer setTrimmerMatcher(StringMatcher matcher) {
        Asserts.notNull(matcher);
        this.trimmerMatcher = matcher;
        return this;
    }

    /**
     * 是否把空令牌当成 {@code null}
     *
     * @return 结果
     */
    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    /**
     * 设置是否把空令牌当成 {@code null}
     *
     * @param emptyAsNull
     *            设置项
     *
     * @return this
     */
    public StringTokenizer setEmptyTokenAsNull(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
        return this;
    }

    /**
     * 是否忽略空令牌
     *
     * @return 结果
     */
    public boolean isIgnoreEmptyTokens() {
        return ignoreEmptyTokens;
    }

    /**
     * 设置是否忽略空令牌
     *
     * @param ignoreEmptyTokens
     *            true
     *
     * @return this
     */
    public StringTokenizer setIgnoreEmptyTokens(boolean ignoreEmptyTokens) {
        this.ignoreEmptyTokens = ignoreEmptyTokens;
        return this;
    }

    /**
     * 获取所有的令牌
     *
     * @return 获取所有的令牌
     */
    public String[] getTokenArray() {
        checkTokenized();
        Asserts.notNull(tokens);
        return tokens.clone();
    }

    /**
     * 获取所有的令牌
     *
     * @param expectCount
     *            期望的Token数量
     *
     * @return 获取所有的Token
     */
    public String[] getCheckedTokenArray(int expectCount) {
        return getCheckedTokenArray(expectCount, () -> new IllegalArgumentException("expect count not match"));
    }

    /**
     * 获取所有的令牌
     *
     * @param expectCount
     *            期望的Token数量
     * @param exceptionSupplier
     *            当期望的Token数量不满足时如何抛出异常
     *
     * @return 获取所有的Token
     */
    public String[] getCheckedTokenArray(int expectCount, Supplier<RuntimeException> exceptionSupplier) {
        Asserts.notNull(exceptionSupplier);
        checkTokenized();
        Asserts.notNull(tokens);
        if (tokens.length != expectCount) {
            throw exceptionSupplier.get();
        }
        return tokens.clone();
    }

    /**
     * 获取所有的令牌
     *
     * @return 获取所有的令牌
     */
    public List<String> getTokenList() {
        checkTokenized();
        Asserts.notNull(tokens);
        return Arrays.asList(tokens);
    }

    /**
     * 获取所有的令牌
     *
     * @param expectCount
     *            期望的Token数量
     *
     * @return 获取所有的Token
     */
    public List<String> getCheckedTokenList(int expectCount) {
        return getCheckedTokenList(expectCount, () -> new IllegalArgumentException("expect count not match"));
    }

    /**
     * 获取所有的令牌
     *
     * @param expectCount
     *            期望的Token数量
     * @param exceptionSupplier
     *            当期望的Token数量不满足时如何抛出异常
     *
     * @return 获取所有的Token
     */
    public List<String> getCheckedTokenList(int expectCount, Supplier<RuntimeException> exceptionSupplier) {
        Asserts.notNull(exceptionSupplier);
        checkTokenized();
        Asserts.notNull(tokens);
        if (tokens.length != expectCount) {
            throw exceptionSupplier.get();
        }
        return Arrays.asList(tokens);
    }

    /**
     * 获取所有的令牌
     *
     * @return 获取所有的令牌
     */
    public Stream<String> getTokenStream() {
        checkTokenized();
        Asserts.notNull(tokens);
        return Stream.of(tokens);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        checkTokenized();
        Asserts.notNull(tokens);
        return tokenPos < tokens.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPrevious() {
        checkTokenized();
        return tokenPos > 0;
    }

    private boolean isQuote(final char[] srcChars, final int pos, final int len, final int quoteStart,
            final int quoteLen) {
        for (int i = 0; i < quoteLen; i++) {
            if (pos + i >= len || srcChars[pos + i] != srcChars[quoteStart + i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String next() {
        if (hasNext()) {
            return tokens[tokenPos++];
        }
        throw new NoSuchElementException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int nextIndex() {
        return tokenPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String previous() {
        if (hasPrevious()) {
            return tokens[--tokenPos];
        }
        throw new NoSuchElementException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int previousIndex() {
        return tokenPos - 1;
    }

    private int readNextToken(final char[] srcChars, int start, final int len, final TextStringBuilder workArea,
            final List<String> tokenList) {
        // skip all leading whitespace, unless it is the
        // field delimiter or the quote character
        while (start < len) {
            final int removeLen = Math.max(getIgnoredMatcher().isMatch(srcChars, start, start, len),
                    getTrimmerMatcher().isMatch(srcChars, start, start, len));
            if (removeLen == 0 || getDelimiterMatcher().isMatch(srcChars, start, start, len) > 0
                    || getQuoteMatcher().isMatch(srcChars, start, start, len) > 0) {
                break;
            }
            start += removeLen;
        }

        // handle reaching end
        if (start >= len) {
            addToken(tokenList, StringPool.EMPTY);
            return -1;
        }

        // handle empty token
        final int delimLen = getDelimiterMatcher().isMatch(srcChars, start, start, len);
        if (delimLen > 0) {
            addToken(tokenList, StringPool.EMPTY);
            return start + delimLen;
        }

        // handle found token
        final int quoteLen = getQuoteMatcher().isMatch(srcChars, start, start, len);
        if (quoteLen > 0) {
            return readWithQuotes(srcChars, start + quoteLen, len, workArea, tokenList, start, quoteLen);
        }
        return readWithQuotes(srcChars, start, len, workArea, tokenList, 0, 0);
    }

    private int readWithQuotes(final char[] srcChars, final int start, final int len, final TextStringBuilder workArea,
            final List<String> tokenList, final int quoteStart, final int quoteLen) {
        // Loop until we've found the end of the quoted
        // string or the end of the input
        workArea.clear();
        int pos = start;
        boolean quoting = quoteLen > 0;
        int trimStart = 0;

        while (pos < len) {
            // quoting mode can occur several times throughout a string
            // we must switch between quoting and non-quoting until we
            // encounter a non-quoted delimiter, or end of string
            if (quoting) {
                // In quoting mode

                // If we've found a quote character, see if it's
                // followed by a second quote. If so, then we need
                // to actually put the quote character into the token
                // rather than end the token.
                if (isQuote(srcChars, pos, len, quoteStart, quoteLen)) {
                    if (isQuote(srcChars, pos + quoteLen, len, quoteStart, quoteLen)) {
                        // matched pair of quotes, thus an escaped quote
                        workArea.append(srcChars, pos, quoteLen);
                        pos += quoteLen * 2;
                        trimStart = workArea.size();
                        continue;
                    }

                    // end of quoting
                    quoting = false;
                    pos += quoteLen;
                    continue;
                }

            } else {
                // Not in quoting mode

                // check for delimiter, and thus end of token
                final int delimLen = getDelimiterMatcher().isMatch(srcChars, pos, start, len);
                if (delimLen > 0) {
                    // return condition when end of token found
                    addToken(tokenList, workArea.substring(0, trimStart));
                    return pos + delimLen;
                }

                // check for quote, and thus back into quoting mode
                if (quoteLen > 0 && isQuote(srcChars, pos, len, quoteStart, quoteLen)) {
                    quoting = true;
                    pos += quoteLen;
                    continue;
                }

                // check for ignored (outside quotes), and ignore
                final int ignoredLen = getIgnoredMatcher().isMatch(srcChars, pos, start, len);
                if (ignoredLen > 0) {
                    pos += ignoredLen;
                    continue;
                }

                // check for trimmed character
                // don't yet know if its at the end, so copy to workArea
                // use trimStart to keep track of trim at the end
                final int trimmedLen = getTrimmerMatcher().isMatch(srcChars, pos, start, len);
                if (trimmedLen > 0) {
                    workArea.append(srcChars, pos, trimmedLen);
                    pos += trimmedLen;
                    continue;
                }
            }
            // copy regular character from inside quotes
            workArea.append(srcChars[pos++]);
            trimStart = workArea.size();
        }

        // return condition when end of string found
        addToken(tokenList, workArea.substring(0, trimStart));
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(final String obj) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    /**
     * 重置
     *
     * @return this
     */
    public StringTokenizer reset() {
        tokenPos = 0;
        tokens = null;
        return this;
    }

    /**
     * 重置
     *
     * @param input
     *            新的内容
     *
     * @return this
     */
    public StringTokenizer reset(@Nullable char[] input) {
        reset();
        this.chars = input != null ? input.clone() : null;
        return this;
    }

    /**
     * 重置
     *
     * @param input
     *            新的内容
     *
     * @return this
     */
    public StringTokenizer reset(@Nullable String input) {
        reset();
        this.chars = input != null ? input.toCharArray() : null;
        return this;
    }

    /**
     * 设置分隔符
     *
     * @param delim
     *            分隔符
     *
     * @return this
     */
    public StringTokenizer setDelimiterChar(char delim) {
        return setDelimiterMatcher(StringMatcher.charMatcher(delim));
    }

    /**
     * 设置分隔符
     *
     * @param delim
     *            分隔符
     *
     * @return this
     */
    public StringTokenizer setDelimiterString(String delim) {
        return setDelimiterMatcher(StringMatcher.stringMatcher(delim));
    }

    /**
     * 设置忽略字符
     *
     * @param ignored
     *            要忽略的字符
     *
     * @return this
     */
    public StringTokenizer setIgnoredChar(char ignored) {
        return setIgnoredMatcher(StringMatcher.charMatcher(ignored));
    }

    /**
     * 设置忽略字符
     *
     * @param quote
     *            引号
     *
     * @return this
     */
    public StringTokenizer setQuoteChar(char quote) {
        return setQuoteMatcher(StringMatcher.charMatcher(quote));
    }

    /**
     * 获取令牌个数
     *
     * @return 令牌个数
     */
    public int size() {
        checkTokenized();
        Asserts.notNull(tokens);
        return tokens.length;
    }

    protected List<String> tokenize(@Nullable char[] srcChars, int offset, int count) {
        if (srcChars == null || count == 0) {
            return Collections.emptyList();
        }
        final TextStringBuilder buf = new TextStringBuilder();
        final List<String> tokenList = new ArrayList<>();
        int pos = offset;

        // loop around the entire buffer
        while (pos >= 0 && pos < count) {
            // find next token
            pos = readNextToken(srcChars, pos, count, buf, tokenList);

            // handle case where end of string is a delimiter
            if (pos >= count) {
                addToken(tokenList, StringPool.EMPTY);
            }
        }
        return tokenList;
    }

}
