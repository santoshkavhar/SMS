/*
	1:37 PM 20th Sept 2018
	Santosh Kavhar
	Server
*/


/*

	*** What is telnet service ? ***
	
	** Telnet is a protocol that allows you to connect to 
	* remote computers (called hosts) over a TCP/IP network 
	* (such as the internet). Using telnet client software 
	* on your computer, you can make a connection to a telnet server 
	* (i.e., the remote host). Once your telnet client establishes
	* a connection to the remote host, your client becomes
	* a virtual terminal, allowing you to communicate with
	* the remote host from your computer. In most cases, 
	* you'll need to log into the remote host, 
	* which requires that you have an account on that system.
	* Occasionally, you can log in as guest or public 
	* without having an account.

	* Telnet clients are available for all major operating systems.

	* Command-line telnet clients are built into most
	* versions of macOS, Windows, Unix, and Linux.
	* To use these clients, go to their respective 
	* command lines (i.e., the Terminal application in macOS,
	* the shell in Unix or Linux, or the DOS prompt in Windows),
	* and then enter:
		* telnet host_ip host_port

	* Replace host_ip with the address of the service,
	* and port with the host_port number on which the
	* service runs (for example, 80 for http). 


	** Telnet (TN) is a networking protocol and software 
	* program used to access remote computers and terminals 
	* over the Internet or a TCP/IP computer network. 
	* Telnet was conceived in 1969 and standardized as 
	* one of the first Internet standards by the 
	* Internet Engineering Task Force (IETF).

	** Telnet is a network text-only protocol that provides 
	* bidirectional interactive communications facility 
	* using virtual terminal connection. 
	* Telnet is the method that allows connecting to a 
	* remote computer over Internet and using programs 
	* and data as if they were on your local machine. 
	* User data is distributed in-band with Telnet 
	* control information in an 8-bit byte data 
	* connection over the TCP (Transmission Control Protocol).

	* Telnet was developed in 1969.
	* It started as RFC 15, then extended in RFC 854, 
	* and standardized as 
	* Internet Engineering Task Force Internet Standard STD 8.

*/

//export CLASSPATH=$CLASSPATH:/usr/share/java/mysql-connector-java.jar


import java.io.*;
//NETWORK compatibility
import java.net.*;
import java.util.*;
//supports SQL
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

//Threaded Server
public class TServer
{
 	
	public static void main(String args[]) throws Exception
	{
	  	// 'try' because there is a chance that socket won't be created
		try
		{
			// To give each serving client a number
			
			int i=1;
			
			// establish a new server socket
			ServerSocket s = new ServerSocket(9002);	
			
			// keep the server always 'Listening' or on
			// to stop the server press 'Control + C'
			while(true)
			{
				//accept a new incoming connection
				Socket incoming = s.accept();
				
				// print on Server's window that a client is connected
				// Client is provided a natural number
				System.out.println("--- Client no." + i + " connected !!!");
				
				// run the method of TThreadedHandler Class
				
				Runnable r = new TThreadedHandler(incoming);
				
				// create a new thread of that 'running' method
				Thread t= new Thread(r);
				
				// start the thread
				t.start();
				
				// increment 'i' for next client
				i++;
			}
		}
		// catch 'Input-Output Exception'
		catch (IOException e)
		{
			// print details of error and possible origins
			e.printStackTrace();
		}
	}
}

/*
	* This class Handles the client input for one or more server socket connection.
	* Runnable interface is implemented for Threading Purposes
*/

class TThreadedHandler implements Runnable
{

	// static so as to be accessed from any class in the program
	// final to not let these values change after the login
	static final String url="jdbc:mysql://localhost:3306/SIS?useSSL=false"; //JDBC 
  	static final String user="root";
   	static final String password="root";
   		    
   		    
   		    
   	// handle each client by communicating via a 'specific port'
   	// duty of Operating System to provide such a port	    
	private Socket incoming;
	
	// constructor to initialize the socket with a port number
	// possibilty of no port available so throw Exception
	public TThreadedHandler(Socket i)throws Exception
	{
		incoming = i;
	}
	
