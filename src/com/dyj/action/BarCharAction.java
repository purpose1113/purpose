package com.dyj.action;
import java.awt.Color;
import java.sql.Connection;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

import com.dyj.dao.GoodDao;
import com.dyj.dao.OutstockDao;
import com.dyj.util.DbUtil;
import com.opensymphony.xwork2.ActionSupport;


public class BarCharAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
       private JFreeChart chart;
       private String goodid;
       
	public JFreeChart getChart() {
		return chart;
	}
	
	public String getGoodid() {
		return goodid;
	}

	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}

	DbUtil dbUtil=new DbUtil();
	GoodDao gooddao=new GoodDao();
    OutstockDao outstockdao = new OutstockDao();
	@Override
	public String execute() throws Exception {
		Connection con=null;
		con=dbUtil.getCon();
		String goodname = gooddao.getGoodById(con, goodid);
		double [][]data=outstockdao.getOutstock(con,goodid);
		String []rowKeys = {"��һ����","�ڶ�����","��������","���ļ���"}; 
		String []columnKeys={goodname};
    	CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys,data);
    	chart = ChartFactory.createBarChart3D("��Ʒ����ͳ��ͼ", "��Ʒ", "����", 
    			dataset, PlotOrientation.VERTICAL, true, true, true);
    	CategoryPlot plot = chart.getCategoryPlot();
    	// �������񱳾���ɫ
		plot.setBackgroundPaint(Color.white);
		// ��������������ɫ
		plot.setDomainGridlinePaint(Color.pink);
		// �������������ɫ
		plot.setRangeGridlinePaint(Color.pink);
		
		// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
		BarRenderer3D renderer=new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);  
		
		// ����ƽ������֮�����
		renderer.setItemMargin(0.4);
		
		plot.setRenderer(renderer);
		return SUCCESS;
	}

}
