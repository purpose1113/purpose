package com.dyj.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.dyj.dao.StockDao;
import com.dyj.dao.StockDao;
import com.dyj.model.OutStock;
import com.dyj.model.PageBean;
import com.dyj.model.Stock;
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

public class StockAction extends ActionSupport{
		   private String page;
		   private String rows;
		   private String id;
		   private String g_name;
		   private String delIds;
		   private File userUploadFile;
           private Stock stock;
           
           


		public Stock getStock() {
			return stock;
		}


		public void setStock(Stock stock) {
			this.stock = stock;
		}


		public File getUserUploadFile() {
			return userUploadFile;
		}


		public void setUserUploadFile(File userUploadFile) {
			this.userUploadFile = userUploadFile;
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

		DbUtil dbUtil=new DbUtil();
		StockDao stockdao = new StockDao();
			@Override
			public String execute() throws Exception {
				Connection con=null;
				PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
				try{
					if(stock==null){
						stock=new Stock();
					}
					stock.setGoodname(g_name);
					con=dbUtil.getCon();
					JSONObject result=new JSONObject();
					JSONArray jsonArray=JsonUtil.formatRsToJsonArray(stockdao.stockList(con, pageBean,stock));
					int total=stockdao.stockCount(con,stock);
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
			public String find() throws Exception {
				Connection con=null;
				PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
				try{
					if(stock==null){
						stock=new Stock();
					}
					con=dbUtil.getCon();
					JSONObject result=new JSONObject();
					JSONArray jsonArray=JsonUtil.formatRsToJsonArray(stockdao.stockList2(con, pageBean,stock));
					int total=stockdao.stockCount(con,stock);
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
					int delNums=stockdao.stockDelete(con, delIds);
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
					stock.setStockid(Integer.parseInt(id));
				}
				Connection con=null;
				try{
					con=dbUtil.getCon();
					int saveNums=0;
					JSONObject result=new JSONObject();
					if(StringUtil.isNotEmpty(id)){
						saveNums=stockdao.stockModify(con, stock);
					}else{
						saveNums=stockdao.stockAdd(con, stock);
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
					String headers[]={"编号","商品名称","数量","成本价","销售价","备注"};
					if(stock==null){
						stock=new Stock();
					}
					stock.setGoodname(g_name);
					ResultSet rs=stockdao.stockList3(con, null, stock);
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
					Workbook wb=ExcelUtil.fillExcelDataWithTemplate(stockdao.stockList3(con, null,null), "stockExporTemplate.xls",con);
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
						Stock stock=new Stock();
						stock.setGoodid(Integer.parseInt(FormatStringUtil.formatString(Conversiongood.conversion(ExcelUtil.formatCell(hssfRow.getCell(0))))));
						stock.setGoodnumber(Integer.parseInt(FormatStringUtil.formatString(ExcelUtil.formatCell(hssfRow.getCell(1)))));
						stock.setStocknote((FormatStringUtil.formatString(ExcelUtil.formatCell(hssfRow.getCell(4)))));
						stock.setOutstockid(Integer.parseInt(FormatStringUtil.formatString(Conversiongood.conversion(ExcelUtil.formatCell(hssfRow.getCell(2))))));
						stock.setStorageid(Integer.parseInt(FormatStringUtil.formatString(Conversiongood.conversion(ExcelUtil.formatCell(hssfRow.getCell(3))))));
						Connection con=null;
						try{
							con=dbUtil.getCon();
							stockdao.stockAdd(con, stock);
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
