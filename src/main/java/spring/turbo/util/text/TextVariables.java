package spring.turbo.util.text;

import org.springframework.lang.Nullable;
import spring.turbo.util.StringUtils;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static spring.turbo.util.CharPool.LF;
import static spring.turbo.util.CharPool.SEMICOLON;

/**
 * @author 应卓
 * @see TextVariablesEditor
 * @since 3.3.1
 */
public class TextVariables extends HashMap<String, String> {

    private static final StringMatcher DEFAULT_DELIMITER = StringMatcher.charSetMatcher(SEMICOLON, LF);

    /**
     * 默认构造方法
     */
    public TextVariables() {
    }

    /**
     * 构造方法
     *
     * @param text 文本
     */
    public TextVariables(@Nullable String text) {
        this(text, null);
    }

    /**
     * 构造方法
     *
     * @param text      多个参数合成的字符串
     * @param delimiter 参数之间的分隔符
     * @see StringMatcher
     * @see StringMatcher#stringMatcher(String)
     */
    public TextVariables(@Nullable String text, @Nullable StringMatcher delimiter) {
        reset(text, delimiter);
    }

    /**
     * 重置
     *
     * @param text      多个参数合成的字符串
     * @param delimiter 参数之间的分隔符
     * @see StringMatcher
     * @see StringMatcher#stringMatcher(String)
     */
    public void reset(@Nullable String text, @Nullable StringMatcher delimiter) {
        this.clear();

        if (StringUtils.isNotBlank(text)) {
            delimiter = delimiter != null ? delimiter : DEFAULT_DELIMITER;

            var stringTokenizer = StringTokenizer.newInstance(text).setDelimiterMatcher(delimiter).setTrimmerMatcher(StringMatcher.noneMatcher());

            while (stringTokenizer.hasNext()) {
                var token = stringTokenizer.next();

                if (!token.isBlank()) {

                    // 注意: Group1 用的是懒惰量词
                    // 注意: Group2 用的是贪婪量词
                    var regex = "(.*?)=(.*)";
                    var pattern = Pattern.compile(regex, DOTALL | MULTILINE);
                    var matcher = pattern.matcher(token);

                    if (matcher.matches()) {
                        var variableName = matcher.group(1).trim();
                        var variableValue = matcher.group(2).trim();
                        put(variableName, variableValue);
                    }
                }
            }
        }
    }

    /**
     * 获取解析出的参数名
     *
     * @return 参数名集合
     */
    public Set<String> getVariableNames() {
        return this.keySet();
    }

    /**
     * 获取参数值
     *
     * @param variableName 参数名
     * @return 参数值或空值
     */
    @Nullable
    public String getVariableValue(String variableName) {
        return this.get(variableName);
    }

    /**
     * 获取参数值
     *
     * @param name         参数名
     * @param defaultValue 默认值
     * @return 参数值默认值
     */
    @Nullable
    public String getVariableValue(String name, @Nullable String defaultValue) {
        var value = getVariableValue(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 转换成 {@link Properties}
     *
     * @return {@link Properties} 实例
     */
    public Properties toProperties() {
        var props = new Properties();
        props.putAll(this);
        return props;
    }

}