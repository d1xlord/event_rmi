package nafis.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nafis on 10/7/15.
 */
public interface GetDataMaster extends Remote{
    public String getDataMaster() throws RemoteException;
}
