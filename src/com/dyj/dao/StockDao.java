package com.dyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dyj.model.Goodcategories;
import com.dyj.model.PageBean;
import com.dyj.model.Stock;
import com.dyj.util.StringUtil;

public class StockDao {
	public ArrayList<Stock> getStockList(Connection con)throws Exception{
		ArrayList<Stock> StockList = new ArrayList<Stock>();
		String sql = "select * from stock";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Stock stock = new Stock();
			stock.setGoodnumber(Integer.parseInt(rs.getString("goodnumber")));
			StockList.add(stock);
		}
		return StockList;
	}
	public ResultSet stockList(Connection con,PageBean pageBean,Stock stock)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT stockid,goodname,outstock.saleprice,stock.goodnumber,stocknote,stock.goodid,inprice,storage.id,outstock.outstockid FROM stock,good,storage,outstock WHERE stock.goodid=good.id AND stock.outstockid=outstock.outstockid AND stock.storageid=storage.id");
		if(stock!=null&&StringUtil.isNotEmpty(stock.getGoodname())){
			sb.append(" and goodname like '%"+stock.getGoodname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public ResultSet stockList2(Connection con,PageBean pageBean,Stock stock)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT * FROM outstock,good,storage WHERE outstock.goodid=good.id AND storage.goodid=good.id");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public ResultSet stockList3(Connection con,PageBean pageBean,Stock stock)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT goodname,stock.goodnumber,inprice,outstock.saleprice,stocknote,outstock.goodid,outstock.outstockid,storage.id FROM stock,good,storage,outstock WHERE stock.goodid=good.id AND stock.outstockid=outstock.outstockid AND stock.storageid=storage.id");
		if(stock!=null&&StringUtil.isNotEmpty(stock.getGoodname())){
			sb.append(" and goodname like '%"+stock.getGoodname()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int stockCount(Connection con,Stock stock)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_stock,good where stock.goodid=good.id");
		if(StringUtil.isNotEmpty(stock.getGoodname())){
			sb.append(" and goodname like '%"+stock.getGoodname()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());	
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	public int stockDelete(Connection con,String delIds)throws Exception{
		String sql="delete from stock where stockid in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	public int stockAdd(Connection con,Stock stock)throws Exception {
		String sql="insert into stock values(?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, stock.getGoodid());
		pstmt.setInt(2, stock.getGoodnumber());
		pstmt.setString(3, stock.getStocknote());
		pstmt.setInt(4, stock.getStorageid());
		pstmt.setInt(5, stock.getOutstockid());
		return pstmt.executeUpdate();
	}
	public int stockModify(Connection con,Stock stock)throws Exception{
		String sql="update stock set goodid=?,goodnumber=?,stocknote=?,outstockid=?,storageid=? where stockid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, stock.getGoodid());
		pstmt.setInt(2, stock.getGoodnumber());
		pstmt.setString(3, stock.getStocknote());
		pstmt.setInt(4, stock.getOutstockid());
		pstmt.setInt(5, stock.getStorageid());
		pstmt.setInt(6, stock.getStockid());
		return pstmt.executeUpdate();
	}
}
