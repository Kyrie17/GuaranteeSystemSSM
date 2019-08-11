window.onload = function() {
	$.ajaxSettings.async = false;
	var url = "http://localhost:8080/commidity/personCommidity";
	var com={
		"cookie":getCookie("cookie")
	};
	var deleteChart=document.getElementById("delete");
	$.post(url,com,function(data){
		var str = "";
		for(var k in data){
			str += "<tr><th id='"+data[k].commidityName+"' class='buy_Name'>" + data[k].commidityName + "</th>"
			+ "<th id='"+data[k].commidityName+"name'>" + data[k].quantity + "</th>"
			+ "<th id='"+data[k].commidityName+"description'>" + data[k].quantity*data[k].commidityPrice + "</th>"
			+ "<th><button id='" + data[k].commidityId + "' onclick=deleteCommidity('"+data[k].commidityName+"') >删除</button></th>"
			+  "</th></tr>";
		}
		$("#commidity").html(str);
		var url2 = "http://localhost:8080/commidity/deleteChart";
		deleteChart.onclick=function(){
			$.post(url2,com,function(data){
				if(data==1){
					swal("提示", "删除成功", "success");
					var url = "http://localhost:8080/commidity/personCommidity";
					$.post(url,com,function(data){
						var str = "";
						for(var k in data){
                            str += "<tr><th id='"+data[k].commidityName+"' class='buy_Name'>" + data[k].commidityName + "</th>"
                                + "<th id='"+data[k].commidityName+"name'>" + data[k].quantity + "</th>"
                                + "<th id='"+data[k].commidityName+"description'>" + data[k].quantity*data[k].commidityPrice + "</th>"
                                + "<th><button id='" + data[k].commidityId + "' onclick=deleteCommidity('"+data[k].commidityName+"') >删除</button></th>"
                                +  "</th></tr>";
						}
						$("#commidity").html(str);
					});
				}else{
					swal("提示", "删除失败", "warning");
				}
			});
		}
	});
	
}			
function getCookie(name) {
	var strcookie = document.cookie;//获取cookie字符串
	var arrcookie = strcookie.split("; ");//分割
	//遍历匹配
	for (var i = 0; i < arrcookie.length; i++) {
		var arr = arrcookie[i].split("=");
		if (arr[0] == name) {
			return arr[1];
		}
	}
	return "";
}
function Setcookie (name, value){ 
    //设置名称为name,值为value的Cookie
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间单位毫秒
    document.cookie = name+"="+value+";expires="+expdate.toGMTString()+";path=/";
 
   //即document.cookie= name+"="+value+";path=/";  时间默认为当前会话可以不要，但路径要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！
}
function deleteCommidity(name) {
	var commidity_name=name;
	var url = "http://localhost:8080/commidity/deleteCommidity";
	var args={
			"commidityName":commidity_name,
			"cookie":getCookie("cookie")
	}
	var com={
        "cookie":getCookie("cookie")
	}
	$.post(url,args,function(data){
		if(data==1){
			swal("提示", "删除成功", "success");
			var url = "http://localhost:8080/commidity/personCommidity";
			$.post(url,com,function(data){
				var str = "";
				for(var k in data){
                    str += "<tr><th id='"+data[k].commidityName+"' class='buy_Name'>" + data[k].commidityName + "</th>"
                        + "<th id='"+data[k].commidityName+"name'>" + data[k].quantity + "</th>"
                        + "<th id='"+data[k].commidityName+"description'>" + data[k].quantity*data[k].commidityPrice + "</th>"
                        + "<th><button id='" + data[k].commidityId + "' onclick=deleteCommidity('"+data[k].commidityName+"') >删除</button></th>"
                        +  "</th></tr>";
				}
				$("#commidity").html(str);
			});
		}else{
			swal("提示", "删除失败", "warning");
		}
});
}