	// brain of our program
	// every packet sent after temporarily connection establishment
	//is sent via here 
	public void run()
	{
			// To try any Exceptions
			try
			{
				// get input from the alloted port
				InputStream inStream = incoming.getInputStream();
				// object of scanner to get data in as it is format
				Scanner in = new Scanner(inStream);
				
				// to output to the client's port
				OutputStream outStream = incoming.getOutputStream();
				// object of PrintWriter to write on Client's screen
				// autoFlush is used to push the data to client 
				//without any extra commands
				PrintWriter out = new PrintWriter (outStream , true /* autoFlush */);
				
				int b = 0;
				int j = 0;
				
					// Starting intro window
					out.println("\nGreetings !!! ");
					out.println("\nWELCOME to the portal of \"Suggesstions Management System\"");
					out.println("\nEnter your choice and hit Enter ");
					out.println("\n\tPress 1 to Login for Suggesting");
					out.println("\n\tPress 2 for Authority Member Login");
					out.println("\n\tPress 3 to Exit");
					
					// Client(s) input as String store in variable line 
					String line= in.nextLine();
					
					// Note: Could have used switch case thing but that is
					//kept reserved for main task and this makes it look
					//less complicated  and conjusted
					
					
					// if client has input only 1 and hit enter
					if(line.trim().equals("1"))
					/*
						* trim() method used to ignore white spaces such as 'Enter' and 'space'
						* equals() method lets us compare the strings than their indices
						* It simply means " 1 " is same as "1"
					*/
					{
						// Inform the Server of client's connection
						System.out.println("\nClient is Logging to Suggest");
						
						// Try for the connection to mysql database
						try
						{
							
							// ask for the username and store it
							out.println("Hello !!! Enter your username");
							// store the username in String variable user_name
							// its reply from client
							String user_name= in.nextLine();
							
							// output to Client's window
							// ask for the password and store it
							out.println("Hello !!! Enter your password");
							// store the password in String variable pwd
							// its reply from client
							String pwd = in.nextLine();
						
						
							Class.forName("com.mysql.jdbc.Driver");
							/*
								** The Class class is located in the java.lang package,
								* so it is distributed with java, and 
								* imported automatically into every class.

								** What the forName() method does, is just return 
								* the Class object for the paramater that was 
								* loaded by the class loader. The newInstance() 
								* method then returns a new instance of the class.

								** So then what happens is you call 
								* Class.forName(...) it returns 
								* com.mysql.jdbc.Driver.class. You then call 
								* newInstance() on that class which returns an 
								* instance of the class, whith no paramaters,
								* so it's basically calling new com.mysql.jdbc.Driver();.

								** It initialize the class "com.mysql.jdbc.Driver"
								* if found in the classpath, this imply that
								* the driver is registered in the JDBC driver 
								* manager since the registration process is 
								* inside the static initializer of the driver class
							*/
							
							
												  		
							Connection con=DriverManager.getConnection(url,user,password);               //mysql Table
							/*
								** Connect to a data source by invoking the 
								* DriverManager.getConnection method.
								
								** You can use one of the following forms of getConnection:
								* getConnection(String url);
								* getConnection(String url, user, password);
								
								** For Data Server Driver for JDBC and SQL type 4 connectivity,
								* the getConnection method must specify 
								* a user ID and password,
								* through parameters or through property values.
								* The url argument represents a data source, 
								* and indicates what type of JDBC connectivity you are using.
							
								** URL is the address of database that we wish to
								* edit or use
								
								* user is the mysql accout's username
								* password is the password of user's account
								
							*/
							
							
						  	PreparedStatement pstmt=con.prepareStatement("select * from Login where user =  ? and passwd = ?");
						  	/*
								** An object that represents a precompiled SQL 
								* statement is created .
								* A SQL statement is precompiled and stored in a
								* PreparedStatement object. This object can then
								* be used to efficiently execute this 
								* statement multiple times.
							
							*/
						  	
						  	// set 1st '?' as user_name	
						  	pstmt.setString(1, user_name.trim());
						  	
						  	// set 2nd '?' as user's pwd
						  	pstmt.setString(2, pwd.trim());      
					   	
					   		// store the result of executed query in 
					   		//ResultSet's object rs
						  	ResultSet rs=pstmt.executeQuery();
						  	
						  	// if credentials exist in the database then login
						  	if(rs.next())
						  		{
						  			out.println("\nLogin Successfull!!!\n");
						  			
						  			// to hold choice that client will send next
						  			String sug_ch;
						  			
						  			
						  			out.println("\nEnter your Choice for Suggestion");
						  			out.println("\n1.Laboratory and Classrooms\n2.Sanitation and hygiene");
						  			out.println("3.Canteen\n4.Library");
						  			out.println("\n9.Search Suugestion Staus by ID\n10.Exit");
						  			out.println("\nfor example press 3 then 'press_Enter' for Suggestions on Canteen");				  			
						  			
						  			
						  			// take the choice, obviously from client side
						  			sug_ch = in.nextLine();
						  			
						  			// play the game of cases
						  			switch(sug_ch)
						  			{
						  				case "1":
						  					out.println("You have chosen to suggest about Laboratory and Classrooms");
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un1 =con.prepareStatement("select id, content from LACS where status = 'P'");
						  					//statement 
						  					ResultSet rs1 = pstmt_un1.executeQuery();
						  					
						  					
						  							// initialise j to '0'
						  							j=0;
						  							
						  							while(rs1.next())
						  							{
						  							
						  							int ID = rs1.getInt("id");
						  							String Content = rs1.getString("content");
        											out.println( ++j + "\t"+ ID + " -> "+ Content);	
						  							
						  							}
						  							
						  					out.println("\nPlease don't repeat from any of the above suggestions!!!\n");
						  					
						  					out.println("Please add your suggestion (not more than 100 words)\n:");
						  					
						  					String suggestion1=(String)in.nextLine();
						  					
						  					
						  					
						  					PreparedStatement pstmt1 =con.prepareStatement("insert into LACS(content,user_name, status) values (?,?,'P')");
						  		
						  					pstmt1.setString(1, suggestion1.trim());
						  					pstmt1.setString(2, user_name.trim());
						  			    
						  					
						  					b = pstmt1.executeUpdate();
						  					
						  					if(b == 0)
						  						j = 2;
						  					else j= 0;
											
											PreparedStatement pstmt_id1 =con.prepareStatement("SELECT id,content FROM LACS order by id desc limit 1");
						  					ResultSet rsid1 = pstmt_id1.executeQuery();
						  					
						  					
						  							
						  							
						  							
						  							while(rsid1.next() && j== 0)
						  							{
						  							out.println("\nPlease note down the id!!!\n");
						  							
						  							int ID = rsid1.getInt("id");
						  							String Content = rsid1.getString("content");
        											out.println(++j + "  "+ID + " -> "+ Content );
        											
        											out.println("Your suggestion has been accepted!!!");	
						  							
						  							}
						  							
						  							if(j == 2)
						  								out.println("Your suggestion has been rejected, Kindly try after some time or contact HOD");
											
											 
		  					
						  					break;
						  				case "2":
						  					out.println("You have chosen to suggest about Sanitation and hygiene");
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un2 =con.prepareStatement("select id, content from SAH where status = 'P'");
						  					ResultSet rs2 = pstmt_un2.executeQuery();
						  					
						  					
						  							// initialise j to '0'
						  							j=0;
						  							while(rs2.next())
						  							{
						  							
						  							int ID = rs2.getInt("id");
						  							String Content = rs2.getString("content");
        											out.println(++ j + "\t" + ID + " -> "+ Content );	
						  							
						  							}
						  							
						  					out.println("\nPlease don't repeat from any of the above suggestions!!!\n");
						  					
						  					
						  					out.println("Please add your suggestion (not more than 100 words)\n:");
						  					
						  					String suggestion2=(String)in.nextLine();
						  					
						  					
						  					
						  					PreparedStatement pstmt2 =con.prepareStatement("insert into SAH(content,user_name, status) values (?,?,'P')");
						  		
						  					pstmt2.setString(1, suggestion2.trim());
						  					pstmt2.setString(2, user_name.trim());
						  			    
						  					
						  					b = pstmt2.executeUpdate();
						  					
						  					if(b == 0)
						  						j = 2;
						  					else j= 0;

											PreparedStatement pstmt_id2 =con.prepareStatement("SELECT id,content FROM SAH order by id desc limit 1");
						  					ResultSet rsid2 = pstmt_id2.executeQuery();
						  					
						  					
						  							while(rsid2.next() && j== 0)
						  							{
						  							out.println("\nPlease note down the id!!!\n");
						  							
						  							int ID = rsid2.getInt("id");
						  							String Content = rsid2.getString("content");
        											out.println(++j + "  " + ID + " -> "+ Content );
        											
        											out.println("Your suggestion has been accepted!!!");	
						  							
						  							}
						  							
						  							if(j == 0)
						  								out.println("Your suggestion has been rejected, Kindly try after some time or contact HOD");
											
											 
		  					
						  					break;
						  				case "3":
						  					out.println("You have chosen to suggest about Canteen");
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un3 =con.prepareStatement("select id, content from Canteen where status = 'P'");
						  					ResultSet rs3 = pstmt_un3.executeQuery();
						  					
						  					
						  							// initialise j to '0'
						  							j=0;
						  							while(rs3.next())
						  							{
						  							
						  							int ID = rs3.getInt("id");
						  							String Content = rs3.getString("content");
        											out.println(++ j + "\t" + ID + " -> "+ Content );				
						  							}
						  							
						  					out.println("\nPlease don't repeat from any of the above suggestions!!!\n");
						  					
						  					
						  					out.println("Please add your suggestion (not more than 100 words)\t:");
						  					
						  					String suggestion3=(String)in.nextLine();
						  					
						  					
						  					
						  					PreparedStatement pstmt3 =con.prepareStatement("insert into Canteen(content,user_name, status) values (?,?,'P')");
						  		
						  					pstmt3.setString(1, suggestion3.trim());
						  					pstmt3.setString(2, user_name.trim());
						  			    
						  					
						  					b = pstmt3.executeUpdate();
						  					
						  					if(b == 0)
						  						j = 2;
						  					else j= 0;

											PreparedStatement pstmt_id3 =con.prepareStatement("SELECT id,content FROM Canteen order by id desc limit 1");
						  					ResultSet rsid3 = pstmt_id3.executeQuery();
						  					
						  					
						  							while(rsid3.next() && j== 0)
						  							{
						  							out.println("\nPlease note down the id!!!\n");
						  							
						  							int ID = rsid3.getInt("id");
						  							String Content = rsid3.getString("content");
        											out.println(++j + "  " + ID + " -> "+ Content );
        											
        											out.println("Your suggestion has been accepted!!!");	
						  							
						  							}
						  							
						  							if(j == 0)
						  								out.println("Your suggestion has been rejected, Kindly try after some time or contact HOD");
											
											 
		  					
						  					break;
						  				case "4":
						  					out.println("You have chosen to suggest about Library");
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un4 =con.prepareStatement("select id, content from Library where status = 'P'");
						  					ResultSet rs4 = pstmt_un4.executeQuery();
						  					
						  					
						  							// initialise j to '0'
						  							j=0;
						  							while(rs4.next())
						  							{
						  							
						  							int ID = rs4.getInt("id");
						  							String Content = rs4.getString("content");
        											out.println(++ j + "\t" + ID + " -> "+ Content );	
						  							
						  							}
						  							
						  					out.println("\nPlease don't repeat from any of the above suggestions!!!\n");
						  					
						  					
						  					out.println("Please add your suggestion (not more than 100 words)\n:");
						  					
						  					String suggestion4=(String)in.nextLine();
						  					
						  					
						  					
						  					PreparedStatement pstmt4 =con.prepareStatement("insert into Library(content,user_name, status) values (?,?,'P')");
						  		
						  					pstmt4.setString(1, suggestion4.trim());
						  					pstmt4.setString(2, user_name.trim());
						  			    
						  					
						  					b = pstmt4.executeUpdate();
						  					
						  					if(b == 0)
						  						j = 2;
						  					else j= 0;

											PreparedStatement pstmt_id4 =con.prepareStatement("SELECT id,content FROM Library order by id desc limit 1");
						  					ResultSet rsid4 = pstmt_id4.executeQuery();
						  					
						  					
						  							while(rsid4.next() && j== 0)
						  							{
						  							out.println("\nPlease note down the id!!!\n");
						  							
						  							int ID = rsid4.getInt("id");
						  							String Content = rsid4.getString("content");
        											out.println(++j + "  " + ID + " -> "+ Content );
        											
        											out.println("Your suggestion has been accepted!!!");	
						  							
						  							}
						  							
						  							if(j == 0)
						  								out.println("Your suggestion has been rejected, Kindly try after some time or contact HOD");
											
											 
		  					
						  					break;
						  					
						  					
						  					// option for admin login
						  				case "9":
						  						out.println("\nNote:Status if is 'P' means Proccessing and if is 'D' means Done.\n");
						  						out.println("Enter the unsolved suggestion's Id:");
						  						
						  						int suggestion_s= in.nextInt();
						  						
						  						
						  						PreparedStatement pstmt_se9 =con.prepareStatement("select status from  LACS UNION SELECT status from SAH UNION SELECT status from Canteen UNION SELECT status from Library where id = ? ");
						  						pstmt_se9.setInt(1, suggestion_s);
						  						
						  						ResultSet se9 = pstmt_se9.executeQuery();
						  					
						  						
						  						
						  							if(se9.next())
						  							{
						  							
						  							String S_tatus = se9.getString("status");
        											out.println("\nStatus of your query is ->  " + S_tatus);	
						  							
						  							}
						  							else
						  							{
						  							out.println(" Entered ID doesn't exist!!!");	
						  							
						  							}
						  							
						  							
						  						break;
						  						
						  					// option for exit
						  				case "10":
						  					out.println("You've chosen to exit!!!");
						  						break;
						  					// if user enters any other response
						  				default:
						  					//out.println("Please try again!!!");
						  			}
						  			// close the connection
						  			con.close();
						  			
						  			out.println("For next suggestion login again!!!");
						  			
						  			}
						  			
						  	// when the credentials were wrong
						  	else 
						  	{
						  		 out.println("\nLogin Unsuccessfull try again!!!\n");
						  		 System.out.println("--- Client with " +incoming + " wrong credentials !!!");
						  	}	
						  }
						  // catch any Exception
						  catch(Exception e)
						{
						  	e.printStackTrace();
						}
					}
					// if client has input only 2 and hit enter	  	
					else if(line.trim().equals("2"))
					{
						// Server side msg
						System.out.println("\nClient is Logging to Edit");
					//String user_name, pwd;
						try
						{
			
							out.println("Hello !!! Enter your username");
							String user_name= in.nextLine();
				
							out.println("Hello !!! Enter your password");
							String pwd = in.nextLine();
							
							out.println("Hello !!! Enter your department");
							String department = in.nextLine();
							
							Class.forName("com.mysql.jdbc.Driver");					  		
							Connection con=DriverManager.getConnection(url,user,password);               //mysql Table

						  	PreparedStatement pstmt=con.prepareStatement("select * from Login_A where user =  ? and passwd = ? and dept = ?");
						  		
						  	pstmt.setString(1, user_name.trim());
						  	pstmt.setString(2, pwd.trim()); 
						  	pstmt.setString(3, department.trim());    
					   
						  	ResultSet rs=pstmt.executeQuery();
						  	
						  	if(rs.next())
						  		{
						  			out.println("\nLogin Successfull!!!\n");
						  			
						  			String sug_ch = "0";
						  			//do{
						  			
						  			if(department.equals("LACS"))			sug_ch = "1";
						  			else if (department.equals("SAH"))		sug_ch = "2";
						  			else if (department.equals("Canteen"))	sug_ch = "3";
						  			else if (department.equals("Library"))	sug_ch = "4";
						  			else if (department.equals("admin"))	sug_ch = "7";
						  			else sug_ch = "10";
						  			
						  			
						  			switch(sug_ch)
						  			{
						  				
						  			
						  			
						  				case "1":
						  					out.println("You have chosen to Edit about Laboratory and Classrooms suggestions");
						  					
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un1 =con.prepareStatement("select id, content from LACS where status = 'P'");
						  					ResultSet rs1 = pstmt_un1.executeQuery();
						  					
						  					
						  							out.println("\nUnsolved Suggestions!!!\n");
						  							
						  							//j=0;
						  							while(rs1.next())
						  							{
						  							
						  							int ID = rs1.getInt("id");
						  							String Content = rs1.getString("content");
        											out.println(ID + " -> "+ Content );	
						  							
						  							}
						  										
						  					
						  					out.println("Please enter suggestion id to update (If none present to update enter '0' you maybe get rejected msg but its OK)\n");
						  					
						  					String suggestion1=(String)in.nextLine();
						  					
						  					PreparedStatement pstmt1 =con.prepareStatement("update LACS SET status = 'D' where id = ? ");
						  		
						  					pstmt1.setString(1, suggestion1.trim());
						  			    
						  					int rs11 = pstmt1.executeUpdate();
						  					
						  					
											if(rs11 > 0 )
											out.println("Your Edit has been Accepted!!!");
											else
											out.println("Your Edit has been Rejected(ignore if input was '0')!!!");
											 
		  									
						  					break;
						  				case "2":
						  					out.println("You have chosen to Edit about Sanitation and hygiene suggestions");
						  					
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un2 =con.prepareStatement("select id,content from SAH where status = 'P'");
						  					ResultSet rs2 = pstmt_un2.executeQuery();
						  					
						  					
						  							out.println("\nUnsolved Suggestions!!!\n");
						  							//j = 0;
						  							while(rs2.next())
						  							{
						  							
						  							int ID = rs2.getInt("id");
        											String Content = rs2.getString("content");
        											out.println(ID + " -> "+ Content );
						  							
						  							}
						  						
						  					
						  					
						  					
						  					out.println("Please enter suggestion id to update (If none present to update enter '0' you maybe get rejected msg but its OK)\n");
						  					
						  					String suggestion2=(String)in.nextLine();
						  					
						  					
						  					PreparedStatement pstmt2 =con.prepareStatement("update SAH SET status = 'D' where id = ? ");
						  		
						  					pstmt2.setString(1, suggestion2.trim());
						  			    
											int rs22 = pstmt2.executeUpdate();
						  					
						  					
											if(rs22 > 0 )
											out.println("Your Edit has been Accepted!!!");
											else
											out.println("Your Edit has been Rejected(ignore if input was '0')!!!");
						  					
						  					
						  					break;
						  					
						  				case "3":
						  					out.println("You have chosen to Edit about Canteen suggestions");
						  					
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un3 =con.prepareStatement("select id,content from Canteen where status = 'P'");
						  					ResultSet rs3 = pstmt_un3.executeQuery();
						  					
						  					
						  							out.println("\nUnsolved Suggestions!!!\n");
						  							//j = 0;
						  							while(rs3.next())
						  							{
						  							
						  							int ID = rs3.getInt("id");
        											String Content = rs3.getString("content");
        											out.println(ID + " -> "+ Content );	
						  							
						  							}
						  						
						  					
						  					
						  					
						  					out.println("Please enter suggestion id to update (If none present to update enter '0' you maybe get rejected msg but its OK)\n");
						  					
						  					String suggestion3=(String)in.nextLine();
						  					
						  					
						  					
						  					PreparedStatement pstmt3 =con.prepareStatement("update Canteen SET status = 'D' where id = ? ");
						  		
						  					pstmt3.setString(1, suggestion3.trim());
						  			    
						  					
						  					int rs33 = pstmt3.executeUpdate();
						  					
						  					
											if(rs33 > 0 )
											out.println("Your Edit has been Accepted!!!");
											else
											out.println("Your Edit has been Rejected(ignore if input was '0')!!!");
						  					
						  					
						  					break;
						  				case "4":
						  					out.println("You have chosen to Edit about Library suggestions");
						  					
						  					out.println("The unsolved suggestion are :\n");
						  					
						  					PreparedStatement pstmt_un4 =con.prepareStatement("select id,content from Library where status = 'P'");
						  					ResultSet rs4 = pstmt_un4.executeQuery();
						  					
						  					
						  							out.println("\nUnsolved Suggestions!!!\n");
						  							//j = 0;
						  							while(rs4.next())
						  							{
						  							
						  							int ID = rs4.getInt("id");
        											String Content = rs4.getString("content");
        											out.println(++j + "  "+ID + " -> "+ Content );	
						  							
						  							}
						  						
						  					
						  					
						  					
						  					out.println("Please enter suggestion id to update (If none present to update enter '0' you maybe get rejected msg but its OK)\n");
						  					
						  					String suggestion4=(String)in.nextLine();
						  					
						  					PreparedStatement pstmt4 =con.prepareStatement("update Library SET status = 'D' where id = ? ");
						  		
						  					pstmt4.setString(1, suggestion4.trim());
						  			    
						  					
						  					int rs44 = pstmt4.executeUpdate();
						  					
						  					
											if(rs44 > 0 )
											out.println("Your Edit has been Accepted!!!");
											else
											out.println("Your Edit has been Rejected(ignore if input was '0')!!!");
						  					
						  
						  					break;
						  				case "7":
						  					out.println("Enter your choice!!!");
						  					
						  					out.println("1.Add Student\n2.Add HOD");
							  					sug_ch = in.nextLine();
							  					
							  				
						  					if(sug_ch.trim().equals("1"))
						  					{
							  					
							  					out.println("You have chosen to Add Student");
							  					
							  					out.println("Hello !!! Enter new username");
												user_name= in.nextLine();
				
												out.println("Hello !!! Enter  new password");
												pwd = in.nextLine();
							
											
							  					PreparedStatement pstmt8 =con.prepareStatement("insert into Login values (?,?)");
							  		
							  					pstmt8.setString(1, user_name.trim());
							  					pstmt8.setString(2, pwd.trim());  
							  					
							  					pstmt8.executeUpdate();

											}
							  					
							  				else if(sug_ch.trim().equals("2"))
							  				{
							  					out.println("You have chosen to Add HOD");
							  					
							  					out.println("Hello !!! Enter username");
												user_name= in.nextLine();
				
												out.println("Hello !!! Enter  password");
												pwd = in.nextLine();
							
												out.println("Hello !!! Enter  department");
												department = in.nextLine();
							  					
							  					
							  					PreparedStatement pstmt9 =con.prepareStatement("insert into Login_A values (?,?,?)");
							  		
							  					pstmt9.setString(1, user_name.trim());
							  					pstmt9.setString(2, pwd.trim());  
							  					pstmt9.setString(2, department.trim());   
							  					
							  					pstmt9.executeUpdate();
											}
												out.println("Your Edit has been accepted!!!");
							  					
						  				
						  					break;
						  				
						  					
						  				default :
						  					//out.println("Please try again!!!");
						  			}
						  			
						  		}
						  	else
						  		{
						  		 out.println("\nLogin Unsuccessfull try connecting again!!!\n");
						  		 System.out.println("--- Client with " +incoming + " wrong credentials !!!");
						  		}
						  		
						  		con.close();
						  			
						  			
						}
					catch(Exception e)
						{
						  	e.printStackTrace();
						}
						  			
						  			
						  			
						  			
						  			out.println("For next suggestion Edit login again!!!");
						  		
					}
					
				// if client has input only 3 and hit enter	
				else if(line.trim().equals("3"))
					{
						out.println("You have chosen to exit!!!");
					}
						
				// if client has input anything other than 1 or 2 or 3
				else{	  
					out.println("\nLogin Unsuccessfull try connecting again!!!\n");
					}
						  	
				out.println("You are to exit!!!");
				out.println("BYE !!!");
				System.out.println("--- Client with " +incoming + " dis-connected !!!");   	
						
				incoming.close();
								
		}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
	}
}


