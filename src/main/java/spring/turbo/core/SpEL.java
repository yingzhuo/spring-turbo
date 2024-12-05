package spring.turbo.core;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 简易SpEL相关工具，这个工具适用于一般绝大部分情况。
 *
 * @author 应卓
 * @since 3.4.0
 */
public final class SpEL {

    /**
     * 表达式解析器
     */
    private static final ExpressionParser EXPRESSION_PARSER;

    static {
        var config = new SpelParserConfiguration(true, true);
        EXPRESSION_PARSER = new SpelExpressionParser(config);
    }

    /**
     * 私有构造方法
     */
    private SpEL() {
    }

    /**
     * 解析SpEL并获得其值
     *
     * @param expression 表达式
     * @param rootObject 根数据
     * @param <T>        最终值的类型
     * @return 最终值或空值
     */
    @Nullable
    public static <T> T getValue(String expression, @Nullable Object rootObject) {
        return getValue(expression, rootObject, null);
    }

    /**
     * 解析SpEL并获得其值
     *
     * @param expression 表达式
     * @param variables  其他变量
     * @param <T>        最终值的类型
     * @return 最终值或空值
     */
    @Nullable
    public static <T> T getValue(String expression, @Nullable Map<String, ?> variables) {
        return getValue(expression, null, variables);
    }

    /**
     * 解析SpEL并获得其值
     *
     * @param expression 表达式
     * @param rootObject 根数据
     * @param variables  其他变量
     * @param <T>        最终值的类型
     * @return 最终值或空值
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T getValue(String expression, @Nullable Object rootObject, @Nullable Map<String, ?> variables) {
        var ctx = new StandardEvaluationContext(rootObject);
        if (!CollectionUtils.isEmpty(variables)) {
            for (var key : variables.keySet()) {
                var value = variables.get(key);
                ctx.setVariable(key, value);
            }
        }

        return (T) EXPRESSION_PARSER
                .parseExpression(expression)
                .getValue(ctx);
    }

}
