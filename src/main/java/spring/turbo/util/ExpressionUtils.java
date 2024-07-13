package spring.turbo.util;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * {@code SpEL}相关工具
 *
 * @author 应卓
 * @see Expression
 * @see SpelExpressionParser
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public final class ExpressionUtils {

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser(
            new SpelParserConfiguration(true, true));

    /**
     * 私有构造方法
     */
    private ExpressionUtils() {
    }

    /**
     * 获取表达式的值
     *
     * @param expressionString 表达式
     * @param <T>              表达式值的类型
     * @return 值
     */
    @Nullable
    public static <T> T getValue(String expressionString) {
        Assert.hasText(expressionString, "expressionString is required");
        final Expression expression = EXPRESSION_PARSER.parseExpression(expressionString);
        return (T) expression.getValue();
    }

    /**
     * 获取表达式的值
     *
     * @param rootObj          根值
     * @param expressionString 表达式
     * @param <T>              表达式值的类型
     * @return 值
     */
    @Nullable
    public static <T> T getValue(Object rootObj, String expressionString) {
        Assert.notNull(rootObj, "rootObj is required");
        Assert.hasText(expressionString, "expressionString is required");
        final Expression expression = EXPRESSION_PARSER.parseExpression(expressionString);
        final StandardEvaluationContext context = new StandardEvaluationContext(rootObj);
        return (T) expression.getValue(context);
    }

}
