<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<title>添加章节</title>  

</head>  
<body>  
  <div style="width:350px; margin:0 auto;border:1px solid #000; padding:25px;">
     <form action="../saleadmin/addChapter" method="post" style="width:100%">
     		  <span style="font-size: 20px;color: red;">章节信息</span><br /><br />
		      <span style="display:inline-block; width:30%;">id：</span><input type="text" name="id" value="" placeholder="插入新课程此项不填"/><br /><br />
		      <span style="display:inline-block; width:30%;">名称：</span><input type="text" name="name" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">描述：</span><input type="text" name="description" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">课程id：</span><input type="text" name="courseid" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">图片链接：</span><input type="text" name="coverUrl" /><br /><br />
		      <span style="display:inline-block; width:30%;">标签id：</span><input type="text" name="labelids" value="" placeholder="多个标签请用英文逗号隔开"><br /><br />
		      <span style="display:inline-block; width:30%;">标签名称：</span><input type="text" name="labelnames" value="" placeholder="多个标签请用英文逗号隔开"><br /><br />
		      <span style="display:inline-block; width:30%;">类型：</span><input type="text" name="type" value="" placeholder="1视频、2录音、3图片" ><br /><br />
		      <span style="display:inline-block; width:30%;">启用：</span><input type="text" name="enable" value="" ><br /><br />
		      
		      <span style="font-size: 20px;color: red;">音频/图片信息</span><br /><br />
		      <span style="display:inline-block; width:30%;">视频名称：</span><input type="text" name="vname" value=""/><br /><br />
		      <span style="display:inline-block; width:30%;">视频时长：</span><input type="text" name="vtimeLength" /><br /><br />
		      <span style="display:inline-block; width:30%;">视频图片：</span><input type="text" name="vimgUrl"/><br /><br />
		      <span style="display:inline-block; width:30%;">视频秘钥：</span><input type="text" name="sdUrl" /><br /><br />
		      <span style="display:inline-block; width:30%;">视频来源：</span><input type="text" name="videofrom" /><br /><br />
		      
		      
		      <span style="font-size: 20px;color: red;">音频/图片信息</span><br /><br />
		      <span style="display:inline-block; width:30%;">资源链接：</span><input type="text" name="url" value="" ><br /><br />
		      <span style="display:inline-block; width:30%;">时长：</span><input type="text" name="timeLength" value="" ><br /><br />
                
              <input type="submit" name="submit" value="保存" style="display:inline-block; width:80%; height:25px; margin-left:10%;"><br /><br />
              <div style="color: red;">${msg}</div>
      </form>
   </div>
</body>  
</html>  