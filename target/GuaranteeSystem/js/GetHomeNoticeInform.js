window.onload=function() {
    $.ajax({
        type: "post",
        url: "http://localhost:8080/getNoticeInform/homepage",
        success: function (data) {
            var str = "";
            for (var k in data) {
                str += "<tr><th align='center'>" + data[k].fileName + "</th>"
                    + "<th>" + data[k].upLoadDate + "</th>"
                    + "<th>" + data[k].downLoadNum + "</th>"
                    + "<th> <a href='http://localhost:8080/getNoticeInform/loadNoticeInform?fileName=" + data[k].fileName + "'>" + "<img src='/imgs/download.png' width='20' height='20'></a>"
                    + "</th></tr>";
            }
            $("#Notice_Inform").html(str);
        }
    });
}
function loadFile(fileName){
    var url="http://localhost:8080/getNoticeInform/loadNoticeInform";
    var args={
        "fileName":fileName
    };
    $.get(url,args,function(data){

    });
}