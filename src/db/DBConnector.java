package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	private String db;
	private String user;
	private String pass;
	private Connection conn = null;
	private Statement stmt = null;

	public DBConnector(String db_url, String user, String pass){
		try {
			//Open a connection
			conn = DriverManager.getConnection(db_url,user,pass);	
		} catch (SQLException e) {
			System.err.println("Database "+db+" is not available.");
		}
		this.db = db_url;
		this.user = user;
		this.pass = pass;
	}

	public ResultSet performQuery(String sql){
		ResultSet rs = null;
		if(conn!=null){
			try {
				if(stmt!=null){
					stmt.close();
				}
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public PreparedStatement prepareInsert(String sql){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public void endConnector(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
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
}
