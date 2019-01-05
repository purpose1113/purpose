package com.dyj.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtil {
	  private String url = "jdbc:mysql://localhost:3306/demo";
	  private String userName = "root";
	  private String passWord = "1234";
	  private String jdbcName = "com.mysql.jdbc.Driver";
	  
	  public Connection getCon() throws Exception{
		  Class.forName(jdbcName);
		 
		Connection con = DriverManager.getConnection(url,userName,passWord);
		  return con;
	  }
	  public void closeCon(Connection con) throws Exception{
		  if(con!=null){
			  con.close();
		  }
	  }
	  
	 
	}


