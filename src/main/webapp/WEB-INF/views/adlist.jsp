<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<title>广告位编辑</title>  

</head>  
<body>  
 <input type="button" value="插入" onclick="window.location.href='${ctx}/saleadmin/toInsertAd'" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
  <c:forEach var="ad" items="${adList}">
	  <div style="width:350px; margin:0 auto;border:1px solid #000; padding:25px;">
	     <form id="form1" action="" method="post" style="width:100%">
    		  	  <span style="font-size: 20px;color: red;">广告信息</span><br /><br />
		      <span style="display:inline-block; width:30%;">标题：</span><input type="text" name="name" value="${ad.name}"/><br /><br />
		      <span style="display:inline-block; width:30%;">描述：</span><input type="text" name="description" value="${ad.description}"/><br /><br />
		      <span style="display:inline-block; width:30%;">图片链接：</span><input type="text" name="imgUrl" value="${ad.imgUrl}"/><br /><br />
		      <span style="display:inline-block; width:30%;">跳转链接：</span><input type="text" name="goUrl" value="${ad.goUrl}"/><br /><br />
		      <span style="display:inline-block; width:30%;">对象id：</span><input type="text" name="objectid" value="${ad.objectid}" placeholder="对象id 根据type判断"><br /><br />
		      <span style="display:inline-block; width:30%;">类型：</span><input type="text" name="type" value="${ad.type}" placeholder="广告类型 1课程 2老师 3 活动列表"><br /><br />
		      <span style="display:inline-block; width:30%;">状态：</span><input type="text" name="state" value="${ad.state}" placeholder="0下线 1上线"><br /><br />
		      <span style="display:inline-block; width:30%;">排序：</span><input type="text" name="sort" value="${ad.sort}" placeholder="越小越靠前"><br /><br />
		      <input type="button" value="编辑" onclick="window.location.href='${ctx}/saleadmin/editAd?id=${ad.id}'" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <input type="button" value="删除" onclick="window.location.href='${ctx}/saleadmin/deleteAd2?id=${ad.id}'" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
	      </form>
	   </div>
   </c:forEach>
</body>  
</html>  