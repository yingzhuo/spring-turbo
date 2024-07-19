package spring.turbo.util.io;

import org.springframework.boot.io.ApplicationResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author 应卓
 * @since 3.3.2
 */
public class RichResourceEditor extends PropertyEditorSupport {

    private static final ResourceLoader LOADER = new ApplicationResourceLoader(ClassUtils.getDefaultClassLoader());

    /**
     * 默认构造方法
     */
    public RichResourceEditor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAsText(String text) throws IllegalArgumentException {
        try {
            var resource = LOADER.getResource(text);
            setValue(new RichResourceImpl(resource));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
