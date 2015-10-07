/**
 * Created by nafis on 10/7/15.
 */
package nafis.test;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent{

    public TestEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My Custom Event";
    }
}
