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
			dataset.setValue("黑心矿难", 1000);
			dataset.setValue("醉酒驾驶", 800);
			dataset.setValue("城管强拆", 400);
			dataset.setValue("医疗事故", 100);
			dataset.setValue("其他", 29);

			chart=ChartFactory.createPieChart3D("非正常死亡人数分布图", dataset, true, true, true);
			
			// 副标题
			chart.addSubtitle(new TextTitle("2013年度"));
			
			PiePlot pieplot=(PiePlot)chart.getPlot();
			pieplot.setLabelFont(new Font("宋体",0,11));
			// 设置饼图是圆的（true），还是椭圆的（false）；默认为true  
			pieplot.setCircular(true);
			// 没有数据的时候显示的内容
			pieplot.setNoDataMessage("无数据显示");
			StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1}.{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
			pieplot.setLabelGenerator(standarPieIG);  
			
			
			PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot(); 
			
			//设置开始角度  
			pieplot3d.setStartAngle(120D);  
			//设置方向为”顺时针方向“  
			pieplot3d.setDirection(Rotation.CLOCKWISE);  
			//设置透明度，0.5F为半透明，1为不透明，0为全透明  
			pieplot3d.setForegroundAlpha(0.7F); 
            return SUCCESS;			
	}

}
