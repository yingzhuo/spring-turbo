package spring.turbo.util.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RichResourceEditorTest {

    @Test
    void test1() {
        var editor = new RichResourceEditor();

        editor.setAsText("string:just a string resource");
        var resource = (RichResource) editor.getValue();

        Assertions.assertEquals("just a string resource", resource.toString());
    }

}
