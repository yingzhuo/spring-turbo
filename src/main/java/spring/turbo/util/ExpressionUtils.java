/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;

/**
 * {@code SPEL}相关工具
 *
 * @author 应卓
 *
 * @see Expression
 * @see SpelExpressionParser
 *
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
        super();
    }

    /**
     * 获取表达式的值
     *
     * @param expressionString
     *            表达式
     * @param <T>
     *            表达式值的类型
     *
     * @return 值
     */
    @Nullable
    public static <T> T getValue(String expressionString) {
        Asserts.hasText(expressionString);
        final Expression expression = EXPRESSION_PARSER.parseExpression(expressionString);
        return (T) expression.getValue();
    }

    /**
     * 获取表达式的值
     *
     * @param rootObj
     *            根值
     * @param expressionString
     *            表达式
     * @param <T>
     *            表达式值的类型
     *
     * @return 值
     */
    @Nullable
    public static <T> T getValue(Object rootObj, String expressionString) {
        Asserts.notNull(rootObj);
        Asserts.hasText(expressionString);
        final Expression expression = EXPRESSION_PARSER.parseExpression(expressionString);
        final StandardEvaluationContext context = new StandardEvaluationContext(rootObj);
        return (T) expression.getValue(context);
    }

}
