/*
	4:40 AM 16th Sept 2018
	Santosh Kavhar
	Client
*/

import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.concurrent.*;

 public class Client
  {
 
   public static void main(String args[])
   {
 
  try{
    
	Socket socket=new Socket("localhost",7008);  
	
	
	Scanner sc = new Scanner(System.in);
	
	DataInputStream din =new  DataInputStream(socket.getInputStream());
	DataOutputStream dos =new DataOutputStream(socket.getOutputStream());

	
	
	System.out.println(din.readUTF());
	dos.writeInt(sc.nextInt());
	
	System.out.println(din.readUTF());
	dos.writeUTF(sc.next());
	
	System.out.println(din.readUTF());
	dos.writeUTF(sc.next());
	
	System.out.println(din.readInt());
	
	
 
	}
	catch(Exception e)
	{
		System.out.println(e);
	} 



}
}  

//String str1=(String)in.readUTF();
	
	/*while(true)
	{
		String line = in.nextLine();
		 
		
	}*/

/*
	ScheduledExecutorService scheduler
                            = Executors.newSingleThreadScheduledExecutor();
 
       Runnable task = new Runnable() {
            public void run() {
               // s.close();
            }
        };

		int delay = 5;
        scheduler.schedule(task, delay, TimeUnit.MINUTES);
        scheduler.shutdown();
*/
  //  char ans;

   /*
    private static final String url = "jdbc:mysql://localhost:3306/SIS";
   private static final String user = "root";
   private static final String password = "root";

   transient Scanner ch=new Scanner(System.in);

  

    public void Enroll()     //Registartion Function
    {
  
     try
     {
      System.out.println("*********SUGGESTIONS INFORMATION SYSTEM********:");
        
      

      DataInputStream dis=new DataInputStream(s.getInputStream());
      DataOutputStream dout=new  DataOutputStream(s.getOutputStream());
  }
  
  }
*/
