package nafis.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nafis on 10/7/15.
 */
public interface NotifyWorkers extends Remote{
    public void notifyWorker(int value) throws RemoteException;
}
