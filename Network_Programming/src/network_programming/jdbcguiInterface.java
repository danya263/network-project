
package network_programming;


import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.ArrayList;

public interface jdbcguiInterface extends Remote {
    
public String Delete (int id) throws RemoteException;

public String Insert (String name, int id, String gender, int age, String department, String year) throws RemoteException;

public String Update (String name, int id, String gender, int age, String department, String year) throws RemoteException;

public ArrayList Search (int id) throws RemoteException;
}
