<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<title>编辑营销对练课程</title>  

</head>  
<body>  
  <div style="width:350px; margin:0 auto;border:1px solid #000; padding:25px;">
     <form action="../userExercise/saveCourse" method="post" style="width:100%">
     		  <span style="font-size: 20px;color: red;">对练课程信息</span><br /><br />
		      <span style="display:inline-block; width:30%;">id：</span><input type="text" name="id" value="" placeholder="插入新课程此项不填"/><br /><br />
		      <span style="display:inline-block; width:30%;">名称：</span><input type="text" name="name" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">描述：</span><input type="text" name="description" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">销售对练步骤：</span><textarea name="steps" ></textarea><br /><br />
		      <span style="display:inline-block; width:30%;">客户对练步骤：</span><textarea name="partnerSteps"></textarea></<br /><br />
		      <span style="display:inline-block; width:30%;">图片链接：</span><input type="text" name="coverUrl" /><br /><br />
		      <span style="display:inline-block; width:30%;">评价：</span><textarea name="evaluate"></textarea><br /><br />
		      <span style="display:inline-block; width:30%;">得分：</span><input type="text" name="score" value="" ><br /><br />
		      <span style="display:inline-block; width:30%;">标签id：</span><input type="text" name="labelids" value="" placeholder="多个标签请用英文逗号隔开"><br /><br />
		      <span style="display:inline-block; width:30%;">标签名称：</span><input type="text" name="labelnames" value="" placeholder="多个标签请用英文逗号隔开"><br /><br />
		      <span style="display:inline-block; width:30%;">启用：</span><input type="text" name="enable" value="" ><br /><br />
                
              <input type="submit" name="submit" value="保存" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <div style="color: red;">${msg}</div>
      </form>
   </div>
</body>  
</html>  