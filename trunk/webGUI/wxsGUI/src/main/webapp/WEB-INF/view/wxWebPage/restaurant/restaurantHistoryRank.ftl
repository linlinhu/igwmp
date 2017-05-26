<#assign basePath=request.contextPath />
<!DOCTYPE html>
<html>

<head>
    <base id="basePath" href="${basePath}">
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="${basePath}/weixinResources/css/mui.min.css">

    <link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.picker.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.dtpicker.css"/>
    <title>饭店销售排行榜</title>
    <style>
        body {
            height: 100vh;
            width: 100vw;
            padding: 0;
            margin: 0;
            background-image: url(../img/background1.png);
            background-size: cover;
            background-repeat: no-repeat;
            font-family: "微软雅黑";
        }

        .g-restaurant_head {
            position: relative;
        }

        .g-restaurant_head button {
            position: absolute;
            top: 4vh;
            right: 4vw;
            background: transparent;
            border: 0;
        }

        .g-restaurant_head img {
            width: 4vh;
        }

        .g-restaurant_head .m-restaurant_date {
            position: absolute;
            top: 4vh;
            left: 4vw;
            color: #605E5D;
        }

        .mui-scroll-wrapper {
            background: #fff;
            position: absolute;
            top: 12vh;
            height: 88vh;
            border: solid 1px yellowgreen;
        }

        .g-restaurant_rank {
            border-bottom: 1px solid #F3F3F3;
            margin: 0;
            padding: 2vh 0;
        }

        .g-restaurant_rank li {
            list-style: none;
        }

        .g-restaurant_rank .m-restaurant_logo .u-restaurant_info {
            padding-left: 2vw;
            color: #6F6F6F;
        }

        .g-restaurant_rank .m-restaurant_logo .u-restaurant_name {
            margin: 0;
            color: #6F6F6F;
        }

        .g-restaurant_rank .m-restaurant_logo img {
            width: 16vw;
            border-radius: 50%;
        }

        .g-restaurant_rank .m-restaurant_rank_term img {
            width: 10vh;
            height: 10vh;
            border-radius: 50%;
            margin-top: 2vh;
        }

        .g-restaurant_rank .m-restaurant_quantity {
            color: #EB5622;
        }

        form {
            display: none;
        }
    </style>
</head>

<body>
<#if msg!=''>
${msg}
<#else>
<div class="mui-content">
    <div class="g-restaurant_head">
        <button class="btn mui-btn" data-options='{"type":"date"}'>
            <img src="${basePath}/weixinResources/img/canlendar.png"/>
        </button>

        <div class="m-restaurant_date" id="result"><#if curDate != ''>${curDate}
            日<#else>${.now?string("yyyy年MM月dd日")}</#if></div>
    </div>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <#if (list?size>0)>
                <#list list as res>
                    <ul class="g-restaurant_rank mui-row">
                        <li class="m-restaurant_rank_term mui-col-xs-2 mui-text-center" data-index="term">
                            <#if res_index==0>
                                <img src="/weixinResources/img/rank1.png">
                            <#elseif res_index==1>
                                <img src="/weixinResources/img/rank2.png">
                            <#elseif res_index==2>
                                <img src="/weixinResources/img/rank3.png">
                            <#else>
                            ${res_index+1}
                            </#if>
                        </li>
                        <li class="mui-col-xs-5 m-restaurant_logo mui-clearfix"><img class="mui-pull-left"
                                                                                     src="${res.url}"/>
                            <div class="mui-pull-left u-restaurant_info">
                                <p class="u-restaurant_name">${res.name}</p>
                                <p class="u-restaurant_address">凯德天府店</p>
                            </div>
                        </li>
                        <li class="mui-col-xs-3 mui-text-right m-restaurant_quantity">
                        ${res.amout} 两
                        </li>
                    </ul>
                </#list>
            <#else>
                <div style="text-align: center">
                    <span style="color: #000;font-size: 1rem">暂无记录</span>
                </div>
            </#if>
        </div>
    </div>

</div>
</#if>

<form id="form" method="POST" action="${basePath}/restaurant/rankHistoryList.do">
    <input type="date" id="curDate" name="curDate">
</form>

<script type="text/javascript" src="${basePath}js/base64.min.js"></script>
<script type="text/javascript" src="${basePath}js/crypto-sha1.min.js"></script>
<script type="text/javascript" src="${basePath}/weixinResources/js/mui.min.js"></script>
<script type="text/javascript" src="${basePath}/weixinResources/js/mui.picker.min.js"></script>

<script type="text/javascript">

    <#if msg != ''>
    mui.alert("${msg}");
    </#if>

    mui('.mui-scroll-wrapper').scroll({
        deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
    });

    (function ($) {
        $.init();
        var result = $('#result')[0];
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var btns = $('.btn');
        btns.each(function (i, btn) {
            btn.addEventListener('tap', function () {
                var optionsJson = this.getAttribute('data-options') || '{}';
                var options = JSON.parse(optionsJson);
                var picker = new $.DtPicker(options);
                picker.show(function (rs) {
                    result.innerText = rs.y.text + '年' + rs.m.text + '月' + rs.d.text + '日';
                    picker.dispose();
                    document.getElementById("curDate").setAttribute("value", rs.value);
                    document.getElementById("form").submit();
                });
            }, false);
        });
    })(mui);
</script>
</body>

</html>