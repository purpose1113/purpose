package com.dyj.util;

public class Conversiongood {
	 public static String conversion(String s){
		   String i;
		   int j=-1;
		   j=s.indexOf('-');
		   if(j!=-1){
		   i=s.substring(j+1,s.length());
		   return i;
		   }
		   return s;
	   }
	 public static String conversion2(String s){
		   String i;
		   int j=-1;
		   j=s.indexOf('-');
		   if(j!=-1){
		   i=s.substring(0,j-1);
		   return i;
		   }
		   return s;
	   }
}
