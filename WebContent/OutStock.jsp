<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>商品信息</title>
<script type="text/javascript">
	var url;
	    function searchGood(){
	    	$('#dg').datagrid('load',{
	    		g_name:$('#g_name').val()
	    	});
	    }
	    function deleteStorage(){
	    	var selectedRows=$("#dg").datagrid("getSelections");
	    	if(selectedRows.length==0){
	    		$.messager.alert("系统提示","请选择要删除的数据");
	    		return;
	    	}
	    var strIds=[];
	    for(var i=0;i<selectedRows.length;i++){
	    	strIds.push(selectedRows[i].outstockid);
	    }
	    var ids = strIds.join(",");
	    $.messager.confirm("系统提示","您确定要删掉这<font color='red'>"+selectedRows.length+"</font>条数据么?",function(r){
	    	if(r){
	    		$.post("outstock!delete",{delIds:ids},function(result){
	    			if(result.success){
	    				$.messager.alert("系统提示",'您已成功删除<font color=red>'+result.delNums+"</font>条数据!");
	    				$("#dg").datagrid("reload");
	    			}else{
	    				$.messager.alert("系统提示",selectedRows[result.errorIndex].gradeName+result.errorMsg);
	    			}
	    		},"json"); 
	    	}
	    });
	    }
	    function addopendialog(){
	    	$("#dlg").dialog("open").dialog("setTitle","添加入库信息");
	    	url="outstock!save";
	    }
	    function closeDialog(){
	    	$("#dlg").dialog("close");
	    	resetValue();
	    }
	    function closeDialog2(){
	    	$("#dlg2").dialog("close");
	    }
	    function closeDialog3(){
	    	$("#dlg3").dialog("close");
	    }
	    function resetValue(){
	    	$("#goodname").val("");
	    	$("#saleprice").val("");
	    	$("#outstockdate").datebox("setValue","");
	    	$("#outstocknumber").val("");
	    	$("#outstocknote").val("");
	    }
	    function saveOutStock(){
	    	$("#fm").form("submit",{
	    		url:url,
	    		onSubmit:function(){
	    			return $(this).form('validate');
	    		},success:function(result){
	    			if(result.errMsg){
	    				$.messager.alert("系统提示",result.errMsg);
	    				return;
	    			}else{
	    				$.messager.alert("系统提示","保存成功");
	    				resetValue();
	    				$("#dlg").dialog("close");
	    				$("#dg").datagrid("reload");
	    			}
	    		}
	    	});
	    }
	       
	    function modifyopendialog(){
	    	var selectedRows=$("#dg").datagrid("getSelections");
	    	if(selectedRows.length!=1){
	    		$.messager.alert("系统提示","请选择一条要修改的数据");
	    		return;
	    	}
	    	var row=selectedRows[0];
	    	$("#dlg").dialog("open").dialog("setTitle","编辑商品入库信息");
	    	$("#outstocknumber").val(row.outstocknumber);
	    	$("#goodname").val(row.goodname);
	    	$("#outstockdate").datebox("setValue",row.outstockdate);
	    	$("#saleprice").val(row.saleprice);
	    	$("#outstocknote").val(row.outstocknote);
	    	$("#goodid").val(row.goodid);
	    	url="outstock!save?id="+row.outstockid;
	    }
	    function exportOutStock(){
			$('#condition').submit();
		}
	    function openImportDialog(){
	    	$("#dlg2").dialog('open').dialog('setTitle','批量导入数据');
	    }
	    function uploadFile(){
			$("#uploadForm").form("submit",{
				success:function(result){
					var result=eval('('+result+')');
					if(result.errorMsg){
						$.messager.alert("系统提示",result.errorMsg);
					}else{
						$.messager.alert("系统提示","上传成功");
						$("#dlg2").dialog("close");
						$("#dg").datagrid("reload");
					}
				}
			});
		}
	    function openGoodDialog(){
	    	$("#dlg3").dialog('open').dialog('setTitle','选择商品');
	    }
	    function turnback(){
	    	var selectedRows=$("#dg2").datagrid("getSelections");
	    	if(selectedRows.length!=1){
	    		$.messager.alert("系统提示","请选择一条要修改的数据");
	    		return;
	    	}
	    	var row=selectedRows[0];
	    	$("#goodname").val(row.goodname);
	    	$("#goodid").val(row.goodid);
	    	$("#dlg3").dialog("close");
	    }
	    function exportUser2(){
			window.open('outstock!export2');
			$("#dlg2").dialog("close");
		}
	</script>
