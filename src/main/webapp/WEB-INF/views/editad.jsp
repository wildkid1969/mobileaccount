<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<title>广告位编辑</title>  

<script type="text/javascript">
	function edit(){
		console.log(1);
		$("#formType").val(1);
		$("#form1").attr("action","../saleadmin/saveAd");
		$("#form1").submit();
	}
	function save(){
		console.log(2);
		$("#formType").val(2);
		$("#form1").attr("action","../saleadmin/insertAd");
		$("#form1").submit();
	}
	function deleteAd(){
		console.log(2);
		$("#formType").val(2);
		$("#form1").attr("action","../saleadmin/deleteAd");
		$("#form1").submit();
	}
</script>

</head>  
<body>  
  
  <div style="width:350px; margin:0 auto;border:1px solid #000; padding:25px;">
     <form id="form1" action="../admin/editAd" method="post" style="width:100%">
     		  <input id="formType" type="hidden" name="formType" value="">
     		  <input id="id" type="hidden" name="id" value="${ad.id}">
     		  <span style="font-size: 20px;color: red;">广告信息</span><br /><br />
		      <span style="display:inline-block; width:30%;">标题：</span><input type="text" name="name" value="${ad.name}"/><br /><br />
		      <span style="display:inline-block; width:30%;">描述：</span><input type="text" name="description" value="${ad.description}"/><br /><br />
		      <span style="display:inline-block; width:30%;">图片链接：</span><input type="text" name="imgUrl" value="${ad.imgUrl}"/><br /><br />
		      <span style="display:inline-block; width:30%;">跳转链接：</span><input type="text" name="goUrl" value="${ad.goUrl}"/><br /><br />
		      <span style="display:inline-block; width:30%;">对象id：</span><input type="text" name="objectid" value="${ad.objectid}" placeholder="对象id 根据type判断"><br /><br />
		      <span style="display:inline-block; width:30%;">类型：</span><input type="text" name="type" value="${ad.type}" placeholder="广告类型 1课程 2老师 3 活动列表"><br /><br />
		      <span style="display:inline-block; width:30%;">状态：</span><input type="text" name="state" value="${ad.state}" placeholder="0下线 1上线"><br /><br />
		      <span style="display:inline-block; width:30%;">排序：</span><input type="text" name="sort" value="${ad.sort}" placeholder="越小越靠前"><br /><br />
                
              <input type="button" value="更新" onclick="edit();" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <input type="button" value="插入" onclick="save();" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <input type="button" value="删除" onclick="deleteAd();" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <div style="color: red;">${msg}</div>
      </form>
   </div>
</body>  
</html>  