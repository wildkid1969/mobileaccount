<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<title>编辑版本信息</title>  

</head>  
<body>  
  <div style="width:350px; margin:0 auto;border:1px solid #000; padding:25px;">
     <form action="../appVersion/save" method="post" style="width:100%">
     		  <span style="font-size: 20px;color: red;">课程信息</span><br /><br />
		      <span style="display:inline-block; width:30%;">id：</span><input type="text" name="versionid" value="${version.versionid}" placeholder="插入新版本此项不填"/><br /><br />
		      <span style="display:inline-block; width:30%;">版本号：</span><input type="text" name="versionCode" value="${version.versionCode}"/><br /><br />
		      <span style="display:inline-block; width:30%;">版本名称：</span><input type="text" name="versionName" value="${version.versionName}"/><br /><br />
		      <span style="display:inline-block; width:30%;">描述：</span><textarea name="description">${version.description}</textarea><br /><br />
		      <span style="display:inline-block; width:30%;">文件大小：</span><input type="text" name="fileSize" value="${version.fileSize}" placeholder="单位kb"/><br /><br />
		      <span style="display:inline-block; width:30%;">文件加密串：</span><input type="text" name="fileEncrypt" value="${version.fileEncrypt}" /><br /><br />
		      <span style="display:inline-block; width:30%;">强制更新：</span><input type="text" name="force" value="${version.force}" placeholder="0否 1是"/><br /><br />
		      <span style="display:inline-block; width:30%;">下载链接：</span><input type="text" name="downUrl" value="${version.downUrl}" /><br /><br />
		      <span style="display:inline-block; width:30%;">启用：</span><input type="text" name="state" value="${version.state}" placeholder="0禁用 1启用"><br /><br />
              
              <input type="submit" name="submit" value="保存" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <div style="color: red;">${msg}</div>
      </form>
   </div>
</body>  
</html>  