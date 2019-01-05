<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">   
    <script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>    
</head>
<script type="text/javascript">
function topwin(x){
window.showModalDialog("1?goodid="+x,"","dialogLeft:400px; dialogTop:100px ;dialogWidth:500px;dialogHeight:500px;scroll:no;status:no");
}
</script>
<body id="page4">
	<!--======================= =======header=================================-->
    <header>
    	<div class="row-1">
        	<div class="main">
            	<div class="container_12">
                	<div class="grid_12">
                    	<nav>
                            <ul class="menu">
                                <li><a href="index.jsp">About Us</a></li>
                                <li><a  href="pricing.jsp">Pricing</a></li>
                                <li><a  href="contacts.jsp">Contacts</a></li>
                                <li >
                                 <a href="/Ccgl/admin.jsp" >[进入后台]</a>
                                 </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="row-2">
        	<div class="main">
            	<div class="container_12">
                	<div class="grid_9">
                    	<h1>
                            <a class="logo" href="index.html">ST<strong>O</strong>RAGE</a>
                            <span>Message</span>
                        </h1>
                    </div>
                    <div class="grid_3">
                    	<form id="search-form" method="post" enctype="multipart/form-data">
                            <fieldset>	
                                <div class="search-field">
                                    <input name="search" type="text" />
                                    <a class="search-button" href="#" onClick="document.getElementById('search-form').submit()"><span>search</span></a>	
                                </div>						
                            </fieldset>
                        </form>
                     </div>
                     <div class="clear"></div>
                </div>
            </div>
        </div>    	
    </header><div class="ic">More Website Templates  @ TemplateMonster.com - August22nd 2011!</div>
    
<!-- content -->
    <section id="content">
        <div class="bg-top">
        	<div class="bg-top-2">
                <div class="bg">
                    <div class="bg-top-shadow">
                        <div class="main">
                            <div class="box">
                                <div class="padding">
                                    <div class="container_12">
                                        <div class="wrapper">
                                            <div class="grid_12">
                                            	<div class="indent-left p2">
                                                	<h3 class="p0">Latest Income </h3>
                                                	<h1><font color=red>注：点击图片可查看报表</font></h1>
                                                </div>
                                                <c:choose>
                                                <c:when test="${goodList==null || goodList.size()==0}">
                                                                                                                                              没有信息类别可以显示!
                                                </c:when>
                                                <c:otherwise>
	                                                <table  width="600" border="1">
	                                                <tr align="center" width="600">
	                                                <c:forEach var="good" items="${goodList}" varStatus="status">
	                                                <td width="500"><table width="100%"><tr><img src="${good.photo}" alt="" onClick="javascript:topwin('${good.id}')">
	                                                </tr>
	                                                <tr align="center"><td >商品名称:${good.goodname}</td>&nbsp;&nbsp;&nbsp;
	                                                <td align="center">商品数量:【${good.goodmumber}】</td></tr>
	                                                </table></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></td>
	                                                <c:if test="${status.index%3==2}">
	                                                </tr>
	                                               <tr>
                                                   <td>&nbsp
                                                   </td>
                                                   </tr>
	                                                <tr align="center">
	                                                </c:if>
	                                                </c:forEach>
	                                                </tr>
	                                                </table>
                                                </c:otherwise>
                                                </c:choose>
                                                <div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>	
        </div>
        <!--==============================footer=================================-->
    <footer>
        <div class="main">
        	<div class="container_12">
            	<div class="wrapper">
                	<div class="grid_4">
                    	<div>Interior Design &copy; 2011 <a class="link color-3" href="#">Privacy Policy</a></div>
                        <div>More Templates</div>
                        <!-- {%FOOTER_LINK} -->
                    </div>
                    <div class="grid_4">
                    	<span class="phone-numb"><span>+1(800)</span> 123-1234</span>
                    </div>
                    <div class="grid_4">
                    	<ul class="list-services">
                        	<li><a href="#"></a></li>
                            <li><a class="item-2" href="#"></a></li>
                            <li><a class="item-3" href="#"></a></li>
                            <li><a class="item-4" href="#"></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>