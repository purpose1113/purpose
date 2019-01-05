<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HEAD id=Head1>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>超市管理系统</title>
<STYLE type=text/css>BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR></HEAD>
<script type="text/javascript" src="${ pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">

function changeValue(){
	alert(document.getElementById("test").value);
	document.getElementById("test").value="aa";
	alert(document.getElementById("test").value);
}

function execute(){
	var t = $('#userName').val();
	var q = $('#password').val();
	var p = $('#imageCode').val();
	$.post("login!login",{s_userName:t,s_password:q,s_code:p},
		function(error){
		if(error==1){
			$("#cw").text("用户名或密码为空!");
		}else if(error ==2){
			$("#cw").text("验证码为空!");
		}else if(error ==3){
			$("#cw").text("验证码错误!");
		}else if(error ==4){
			$("#cw").text("用户名或密码错误!");
		}else{
			 window.location="main.jsp";
		}
	});
}
function resetValue(){
	  document.getElementById("userName").value="";
	  document.getElementById("password").value="";
	  document.getElementById("imageCode").value="";
}
function loadimage(){
	  document.getElementById("randImage").src="image.jsp?"+Math.random();
  }</script>
<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<SCRIPT language=JavaScript> 
var speed=20;
var temp=new Array(); 
var clipright=document.body.clientWidth/2,clipleft=0 
for (i=1;i<=2;i++){ 
	temp[i]=eval("document.all.div"+i+".style");
	temp[i].width=document.body.clientWidth/2;
	temp[i].height=document.body.clientHeight;
	temp[i].left=(i-1)*parseInt(temp[i].width);
} 
function openit(){ 
	clipright-=speed;
	temp[1].clip="rect(0 "+clipright+" auto 0)";
	clipleft+=speed;
	temp[2].clip="rect(0 auto auto "+clipleft+")";
	if (clipright<=0)
		clearInterval(tim);
} 
tim=setInterval("openit()",100);
                </SCRIPT>

<DIV>&nbsp;&nbsp; </DIV></DIV>

<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="login_files/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=login_files/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
          <form id="condition" name="condition">
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id="userName"
                  style="WIDTH: 130px" name="user.userName" value="${user.userName}"></TD>
                <TD style="HEIGHT: 28px" width=370><SPAN 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id=password style="WIDTH: 130px" 
                  type=password name="user.password" value="${user.password}"></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD></TR>
              <TR >
                <TD style="HEIGHT: 28px">验证码：</TD>
                <TD style="HEIGHT: 38px"><INPUT 
                  style="WIDTH: 72px" id="imageCode" name="imageCode" type="text" value="${imageCode}">&nbsp;<img onclick="javascript:loadimage();" title="换一张试试" name="randImage" id="randImage" src="image.jsp" width="50px" height="22px" border="1" align="absmiddle"></TD>
                <TD style="HEIGHT: 28px">&nbsp;</TD></TR>
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD align="left"><image id=btn 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  src="login_files/login_button.gif"  onclick="javascript:execute()"> 
                  <td align="center"><image 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  src="login_files/reset.jpg" onclick="resetValue()"> </td>
                  <TD style="HEIGHT: 28px">&nbsp;</TD></TR>
			     <tr height="10" >
			      <td width="40%" align="center" colspan="2"><font color="red" size="5" id="cw" name="cw"></font>
			     </td>
			    </tr>
			    <tr>
			    <td></td></tr>
              </TD></TR></TBODY></TABLE></form></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="login_files/login_3.jpg"
border=0></TD></TR></TBODY></TABLE></DIV>
</BODY></HTML>
