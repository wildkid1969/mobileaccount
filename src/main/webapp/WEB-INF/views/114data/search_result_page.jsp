<!DOCTYPE html>
<html>
<head>
	<%@ page contentType="text/html; charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="Keywords" content="搜索结果页"/>
	<meta name="Description" content="搜索结果页"/>
	<title>搜索结果页</title>
	<link rel="styleSheet" type="text/css" href="http://testwx.mdata.hc360.com/114cms/css/css.css" >
	<script type="text/javascript" src="http://testwx.mdata.hc360.com/114cms/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="http://testwx.mdata.hc360.com/114cms/js/common.js"></script>
	<style type="text/css">
		.search-panel{
			position: relative;
			height: 40px;
			padding: 20px 0px;
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
		.back{
			display: inline-block;
			width: 40px;
			height: 40px;
			vertical-align: middle;
			font-size: 35px;
			font-weight: bolder;
		}
		h3{
			font-size: 13px;
			color: #A1A1A1;
			margin: 30px 0px;
		}
		.search-result-list a{
			display: block;
			font-size: 18px;
			margin: 14px 0px;
		}
		.search-result-list a:hover{
			font-weight: bold;
		}
		.search-result-list button{
			float: right;
			height: 30px;
		}
		.search-result-list img{
			margin-left: 30%;
		}
		.pagination-panel{
			text-align: right;
			margin-top: 30px;
		}
		.pagination-panel a{
			padding: 10px;
			border: 1px solid #C9C9C9;
			margin: 0px 3px;
		}
		.pagination-panel span{
			color: #AEAEAE;
			font-weight: bold;
			padding: 0px 10px;
		}
		.selected{
			border: 1px solid #FF6600!important;
			background-color: #FEEDE2;
		}
	</style>
	<script type="text/javascript">
		// 正式环境
		var requestURL = "http://168.mobile.hc360.com";
		// 测试环境
		//var requestURL = "http://testwx.mdata.hc360.com";
		// 临时环境
		//var requestURL = "http://localhost:8080";

		function load(){
			var pageSize = 12;
			var word = getParameters()["wd"];
			var corpid = getParameters()["corpid"];
			var pageNum = getParameters()["pageNum"];
			pageNum = pageNum==null ? 1 : parseInt(pageNum);
			if(word){
				document.getElementById("search").value = word;
				document.getElementById("back").href = "./search_page.html?wd=" + encodeURIComponent(word);
			}else{
				alert("请正确访问。");
				window.location.href = "./search_page.html";
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
					if(searchRecord != null){
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

			loadCorpList(word, pageSize, pageNum);
		}

		function loadCorpList(wd, ps, pn){
			// 获取企业信息一览
			$.ajax({
				type : "get",
				async : true,
				url : requestURL+"/mobileaccount/call/corpinfodata",
				data: {
					corpname : wd,
					pagesize : ps,
					pageNow : pn
				},
				dataType : "jsonp",
				jsonp: "callback",
				beforeSend: function(XMLHttpRequest){
					document.getElementById("search-result-list").innerHTML = "<img src=\"http://testwx.mdata.hc360.com/114cms/image/loading.gif\" alt=\"Loading\" />";
				},
				success : function(json, textStatus){
					document.getElementById("search-result-list").innerHTML = "";
					document.getElementById("count").innerHTML = json.allNum;
					for(var i=0; i<json.corpInfoList.length; i++){
						var sty = "";
						if(json.corpInfoList[i].corpid == getParameters()["corpid"]){
							sty = "style=\"font-weight: bold;\"";
						}
						document.getElementById("search-result-list").innerHTML += "<a "+sty+" corpid=\""+json.corpInfoList[i].corpid+"\" href=\"./info_read.html?wd="
																					+encodeURIComponent(wd)+"&corpid="
																					+json.corpInfoList[i].corpid+"&pageNum="
																					+pn+"\">"+json.corpInfoList[i].corpname+"<button onclick=\"deleteCorpInfo("+json.corpInfoList[i].corpid+",'"+json.corpInfoList[i].corpname+"', this);\">删除</button></a>";
					}

					pageRefresh(wd, json.allNum, ps, pn);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert("获取企业信息一览失败。[readyState:"+XMLHttpRequest.readyState+", textStatus:"+textStatus+"]");
				}
			});
		}

		function deleteCorpInfo(corpid, corpName, o){
			var e = window.event || arguments[0];
			e.preventDefault();
			if(corpid == undefined || corpid == "")	return;

			if(!confirm("确定要删除【"+corpName+"】吗？")){
				return;
			}

			// 删除企业信息
			$.ajax({
				type : "post",
				async : true,
				url : requestURL+"/mobileaccount/call/deleteCorpInfo",
				data: {
					corpid : corpid
				},
				dataType : "jsonp",
				jsonp: "callback",
				success : function(json, textStatus){
					o.parentNode.parentNode.removeChild(o.parentNode);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert("删除企业信息失败。[readyState:"+XMLHttpRequest.readyState+", textStatus:"+textStatus+"]");
				}
			});
		}

		function pageRefresh(wd, pc, ps, pn){
			if(pc <= 0 || ps == 0){
				document.getElementById("pagination-panel").style.display = "none";
				return ;
			}else if(document.getElementById("pagination-panel").style.display == "none"){
				document.getElementById("pagination-panel").style.display = "block";
			}

			var pageCount = Math.ceil(pc/ps);
			var pageContent = "";
			var startIndex = 1;
			var endIndex = 1;
			var select = "";
			var prevContent = "";
			var nextContent = "";
			if(pageCount <= 3){
				prevContent = "";
				nextContent = "";
				startIndex = 1;
				endIndex = pageCount;
			}else if(pn+2 >= pageCount){
				startIndex = (pn+2)==pageCount ? (pageCount-3) : (pageCount-2);
				endIndex = (pn+2)==pageCount ? (pageCount-1) : pageCount;

				if(startIndex != 1){
					prevContent = "<span>...</span>";
				}else{
					prevContent = "";
				}

				if(endIndex != pageCount){
					nextContent = "<span>...</span>";
				}else{
					nextContent = "";
				}
			}else{
				if(pn == 1){
					startIndex = pn;
					endIndex = pn+2;
					prevContent = "";
				}else if(pn == 2){
					startIndex = pn-1;
					endIndex = pn+1;
					prevContent = "";
				}else{
					startIndex = pn-1;
					endIndex = pn+1;
					prevContent = "<span>...</span>";
				}
				nextContent += "<span>...</span>";
			}

			if(pn != 1){
				prevContent = "<a href=\"javascript:;\" onclick=\"loadCorpList('"+wd+"',"+ps+","+(pn-1)+");\">上一页</a>"+prevContent;
			}

			if(pn != pageCount){
				nextContent = nextContent+"<a href=\"javascript:;\" onclick=\"loadCorpList('"+wd+"',"+ps+","+(pn+1)+");\">下一页</a>";
			}

			pageContent += prevContent;
			for(var i=startIndex; i<=endIndex; i++){
				if(i == pn){
					select = "class=\"selected\"";
				}else{
					select = "";
				}
				pageContent += "<a href=\"javascript:;\" "+select+" onclick=\"loadCorpList('"+wd+"',"+ps+","+i+");\">"+i+"</a>";
			}
			pageContent += nextContent;
			document.getElementById("pagination-panel").innerHTML = pageContent;
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
		<a class="back" id="back" href="#">&lt;</a><label for="search">查找企业：</label><input id="search" type="text" placeholder="请输入企业名称" /><button id="search-btn">搜索</button>
	</div>
	<div class="search-result-panel">
		<h3>找到相关结果约<span id="count">0</span>个</h3>
		<div id="search-result-list" class="search-result-list"></div>
		<div id="pagination-panel" class="pagination-panel"></div>
	</div>
</body>
</html>