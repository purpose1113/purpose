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
<title>供应商信息管理</title>
<script type="text/javascript">
	var url;
	    function searchSupplier(){
	    	$('#dg').datagrid('load',{
	    		s_name:$('#s_name').val()
	    	});
	    }
	    function deleteGrade(){
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
	    		$.post("supplier!delete",{delIds:ids},function(result){
	    			if(result.success){
	    				$.messager.alert("系统提示",'您已成功删除<font color=red>'+result.delNums+"</font>条数据!");
	    				$("#dg").datagrid("reload");
	    			}else{
	    				$.messager.alert("系统提示",'<font color=red>'+selectedRows[result.errorIndex].name+'</font>'+result.errorMsg);
	    			}
	    		},"json"); 
	    	}
	    });
	    }
	    function addopendialog(){
	    	$("#dlg").dialog("open").dialog("setTitle","添加供应商");
	    	url="supplier!save";
	    }
	    function closeDialog(){
	    	$("#dlg").dialog("close");
	    	resetValue();
	    }
	    function resetValue(){
	    	$("#number").val("");
	    	$("#name").val("");
	    	$("#linkman").val("");
	    	$("#linkphone").val("");
	    	$("#note").val("");
	    }
	    function saveSupplier(){
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
	    	$("#dlg").dialog("open").dialog("setTitle","编辑班级");
	    	$("#number").val(row.number);
	    	$("#name").val(row.name);
	    	$("#linkman").val(row.linkman);
	    	$("#linkphone").val(row.linkphone);
	    	$("#note").val(row.note);
	    	url="supplier!save?id="+row.id;
	    }
	    function exportUser(){
			$('#condition').submit();
		}
	</script>
</head>
<body style="margin:5px">
  <table class=easyui-datagrid id="dg" title="供应商信息" fitColumns="true" pagination="true" rownumbers="true"
    fit="true" url="supplier" toolbar="#tb">
    <thead>
      <tr>
        <th field="cb" checkbox="true">编号</th>
        <th field="id" width="50">编号</th>
        <th field="number" width="100">供应商编码</th>
        <th field="name" width="250">供应商名称</th>
        <th field="linkman" width="250">联系人</th>
        <th field="linkphone" width="250">联系电话</th>
        <th field="note" width="250">备注</th>
  </table>
  <div id=tb>
  <div>
  <div  style="float: left">
    <a href="javascript:addopendialog()" class=easyui-linkbutton iconCls="icon-add" plain="true">添加</a>
    <a href="javascript:modifyopendialog()" class=easyui-linkbutton iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:deleteGrade()" class=easyui-linkbutton iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="exportUser()">导出用户</a>
  </div>
  <div>
  <form  id='condition' name='condition' action='supplier!export' method="post"> 
      <input type="text" id='s_name' name="s_name" >
        <a href="javascript:searchSupplier()" class="easyui-linkbutton" iconCls="icon-search" id="btn" plain="true" >搜索</a>
        </form>
  </div>
     </div>
  <div id="dlg" class="easyui-dialog" style="width:600px;height:280px;padding:10px 20px"
    closed="true" buttons="#dlg-buttons">
     <form id=fm method="post">
       <table>
        <tr>
          <td >供应商编码：
          </td>
           <td><input type="text" name="supplier.number"  id="number" class=easyui-validatebox required="true">
          </td>
          <td>供应商名称：
          </td>
           <td><input type="text" name="supplier.name"  id="name" class=easyui-validatebox required="true">
          </td>
        </tr>
        <tr>
          <td>联系人：
          </td>
           <td><input type="text" name="supplier.linkman"  id="linkman" class=easyui-validatebox required="true">
          </td>
          <td>联系电话：
          </td>
           <td><input type="text" name="supplier.linkphone"  id="linkphone" class=easyui-validatebox required="true">
          </td>
        </tr>
        <tr>
          <td valign="top">备注：
          </td>
           <td colspan="3"><textarea rows="4" cols="45" name="supplier.note" id="note"></textarea>
           </td>
        </tr>
       </table>
     </form>
  </div>
  <div id="dlg-buttons">
		<a href="javascript:saveSupplier()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>