
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


//获取未审核的报修单
$(document).ready(function () {  
	
	Filter();
	
});


function Filter(){
	//获取当前页面
	var currentPage = document.getElementById("currentPage").innerHTML;
	if(currentPage == "" || currentPage == "0"){
		currentPage = 1;
	}

	var url = "http://localhost:8080/repair/getAllRF";
	var args = {"time":new Date(),"username":"","pageNum":currentPage,"serType":"0","judgeState":0}

	
	$.getJSON(url, args, function(data){
		var str = "";	//每条记录
		for(var k in data.repairForms){
			var str2 = "";	//审核状态的下拉列表
			var str3 = "";	//维修工的下拉列表
			var str4 = "";	//更新的提交按钮
			var str5 = ""; 	//详情的提交按钮
			var str6 = ""; 	//删除的提交按钮
			
			//删除的提交按钮
			str5 = "<th><form action='http://localhost:8080/repair/removeRF' method='post'><input type='hidden' name='orderNumber' id='orderNumber' value='" + data.repairForms[k].orderNumber + "'><button type='submit' style='cursor:pointer;'><img src='/imgs/delete.png' title='删除'></button></form></th>";
			
			//详情的提交按钮
			str6 = "<th><form action='#' method='post'><input type='hidden' name='orderNumber'><button disabled='true' type='submit' style='cursor:pointer;'><img src='/imgs/xiangxi.png' title='详细'></button></form></tr></th>";
			
			//更新的提交按钮					
			str4 = "<input type='hidden' name='orderNumber' id='orderNumber' value='" + data.repairForms[k].orderNumber + "'><button type='submit' style='cursor:pointer;'><img src='/imgs/updata.png' title='更新' class='updata'></button>";
			
			//将数据库中保存的报修类型参数，转换为下拉链表
			str3 = getRepairMan(data.repairForms[k].serType, data.repairForms[k].repairMan);
			
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
			
			//将数据库中保存的审核状态参数，转换为下拉链表
			var judgeState = "";
			switch(data.repairForms[k].judgeState)
			{
			case -1:
				str2  += "<select name='judgeState' class='through'><option value='-1' selected='selected'>未审核</option><option value='1'>已审核</option><option value='2'>已完成</option></select>";
			  break;
			case 1:
				str2  += "<select name='judgeState' class='through'><option value='-1'>未审核</option><option value='1' selected='selected'>已审核</option><option value='2'>已完成</option></select>";
			  break;
			case 2:
				str2  += "<select name='judgeState' class='through'><option value='-1'>未审核</option><option value='1'>已审核</option><option value='2' selected='selected'>已完成</option></select>";
			  break;
			}
			str += "<tr><th id='number' name='number'>" + data.repairForms[k].orderNumber + "</th>"
			+ "<th id='student_id' name='student_id'>" + data.repairForms[k].username + "</th>"
			+ "<th id='phone name='phone'>" + data.repairForms[k].phone + "</th>"
			+ "<th id='type' name='type'>" + serType + "</th>"
			+ "<th class='operate'><form action='http://localhost:8080/repair/rfUpdate' method='post' class='print'>" + str2 + str3 + str4 + "</form></th>"
				//"<input type='hidden' name='orderNumber' id='orderNumber' value='" + data.repairForms[k].orderNumber + "'>" +
			+ str5
			+ str6;
			
		}
		
		$("#content").html(str);
		$("#currentPage").text(data.currentPage);
		$("#totalRecord").text(data.total);
		$("#totalPage").text(data.totalPage);
	}); 
}


//获取某维修类型的维修工，制成下拉链表
function getRepairMan(serType, repairMan){
	
	var str = "<select name='repairMan'>";	//将某一报修类型的维修人员制成下拉链表
	
	 $.ajax({
         type: "GET",
         url: "http://localhost:8080/repairMan/getRepairManBySerType",
         data: {"time":new Date(), "serType":serType},
         async: false,	//设置同步，等待ajax请求返回的结果
         dataType: "json",
         success: function(data){
        	 
        	 for(var j in data){
        		 
     			if(repairMan.length != 0){
     				
     				if(repairMan == data[j].username){
     					
     					str += "<option selected='selected' value='" + data[j].username +"'>" + data[j].username + "</option>";
     					
     				}else{
     					
     					str += "<option value='" + data[j].username +"'>" + data[j].username + "</option>";
     					
     				}
     			}else{
     				
     				str += "<option value='" + data[j].username +"'>" + data[j].username + "</option>";
     				
     			}
     		}
        	 
         }
     });
	
	str += "</select>";
	
	return str;
}

