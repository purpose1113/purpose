package com.dyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dyj.model.Goodcategories;
import com.dyj.model.PageBean;
import com.dyj.model.Supplier;
import com.dyj.util.StringUtil;

public class GoodcategoriesDao {
	public ArrayList<Goodcategories> getGoodcategoriesList(Connection con)throws Exception{
		ArrayList<Goodcategories> goodcategoriesList = new ArrayList<Goodcategories>();
		String sql = "select * from goodcategories";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Goodcategories goodcategories = new Goodcategories();
			goodcategories.setGoodname(rs.getString("goodname"));
			goodcategoriesList.add(goodcategories);
		}
		return goodcategoriesList;
	}
	public ResultSet goodcategoriesList(Connection con,PageBean pageBean,Goodcategories goodcategories)throws Exception{
		StringBuffer sb=new StringBuffer("select * from goodcategories");
		if(goodcategories!=null&&StringUtil.isNotEmpty(goodcategories.getGoodname())){
			sb.append(" and goodname like '%"+goodcategories.getGoodname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	public int goodcategoriesCount(Connection con,Goodcategories goodcategories)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from goodcategories");
		if(StringUtil.isNotEmpty(goodcategories.getGoodname())){
			sb.append(" and goodname like '%"+goodcategories.getGoodname()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public int goodcategoriesDelete(Connection con,String delIds)throws Exception{
		String sql="delete from goodcategories where id in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	public int goodcategoriesAdd(Connection con,Goodcategories goodcategories)throws Exception {
		String sql="insert into goodcategories values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, goodcategories.getId());
		pstmt.setString(2, goodcategories.getGoodname());
		pstmt.setString(3, goodcategories.getDescription());
		return pstmt.executeUpdate();
	}
	public int goodcategoriesModify(Connection con,Goodcategories goodcategories)throws Exception{
		String sql="update goodcategories set goodname=?,description=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, goodcategories.getGoodname());
		pstmt.setString(2, goodcategories.getDescription());
		pstmt.setInt(3, goodcategories.getId());
		return pstmt.executeUpdate();
	}
	
}
