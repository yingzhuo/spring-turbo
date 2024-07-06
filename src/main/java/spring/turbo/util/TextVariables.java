package spring.turbo.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static spring.turbo.util.StringPool.SEMICOLON;

/**
 * 工具类，从文本中解析出若干参数
 *
 * @author 应卓
 * @see StringTokenizer
 * @since 3.3.1
 */
public class TextVariables implements Serializable {

    /**
     * 依赖{@link HashMap} 实现
     */
    private final Map<String, String> innerMap = new HashMap<>();

    /**
     * 默认构造方法
     */
    public TextVariables() {
    }

    /**
     * 构造方法
     *
     * @param text  多个参数合成的字符串
     * @param delim 参数之间的分隔符。
     * @see StringMatcher
     * @see StringMatcher#stringMatcher(String)
     */
    public TextVariables(@Nullable String text, @Nullable StringMatcher delim) {
        reset(text, delim);
    }

    /**
     * 获取解析出的参数名
     *
     * @return 参数名集合
     */
    public Set<String> getNames() {
        return innerMap.keySet();
    }

    /**
     * 获取参数值
     *
     * @param name 参数名
     * @return 参数值或空值
     */
    @Nullable
    public String getValue(String name) {
        return innerMap.get(name);
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
     * 获取参数的数量
     *
     * @return 参数的数量
     * @see Map#size()
     */
    public int size() {
        return innerMap.size();
    }

    /**
     * 判断是不是包含参数
     *
     * @return 结果
     * @see Map#size()
     */
    public boolean isEmpty() {
        return innerMap.isEmpty();
    }

    /**
     * 清除所有参数
     */
    public void clear() {
        innerMap.clear();
    }

    /**
     * 重置
     *
     * @param text  多个参数合成的字符串
     * @param delim 参数之间的分隔符
     * @see StringMatcher
     * @see StringMatcher#stringMatcher(String)
     */
    public void reset(@Nullable String text, @Nullable StringMatcher delim) {
        innerMap.clear();

        if (text == null || !text.isBlank()) {
            delim = Objects.requireNonNullElse(delim, StringMatcher.stringMatcher(SEMICOLON));

            var stringTokenizer = new StringTokenizer(text, delim);

            while (stringTokenizer.hasNext()) {
                var token = stringTokenizer.next();

                if (!token.isBlank()) {
                    var regex = "(.*?)=(.*)";
                    var pattern = Pattern.compile(regex, DOTALL | MULTILINE);
                    var matcher = pattern.matcher(token);

                    if (matcher.matches()) {
                        var variableName = matcher.group(1).trim();
                        var variableValue = matcher.group(2).trim();
                        innerMap.put(variableName, variableValue);
                    }
                }
            }
        }
    }

}
