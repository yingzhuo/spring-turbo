package spring.turbo.util.text;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static spring.turbo.util.CharPool.LF;
import static spring.turbo.util.CharPool.SEMICOLON;
import static spring.turbo.util.StringUtils.isNotBlank;

/**
 * @author 应卓
 * @since 3.3.1
 */
public class TextVariables extends MutablePropertyValues implements Serializable {

    private static final StringMatcher DEFAULT_DELIMITER = StringMatcher.charSetMatcher(SEMICOLON, LF);

    /**
     * 创建实例
     *
     * @param text 文本
     * @return 实例
     */
    public static TextVariables valueOf(@Nullable String text) {
        return new TextVariables(text, null);
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
        set(text, delimiter);
    }

    /**
     * 转换成 {@link Map}
     *
     * @return {@link Map} 实例
     */
    public Map<String, String> toMap() {
        var map = new HashMap<String, String>();
        stream().forEach(pv -> map.put(pv.getName(), (String) pv.getValue()));
        return map;
    }

    /**
     * 转换成 {@link Properties}
     *
     * @return {@link Properties} 实例
     */
    public Properties toProperties() {
        var props = new Properties();
        props.putAll(toMap());
        return props;
    }

    private void set(@Nullable String text, @Nullable StringMatcher delimiter) {
        if (isNotBlank(text)) {
            delimiter = delimiter != null ? delimiter : DEFAULT_DELIMITER;

            var stringTokenizer = StringTokenizer.newInstance(text)
                    .setDelimiterMatcher(delimiter)
                    .setTrimmerMatcher(StringMatcher.noneMatcher());

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
                        super.addPropertyValue(new PropertyValue(variableName, variableValue));
                    }
                }
            }
        }
    }

}
