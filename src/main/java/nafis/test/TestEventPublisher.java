/**
 * Created by nafis on 10/7/15.
 */
package nafis.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class TestEventPublisher{

    private ApplicationEventPublisher publisher;

    @Autowired
    public void myComponent(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish() {
        TestEvent te = new TestEvent(this);
        publisher.publishEvent(te);
    }
}
