<!DOCTYPE html>
<html>
<head>
	<%@ page contentType="text/html; charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="Keywords" content="企业信息页"/>
	<meta name="Description" content="企业信息页"/>
	<title>企业信息页</title>
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
		.infomation-panel{
			position: relative;
			margin-top: 20px;
		}
		.infomation-panel span{
			font-size: 18px;
		}
		.infomation-panel button{
			position: absolute;
			right: 0;
			top: 0;
			background-color: #FF9900;
			border: 1px solid #797979;
			border-radius: 3px;
			color: #FFFFFF;
			font-size: 16px;
		}
		.infomation-panel h2{
			font-size: 16px;
			line-height: 25px;
			color: #333333;
			background-color: #F3F8FE;
			margin: 15px 0px;
		}
		.infomation-panel h2:first-of-type{
			margin-top: 30px;
		}
		.infomation-panel .items .item{
			margin: 20px 0px;
			position: relative;
			box-sizing: border-box;
			padding-left: 120px;
		}
		.infomation-panel .items .item label{
			position: absolute;
			left: 0;
			font-size: 13px;
			line-height: 25px;
			width: 120px;
			text-align: right;
		}
		.infomation-panel .items .item span{
			display: block;
			width: 100%;
			height: 25px;
			font-size: 13px;
			line-height: 25px;
			box-sizing: border-box;
			padding: 0px 10px;
			word-wrap:break-word;
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
			var word = getParameters()["wd"];
			var corpid = getParameters()["corpid"];
			var pageNum = getParameters()["pageNum"];
			if(word && corpid){
				document.getElementById("search").value = word;
				document.getElementById("back").href = "./search_result_page.html?wd=" + encodeURIComponent(word) + "&corpid=" + corpid+ "&pageNum=" + pageNum;
			}else if(word && !corpid){
				alert("请正确访问。");
				window.location.href = "./search_result_page.html?wd=" + encodeURIComponent(word);
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

			// 获取企业信息
			$.ajax({
				type : "get",
				async : true,
				url : requestURL+"/mobileaccount/call/corpinfobyid",
				data: {
					corpid : corpid
				},
				dataType : "jsonp",
				jsonp: "callback",
				success : function(json, textStatus){
					if(json.corpid != corpid)	return ;

					document.getElementById("corpname").innerHTML = json.corpname;

					var enterpriseType = "";
					switch(json.enterpriseType){
						case 0:
							enterpriseType = "生产型";
							break;
						case 1:
							enterpriseType = "贸易型";
							break;
						case 2:
							enterpriseType = "服务型";
							break;
						case 3:
							enterpriseType = "政府或其他机构";
							break;
						default:
							break;
					}
					document.getElementById("base-info").innerHTML = "<div class=\"item\"><label>企业类型：</label><span>"+enterpriseType+"</span><label>省份：</label><span>"+json.province+"</span><label>城市：</label><span>"
																									+json.city+"</span><label>组织机构代码证：</label><span>"+json.organizationcode+"</span><label>品牌名称：</label><span>"+json.brandname+"</span></div>";

					var connects = json.personmessage.split(";");
					for(var i=0; i<connects.length-1; i++){
						var connect = connects[i].split(":");
						document.getElementById("contacts").innerHTML += "<div class=\"item\"><label>电话号码：</label><span>"+connect[1]+"</span><label>手机号码：</label><span>"+connect[2]+"</span><label>联系人姓名：</label><span>"+connect[0]+"</span></div>";
					}

					document.getElementById("registered-info").innerHTML = "<div class=\"item\"><label>法人姓名：</label><span>"+json.legalperson+"</span><label>注册资本：</label><span>"
																												+json.registeredcapital+" <b>"+json.currencytype+"</b></span><label>成立日期：</label><span>"
																												+json.createTime+"</span><label>注册时间：</label><span>"+json.logindate+"</span><label>营业执照编号：</label><span>"
																												+json.businesslicensecode+"</span><label>注册地址：</label><span>"+json.address+"</span><label>工商留存电话：</label><span>"
																												+json.keepphone+"</span></div>";

					document.getElementById("business-info").innerHTML = "<div class=\"item\"><label>法人姓名：</label><span>"+ json.mainproducts+"</span><label>主营行业：</label><span>"
																												+json.mainarea+"</span><label>经营地址：</label><span>"+json.businessaddress+"</span><label>专业市场：</label><span>"
																												+json.specialmarket+"</span><label>经营范围：</label><span>"+json.areaid+"</span><label>经营状态：</label><span>"
																												+json.operatingstatus+"</span><label>公司简介：</label><span>"+json.introduce+"</span><div>";
					if(json.datagrade == "001"){
						document.getElementById("datagrade").innerHTML ="有工商，有电话。";
					}else if(json.datagrade == "002"){
						document.getElementById("datagrade").innerHTML ="有工商，无电话。";
					}else{
						document.getElementById("datagrade").innerHTML ="有电话，无工商。";
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					alert("获取企业信息一览失败。[readyState:"+XMLHttpRequest.readyState+", textStatus:"+textStatus+"]");
				}
			});

			eBind(document.getElementById("edit-btn"), "click", function(){
				window.location.href = "./info_update.html?wd=" + encodeURIComponent(word) + "&corpid=" + corpid + "&pageNum=" + pageNum;
			});
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
		<a id="back" class="back" href="#">&lt;</a><label for="search">查找企业：</label><input id="search" type="text" placeholder="请输入企业名称" /><button id="search-btn">搜索</button>
	</div>
	<div class="infomation-panel">
		<span id="corpname"></span><button id="edit-btn">编辑</button>
		<h2>基本信息</h2>
		<div id="base-info" class="items"></div>
		<h2>联系方式</h2>
		<div id="contacts" class="items"></div>
		<h2>工商备案</h2>
		<div id="registered-info" class="items"></div>
		<h2>经营信息</h2>
		<div id="business-info" class="items"></div>
		<h2>质量级别</h2>
		<div class="items">
			<div class="item">
				<label>质量级别：</label>
				<span id="datagrade"></span>
			</div>
		</div>
	</div>
</body>
</html>