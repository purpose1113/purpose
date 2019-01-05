package com.dyj.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dyj.dao.GoodDao;
import com.dyj.dao.GoodcategoriesDao;
import com.dyj.dao.StockDao;
import com.dyj.model.Good;
import com.dyj.model.Goodcategories;
import com.dyj.model.Stock;
import com.dyj.util.DbUtil;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements ServletRequestAware{

	private static DbUtil dbutil = new DbUtil();
	private static StockDao stockdao = new StockDao();
	private static GoodDao gooddao = new GoodDao();
	private static ArrayList<Goodcategories> goodcategories;
	private HttpServletRequest request;
	
	
	
	@Override
	public String execute() throws Exception {
		Connection con = null;
		HttpSession session = request.getSession();
		con=dbutil.getCon();
		String sql = "SELECT id,goodname,t_stock.goodnumber AS goodmumber,photo FROM t_good,t_stock WHERE t_good.id=t_stock.goodid";
		ArrayList<Good> goodList = gooddao.getGoodList(con,sql);
		session.setAttribute("goodList", goodList);
		return "index";
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
   
}
