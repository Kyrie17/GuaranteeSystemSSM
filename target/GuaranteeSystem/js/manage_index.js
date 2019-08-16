

$(document).ready(function () {  

	var url = "http://localhost:8080/repairMan/getAllRepairMan";
	var args = {"time":new Date()}
	$.getJSON(url, args, function(data){
		var str = "";
		
		for(var k in data){
			var major = "";
			//将数据库中保存的工种参数，转换为文字
			switch(data[k].major)
			{
			case 1:
			  major = "水工";
			  break;
			case 2:
			  major = "木工";
			  break;
			case 3:
			  major = "电工";
			  break;
			case 4:
			  major = "其他";
			  break;
			}
			
			str += "<li><img src='/imgs/repair_per.jpg'><p><span id='name' class='name'>" + data[k].username + "</span><span id='type' class='type'>工种：" + major + "</span><span id='phone' class='phone'>" + data[k].phone + "</span></p></li>";
		}
		$("#content").html(str);
	});
	
});