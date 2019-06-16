/*
	4:40 AM 16th Sept 2018
	Santosh Kavhar
	Server
*/

//javac -classpath ".:jv.jar" Server.java
//java -classpath ".:jv.jar" Server

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
//import java.sql.Connection;
//import java.sql.DriverManager;

//Threaded Server
public class Server
{
            static final String url="jdbc:mysql://localhost:3306/SIS?useSSL=false"; //JDBC 
  		    static final String user="root";
   		    static final String password="root";

	public static void main(String args[]) throws Exception
	{
	  
		try
		{
			int i=1;
			//establish server socket
			ServerSocket s = new ServerSocket(7008);
			
			while(true)
			{
				Socket incoming = s.accept();
				System.out.println("--- Client no." + i + " connected !!!");
				//Thread Handler
				Runnable r = new ThreadedHandler(incoming);
				Thread t= new Thread(r);
				t.start();
				i++;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

/**
	This class Handles the client input for one or more server socket connection.
*/

class ThreadedHandler implements Runnable
{
	private Socket incoming;
	
	DataInputStream din;
	DataOutputStream dos;
	/**
		Constructs a handler
		@param i the incoming socket
	*/
	
	public ThreadedHandler(Socket i)throws Exception
	{
		incoming = i;
		din = new DataInputStream(incoming.getInputStream());
		dos = new DataOutputStream(incoming.getOutputStream());
	}
	
	
	
	public void login() 
	{
					//String user_name, pwd;
		try
		{
			dos.writeUTF("Hello !!! Enter your username\n");
			String user_name = din.readUTF();
					
			dos.writeUTF("Hello !!! Enter your password\n");
			String pwd =din.readUTF();
					
					
				
			Class.forName("com.mysql.cj.jdbc.Driver");
          		
          	Connection con=DriverManager.getConnection(Server.url,Server.user,Server.password);               //mysql Table

          	PreparedStatement pstmt=con.prepareStatement("select * from Login where user =  ? and passwd = ?");
          		
          	pstmt.setString(1, user_name);
          	pstmt.setString(2, pwd);      
       
          	ResultSet rs=pstmt.executeQuery();
          	if(rs.next())
          		dos.writeInt(1);
          	else 
          	//return 0;
          		 dos.writeInt(0);
          		 
     	}
        catch(Exception e)
        {
          	e.printStackTrace();
        }
	}
	
	
	
	
	
	public void run()
	{
			try
			{
				//boolean to see if clients wants to disconnect
				boolean done =false;
				
			
				

				Scanner in = new Scanner(System.in);
				//PrintWriter out = new PrintWriter (outStream , true /* autoFlush  */);
				
				while(!done /*&& in.hasNextLine()*/)
				{
					dos.writeUTF("Hello !!! Enter your choice \n1.Log in\n2.Exit\n");
					//client(s) input
					int choice = din.readInt();
					
					if(choice == 1) 
					{
						login();
					}
					
					else	 
					{
						done = true;
						dos.writeUTF("BYE !!!");
						System.out.println("--- Client with " +incoming + " dis-connected !!!");   		
					}
				}
				
				
				
				
				
			
    		}
    		/*finally
    		{
    			incoming.close();
    		}*/
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    	finally
    		{
    			incoming.close();
    		}

   }
  }


