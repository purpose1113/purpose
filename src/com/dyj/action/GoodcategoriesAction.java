package com.dyj.action;

import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.dyj.dao.GoodDao;
import com.dyj.dao.GoodcategoriesDao;
import com.dyj.model.Goodcategories;
import com.dyj.model.PageBean;
import com.dyj.model.Supplier;
import com.dyj.util.DbUtil;
import com.dyj.util.ExcelUtil;
import com.dyj.util.JsonUtil;
import com.dyj.util.ResponseUtil;
import com.dyj.util.ResponseUtil3;
import com.dyj.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class GoodcategoriesAction extends ActionSupport{
       private Goodcategories goodcategories;
	   private String page;
	   private String rows;
	   private String id;
	   private String g_name;
	   private String delIds;
		
	   
	
	   
	public Goodcategories getGoodcategories() {
		return goodcategories;
	}


	public void setGoodcategories(Goodcategories goodcategories) {
		this.goodcategories = goodcategories;
	}


	public String getDelIds() {
		return delIds;
	}


	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}


		public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getG_name() {
		return g_name;
	}


	public void setG_name(String g_name) {
		this.g_name = g_name;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	DbUtil dbUtil=new DbUtil();
	GoodcategoriesDao goodcategoriesdao=new GoodcategoriesDao();
	GoodDao gooddao=new GoodDao();
		@Override
	public String execute() throws Exception {
			Connection con=null;
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			try{
				if(goodcategories==null){
					goodcategories=new Goodcategories();
				}
				goodcategories.setGoodname(g_name);
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				JSONArray jsonArray=JsonUtil.formatRsToJsonArray(goodcategoriesdao.goodcategoriesList(con, pageBean,goodcategories));
				int total=goodcategoriesdao.goodcategoriesCount(con,goodcategories);
				result.put("rows", jsonArray);
				result.put("total", total);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			return null;
	}
		public String delete()throws Exception{
			Connection con=null;
			try{
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				String str[]=delIds.split(",");
				for(int i=0;i<str.length;i++){
					boolean f=gooddao.getGoodByGoodcategoriesId(con, str[i]);
					if(f){
						result.put("errorIndex",i);
						result.put("errorMsg", "类别下面有商品，不能删除!");
						ResponseUtil.write(ServletActionContext.getResponse(), result);
						return null;
					}
				}
				int delNums=goodcategoriesdao.goodcategoriesDelete(con, delIds);
				if(delNums>0){
					result.put("success", "true");
					result.put("delNums", delNums);
				}else{
					result.put("errorMsg", "删除失败");
				}
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		public String save()throws Exception{
			if(StringUtil.isNotEmpty(id)){
				goodcategories.setId(Integer.parseInt(id));
			}
			Connection con=null;
			try{
				con=dbUtil.getCon();
				int saveNums=0;
				JSONObject result=new JSONObject();
				if(StringUtil.isNotEmpty(id)){
					saveNums=goodcategoriesdao.goodcategoriesModify(con, goodcategories);
				}else{
					saveNums=goodcategoriesdao.goodcategoriesAdd(con, goodcategories);
				}
				if(saveNums>0){
					result.put("success", "true");
				}else{
					result.put("success", "true");
					result.put("errorMsg", "保存失败");
				}
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		public String export()throws Exception{
			Connection con=null;
			try {
				con=dbUtil.getCon();
				Workbook wb=new HSSFWorkbook();
				String headers[]={"商品类别编号","商品类别名称","备注"};
				if(goodcategories==null){
					goodcategories=new Goodcategories();
				}
				goodcategories.setGoodname(g_name);
				ResultSet rs=goodcategoriesdao.goodcategoriesList(con, null, goodcategories);
				ExcelUtil.fillExcelData(rs, wb, headers);
				ResponseUtil3.export(ServletActionContext.getResponse(), wb, "导出excel.xls");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	   
	}
