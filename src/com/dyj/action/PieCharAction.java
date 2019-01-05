package com.dyj.action;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

import com.opensymphony.xwork2.ActionSupport;


public class PieCharAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
       private JFreeChart chart;
       private String goodname;
       
       
	public String getGoodname() {
		return goodname;
	}

	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}

	public JFreeChart getChart() {
		return chart;
	}

	@Override
	public String execute() throws Exception {
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("���Ŀ���", 1000);
			dataset.setValue("��Ƽ�ʻ", 800);
			dataset.setValue("�ǹ�ǿ��", 400);
			dataset.setValue("ҽ���¹�", 100);
			dataset.setValue("����", 29);

			chart=ChartFactory.createPieChart3D("���������������ֲ�ͼ", dataset, true, true, true);
			
			// ������
			chart.addSubtitle(new TextTitle("2013���"));
			
			PiePlot pieplot=(PiePlot)chart.getPlot();
			pieplot.setLabelFont(new Font("����",0,11));
			// ���ñ�ͼ��Բ�ģ�true����������Բ�ģ�false����Ĭ��Ϊtrue  
			pieplot.setCircular(true);
			// û�����ݵ�ʱ����ʾ������
			pieplot.setNoDataMessage("��������ʾ");
			StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1}.{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
			pieplot.setLabelGenerator(standarPieIG);  
			
			
			PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
			
			//���ÿ�ʼ�Ƕ�  
			pieplot3d.setStartAngle(120D);  
			//���÷���Ϊ��˳ʱ�뷽��  
			pieplot3d.setDirection(Rotation.CLOCKWISE);  
			//����͸���ȣ�0.5FΪ��͸����1Ϊ��͸����0Ϊȫ͸��  
			pieplot3d.setForegroundAlpha(0.7F); 
            return SUCCESS;			
	}

}
