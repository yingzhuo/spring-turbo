package spring.turbo.util.text;

import java.beans.PropertyEditorSupport;

/**
 * @author 应卓
 * @since 3.3.1
 */
public class TextVariablesEditor extends PropertyEditorSupport {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new TextVariables(text, null));
    }

}
