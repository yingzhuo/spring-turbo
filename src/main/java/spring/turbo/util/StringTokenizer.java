/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.*;

/**
 * @author 应卓
 * @since 2.0.2
 */
public class StringTokenizer implements ListIterator<String>, Cloneable {

    private static final StringTokenizer CSV_TOKENIZER_PROTOTYPE;
    private static final StringTokenizer TSV_TOKENIZER_PROTOTYPE;

    static {
        CSV_TOKENIZER_PROTOTYPE = new StringTokenizer();
        CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StringMatcherFactories.commaMatcher());
        CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StringMatcherFactories.doubleQuoteMatcher());
        CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StringMatcherFactories.noneMatcher());
        CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StringMatcherFactories.whitespaceMatcher());
        CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);

        TSV_TOKENIZER_PROTOTYPE = new StringTokenizer();
        TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StringMatcherFactories.tabMatcher());
        TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StringMatcherFactories.doubleQuoteMatcher());
        TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StringMatcherFactories.noneMatcher());
        TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StringMatcherFactories.whitespaceMatcher());
        TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
    }

    private char[] chars;
    private String[] tokens;
    private int tokenPos;
    private StringMatcher delimMatcher = StringMatcherFactories.splitMatcher();
    private StringMatcher quoteMatcher = StringMatcherFactories.noneMatcher();
    private StringMatcher ignoredMatcher = StringMatcherFactories.noneMatcher();
    private StringMatcher trimmerMatcher = StringMatcherFactories.noneMatcher();
    private boolean emptyAsNull;
    private boolean ignoreEmptyTokens = true;

    public StringTokenizer() {
        this.chars = null;
    }

    public StringTokenizer(final char[] input) {
        this.chars = input != null ? input.clone() : null;
    }

    public StringTokenizer(final char[] input, final char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    public StringTokenizer(final char[] input, final char delim, final char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    public StringTokenizer(final char[] input, final String delim) {
        this(input);
        setDelimiterString(delim);
    }

    public StringTokenizer(final char[] input, final StringMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    public StringTokenizer(final char[] input, final StringMatcher delim, final StringMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    public StringTokenizer(final String input) {
        this.chars = input != null ? input.toCharArray() : null;
    }

    public StringTokenizer(final String input, final char delim) {
        this(input);
        setDelimiterChar(delim);
    }

    public StringTokenizer(final String input, final char delim, final char quote) {
        this(input, delim);
        setQuoteChar(quote);
    }

    public StringTokenizer(final String input, final String delim) {
        this(input);
        setDelimiterString(delim);
    }

    public StringTokenizer(final String input, final StringMatcher delim) {
        this(input);
        setDelimiterMatcher(delim);
    }

    public StringTokenizer(final String input, final StringMatcher delim, final StringMatcher quote) {
        this(input, delim);
        setQuoteMatcher(quote);
    }

    private static StringTokenizer getCSVClone() {
        return (StringTokenizer) CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StringTokenizer getCSVInstance() {
        return getCSVClone();
    }

    public static StringTokenizer getCSVInstance(final char[] input) {
        return getCSVClone().reset(input);
    }

    public static StringTokenizer getCSVInstance(final String input) {
        return getCSVClone().reset(input);
    }

    private static StringTokenizer getTSVClone() {
        return (StringTokenizer) TSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StringTokenizer getTSVInstance() {
        return getTSVClone();
    }

    public static StringTokenizer getTSVInstance(final char[] input) {
        return getTSVClone().reset(input);
    }

    public static StringTokenizer getTSVInstance(final String input) {
        return getTSVClone().reset(input);
    }

    @Override
    public void add(final String obj) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    private void addToken(final List<String> list, String tok) {
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

    public String getContent() {
        if (chars == null) {
            return null;
        }
        return new String(chars);
    }

    public StringMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StringTokenizer setDelimiterMatcher(final StringMatcher delim) {
        this.delimMatcher = delim == null ? StringMatcherFactories.noneMatcher() : delim;
        return this;
    }

    public StringMatcher getIgnoredMatcher() {
        return ignoredMatcher;
    }

    public StringTokenizer setIgnoredMatcher(final StringMatcher ignored) {
        if (ignored != null) {
            this.ignoredMatcher = ignored;
        }
        return this;
    }

    public StringMatcher getQuoteMatcher() {
        return quoteMatcher;
    }

    public StringTokenizer setQuoteMatcher(final StringMatcher quote) {
        if (quote != null) {
            this.quoteMatcher = quote;
        }
        return this;
    }

    public String[] getTokenArray() {
        checkTokenized();
        return tokens.clone();
    }

    public List<String> getTokenList() {
        checkTokenized();
        return Arrays.asList(tokens);
    }

    public StringMatcher getTrimmerMatcher() {
        return trimmerMatcher;
    }

    public StringTokenizer setTrimmerMatcher(final StringMatcher trimmer) {
        if (trimmer != null) {
            this.trimmerMatcher = trimmer;
        }
        return this;
    }

    @Override
    public boolean hasNext() {
        checkTokenized();
        return tokenPos < tokens.length;
    }

    @Override
    public boolean hasPrevious() {
        checkTokenized();
        return tokenPos > 0;
    }

    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    public StringTokenizer setEmptyTokenAsNull(final boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
        return this;
    }

    public boolean isIgnoreEmptyTokens() {
        return ignoreEmptyTokens;
    }

    public StringTokenizer setIgnoreEmptyTokens(final boolean ignoreEmptyTokens) {
        this.ignoreEmptyTokens = ignoreEmptyTokens;
        return this;
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

    @Override
    public String next() {
        if (hasNext()) {
            return tokens[tokenPos++];
        }
        throw new NoSuchElementException();
    }

    @Override
    public int nextIndex() {
        return tokenPos;
    }

    public String nextToken() {
        if (hasNext()) {
            return tokens[tokenPos++];
        }
        return null;
    }

    @Override
    public String previous() {
        if (hasPrevious()) {
            return tokens[--tokenPos];
        }
        throw new NoSuchElementException();
    }

    @Override
    public int previousIndex() {
        return tokenPos - 1;
    }

    public String previousToken() {
        if (hasPrevious()) {
            return tokens[--tokenPos];
        }
        return null;
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    public StringTokenizer reset() {
        tokenPos = 0;
        tokens = null;
        return this;
    }

    public StringTokenizer reset(final char[] input) {
        reset();
        this.chars = input != null ? input.clone() : null;
        return this;
    }

    public StringTokenizer reset(final String input) {
        reset();
        this.chars = input != null ? input.toCharArray() : null;
        return this;
    }

    @Override
    public void set(final String obj) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    public StringTokenizer setDelimiterChar(final char delim) {
        return setDelimiterMatcher(StringMatcherFactories.charMatcher(delim));
    }

    public StringTokenizer setDelimiterString(final String delim) {
        return setDelimiterMatcher(StringMatcherFactories.stringMatcher(delim));
    }

    public StringTokenizer setIgnoredChar(final char ignored) {
        return setIgnoredMatcher(StringMatcherFactories.charMatcher(ignored));
    }

    public StringTokenizer setQuoteChar(final char quote) {
        return setQuoteMatcher(StringMatcherFactories.charMatcher(quote));
    }

    public int size() {
        checkTokenized();
        return tokens.length;
    }

    protected List<String> tokenize(final char[] srcChars, final int offset, final int count) {
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