</head>
<body style="margin:5px">
  <table class=easyui-datagrid id="dg" title="入库信息" fitColumns="true" pagination="true" rownumbers="true"
    fit="true" url="outstock" toolbar="#tb">
    <thead>
      <tr>
        <th field="cb" checkbox="true">编号</th>
        <th field="outstockid" width="50">编号</th>
        <th field="goodname" width="50">商品名称</th>
        <th field="saleprice" width="100">销售价</th>
        <th field="outstockdate" width="50">出库日期</th>
        <th field="outstocknumber" width="50">数量</th>
        <th field="outstocknote" width="250">备注</th>
  </table>
   <div id=tb>
  <div  style="float: left">
    <a href="javascript:addopendialog()" class=easyui-linkbutton iconCls="icon-add" plain="true">添加</a>
    <a href="javascript:modifyopendialog()" class=easyui-linkbutton iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:deleteStorage()" class=easyui-linkbutton iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="exportOutStock()">导出用户</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="openImportDialog()">导入用户</a>
  </div>
  <div>
  <form id='condition' name='condition' action='outstock!export' method="post">&nbsp&nbsp&nbsp&nbsp&nbsp<font color="red" >商品名称：</font>
      <input type="text" id='g_name' name="g_name" >
        <a href="javascript:searchGood()" class="easyui-linkbutton" iconCls="icon-search" id="btn" plain="true" >搜索</a>
        </form>
  </div>
     </div>
  <div id="dlg" class="easyui-dialog" style="width:650px;height:280px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons" data-options="onBeforeClose:function(){resetValue()}
	   ">
     <form id="fm" method="post" >
       <table >
        <tr>
          <td width="30%" align="center">商品：
          </td>
           <td width="10%"><input type="text" style="background-color: lightgray" name="outstock.goodname"  id="goodname" class="easyui-validatebox" required="true" readOnly="true" >
          </td>
          <td width="20%"><a href="javascript:openGoodDialog()" class="easyui-linkbutton" iconCls="icon-mini-edit" >选择</a>
          </td>
          <td width="10%" align="center">售价：
          </td>
           <td width="30%"><input type="text" name="outstock.saleprice"  id="saleprice" class="easyui-validatebox" required="true" plain="true">
          </td>
        </tr>
        <tr>
          <td align="center">出库日期：
          </td>
           <td ><input class="easyui-datebox" name="outstock.outstockdate"  id="outstockdate" class="easyui-validatebox" required="true" editable="false">
          </td>
          <td><input type="hidden" name="outstock.goodid"  id="goodid"  >
          </td>
          <td align="center">数量：
          </td>
           <td><input type="text" name="outstock.outstocknumber"  id="outstocknumber" class="easyui-validatebox" required="true">
          </td>
        </tr>
        <tr>
          <td valign="top" align="center">备注：
          </td>
           <td colspan="4"><textarea rows="4" cols="40" name="outstock.outstocknote" id="outstocknote"></textarea>
           </td>
        </tr>
       </table>
     </form>
  </div>
  <div id="dlg2" class="easyui-dialog" style="width:400px;height:200px;padding:10px 20px"
    closed="true" buttons="#dlg2-buttons" data-options="onBeforeClose:function(){resetValue()}
	   ">
      <form id="uploadForm" action="outstock!upload" method="post" enctype="multipart/form-data">
       <table>
       <tr>
        	<td>下载模版：</td>
        	<td><a href="javascript:void(0)" class="easyui-linkbutton"  onclick="exportUser2()" plain="true" style="background-color: lightgray" iconCls="icon-import">导入模版</a></td>
        </tr>
        <tr>
          <td >请选择文件位置：
          </td>
           <td float="right"><input type="file" name="userUploadFile"></td>
          </td>
        </tr>
       </table>
     </form>
  </div>
  <div id="dlg3" class="easyui-dialog" style="width:650px;height:280px;padding:10px 20px"
    closed="true" buttons="#dlg3-buttons" 
	   >
	   <table class=easyui-datagrid  id="dg2" fitColumns="true" pagination="true" rownumbers="true"
    fit="true" url="storage" >
    <thead>
      <tr>
        <th field="cb" checkbox="true">编号</th>
        <th field="id" width="50">编号</th>
        <th field="goodname" width="50">商品名称</th>
  </table>
	   </div>
  <div id="dlg-buttons">
		<a href="javascript:saveOutStock()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<div id="dlg2-buttons">
		<a href="javascript:uploadFile()" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
		<a href="javascript:closeDialog2()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<div id="dlg3-buttons">
		<a href="javascript:turnback()" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
		<a href="javascript:closeDialog3()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
</body>
</html>