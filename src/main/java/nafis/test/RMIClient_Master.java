package nafis.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by nafis on 10/7/15.
 */
public class RMIClient_Master {

    private static RMIClient_Master rmiMaster = new RMIClient_Master();
    ArrayList<String> host = new ArrayList<String>();

    private RMIClient_Master() {
        host.add("192.168.104.64:1099");

    }

    public static RMIClient_Master getInstance() {
        return rmiMaster;
    }

    public void notifyWorkers() {

        for (int i = 1; i <= 3; i++) {
            try {
                NotifyWorkers test = (NotifyWorkers) Naming.lookup("//" + host.get(0) + "/RmiServer_worker" + Integer.toString(i));

                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                System.out.println("Notifying at: " + dateFormat.format(date));
                test.notifyWorker(1);
                System.out.println("Worker " + Integer.toString(i) + " is notified");
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
