<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<title>upload</title>  

<script type="text/javascript">
//获取文件名
var file = $("#file").val();
var fileName = getFileName(file);
 
function getFileName(o){
    var pos=o.lastIndexOf("\\");
    return o.substring(pos+1);  
}
  
</script>
</head>  
<body>  
  
     <form action="../fileUpload/uploadAudio" enctype="multipart/form-data" method="post">
            <fieldset>
                <input type="file" name="uploadFile">
                <input type="hidden" name="type" value="1">
                <input type="submit" name="submit" value="上传音频">
            </fieldset>
     </form>
     
     
     <form action="../fileUpload/uploadLeVideo" enctype="multipart/form-data" method="post">
            <fieldset>
                <input type="file" name="video_file">
                <input type="submit" name="submit" value="上传乐视视频">
            </fieldset>
     </form>
</body>  
</html>  