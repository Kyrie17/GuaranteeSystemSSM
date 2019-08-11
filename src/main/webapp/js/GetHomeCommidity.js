

function SubmitCommidity(id,price,name) {
    var common={
        "commidityName":name,
        "commidityId":id,
        "commidityPrice":price,
        "quantity":0
    }
    $.ajax({
        url:"http://localhost:8080/commidity/homeCommidity",
        type: 'post',
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        data: JSON.stringify(common),
        success:function(data){
            if (data.msg == "添加购物车成功") {
                swal("提示", "添加购物车成功", "success");
            } else {
                swal("提示", "添加购物车失败", "warning");
            }
        }

    });
}



