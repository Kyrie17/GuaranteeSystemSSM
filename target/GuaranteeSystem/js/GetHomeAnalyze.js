url="http://localhost:8080/analyzeData/analyze";
$.get(url,function(data){
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
var myChart1 = echarts.init(document
    .getElementById('main1'));
// 指定图表的配置项和数据
var option = {
    // 标题
    title: {
        text: '报修类别',
        x: 'center'
    },
    // 工具箱
    toolbox: {
        show: true,
        feature: {
            saveAsImage: {
                show: true
            }
        }
    },
    tooltip: {
        trigger: 'axis'
    },
    // 图例
    legend: {
        data: ['销量']
    },
    // x轴
    xAxis: {
        type: 'category',
        data: ["水", "木", "电", "其他"]
    },
    yAxis: {},
    // 数据
    series: [{
        name: '类别',
        type: 'bar',
        data: [data[0], data[1], data[2],
            data[3]]
    }]

};
// 指定图表的配置项和数据
var option1 = {
    title: {
        text: '审核状态饼图',
        subtext: '学生',
        x: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['未审核', '已审核', '已完成']
    },
    series: [{
        name: '学生报修类别',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        data: [{
            value: data[4],
            name: '未审核'
        }, {
            value: data[5],
            name: '已审核'
        }, {
            value: data[6],
            name: '已完成'
        }]
    }]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
myChart1.setOption(option1);

var str = "";
for (var k in data) {
    //将数据库中保存的报修类型参数，转换为文字
    var serType = "";
    switch (data[k].serType) {
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
    switch (data[k].judgeState) {
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
    str += "<tr><th>" + serType + "</th>"
        + "<th>" + data[k].serInform + "</th>"
        + "<th>" + data[k].username + "</th>"
        + "<th>" + data[k].serTime + "</th>"
        + " < th > " + judgeState+ " < /th> </tr>";
}
$("#repair_infor").html(str);
});