<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html; charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="Keywords" content="注册企业信息页"/>
	<meta name="Description" content="注册企业信息页"/>
	<title>注册企业信息页</title>
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
			position: relative;
		}
		.infomation-panel h2 button{
			position: absolute;
			right: 0;
			z-index: 100;
		}
		.infomation-panel h2 span{
			margin-left: 30px;
			font-size: 13px;
			font-weight: normal;
			color: #CC0000;
			cursor: pointer;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			-o-user-select: none;
		}
		.infomation-panel .items .item{
			margin: 20px 0px;
			position: relative;
			box-sizing: border-box;
			padding-left: 140px;
		}
		.infomation-panel .items .item label{
			position: absolute;
			left: 0;
			font-size: 13px;
			line-height: 35px;
			width: 140px;
			text-align: right;
		}
		.infomation-panel .items .item span{
			display: block;
			width: 100%;
			font-size: 13px;
			line-height: 35px;
			box-sizing: border-box;
			padding: 0px 10px;
			word-wrap:break-word;
		}
		.infomation-panel .items .item span li{
			display: inline-block;
			padding: 4px 20px;
			border-radius: 4px;
			margin-right: 20px;
			margin-bottom: 10px;
			border: 1px solid #C9C9C9;
			background-color: #F2F2F2;
			position: relative;
			line-height: 25px;
		}
		.infomation-panel .items .item span li b{
			width: 10px;
			height: 10px;
			line-height: 10px;
			position: absolute;
			right: 1px;
			top: 4px;
			cursor: pointer;
		}
		.infomation-panel .items .item span li b:hover{
			color: #FF0000;
		}
		.infomation-panel .items .item span input[type="text"]{
			width: 250px;
			max-width: 50%;
			height: 25px;
			line-height: 25px;
			padding: 0px 5px;
			margin-right: 10px;
		}
		.infomation-panel .items .item span textarea{
			width: 60%;
			height: 75px;
			padding: 5px;
			line-height: 25px;
			min-width: 400px;
		}
		.infomation-panel .items .item span textarea{
			width: 60%;
			height: 100px;
			padding: 5px;
			line-height: 25px;
			min-width: 400px;
		}
		.infomation-panel .items .item span select{
			height: 25px;
			font-size: 13px;
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

			eBind(document.getElementById("save-btn"), "click", function(){
				var reg = /^(([\u4e00-\u9fff\(\)（）]{2,50})|([a-z\.\,\(\)（）]{2,50}))$/i;
				if(!reg.test(document.getElementById("corpname").value)){
					alert("请正确输入公司名称!");
					return;
				}

				var contacts = "";
				var contacts2 = "";
				var items = document.getElementById("contacts").getElementsByTagName("div");
				for(var i=0; i<items.length; i++){
					var item = items[i].getElementsByTagName("input");
					contacts += item[2].value+":"+item[0].value+":"+item[1].value+";";
					contacts2 +=  item[2].value+":"+item[0].value+":"+item[1].value+"::::::;";
				}

				//注册企业信息
				$.ajax({
					type : "post",
					async : true,
					url : requestURL+"/mobileaccount/call/insertCorpInfo",
					data: {
						corpname: document.getElementById("corpname").value,
						address: document.getElementById("address").value,
						businesslicensecode: document.getElementById("businesslicensecode").value,
						legalperson: document.getElementById("legalperson").value,
						createTime: document.getElementById("createTime").value,
						keepphone: document.getElementById("keepphone").value,
						registeredcapital: document.getElementById("registeredcapital").value,
						currencytype: document.getElementById("currencytype").value,
						mainproducts: document.getElementById("mainproducts").value,
						mainarea: document.getElementById("mainarea").value,
						businessaddress: document.getElementById("businessaddress").value,
						specialmarket: document.getElementById("specialmarket").value,
						enterpriseType: document.getElementById("enterpriseType").value,
						corplinkman: contacts2,
						logindate: document.getElementById("logindate").value,
						organizationcode: document.getElementById("organizationcode").value,
						areaid: document.getElementById("areaid").value,
						operatingstatus: document.getElementById("operatingstatus").value,
						brandname: document.getElementById("brandname").value,
						personmessage : contacts,
						datagrade: document.getElementById("datagrade").value,
						province: document.getElementById("province").value,
						city: document.getElementById("city").value,
						introduce: document.getElementById("introduce").value
					},
					dataType : "jsonp",
					jsonp: "callback",
					success : function(json, textStatus){
						if(json.code == 200){
							alert("注册企业信息成功!");
							window.location.href = "./search_page.html?wd=" + encodeURIComponent(document.getElementById("corpname").value);
						}else if(json.code == 100){
							alert("【"+document.getElementById("corpname").value+"】企业信息已经存在了!");
						}else{
							alert("注册企业信息失败!");
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown){
						alert("注册企业信息失败。[readyState:"+XMLHttpRequest.readyState+", textStatus:"+textStatus+"]");
					}
				});
			});

			eBind(document.getElementById("contacts-add"), "click", function(){
				var item = document.createElement("div");
				item.className = "item";
	
				var label_1 = document.createElement("label");
				var text_1 = document.createTextNode("电话号码：");
				label_1.appendChild(text_1);
				var span_1 = document.createElement("span");
				var input_1 = document.createElement("input");
				input_1.type = "text";
				span_1.appendChild(input_1);
	
				var label_2 = document.createElement("label");
				var text_2 = document.createTextNode("手机号码：");
				label_2.appendChild(text_2);
				var span_2 = document.createElement("span");
				var input_2 = document.createElement("input");
				input_2.type = "text";
				span_2.appendChild(input_2);
	
				var label_3 = document.createElement("label");
				var text_3 = document.createTextNode("联系人姓名：");
				label_3.appendChild(text_3);
				var span_3 = document.createElement("span");
				var input_3 = document.createElement("input");
				input_3.type = "text";
				span_3.appendChild(input_3);
	
				item.appendChild(label_1);
				item.appendChild(span_1);
				item.appendChild(label_2);
				item.appendChild(span_2);
				item.appendChild(label_3);
				item.appendChild(span_3);
				document.getElementById("contacts").appendChild(item);
			});
	
			eBind(document.getElementById("contacts-del"), "click", function(){
				var divs = document.getElementById("contacts").getElementsByTagName("div");
				if(divs.length <= 0)	return;
	
				var lastDiv = divs[divs.length-1];
				lastDiv.parentNode.removeChild(lastDiv);
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
		<a id="back" class="back" href="./search_page.html">&lt;</a><label for="search">查找企业：</label><input id="search" type="text" placeholder="请输入企业名称" /><button id="search-btn">搜索</button>
	</div>
	<div class="infomation-panel">
		<h2>基本信息<button id="save-btn">保存</button></h2>
		<div id="base-info" class="items">
			<div class="item">
				<label>企业名称：</label><span><input id="corpname" type="text" value="" placeholder="请输入企业名称" /></span>
				<label>企业类型：</label><span>
					<select id="enterpriseType">
						<option value="0">生产型</option>
						<option value="1">贸易型</option>
						<option value="2">服务型</option>
						<option value="3">政府或其他机构</option>
					</select></span>
				<label>省份：</label><span><input id="province" type="text" value="" placeholder="请输入省份" /></span>
				<label>城市：</label><span><input id="city" type="text" value="" placeholder="请输入城市" /></span>
				<label>组织机构代码证：</label><span><input id="organizationcode" type="text" value="" placeholder="请输入组织机构代码证编号" /></span>
				<label>品牌名称：</label><span><input id="brandname" type="text" value="" placeholder="请输入品牌名称" /></span>
			</div>
		</div>
		<h2>联系方式<span id="contacts-add">+新增</span><span id="contacts-del">-删除</span></h2>
		<div id="contacts" class="items"></div>
		<h2>工商备案</h2>
		<div id="registered-info" class="items">
			<div class="item">
				<label>法人姓名：</label><span><input id="legalperson" type="text" value="" placeholder="请输入法人姓名" /></span>
				<label>注册资本：</label><span><input id="registeredcapital" type="text" value="" placeholder="请输入注册资本" /></span>
				<label>资金币种：</label><span><input id="currencytype" type="text" value="" placeholder="请输入资金币种" /></span>
				<label>成立日期：</label><span><input id="createTime" type="text" value="" placeholder="成立日期(格式：YYYY/MM/DD)" /></span>
				<label>注册时间：</label><span><input id="logindate" type="text" value="" placeholder="注册时间(格式：YYYY/MM/DD HH24:mi:ss)" /></span>
				<label>营业执照编号：</label><span><input id="businesslicensecode" type="text" value="" placeholder="请输入营业执照编号" /></span>
				<label>注册地址：</label><span><textarea id="address" placeholder="请输入注册地址"></textarea></span>
				<label>工商留存电话：</label><span><input id="keepphone" type="text" value="" placeholder="请输入工商留存电话" /></span>
			</div>
		</div>
		<h2>经营信息</h2>
		<div class="items">
			<div class="item">
				<label>主营商品：</label>
				<span><textarea id="mainproducts" placeholder="请输入主营商品"></textarea></span>
			</div>
			<div class="item">
				<label>主营行业：</label>
				<span><input id="mainarea" type="text" value="" placeholder="请输入主营行业" /></span>
			</div>
			<div class="item">
				<label>经营地址：</label>
				<span><input id="businessaddress" type="text" value="" placeholder="请输入经营地址" /></span>
			</div>
			<div class="item">
				<label>专业市场：</label>
				<span><input id="specialmarket" type="text" value="" placeholder="请输入专业市场" /></span>
			</div>
			<div class="item">
				<label>经营范围：</label>
				<span><input id="areaid" type="text" value="" placeholder="请输入经营范围" /></span>
			</div>
			<div class="item">
				<label>经营状态：</label>
				<span><input id="operatingstatus" type="text" value="" placeholder="请输入经营状态" /></span>
			</div>
			<div class="item">
				<label>公司简介：</label>
				<span><textarea id="introduce" placeholder="请输入公司简介"></textarea></span>
			</div>
		</div>
		<h2>质量级别</h2>
		<div class="items">
			<div class="item">
				<label>质量级别：</label>
				<span>
					<select id="datagrade">
						<option value="001">有工商，有电话</option>
						<option value="002">有工商，无电话</option>
						<option value="003">有电话，无工商</option>
					</select>
				</span>
			</div>
		</div>
	</div>
</body>
</html>