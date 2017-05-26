<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html, #allmap {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0;
            font-family: "微软雅黑";
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=QSLj5ofn0HoGHafzVdR6HGObBLfYvmgo"></script>
    <title>餐厅位置</title>
</head>

<body>
<div id="allmap"></div>

</body>

</html>
<script type="text/javascript">

    <#if msg != ''>
    mui.alert("${msg}");
    </#if>

    // 百度地图API功能
    var map = new BMap.Map("allmap");

    var latitude = ${RequestParameters['latitude']};
    var longitude = ${RequestParameters['longitude']};
    var point = new BMap.Point(116.404, 39.915);

    var marker = new BMap.Marker(new BMap.Point(116.404, 39.915)); // 创建点
    if (latitude && longitude) {
        point = new BMap.Point(longitude, latitude);
        marker = new BMap.Marker(new BMap.Point(longitude, latitude)); // 创建点
    }
    map.centerAndZoom(point, 11);
    marker.addEventListener("click", attribute);
    map.addOverlay(marker);    //增加点

    function attribute() {
        var p = marker.getPosition();  //获取marker的位置
        alert("marker的位置是" + p.lng + "," + p.lat);
    }
</script>