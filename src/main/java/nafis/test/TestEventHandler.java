/**
 * Created by nafis on 10/7/15.
 */
package nafis.test;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestEventHandler{

    @EventListener
    public void onApplicationEvent(TestEvent event) {
        RMIClient_Master rmiMaster = RMIClient_Master.getInstance();

        rmiMaster.notifyWorkers();
    }
}
