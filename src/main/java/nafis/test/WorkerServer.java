package nafis.test;

import nafis.test.NotifyWorkers;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by nafis on 10/7/15.
 */
public class WorkerServer extends UnicastRemoteObject implements NotifyWorkers {

    String master_hostname = "192.168.104.64:1099";

    public WorkerServer() throws RemoteException {
        super();
    }

    public void notifyWorker(int value) {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            System.out.println("Worker " + hostname + " notified at " + dateFormat.format(date));

            GetDataMaster worker = (GetDataMaster) Naming.lookup("//" + master_hostname + "/RmiServer_master");
            String data = worker.getDataMaster();
            System.out.println("---> Data from Master: " + data);
            System.out.println("Worker " + hostname + " notified at " + dateFormat.format(date));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println("RMI Server started");
        Scanner scan = new Scanner(System.in);
        int port = 1099;
        int id = scan.nextInt();

        try {
            LocateRegistry.createRegistry(port);
            System.out.println("RMI registry created");
        }
        catch (RemoteException ex) {
            LocateRegistry.getRegistry(port);
            System.out.println("java RMI registry already exists");
        }

        WorkerServer svr = new WorkerServer();

        Naming.rebind("RmiServer_worker" + Integer.toString(id), svr);
        System.out.println("RMI Service bound in registry...");
    }
}
