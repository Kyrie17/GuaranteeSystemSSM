function check() {
	// 信息填写不完整或学号已被注册
	if (($("#password").val() == "")
			|| ($("#password").val() == null)
			|| ($("#stu_id").val() == "")
			|| ($("#stu_id").val() == null)
			|| ($("#mess").val() == "")
			|| ($("#mess").val() == null)
			|| ($("#password").val() == "")
			|| ($("#password").val() == null)
			|| ($("#stu-Wrong").text() == "该学号已被注册"
					|| ($("#code-Wrong").text() == "验证码错误") || (document
					.getElementById("submit").value == 0))) {
		swal("提示", "请填写完整信息!", "warning");
	}
    else if($("#password").val() != $("#repeatPassword").val()){
        swal("提示", "密码输入不一致!", "warning");
        return false;
	}else{
    	var url="http://localhost:8080/register";
		var args={
            _method:"PUT",
			"stuId":document.getElementById("stu_id").value+"",
			"phone":document.getElementById("phone").value,
			"password":document.getElementById("password").value
		};
        $.post(url, args, function(data) {
            if(data.message=="注册成功"){
                swal({
                    title : "注册成功",
                    text : "2秒后跳转主界面。",
                    timer : 2000,
                    showConfirmButton : false
                });
                setTimeout('to()',1000);//2秒后执行

            }else{
                swal({
                    title : "注册失败",
                    text : "信息填写有误",
                    timer : 2000,
                    showConfirmButton : false
                });
            }

        });
	}

	// 两次输入密码不一致
	if ($("#password").val() != $("#repeatPassword").val()) {
	} else {

		
		setTimeout(function() {
			true;
				// swal("Ajax请求完成！");
			}, 2000);
	}

}
function to(){
    window.location.href="http://localhost:8080/html/HomePage.html";
}
