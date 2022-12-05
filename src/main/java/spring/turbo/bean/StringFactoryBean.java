/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import spring.turbo.io.ResourceOptions;
import spring.turbo.util.Asserts;

import java.nio.charset.Charset;

import static spring.turbo.util.CharsetPool.UTF_8;
import static spring.turbo.util.StringPool.EMPTY;

/**
 * {@link String} 类型的Bean工厂
 *
 * @author 应卓
 * @since 1.0.8
 */
public class StringFactoryBean implements FactoryBean<String>, InitializingBean {

    @NonNull
    private String text = EMPTY;
    private boolean trim = false;
    private boolean toOneLine = false;
    private boolean toUpperCase = false;
    private boolean toLowerCase = false;

    public StringFactoryBean() {
        super();
    }

    public StringFactoryBean(String text) {
        Asserts.notNull(text);
        this.text = text;
    }

    public StringFactoryBean(Resource text) {
        this(text, UTF_8);
    }

    public StringFactoryBean(Resource text, Charset charset) {
        Asserts.notNull(text);
        Asserts.notNull(charset);

        this.text = ResourceOptions.builder().add(text).build().toString(charset);
    }

    @Override
    public void afterPropertiesSet() {
        Asserts.notNull(text);

        if (trim) {
            text = text.trim();
        }
        if (toOneLine) {
            text = text.replaceAll("\r*\n*", EMPTY);
        }
        if (toLowerCase) {
            text = text.toLowerCase();
        }
        if (toUpperCase) {
            text = text.toUpperCase();
        }
    }

    @Override
    public String getObject() {
        return text;
    }

    @Override
    public final Class<?> getObjectType() {
        return String.class;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setText(Resource text) {
        setText(text, UTF_8);
    }

    public void setText(Resource text, Charset charset) {
        Asserts.notNull(text);
        Asserts.notNull(charset);
        this.text = ResourceOptions.builder().add(text).build().toString(charset);
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    public void setToOneLine(boolean toOneLine) {
        this.toOneLine = toOneLine;
    }

    public void setToUpperCase(boolean toUpperCase) {
        this.toUpperCase = toUpperCase;
    }

    public void setToLowerCase(boolean toLowerCase) {
        this.toLowerCase = toLowerCase;
    }
}
