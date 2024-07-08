package spring.turbo.util.text;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import spring.turbo.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static spring.turbo.util.CharPool.LF;
import static spring.turbo.util.CharPool.SEMICOLON;

/**
 * 工具类，从文本中解析出若干参数名值对。
 *
 * @author 应卓
 * @see StringTokenizer
 * @since 3.3.1
 */
public class TextVariables extends HashMap<String, String> implements Serializable {

    private static final StringMatcher DEFAULT_DELIMITER = StringMatcher.charSetMatcher(SEMICOLON, LF);

    /**
     * 默认构造方法
     */
    public TextVariables() {
    }

    /**
     * 构造方法
     *
     * @param text 多个参数合成的字符串
     * @see StringMatcher
     * @see StringMatcher#stringMatcher(String)
     */
    public TextVariables(@Nullable String text) {
        reset(text, DEFAULT_DELIMITER);
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
     * 获取解析出的参数名
     *
     * @return 参数名集合
     */
    public Set<String> getNames() {
        return this.keySet();
    }

    /**
     * 获取参数值
     *
     * @param name 参数名
     * @return 参数值或空值
     */
    @Nullable
    public String getValue(String name) {
        return this.get(name);
    }

    /**
     * 获取参数值
     *
     * @param name         参数名
     * @param defaultValue 默认值
     * @return 参数值默认值
     */
    @Nullable
    public String getValue(String name, @Nullable String defaultValue) {
        var value = getValue(name);
        if (value != null && !value.isBlank()) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取参数值并转换成其他类型
     *
     * @param name      参数名
     * @param converter 转换器
     * @param <T>       转换器目标类型泛型
     * @return 转换结果
     */
    @Nullable
    public <T> T getValue(String name, Converter<String, T> converter) {
        var value = getValue(name);
        if (value == null || value.isBlank()) {
            return (T) null;
        }
        return converter.convert(value);
    }

    /**
     * 获取参数值并转换成其他类型
     *
     * @param name          参数名
     * @param converter     转换器
     * @param defaultObject 转换失败或没有对应值时返回此对象
     * @param <T>           转换器目标类型泛型
     * @return 转换结果或默认值
     */
    @Nullable
    public <T> T getValue(String name, Converter<String, T> converter, @Nullable T defaultObject) {
        var value = getValue(name);
        if (value == null || value.isBlank()) {
            return (T) defaultObject;
        }

        try {
            return converter.convert(value);
        } catch (Throwable e) {
            return defaultObject;
        }
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
        clear();

        if (StringUtils.isNotBlank(text)) {
            delimiter = delimiter != null ? delimiter : DEFAULT_DELIMITER;

            var stringTokenizer =
                    StringTokenizer.newInstance(text)
                            .setDelimiterMatcher(delimiter)
                            .setTrimmerMatcher(StringMatcher.noneMatcher());

            while (stringTokenizer.hasNext()) {
                var token = stringTokenizer.next();

                if (!token.isBlank()) {

                    // 注意: Group1 用的是懒惰量词
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

}
