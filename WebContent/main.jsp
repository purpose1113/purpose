<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>超市管理系统</title>
<% 
    if(session.getAttribute("currentUser")==null){
    	response.sendRedirect("index.jsp");
    	return;
    }
%>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	  $(function(){
		  var treeDate=[{
			  text:"库存信息",
			  children:[{
				  text:"供应商信息",
				  attributes:{
					  url:"Supplier.jsp"
				  }
			  },{
				  text:"商品类别信息",
				  attributes:{
					  url:"Goodcategories.jsp"
				  }
			  },
			  {
				  text:"商品信息",
				  attributes:{
					  url:"Good.jsp"
				  }
			  },
			  {
				  text:"商品管理",
				  children:[{
					  text:"商品入库信息",
					  attributes:{
						  url:"Storage.jsp"
					  }
				  },{text:"商品出库信息",
					  attributes:{
						  url:"OutStock.jsp"}},{text:"商品库存信息",
							  attributes:{
								  url:"Stock.jsp"}}]
			  }]
		  }];
		  $("#tree").tree({
			 data:treeDate,
		     lines:true,
		     onClick:function(node){
		    	 if(node.attributes){
		    		 openTab(node.text,node.attributes.url);
		    	 }
		     }
		  });
		  function openTab(text,url){
			  if($("#tabs").tabs("exists",text)){
				  $("#tabs").tabs("select",text);
			  }else{
				  var content="<iframe src="+url+" frameborder='0' scrolling='auto' style='width:100%;height:100%'></frame>"
			  $("#tabs").tabs("add",{
				  title:text,
				  content:content,
				  closable:true
			  });			  
			  }
		  }
	  });
	</script>
</head>
   <body class="easyui-layout">
      <div region="north" style="height:80px;background-color:#E0EDFF">
      <div align="center" style="width:80%;float:left"><img src="images/main.jpg"></div>
      <div align="right"><a href="/Ccgl/login!loginOut"><img src="images/admin_exit.jpg"></a></div>
      <div style="padding-top:20px;padding-right: 20px" align="right">当前用户：<font color="red">&nbsp${currentUser.userName}</font></div>
    </div>
    <div region="center">
    <div class="easyui-tabs" id="tabs" fit="true" border="false">
    <div title=首页>
      <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
    </div>
    </div>
    </div>
    <div region="west" style="width:150px" title="导航栏" split="true">
      <ul id="tree">
      </ul>
    </div>
    <div region="south" style="height:25px" align="center">版权所有<a href="http://www.java1234.com">www.dyj.com</a></div>
    </body>
</html>