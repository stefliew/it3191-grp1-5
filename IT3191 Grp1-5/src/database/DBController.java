package database;
import java.sql.*;
public class DBController {
		private Connection con;		
		/* Method Name     : setUp
		 * Input Parameter : String (Data Source Name)
		 * Purpose         : Load the database driver and establish connection
		 * Return          : nil*/
		public void setUp(String dsn){
			// load the database driver
			try {	Class.forName("com.mysql.jdbc.Driver");	}
		   	catch (Exception e) {System.out.println("Load driver error");}
		  	//after loading the driver, establish a connection	
		   	try {		
				String s = "jdbc:mysql://localhost:3306/" + dsn;
				con=DriverManager.getConnection(s, "root", "root");
		   	}
		   	catch (Exception e) {e.printStackTrace();	}
		}		
		/* Method Name     : readRequest
		 * Input Parameter : String (database query)
		 * Purpose         : Obtain the result set from the db query
		 * Return          : resultset (records from the query)*/
		public ResultSet readRequest(String dbQuery){
			ResultSet rs=null;
		   	try {
				// create a statement object
				Statement stmt = con.createStatement();
				// execute an SQL query and get the result
				rs = stmt.executeQuery(dbQuery);
		   	}
		   	catch (Exception e){e.printStackTrace(); 	}
		   	return rs;
		}
		/* Method Name     : updateRequest
		 * Input Parameter : String (database query)
		 * Purpose         : Execute udpate using the db query
		 * Return          : int (count is 1 if successful)*/	
		public int updateRequest(String dbQuery){
			int count=0;
		  	try {
				// create a statement object
		  		Statement stmt = con.createStatement();
				// execute an SQL query and get the result
				count = stmt.executeUpdate(dbQuery);
		   	}
		   	catch (Exception e){e.printStackTrace(); 	} 
	   	   	return count;	
		}
		/* Method Name     : terminate
		 * Input Parameter : nil
		 * Purpose         : Close db conection
		 * Return          : nil  */
		public void terminate(){			
		   	try {	con.close();} 
		   	catch (Exception e){e.printStackTrace(); 	}
		}
}//end of DBController

