package com.dyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dyj.model.OutStock;
import com.dyj.model.PageBean;
import com.dyj.util.DateUtil;
import com.dyj.util.StringUtil;

public class OutstockDao {
	public ResultSet outstockList(Connection con,PageBean pageBean,OutStock outstock)throws Exception{
		StringBuffer sb=new StringBuffer("select outstockid,goodname,saleprice,outstockdate,outstocknumber,outstocknote,goodid from outstock,good where outstock.goodid=good.id");
		if(outstock!=null&&StringUtil.isNotEmpty(outstock.getGoodname())){
			sb.append(" and goodname like '%"+outstock.getGoodname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public double[][] getOutstock(Connection con,String goodid)throws Exception{
		double[][] outstockList = new double[4][1];
		int i=0;
		String sql="SELECT SUM(outstocknumber) AS totalSalemember,QUARTER  FROM outstock WHERE goodid=? GROUP BY quarter";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,goodid);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			while(i<4){
                if(i==0&&rs.getString("quarter").equals("第一季度")){
                	outstockList[i][0]=Double.parseDouble(rs.getString("totalSalemember"));
                	i++;
                }else{
                	i++;
                }
                if(i==1&&rs.getString("quarter").equals("第二季度")){
                	outstockList[i][0]=Double.parseDouble(rs.getString("totalSalemember"));
                	i++;
                }else{
                	i++;
                }
                if(i==2&&rs.getString("quarter").equals("第三季度")){
                	outstockList[i][0]=Double.parseDouble(rs.getString("totalSalemember"));
                	i++;
                }else{
                	i++;
                }
                if(i==3&&rs.getString("quarter").equals("第四季度")){
                	outstockList[i][0]=Double.parseDouble(rs.getString("totalSalemember"));
                	i++;
                }else{
                	i++;
                }
			}
			i=0;
		}
		return outstockList;
	}
	public ResultSet outstockList2(Connection con,PageBean pageBean,OutStock outstock)throws Exception{
		StringBuffer sb=new StringBuffer("select goodname,saleprice,outstockdate,outstocknumber,outstocknote,goodid from outstock,good where outstock.goodid=good.id");
		if(outstock!=null&&StringUtil.isNotEmpty(outstock.getGoodname())){
			sb.append(" and goodname like '%"+outstock.getGoodname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public ResultSet outstockList3(Connection con,OutStock outstock)throws Exception{
		StringBuffer sb=new StringBuffer("select * from outstock");
		if(outstock!=null&&StringUtil.isNotEmpty(outstock.getGoodname())){
			sb.append(" and goodname like '%"+outstock.getGoodname()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int outstockCount(Connection con,OutStock outstock)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from outstock,good where outstock.goodid=good.id");
		if(StringUtil.isNotEmpty(outstock.getGoodname())){
			sb.append(" and goodname like '%"+outstock.getGoodname()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public int outstockDelete(Connection con,String delIds)throws Exception{
		String sql="delete from outstock where outstockid in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	public int outstockAdd(Connection con,OutStock outstock)throws Exception {
		String sql="insert into outstock values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, outstock.getGoodid());
		pstmt.setDouble(2, outstock.getSaleprice());
		pstmt.setString(3, DateUtil.formatDate(outstock.getOutstockdate(),"yyyy-MM-dd"));
		pstmt.setInt(4, outstock.getOutstocknumber());
		pstmt.setString(5, outstock.getOutstocknote());
		return pstmt.executeUpdate();
	}
	public int outstockModify(Connection con,OutStock outstock)throws Exception{
		String sql="update outstock set goodid=?,saleprice=?,outstockdate=?,outstocknumber=?,outstocknote=? where outstockid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, outstock.getGoodid());
		pstmt.setDouble(2, outstock.getSaleprice());
		pstmt.setString(3, DateUtil.formatDate(outstock.getOutstockdate(),"yyyy-MM-dd"));
		pstmt.setInt(4, outstock.getOutstocknumber());
		pstmt.setString(5, outstock.getOutstocknote());
		pstmt.setInt(6, outstock.getOutstockid());
		return pstmt.executeUpdate();
	}
}
