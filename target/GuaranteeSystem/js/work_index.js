
	
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

	var orderNumber = "1";
	
	function Filter(){
		
		var currentPage = document.getElementById("currentPage").innerHTML;
		if(currentPage == "" || currentPage == "0"){
			currentPage = 1;
		}
		var url = "http://localhost:8080/worker/getRepairManRF";
		var args = {"time":new Date(),"token": getCookie("worker_cookie"),"pageNum":currentPage,"judgeState":2,"userConfirm":0};
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
				
				str += "<tr><th id='serAdd' name='serAdd'>"+ data.repairForms[k].serAdd + "</th>"
				+ "<th id='serTime' name='serTime'>" + data.repairForms[k].serTime + "</th>"
				+ "<th id='serInform' name='serInform'>" + data.repairForms[k].serInform + "</th>"
				+ "<th class='change' title='点击改变状态'><button title='点击改变状态' id='" + data.repairForms[k].orderNumber + "' onclick='orderNumber=" + data.repairForms[k].orderNumber + ";changeJudgeState()'>" + judgeState + "</button></th>"
				+ "<th class='con_tit_scan' id='con_tit_scan'><a href='#' onclick='orderNumber=" + data.repairForms[k].orderNumber + ";showPersonalRF()'>查看更多</a></th>";
			}
			$("#content").html(str);
			$("#currentPage").text(data.currentPage);
			$("#totalRecord").text(data.total);
			$("#totalPage").text(data.totalPage);
		}); 
	}



    function showPersonalRF(){
        var url = "http://localhost:8080/personCenter/getRFByOrderNum";
        var args = {"time":new Date(),"orderNumber":orderNumber};
        $.getJSON(url, args, function(data){
            var str = "";


            //将数据库中保存的报修类型参数，转换为文字
            var serType = "";
            switch(data.serType)
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
            switch(data.judgeState)
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
            $("#r_ordernumber").text(data.orderNumber);
            $("#s_id").text(data.username);
            $("#s_phone").text(data.phone);
            $("#r_sertype").text(serType);
            $("#r_seradd").text(data.serAdd);
            var time = data.serTime + "&nbsp;&nbsp;&nbsp;&nbsp;" + data.detailTime;
            $("#r_sertime").html(time);
            $("#r_serinform").text(data.serInform);
            $("#r_judgestate").text(judgeState);
            $("#a_id").text(data.repairMan);
            //$("#broken_img").attr("src","/upload/" + data.file_path);

        });
	
	var modal = document.getElementById('open_repair');
    // 打开弹窗的按钮对象
    var btn = document.getElementById("show_bill");
    // 获取 <span> 元素，用于关闭弹窗
    var span = document.getElementById("comfirm");
    
    modal.style.display = "block";
    
     
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}

function ChangeUserConfirm(){

	var on = document.getElementById("r_ordernumber").innerHTML;
		
	var url = "http://localhost:8080/ChangeUserconfirmServlet";
	var args = {"time":new Date(),"orderNumber":on};
	$.getJSON(url, args, function(data){
		
		if(data.code == 1){
			swal({
				title : data.msg,
				text : "2秒后跳转回个人中心。",
				timer : 2000,
				showConfirmButton : false
			});
			setTimeout(function() {
				window.location.href = "/html/person_finish.html";
			}, 2000);
		}else{
			swal("提示", data.msg, "warning");
		}
	});
}


//点击改变审核状态的按钮
function changeJudgeState(){
	
	if(document.getElementById(orderNumber).innerHTML == "未审核"){
		var url = "http://localhost:8080/worker/changeJudgeState";
		var args = {"time":new Date(),"orderNumber":orderNumber,"judgeState":-1};
		$.getJSON(url, args, function(data){
			
			if(data.code == 1){
				document.getElementById(orderNumber).innerHTML = "已审核";
				swal("成功", data.msg, "success");
			}else{
				swal("提示", data.msg, "warning");
			}
			
		});
	}else if(document.getElementById(orderNumber).innerHTML == "已审核"){
		var url = "http://localhost:8080/worker/changeJudgeState";
		var args = {"time":new Date(),"orderNumber":orderNumber,"judgeState":1};
		$.getJSON(url, args, function(data){
			if(data.code == 1){
				document.getElementById(orderNumber).innerHTML = "已完成";
				swal("成功", data.msg, "success");
			}else{
				swal("提示", data.msg, "warning");
			}
		});
	}else{
		document.getElementById(orderNumber).disable = true;
		}	
}



//在报修单详情中点击改变审核状态的按钮
function changeJudgeState1(){
	
	if(document.getElementById(orderNumber).innerHTML == "未审核"){
		var url = "http://localhost:8080/worker/changeJudgeState";
		var args = {"time":new Date(),"orderNumber":orderNumber,"judgeState":-1};
		$.getJSON(url, args, function(data){
			
			if(data.code == 1){
				document.getElementById("change_repair").innerHTML = "已审核";
				swal({
					title : "操作成功",
					text : "2秒后跳转回首页。",
					timer : 2000,
					showConfirmButton : false
				});
				setTimeout(function() {
					window.location.href = "/html/work_index.html";
				}, 2000);
			}else{
				swal("提示", data.msg, "warning");
			}
			
		});
	}else if(document.getElementById(orderNumber).innerHTML == "已审核"){
		var url = "http://localhost:8080/worker/changeJudgeState";
		var args = {"time":new Date(),"orderNumber":orderNumber,"judgeState":1};
		$.getJSON(url, args, function(data){
			if(data.code == 1){
				document.getElementById("change_repair").innerHTML = "已完成";
				swal({
					title : "操作成功",
					text : "2秒后跳转回首页。",
					timer : 2000,
					showConfirmButton : false
				});
				setTimeout(function() {
					window.location.href = "/html/work_index.html";
				}, 2000);
			}else{
				swal("提示", data.msg, "warning");
			}
		});
	}else{
		document.getElementById(orderNumber).disable = true;
		}	

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

