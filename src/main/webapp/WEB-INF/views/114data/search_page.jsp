<!DOCTYPE html>
<html>
<head>
	<%@ page contentType="text/html; charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="Keywords" content="搜索页"/>
	<meta name="Description" content="搜索页"/>
	<title>搜索页</title>
	<link rel="styleSheet" type="text/css" href="http://testwx.mdata.hc360.com/114cms/css/css.css" >
	<script type="text/javascript" src="http://testwx.mdata.hc360.com/114cms/js/common.js"></script>
	<style type="text/css">
		.search-panel{
			position: relative;
			height: 40px;
			padding: 10px 0px 20px 0px;
			white-space: nowrap;
		}
		label{
			font-size: 16px;
			line-height: 40px;
			width: 80px;
		}
		#search{
			height: 40px;
			width: 60%;
			vertical-align: middle;
			min-width: 200px;
			box-sizing: border-box;
			padding: 0px 10px;
			line-height: 40px;
		}
		h2{
			font-size: 16px;
			color: #A1A1A1;
			margin: 30px 0px;
		}
		.search-history{
			max-height: 470px;
			overflow: hidden;
		}
		.search-history a{
			display: inline-block;
			padding: 10px 30px;
			margin-right: 20px;
			margin-bottom: 10px;
			border: 1px solid #C9C9C9;
		}
		.register-corp{
			text-align: center;
		}
		.register-corp a{
			display: inline-block;
			*display: inline;
			*zoom: 1;
			height: 40px;
			line-height: 40px;
			background-color: rgb(239, 131, 43);
			color: #FFFFFF;
			padding: 10px 30px;
			border-radius: 10px;
			margin: 0 auto;
			margin-top: 50px;
			text-align: center;
			font-size: 20px;
			font-weight: bold;
			white-space: nowrap;
		}
	</style>
	<script type="text/javascript">
		function load(){
			var words = getParameters()["wd"];
			if(words){
				document.getElementById("search").value = words;
			}

			eBind(document.getElementById("search-btn"), "click", function(){
				var reg = /^(([\u4e00-\u9fff\(\)（）]{2,50})|([a-z\.\,\(\)（）]{2,50}))$/i;
				var value = document.getElementById("search").value;
				if(!reg.test(value)){
					document.getElementById("msg").style.display = "block";
					document.getElementById("msg").innerHTML = "请正确输入企业名称。";
				}else{
					document.getElementById("msg").style.display = "none";
					document.getElementById("msg").innerHTML = "";

					// 本地存储最近的搜索
					var arr = [];
					var searchRecord = getKey("search-record");
					if(searchRecord != null && searchRecord != ""){
						arr = searchRecord.split(",");
					}
					var i = 0;
					for( ; i<arr.length; i++){
						if(arr[i] == value){
							break;
						}
					}
					if(i >= arr.length){
						arr.unshift(value);
						setKey("search-record", arr.join(","));
					}

					window.location.href = "./search_result_page.html?wd=" + encodeURIComponent(value);
				}
			});

			var searchHistorys = getKey("search-record");
			if(searchHistorys != null && searchHistorys != ""){
				var searchHistory = searchHistorys.split(",");
				for(var i=0; i<searchHistory.length; i++){
					document.getElementById("searchHistory").innerHTML += "<a href=\"./search_result_page.html?wd="+encodeURIComponent(searchHistory[i])+"\">"+searchHistory[i]+"</a>";
				}
			}
		}

		eBind(window, "load", load);
	</script>
</head>
<body>
	<div class="login-panel">
		<a href="#">退出登陆</a>
	</div>
	<div id="msg" class="msg"></div>
	<div class="search-panel">
		<label for="search">查找企业：</label><input id="search" type="text" maxlength="50" placeholder="请输入企业名称" /><button id="search-btn">搜索</button>
	</div>
	<div class="history-panel">
		<h2>最近搜索</h2>
		<div id="searchHistory" class="search-history"></div>
	</div>
	<div class="register-corp">
		<a href="./info_insert.html">我要注册公司</a>
	</div>
</body>
</html>