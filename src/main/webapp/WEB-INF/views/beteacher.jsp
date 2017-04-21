<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<title>老师入驻</title>  

</head>  
<body>  
  
  <div style="width:350px; margin:0 auto;border:1px solid #000; padding:25px;">
     <form action="../account/beTeacher" method="post" style="width:100%">
     		  <span style="font-size: 20px;color: red;">老师信息(请确保已注册普通账号)</span><br /><br />
		      <span style="display:inline-block; width:30%;">手机号：</span><input type="text" name="phone" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">姓名：</span><input type="text" name="username" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">图片链接：</span><input type="text" name="imgUrl" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">描述：</span><textarea name="description"></textarea><br /><br />
		      <span style="display:inline-block; width:30%;">标签id：</span><input type="text" name="labelids" value="" placeholder="多个标签请用英文逗号隔开"><br /><br />
		      <span style="display:inline-block; width:30%;">标签名称：</span><input type="text" name="labelnames" value="" placeholder="多个标签请用英文逗号隔开"><br /><br />
                
              <input type="submit" name="submit" value="保存" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <div style="color: red;">${msg}</div>
      </form>
   </div>
</body>  
</html>  