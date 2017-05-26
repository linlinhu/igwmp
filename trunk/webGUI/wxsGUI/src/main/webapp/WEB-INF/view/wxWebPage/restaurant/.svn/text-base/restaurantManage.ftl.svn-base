<#assign basePath=request.contextPath />
<!DOCTYPE html>
<html>

<head>
    <base id="basePath" href="${basePath}">
    <meta charset="UTF-8">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="white">
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/mui.css"/>
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/waiter_common.css"/>
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/restaurant/restaurantManage.css"/>
    <!--<link rel="stylesheet" type="text/css" href="css/mui.min.css"/>-->
    <title>个人中心</title>
    <style type="text/css">
    </style>
</head>

<body>
<div id="allmap"></div>
<div class="mui-content">
    <div class="g-personal_title">
        <img src="/weixinResources/img/logo.jpg"/>
        <div class="m-restaurant_pos">
            <div class="m-waiter_name">
            ${obj.name}
            </div>
            <div class="m-waiter_address">
                <span>${obj.address} </span>
                <img src="/weixinResources/img/locationLogo.png" id="allmap"/>
            </div>

        </div>

        <div class="m-waiter_integral">
            今日售酒 <span>${obj.amount} 两</span>
        </div>
    </div>
    <div class="g-personal_content">
        <div class="m-content1">
            <a class="m-content1_up" id="to_check_details"> <span>今日销售统计</span></a>
            <a class="m-content1_down" id="to_today_rank"> <span>饭店销售排行榜</span></a>
        </div>
        <div class="m-content2">
            <a class="m-content2_up" id="to_earnings_details"> <span>服务员打酒记录</span></a>
            <a class="m-content2_down" id="to_community"> <span>服务员注册审批</span></a>
        </div>

    </div>
</div>
<script src="/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
<script src="/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    <#if msg != ''>
    mui.alert("${msg}");
    </#if>

    mui.init();
    mui('.g-personal_title').on('tap', '#allmap', function () {
        var latitude = "";
        var longitude = "";
    <#if obj.latitude != '' && obj.longitude !=''>
        latitude = ${obj.latitude};
        longitude = ${obj.longitude};
    </#if>
        if (!latitude || !longitude) {
            mui.alert("很抱歉！餐厅经纬度有误！");
            return;
        }
        mui.LEBloadding();
        mui.openWindow({
            url: '/restaurant/goToMap.do?latitude=' + latitude + '&longitude=' + longitude,
            id: 'mapLocation'
        });
    })
    mui('.g-personal_content').on('tap', '#to_check_details', function (e) {
        mui.LEBloadding();
        mui.openWindow({
            url: '/restaurant/restaurantSale.do',
            id: 'restaurantSale'
        });
    });
    /**
     *  饭店销售排行榜
     */
    mui('.g-personal_content').on('tap', '#to_today_rank', function (e) {
        mui.LEBloadding();
        mui.openWindow({
            url: '/restaurant/rankTodayList.do',
            id: 'rankTodayList'
        });
    });
    /**
     *  服务员打酒记录
     */
    mui('.g-personal_content').on('tap', '#to_earnings_details', function (e) {
        mui.LEBloadding();
        mui.openWindow({
            url: '/restaurant/waiterSaleWineList.do',
            id: 'waiterSaleWineList'
        });
    });
    mui('.g-personal_content').on('tap', '#to_community', function (e) {
        mui.LEBloadding();
        mui.openWindow({
            url: '/restaurant/resWaiterApproveList.do',
            id: 'resWaiterApproveList'
        });
    });
</script>
</body>

</html>