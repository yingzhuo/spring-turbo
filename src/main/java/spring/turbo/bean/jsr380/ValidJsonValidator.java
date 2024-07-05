package spring.turbo.bean.jsr380;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.Nullable;
import spring.turbo.io.CloseUtils;

import java.io.IOException;

/**
 * @author 应卓
 * @since 2.0.4
 */
public class ValidJsonValidator implements ConstraintValidator<ValidJson, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(@Nullable String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return isValidJSON(value);
    }

    /**
     * {@inheritDoc}
     */
    private boolean isValidJSON(String json) {
        boolean valid = false;
        try {
            final JsonParser parser = new ObjectMapper().createParser(json);
            while (parser.nextToken() != null) {
                // nop
            }
            valid = true;
            CloseUtils.closeQuietly(parser);
        } catch (IOException ignored) {
            // nop
        }
        return valid;
    }

}
