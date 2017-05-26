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

        p {
            margin-bottom: 3px;
        }

        .g-restaurant_head {
            position: relative;
        }

        .g-restaurant_head img {
            width: 100vw;
            height: 28vh;
        }

        .g-restaurant_head a {
            position: absolute;
            top: 2vh;
            right: 4vw;
        }

        .g-restaurant_head .m-restaurant_info {
            position: absolute;
            top: 19vh;
            left: 4vw;
        }

        .mui-scroll-wrapper {
            background: #fff;
            position: absolute;
            top: 28vh;
            height: 70vh;
        }

        .g-restaurant_rank {
            height: 14vh;
            border-bottom: 1px solid #F3F3F3;
            line-height: 14vh;
            margin: 0;
            padding: 0;
        }

        .g-restaurant_rank li {
            list-style: none;
        }

        .g-restaurant_rank .m-restaurant_logo {
            /*width: 20vh;*/
            height: 10vh;
        }

        .g-restaurant_rank .m-restaurant_logo img {
            width: 10vh;
            height: 10vh;
            border-radius: 50%;
            margin-top: 2vh;
        }

        .g-restaurant_rank .m-restaurant_rank_term img {
            width: 10vh;
            height: 10vh;
            border-radius: 50%;
            margin-top: 2vh;
        }

        .g-restaurant_rank .u-restaurant_info {
            height: 14vh;
            width: 18vw;
            margin-top: 2vh;
        }

        .g-restaurant_rank .u-restaurant_info p {
            margin: 0;
            /*height: 2vh;*/
            margin-top: 2vh;
            line-height: 2vh;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
            text-overflow: -o-ellipsis-lastline;
            overflow: hidden;
        }
    </style>
</head>

<body>
<#if msg!=''>
${msg}
<#else>
<div class="mui-content">
    <#if (list?size>0)>
        <#list list as res>
            <div class="g-restaurant_head">
                <img src="${res.url}"/> <a href="/restaurant/rankHistoryList.do">排行记录</a>
                <div class="m-restaurant_info">
                    <p class="u-restaurant_address">
                    ${res.name}<span>${res.address}</span>
                    </p>
                    <p class="sale_quantity">夺得 ${.now?string("MM月dd日 HH:mm")} 销售榜第一名</p>
                </div>
            </div>
        </#list>
    <#else>
        <div style="text-align: center">
            <span style="color: #000;font-size: 1rem">暂无记录</span>
        </div>
    </#if>
    <div class="mui-scroll-wrapper">
        <div class="mui-scroll">
            <#if (list?size>0)>
                <#list list as res>
                    <ul class="g-restaurant_rank mui-row">
                        <li class="mui-text-center m-restaurant_rank_term mui-col-xs-3" data-index="term">
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
                        <li class="mui-col-xs-4 m-restaurant_logo mui-clearfix">
                            <img class="mui-pull-left" src="${res.url}" style="margin-left: 4vw"/>
                            <div class="mui-pull-right u-restaurant_info">
                                <p class="u-restaurant_name">${res.name}</p>
                                <p class="u-restaurant_address">凯德天府店</p>
                            </div>
                        </li>
                        <li class="mui-col-xs-3 mui-text-right">${res.amout} 两</li>
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

<script type="text/javascript" src="${basePath}/base64.min.js"></script>
<script type="text/javascript" src="${basePath}/crypto-sha1.min.js"></script>
<script type="text/javascript" src="${basePath}/weixinResources/js/mui.min.js"></script>

<script type="text/javascript">
    <#if msg != ''>
    mui.alert("${msg}");
    </#if>

    mui('.mui-scroll-wrapper').scroll({
        deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
    });
</script>
</body>

</html>