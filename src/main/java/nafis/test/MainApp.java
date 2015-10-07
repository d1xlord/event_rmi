/**
 * Created by nafis on 10/7/15.
 */
package nafis.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;

public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        TestEventPublisher tep = (TestEventPublisher) context.getBean("testEventPublisher");
        RMIServer_Master rmiMaster = null;
        try {
            rmiMaster = new RMIServer_Master();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        rmiMaster.start();

        try {
            Thread.sleep(3000);
            // Time is needed for rmi name binding, error occurs if publish is before rmimaster name binding
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tep.publish();
    }
}
