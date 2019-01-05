package com.dyj.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.dyj.dao.StorageDao;
import com.dyj.model.PageBean;
import com.dyj.model.Storage;
import com.dyj.util.Conversiongood;
import com.dyj.util.DateUtil;
import com.dyj.util.DbUtil;
import com.dyj.util.ExcelUtil;
import com.dyj.util.FormatStringUtil;
import com.dyj.util.JsonUtil;
import com.dyj.util.ResponseUtil;
import com.dyj.util.ResponseUtil3;
import com.dyj.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class StorageAction extends ActionSupport{
		       private Storage storage;
			   private String page;
			   private String rows;
			   private String id;
			   private String g_name;
			   private String delIds;
			   private File userUploadFile;
	        
			  
			

			public Storage getStorage() {
				return storage;
			}
			public void setStorage(Storage storage) {
				this.storage = storage;
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
			public String getDelIds() {
				return delIds;
			}
			public void setDelIds(String delIds) {
				this.delIds = delIds;
			}
			public File getUserUploadFile() {
				return userUploadFile;
			}
			public void setUserUploadFile(File userUploadFile) {
				this.userUploadFile = userUploadFile;
			}
			DbUtil dbUtil=new DbUtil();
			StorageDao storagedao=new StorageDao();
				@Override
			public String execute() throws Exception {
					Connection con=null;
					PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
					try{
						if(storage==null){
							storage=new Storage();
						}
						storage.setGoodname(g_name);
						con=dbUtil.getCon();
						JSONObject result=new JSONObject();
						JSONArray jsonArray=JsonUtil.formatRsToJsonArray(storagedao.storageList(con, pageBean,storage));
						int total=storagedao.storageCount(con,storage);
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
						int delNums=storagedao.storageDelete(con, delIds);
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
						storage.setId(Integer.parseInt(id));
					}
					Connection con=null;
					try{
						con=dbUtil.getCon();
						int saveNums=0;
						JSONObject result=new JSONObject();
						if(StringUtil.isNotEmpty(id)){
							saveNums=storagedao.storageModify(con, storage);
						}else{
							saveNums=storagedao.storageAdd(con, storage);
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
						String headers[]={"编号","商品名称","进价","入库日期","数量","备注"};
						if(storage==null){
							storage=new Storage();
						}
						storage.setGoodname(g_name);
						ResultSet rs=storagedao.storageList(con, null, storage);
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
				public String export2()throws Exception{
					Connection con=null;
					try {
						con=dbUtil.getCon();
						ResultSet rs=storagedao.storageList(con, null, null);
						Workbook wb=ExcelUtil.fillExcelDataWithTemplate(storagedao.storageList2(con, null,null), "storageExporTemplate.xls",con);
						ResponseUtil3.export(ServletActionContext.getResponse(), wb, "利用模版导出excel.xls");
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
				public String upload()throws Exception{
					POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(userUploadFile));
					HSSFWorkbook wb=new HSSFWorkbook(fs);
					HSSFSheet hssfSheet=wb.getSheetAt(0);  // 获取第一个Sheet页
					if(hssfSheet!=null){
						for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
							HSSFRow hssfRow=hssfSheet.getRow(rowNum);
							if(hssfRow==null){
								continue;
							}
							Storage storage=new Storage();
							storage.setGoodid(Integer.parseInt(FormatStringUtil.formatString(Conversiongood.conversion(ExcelUtil.formatCell(hssfRow.getCell(0))))));
							storage.setInprice(Integer.parseInt(FormatStringUtil.formatString(ExcelUtil.formatCell(hssfRow.getCell(1)))));
							storage.setStoragedate(DateUtil.formatString(hssfRow.getCell(2).getStringCellValue(),"yyyy-MM-dd"));
							storage.setStoragenumber(Integer.parseInt((FormatStringUtil.formatString(ExcelUtil.formatCell(hssfRow.getCell(3))))));
							storage.setStoragenote((FormatStringUtil.formatString(ExcelUtil.formatCell(hssfRow.getCell(4)))));
							Connection con=null;
							try{
								con=dbUtil.getCon();
								storagedao.storageAdd(con, storage);
							}catch(Exception e){
								e.printStackTrace();
							}finally{
								dbUtil.closeCon(con);
							}
						}
					}
					JSONObject result=new JSONObject();
					result.put("success", "true");
					ResponseUtil.write(ServletActionContext.getResponse(), result);
					return null;
				}
}
