package com.dyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dyj.model.PageBean;
import com.dyj.model.Supplier;
import com.dyj.util.StringUtil;

public class SupplierDao {
	public ResultSet supplierList(Connection con,PageBean pageBean,Supplier supplier)throws Exception{
		StringBuffer sb=new StringBuffer("select * from supplier");
		if(supplier!=null&&StringUtil.isNotEmpty(supplier.getName())){
			sb.append(" and name like '%"+supplier.getName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int supplierCount(Connection con,Supplier supplier)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from supplier");
		if(StringUtil.isNotEmpty(supplier.getName())){
			sb.append(" and name like '%"+supplier.getName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public int supplierDelete(Connection con,String delIds)throws Exception{
		String sql="delete from supplier where id in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	public int supplierAdd(Connection con,Supplier supplier)throws Exception {
		String sql="insert into supplier values(?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, supplier.getId());
		pstmt.setString(2, supplier.getNumber());
		pstmt.setString(3, supplier.getName());
		pstmt.setString(4, supplier.getLinkman());
		pstmt.setString(5, supplier.getLinkphone());
		pstmt.setString(6, supplier.getNote());
		return pstmt.executeUpdate();
	}
	public int supplierModify(Connection con,Supplier supplier)throws Exception{
		String sql="update supplier set number=?,name=?,linkman=?,linkphone=?,note=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, supplier.getNumber());
		pstmt.setString(2, supplier.getName());
		pstmt.setString(3, supplier.getLinkman());
		pstmt.setString(4, supplier.getLinkphone());
		pstmt.setString(5, supplier.getNote());
		pstmt.setInt(6, supplier.getId());
		return pstmt.executeUpdate();
	}
}
