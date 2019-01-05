package com.dyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dyj.model.User;


public class UserDao {
  public User Login (Connection con,User user) throws Exception{
	  User ResultUser = null;
	  String sql = "select * from user where userName=? and password=?";
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  pstmt.setString(1,user.getUserName());
	  pstmt.setString(2,user.getPassword());
	  ResultSet rs = pstmt.executeQuery();
	  while(rs.next()){
		  ResultUser = new User();
		  ResultUser.setUserName(rs.getString("userName"));
		  ResultUser.setPassword(rs.getString("password"));
	  }
	  return ResultUser;
  }
}
