package com.dyj.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.dyj.dao.GoodDao;
import com.dyj.dao.OutstockDao;
import com.dyj.dao.StockDao;
import com.dyj.dao.StorageDao;

public class ExcelUtil {

	public static void fillExcelData(ResultSet rs,Workbook wb,String[] headers)throws Exception{
		int rowIndex=0;
		Sheet sheet=wb.createSheet();
		Row row=sheet.createRow(rowIndex++);
		for(int i=0;i<headers.length;i++){
			row.createCell(i).setCellValue(headers[i]);
		}
		while(rs.next()){
			row=sheet.createRow(rowIndex++);
			for(int i=0;i<headers.length;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
	}
	public static Workbook fillExcelDataWithTemplate(ResultSet rs,String templateFileName,Connection con)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/template/"+templateFileName);
		POIFSFileSystem fs=new POIFSFileSystem(inp);
		Workbook wb=new HSSFWorkbook(fs);
		Sheet sheet=wb.getSheetAt(0);
		// 获取列数
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;
		List list = new ArrayList();;
		StorageDao storagedao = new StorageDao();
		OutstockDao outstockdao = new OutstockDao();
		GoodDao gooddao = new GoodDao();
		ResultSet sr=gooddao.goodList(con, null, null);
		while(sr.next()){
		  list.add(sr.getString("goodname")+"-"+sr.getString("id"));
		}
		List list2 = new ArrayList();;
		ResultSet er=storagedao.storageList4(con,null);
		while(er.next()){
		  list2.add(er.getString("inprice")+"-"+er.getString("id"));
		}
		List list3 = new ArrayList();
		ResultSet qr=outstockdao.outstockList3(con,null);
		while(qr.next()){
		  list3.add(qr.getString("saleprice")+"-"+qr.getString("outstockid"));
		}
		String[] array1= (String[]) list.toArray(new String[2]);
		String[] array2 = (String[]) list2.toArray(new String[2]);
		String[] array3= (String[]) list3.toArray(new String[2]);
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				Cell cell = row.createCell(i);
				if(i==0){ 
					CellRangeAddressList regions = new CellRangeAddressList(rowIndex-1,rowIndex-1,0,0);
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(array1);
					HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
					sheet.addValidationData(data_validation);
					cell.setCellValue(rs.getString("goodname")+"-"+rs.getString("goodid"));
				}else if(i==2){
					CellRangeAddressList regions = new CellRangeAddressList(1,4,2,2);
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(array2);
					HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
					sheet.addValidationData(data_validation);
					cell.setCellValue(rs.getString("inprice")+"-"+rs.getString("id"));
				}else if(i==3){
					CellRangeAddressList regions = new CellRangeAddressList(1,4,3,3);
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(array3);
					HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
					sheet.addValidationData(data_validation);
					cell.setCellValue(rs.getString("saleprice")+"-"+rs.getString("outstockid"));
				}else{
				cell.setCellValue(rs.getObject(i+1).toString());
				}
			}
		}
		return wb;
	}
	public static String formatCell(HSSFCell hssfCell){
		if(hssfCell==null){
			return "";
		}else{
			if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
				return String.valueOf(hssfCell.getBooleanCellValue());
			}else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				return String.valueOf(hssfCell.getNumericCellValue());
			}else{
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
	}
}
