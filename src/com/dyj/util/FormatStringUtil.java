package com.dyj.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatStringUtil {
   public static String formatString(String s){
	   String i;
	   int j=-1,k=-1;
	   j=s.indexOf('.');
	   k=s.indexOf('E');
	   if(j!=-1){
		   if(k!=-1){
			   BigDecimal df=new BigDecimal(s);
			   String result=df.toPlainString();
			   return result;
		   }
	      i=s.substring(0,j);
	      return i;
	   }
	   return s;
   }
}
