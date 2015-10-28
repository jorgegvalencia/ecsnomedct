package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	private String db;
	//  Database credentials
	private String user;
	private String pass;
	private Connection conn = null;
	private Statement stmt = null;

	public DBConnector(String db_url, String user, String pass) throws DBAvailabilityException{
		//STEP 2: Register JDBC driver
		this.db = db_url;
		this.user = user;
		this.pass = pass;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Open a connection
			conn = DriverManager.getConnection(db,user,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();		
		} catch (SQLException e) {
			System.err.println("Database "+db+" is not available.");
		}
	}

	public ResultSet performQuery(String sql){
		ResultSet rs = null;
		if(conn!=null){
			try {
				if(stmt!=null){
					stmt.close();
				}
				//Execute a query
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public void endConnector(){
		if(initialized()){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// nothing we can do
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try
		}
	}
	
	public void showCredentials(){
		System.out.println("DBConnector instance credentials:");
		System.out.println("Database:"+db);
		System.out.println("User:"+user);
		System.out.println("Password:"+pass);
	}
	
	private boolean initialized(){
		return (conn==null || stmt==null) ? false : true;
	}
}
