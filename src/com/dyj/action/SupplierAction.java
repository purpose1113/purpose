package com.dyj.action;

import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.dyj.dao.GoodDao;
import com.dyj.dao.SupplierDao;
import com.dyj.model.PageBean;
import com.dyj.model.Supplier;
import com.dyj.util.DbUtil;
import com.dyj.util.JsonUtil;
import com.dyj.util.ResponseUtil;
import com.dyj.util.ResponseUtil3;
import com.dyj.util.StringUtil;
import com.dyj.util.ExcelUtil;
import com.opensymphony.xwork2.ActionSupport;

public class SupplierAction extends ActionSupport{
   private Supplier supplier;
   private String page;
   private String rows;
   private String id;
   private String s_name;
   private String delIds;
   
   
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





public Supplier getSupplier() {
	return supplier;
}


public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
}


public String getS_name() {
	return s_name;
}


public void setS_name(String s_name) {
	this.s_name = s_name;
}


public static long getSerialversionuid() {
	return serialVersionUID;
}

DbUtil dbUtil=new DbUtil();
SupplierDao supplierdao=new SupplierDao();
GoodDao gooddao = new GoodDao();
	@Override
public String execute() throws Exception {
		Connection con=null;
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		try{
			if(supplier==null){
				supplier=new Supplier();
			}
			supplier.setName(s_name);
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(supplierdao.supplierList(con, pageBean,supplier));
			int total=supplierdao.supplierCount(con,supplier);
			result.put("rows", jsonArray);
			result.put("total", total);
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
					result.put("errorMsg", "供应商下面有商品，不能删除!");
					ResponseUtil.write(ServletActionContext.getResponse(), result);
					return null;
				}
			}
			int delNums=supplierdao.supplierDelete(con, delIds);
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
			supplier.setId(Integer.parseInt(id));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(id)){
				saveNums=supplierdao.supplierModify(con, supplier);
			}else{
				saveNums=supplierdao.supplierAdd(con, supplier);
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
	public String gradeComboList() throws Exception{
		Connection con=null;
		try{
		con=dbUtil.getCon();
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("gradeName", "请选择...");
		jsonArray.add(jsonObject);
		jsonArray.addAll(JsonUtil.formatRsToJsonArray(supplierdao.supplierList(con, null,null)));
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
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
			String headers[]={"供应商编号","供应商名称","联系人","联系电话","备注"};
			if(supplier==null){
				supplier=new Supplier();
			}
			supplier.setName(s_name);
			ResultSet rs=supplierdao.supplierList(con, null, supplier);
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
