package spring.turbo.util.io;

import spring.turbo.core.ResourceUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author 应卓
 * @since 3.3.2
 */
public class RichResourceEditor extends PropertyEditorSupport {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAsText(String text) throws IllegalArgumentException {
        try {
            var resource = ResourceUtils.loadResource(text);
            setValue(new RichResourceImpl(resource));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
