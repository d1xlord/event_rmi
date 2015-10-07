package nafis.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by nafis on 10/7/15.
 */
public class RMIServer_Master extends UnicastRemoteObject implements Runnable, Remote, GetDataMaster{

    public RMIServer_Master() throws RemoteException {
        super();
    }

    public void run() {
        System.out.println("RMI Server for Master started");

        int port = 1099;

        try {
            LocateRegistry.createRegistry(port);
            System.out.println("RMI registry for Master created");
        }
        catch (RemoteException ex) {
            try {
                LocateRegistry.getRegistry(port);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            System.out.println("java RMI registry for Master already exists");
        }

        try {
            RMIServer_Master svr = new RMIServer_Master();
            Naming.rebind("RmiServer_master", svr);
            System.out.println("RMI Server registry for Master is bound");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Thread t = new Thread(this, "rmiMaster_thread");
        t.start();
    }

    public String getDataMaster() throws RemoteException {
        String ret = "Reached the Master successfully";
        return ret;
    }
}
