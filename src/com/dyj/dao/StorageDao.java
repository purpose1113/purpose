package com.dyj.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dyj.model.Good;
import com.dyj.model.Storage;
import com.dyj.model.PageBean;
import com.dyj.util.DateUtil;
import com.dyj.util.StringUtil;

public class StorageDao {
		public ResultSet storageList(Connection con,PageBean pageBean,Storage storage)throws Exception{
			StringBuffer sb=new StringBuffer("select storage.id,goodid,inprice,storagedate,storagenumber,storagenote,goodname,good.id as Goodid from storage,good where storage.goodid=good.id");
			if(storage!=null&&StringUtil.isNotEmpty(storage.getGoodname())){
				sb.append(" and goodname like '%"+storage.getGoodname()+"%'");
			}
			if(pageBean!=null){
				sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
			}
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		}
		public ResultSet storageList2(Connection con,PageBean pageBean,Storage storage)throws Exception{
			StringBuffer sb=new StringBuffer("select goodname,goodid,inprice,storagedate,storagenumber,storagenote from storage,good where storage.goodid=good.id");
			if(storage!=null&&StringUtil.isNotEmpty(storage.getGoodname())){
				sb.append(" and goodname like '%"+storage.getGoodname()+"%'");
			}
			if(pageBean!=null){
				sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
			}
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		}
		public ResultSet storageList3(Connection con,PageBean pageBean,Storage storage)throws Exception{
			StringBuffer sb=new StringBuffer("select goodname,goodid from storage,good where storage.goodid=good.id");
			if(storage!=null&&StringUtil.isNotEmpty(storage.getGoodname())){
				sb.append(" and goodname like '%"+storage.getGoodname()+"%'");
			}
			if(pageBean!=null){
				sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
			}
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		}
		public ResultSet storageList4(Connection con,Storage storage)throws Exception{
			StringBuffer sb=new StringBuffer("select * from storage");
			if(storage!=null&&StringUtil.isNotEmpty(storage.getGoodname())){
				sb.append(" and goodname like '%"+storage.getGoodname()+"%'");
			}
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			return pstmt.executeQuery();
		}
		public int storageCount(Connection con,Storage storage)throws Exception{
			StringBuffer sb=new StringBuffer("select count(*) as total from storage,good where storage.goodid=good.id");
			if(StringUtil.isNotEmpty(storage.getGoodname())){
				sb.append(" and goodname like '%"+storage.getGoodname()+"%'");
			}
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("total");
			}else{
				return 0;
			}
		}
		public int storageDelete(Connection con,String delIds)throws Exception{
			String sql="delete from storage where id in("+delIds+")";
			PreparedStatement pstmt=con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}
		public int storageAdd(Connection con,Storage storage)throws Exception {
			String sql="insert into storage values(?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, storage.getGoodid());
			pstmt.setDouble(2, storage.getInprice());
			pstmt.setString(3, DateUtil.formatDate(storage.getStoragedate(),"yyyy-MM-dd"));
			pstmt.setInt(4, storage.getStoragenumber());
			pstmt.setString(5, storage.getStoragenote());
			return pstmt.executeUpdate();
		}
		public int storageModify(Connection con,Storage storage)throws Exception{
			String sql="update storage set goodid=?,inprice=?,storagedate=?,storagenumber=?,storagenote=? where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, storage.getGoodid());
			pstmt.setDouble(2, storage.getInprice());
			pstmt.setString(3, DateUtil.formatDate(storage.getStoragedate(),"yyyy-MM-dd"));
			pstmt.setInt(4, storage.getStoragenumber());
			pstmt.setString(5, storage.getStoragenote());
			pstmt.setInt(6, storage.getId());
			return pstmt.executeUpdate();
		}
}
