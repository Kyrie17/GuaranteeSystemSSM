window.onload = function() {
	var url = "http://localhost:8080/ShowNoticeServlet";
	$.getJSON(url,function(data){
		var str = "";
		for(var k in data.data){
			str += "<tr><th>" + data.data[k].fileName + "</th>"
			+ "<th>" + data.data[k].upLoadDate_String + "</th>"
			+ "<th>" + data.data[k].downLoadNum + "</th>"
			+ "<td> <a href='/LoadFileServlet?fileName="+data.data[k].fileName+"'><img src='/imgs/1.jpg' width='20' height='20'></a>"
			+  "</th></tr>";
		}
		$("#repair_infor").html(str);
	});
}