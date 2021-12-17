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

/**
 * @author 应卓
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public final class ExpressionUtils {

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser(
            new SpelParserConfiguration(true, true)
    );

    private ExpressionUtils() {
        super();
    }

    public static <T> T getValue(String expressionString) {
        Asserts.hasText(expressionString);
        final Expression expression = EXPRESSION_PARSER.parseExpression(expressionString);
        return (T) expression.getValue();
    }

    public static <T> T getValue(Object rootObject, String expressionString) {
        Asserts.hasText(expressionString);
        Asserts.notNull(rootObject);
        final Expression expression = EXPRESSION_PARSER.parseExpression(expressionString);
        final StandardEvaluationContext context = new StandardEvaluationContext(rootObject);
        return (T) expression.getValue(context);
    }

}
