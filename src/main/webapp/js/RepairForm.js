
	
	// 第一页
	function firstPage(){
		document.getElementById("currentPage").innerHTML = "1";
		Filter();
	}
	
	// 下一页
	function nextPage(){
		var currentPage = document.getElementById("currentPage").innerHTML;
		var totalPage = document.getElementById("totalPage").innerHTML;
        if(parseInt(currentPage) < parseInt(totalPage)){
			var currentPage = parseInt(document.getElementById("currentPage").innerHTML) + 1;
			document.getElementById("currentPage").innerHTML = "" + currentPage;
			Filter();
		}else{
			swal("提示", "已经是最后一页了哦!", "warning");
		}
	}
	
	// 上一页
	function previousPage(){
		var currentPage = document.getElementById("currentPage").innerHTML;
		if(currentPage > "1"){
			var currentPage = parseInt(document.getElementById("currentPage").innerHTML) - 1;
			document.getElementById("currentPage").innerHTML = "" + currentPage;
			Filter();
		}else{
			swal("提示", "已经是第一页了哦!", "warning");
		}
	}
	
	// 尾页
	function lastPage(){
		document.getElementById("currentPage").innerHTML = document.getElementById("totalPage").innerHTML;
		Filter();
	}
	
	$(document).ready(function () {  
	    Filter();  
	});

	function Filter(){
		var formElement = document.getElementById("repairForm");
		var username = "";
		if(formElement.username != ""){
			username = formElement.username.value;
		}
		var serType = formElement.serType.value;
		var judgeState = formElement.judgeState.value;
		var currentPage = document.getElementById("currentPage").innerHTML;
		if(currentPage == "" || currentPage == "0"){
			currentPage = 1;
		}
		var url = "http://localhost:8080/repair/getAllRF";
		var args = {"time":new Date(),"username": username,"pageNum":currentPage,"serType":serType,"judgeState":judgeState};
		$.getJSON(url, args, function(data){
			var str = "";

			for(var k in data.repairForms){
				//将数据库中保存的报修类型参数，转换为文字
				var serType = "";
				switch(data.repairForms[k].serType)
				{
				case 1:
				  serType = "水";
				  break;
				case 2:
				  serType = "木";
				  break;
				case 3:
				  serType = "电";
				  break;
				case 4:
				  serType = "其他";
				  break;
				}
				//将数据库中保存的报修类型参数，转换为文字
				var judgeState = "";
				switch(data.repairForms[k].judgeState)
				{
				case -1:
				  judgeState = "未审核";
				  break;
				case 1:
					judgeState = "已审核";
				  break;
				case 2:
					judgeState = "已完成";
				  break;
				}
				str += "<tr>"/*<td>" + data.data.dataList[k].odata.data.dataList[k].usernamerderNumber + "</td>"
*/				+ "<th id='username' name='username'>" + data.repairForms[k].username + "</th>"
				+ "<th id='phone' name='phone'>" + data.repairForms[k].phone + "</th>"
				+ "<th id='serType' name='serType'>" + serType + "</th>"
				+ "<th id='serInform' name='serInform'>" + data.repairForms[k].serInform + "</th>"
				+ "<th id='serTime' name='serTime'>" + data.repairForms[k].serTime + "</th>"
				+ "<th id='serAdd' name='serAdd'>" + data.repairForms[k].serAdd + "</th>"
				+ "<th id='repair_state' name='repair_state'>" + judgeState + "</th></tr>";
			}
			$("#content").html(str);
			$("#currentPage").text(data.currentPage);
			$("#totalRecord").text(data.total);
			$("#totalPage").text(data.totalPage);
		}); 
	}
	
	