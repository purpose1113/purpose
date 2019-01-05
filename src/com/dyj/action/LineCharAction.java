package com.dyj.action;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;

import com.opensymphony.xwork2.ActionSupport;


public class LineCharAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
       private JFreeChart chart;
       
	public JFreeChart getChart() {
		return chart;
	}

	@Override
	public String execute() throws Exception {
			// ������ͳ��
			TimeSeries timeSeries=new TimeSeries("A��վ������ͳ��", Month.class);
			// �������
			timeSeries.add(new Month(1,2013), 100);
			timeSeries.add(new Month(2,2013), 200);
			timeSeries.add(new Month(3,2013), 300);
			timeSeries.add(new Month(4,2013), 400);
			timeSeries.add(new Month(5,2013), 560);
			timeSeries.add(new Month(6,2013), 600);
			timeSeries.add(new Month(7,2013), 750);
			timeSeries.add(new Month(8,2013), 890);
			timeSeries.add(new Month(9,2013), 120);
			timeSeries.add(new Month(10,2013), 400);
			timeSeries.add(new Month(11,2013), 1200);
			timeSeries.add(new Month(12,2013), 1600);
			
			// ������ͳ��
			TimeSeries timeSeries2=new TimeSeries("B��վ������ͳ��", Month.class);
			// �������
			timeSeries2.add(new Month(1,2013), 50);
			timeSeries2.add(new Month(2,2013), 100);
			timeSeries2.add(new Month(3,2013), 150);
			timeSeries2.add(new Month(4,2013), 200);
			timeSeries2.add(new Month(5,2013), 220);
			timeSeries2.add(new Month(6,2013), 300);
			timeSeries2.add(new Month(7,2013), 340);
			timeSeries2.add(new Month(8,2013), 400);
			timeSeries2.add(new Month(9,2013), 450);
			timeSeries2.add(new Month(10,2013), 500);
			timeSeries2.add(new Month(11,2013), 70);
			timeSeries2.add(new Month(12,2013), 800);
			
			// ����ʱ�����еļ���
			TimeSeriesCollection lineDataset=new TimeSeriesCollection();
			lineDataset.addSeries(timeSeries);
			lineDataset.addSeries(timeSeries2);
			
			chart=ChartFactory.createTimeSeriesChart("������ͳ��ʱ������ͼ", "�·�", "������", lineDataset, true, true, true);
			
			//����������
			chart.setTitle(new TextTitle("A,B��վ������ͳ�ƶԱ�ͼ", new Font("����", Font.ITALIC, 15))); 
			//�����ӱ���
			TextTitle subtitle = new TextTitle("2013���", new Font("����", Font.BOLD, 12));
			chart.addSubtitle(subtitle); 
			chart.setAntiAlias(true); 
			
			//����ʱ����ķ�Χ��
			XYPlot plot = (XYPlot) chart.getPlot(); 
			DateAxis dateaxis = (DateAxis)plot.getDomainAxis();
			dateaxis.setDateFormatOverride(new java.text.SimpleDateFormat("M��"));
			dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH,1)); 
			
			//���������Ƿ���ʾ���ݵ�
			XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer)plot.getRenderer();
			xylinerenderer.setBaseShapesVisible(true); 
			
			//����������ʾ�����ݵ��ֵ
			XYItemRenderer xyitem = plot.getRenderer(); 
			xyitem.setBaseItemLabelsVisible(true);
			xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER)); 
			xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
			xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 12)); 
			plot.setRenderer(xyitem);
		   return SUCCESS;
	}

}
