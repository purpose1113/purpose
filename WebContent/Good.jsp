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
	    function deleteGood(){
	    	var selectedRows=$("#dg").datagrid("getSelections");
	    	if(selectedRows.length==0){
	    		$.messager.alert("系统提示","请选择要删除的数据");
	    		return;
	    	}
	    var strIds=[];
	    for(var i=0;i<selectedRows.length;i++){
	    	strIds.push(selectedRows[i].id);
	    }
	    var ids = strIds.join(",");
	    $.messager.confirm("系统提示","您确定要删掉这<font color='red'>"+selectedRows.length+"</font>条数据么?",function(r){
	    	if(r){
	    		$.post("good!delete",{delIds:ids},function(result){
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
	    	$("#dlg").dialog("open").dialog("setTitle","添加商品");
	    	url="good!save";
	    }
	    function closeDialog(){
	    	$("#dlg").dialog("close");
	    	resetValue();
	    }
	    function closeDialog2(){
	    	$("#dlg2").dialog("close");
	    }
	    function resetValue(){
	    	$("#goodnumber").val("");
	    	$("#goodname").val("");
	    	$("#supplierid").val("");
	    	$("#goodcategoriesid").val("");
	    	$("#note").val("");
	    }
	    function saveGoodcategories(){
	    	$("#fm").form("submit",{
	    		url:url,
	    		onSubmit:function(){
	    			return $(this).form("validate");
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
	    	$("#dlg").dialog("open").dialog("setTitle","编辑商品信息");
	    	$("#goodnumber").val(row.goodnumber);
	    	$("#goodname").val(row.goodname);
	    	$("#supplierid").val(row.supplierid);
	    	$("#goodcategoriesid").val(row.goodcategoriesid);
	    	$("#note").val(row.note);
	    	url="good!save?id="+row.id;
	    }
	    function exportUser(){
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
	</script>
</head>
<body style="margin:5px">
  <table class=easyui-datagrid id="dg" title="供应商信息" fitColumns="true" pagination="true" rownumbers="true"
    fit="true" url="good" toolbar="#tb">
    <thead>
      <tr>
        <th field="cb" checkbox="true">编号</th>
        <th field="id" width="50">编号</th>
        <th field="goodnumber" width="50">商品编号</th>
        <th field="goodname" width="100">商品名称</th>
        <th field="supplierid" width="50">供应商编号</th>
        <th field="goodcategoriesid" width="50">商品类别编号</th>
        <th field="note" width="250">备注</th>
  </table>
   <div id=tb>
  <div>
  <div  style="float: left">
    <a href="javascript:addopendialog()" class=easyui-linkbutton iconCls="icon-add" plain="true">添加</a>
    <a href="javascript:modifyopendialog()" class=easyui-linkbutton iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:deleteGood()" class=easyui-linkbutton iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="exportUser()">导出用户</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="openImportDialog()">导入用户</a>
  </div>
  <div>
  <form  id='condition' name='condition' action='good!export' method="post">&nbsp&nbsp&nbsp&nbsp&nbsp<font color="red" >商品名称：</font>
      <input type="text" id='g_name' name="g_name" >
        <a href="javascript:searchGood()" class="easyui-linkbutton" iconCls="icon-search" id="btn" plain="true" >搜索</a>
        </form>
  </div>
     </div>
  <div id="dlg" class="easyui-dialog" style="width:600px;height:280px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons" data-options="onBeforeClose:function(){resetValue()}
	   ">
     <form id=fm method="post">
       <table>
        <tr>
          <td align="right">编号：
          </td>
           <td><input type="text" name="good.goodnumber"  id="goodnumber" class=easyui-validatebox required="true">
          </td>
          <td align="right">名称：
          </td>
           <td><input type="text" name="good.goodname"  id="goodname" class=easyui-validatebox required="true">
          </td>
        </tr>
        <tr>
          <td>供应商编号：
          </td>
           <td><input type="text" name="good.supplierid"  id="supplierid" class=easyui-validatebox required="true">
          </td>
          <td>类别编号：
          </td>
           <td><input type="text" name="good.goodcategoriesid"  id="goodcategoriesid" class=easyui-validatebox required="true">
          </td>
        </tr>
        <tr>
          <td valign="top">备注：
          </td>
           <td colspan="3"><textarea rows="4" cols="40" name="good.note" id="note"></textarea>
           </td>
        </tr>
       </table>
     </form>
  </div>
  <div id="dlg2" class="easyui-dialog" style="width:350px;height:150px;padding:10px 20px"
    closed="true" buttons="#dlg2-buttons" data-options="onBeforeClose:function(){resetValue()}
	   ">
      <form id="uploadForm" action="good!upload" method="post" enctype="multipart/form-data">
       <table>
        <tr>
          <td >请选择文件位置：
          </td>
           <td float="right"><input type="file" name="userUploadFile"></td>
          </td>
        </tr>
       </table>
     </form>
  </div>
  <div id="dlg-buttons">
		<a href="javascript:saveGoodcategories()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<div id="dlg2-buttons">
		<a href="javascript:uploadFile()" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
		<a href="javascript:closeDialog2()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
</body>
</html>