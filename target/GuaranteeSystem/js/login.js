window.onload = function () {
    // 获取对象并注册失去焦点监听
    var student_id = document.getElementById("stu_id");
    var login_password = document.getElementById("password");
    var mess = document.getElementById("code");
    var submit = document.getElementById("login_submit");
    var change = document.getElementById("change");
    var url = "http://localhost:8080/login/getImageMess"
    var message = "";
    function ImageMess() {
        //获取图片验证码
        $.get(url, function (data) {
            $("#checkimgcode").attr("src", "data:image/png;base64," + data.mess);
            message = data.answer;

        });
    }
    ImageMess();
    change.onclick=function(){
        ImageMess();
    }
    // 判断学号是否为空
    student_id.onblur = function () {
        if (student_id.value == null || student_id.value == "") {
            document.getElementById("id_wrong").innerHTML = "学号不能为空";
        } else {
            document.getElementById("id_wrong").innerHTML = "";
        }

    };
    // 判断密码是否为空
    login_password.onblur = function () {
        if (login_password.value == null || login_password.value == "") {
            document.getElementById("pass_Wrong").innerHTML = "密码不能为空";
        } else {
            document.getElementById("pass_Wrong").innerHTML = "";
        }
    };

    // 判断验证码是否为空
    mess.onblur = function () {
        if (mess.value == null || mess.value == "") {
            document.getElementById("mess_wrong").innerHTML = "验证码不能为空";
        } else {
            document.getElementById("mess_wrong").innerHTML = "";
        }
    };
    // 判断是否点击登录按钮
    submit.onclick = function () {
        if (message != mess.value) {
            swal("提示", "信息填写有误!", "warning");
        } else {
            var url2 = "/login";
            var args2 = {
                "stuId": student_id.value,
                "password": login_password.value,
            };
            $.post(url2, args2, function (data) {
                if (data.msg == "登录成功") {
                    Setcookie("cookie", data.data);
                    swal({
                        title: "登录成功",
                        text: "2秒后跳转登录界面。",
                        timer: 2000,
                        showConfirmButton: false
                    });
                    setTimeout(function () {
                        window.location.href = "/html/HomePage.html";
                    }, 2000);

                }
                else if (data.msg == "不存在该学号") {
                    swal("提示", "不存在该学号!", "warning");
                }else if(data.msg=="学号或密码错误"){
                    swal("提示", "学号或密码错误!", "warning");
                }else{
                    swal("提示", "登录失败", "warning");
                }

            });
        }


    }
    // 检验登录信息是否填写正确
    setTimeout(function () {
        if (($("#mess_wrong").text() == "验证码错误")) {
            window.location.href = "Login.html";
        }
        if (($("#mess_wrong").text() != "验证码错误")) {
        }
    }, 20);


}

// 验证码看不清楚
function change() {
    //获取图片验证码
    $.get(url, function (data) {
        $("#checkimgcode").attr("src", "data:image/png;base64," + data.mess);
        message = data.answer;

    });
}


function Setcookie(name, value) {
    //设置名称为name,值为value的Cookie
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间单位毫秒
    document.cookie = name + "=" + value + ";expires=" + expdate.toGMTString() + ";path=/";

    //即document.cookie= name+"="+value+";path=/";  时间默认为当前会话可以不要，但路径要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！
}

