
package network_programming;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

  public class Server  extends UnicastRemoteObject implements jdbcguiInterface{
public Server() throws RemoteException{

super();}
public static void main(String[] args) throws RemoteException{  
 Registry reg=LocateRegistry.createRegistry(1099);

Server s= new Server();

reg.rebind("db",s );

System.out.println("Server is Running now.... ");
}  

    @Override
    public String Delete(int id) throws RemoteException {
        try{

Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rmiTest", "root", "");

Statement st=con.createStatement();

String sql="delete from student where id='"+id+"'";

st.executeUpdate(sql);

return "Record Deletd Succefully";}

catch (Exception e) {

return (e.toString());}
    }
   
    @Override
    public String Insert(String name, int id, String gender, int age, String department, String year) throws RemoteException {
        try{
System.out.println(name);
System.out.println("after name");
Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rmitest", "root", "");

String st="INSERT INTO student (id, name, gender,department,age,year) VALUES (?,?,?,?,?,?)";
PreparedStatement preparedStatement = con.prepareStatement(st);{
            

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, department);
            preparedStatement.setInt(5, age);
             preparedStatement.setString(6, year);
             int row = preparedStatement.executeUpdate();
               System.out.println(row); }
return "Record Inserted Succesfully";}

catch ( ClassNotFoundException | SQLException e) {

return (e.toString());
}
    }

    @Override
    public String Update(String name, int id, String gender, int age, String department, String year) throws RemoteException {
          try{

Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rmiTest","root", "");

Statement st=con.createStatement();

String sql="update student set name='"+name+"', gender='"+gender+"', department'"+department+"',age='"+age+"',year='"+year+"' where id='"+id+"'";

st.executeUpdate(sql);

return "Record Updated Succesfully";
}

catch (Exception e) {

return (e.toString());
}
    }

    @Override
    public ArrayList Search(int id) throws RemoteException {
        
ArrayList student=new ArrayList();

try{

Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rmiTest","root", "");

Statement st=con.createStatement();

String sql="select *from student where id="+id+"*";

ResultSet rs;

rs=st.executeQuery (sql);

while (rs.next()) {

student.add("Name:"+rs.getString("name"));

student.add("Gender: "+rs.getString("gender"));

student.add("Department:"+rs.getString("department")); 

student.add("Age:"+rs.getString("ager"));
student.add("Year:"+rs.getString("year"));}}

catch (Exception e) {

e.printStackTrace();

}

return student   ;
    }
    
}
