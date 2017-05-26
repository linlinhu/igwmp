<#assign basePath=request.contextPath />
<html>
<head>
    <base id="basePath" href="${basePath}">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="/weixinResources/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/common.css"/>
    <title>服务员申请记录</title>
    <style type="text/css">
        .mui-content {
            height: 100vh;
            background-image: url("/weixinResources/img/background1.png");
            background-repeat: no-repeat;
            background-size: cover;
            z-index: 1;
        }

        .mui-plus.mui-android header.mui-bar {
            display: none;
        }

        .mui-plus.mui-android .mui-bar-nav ~ .mui-content {
            padding: 0;
        }

        /*hm开头的表示仅为 Hello MUI示例定义*/

        .hm-description {
            margin: .5em 0;
        }

        .hm-description > li {
            font-size: 14px;
            color: #8f8f94;
        }

        .mui-control-content {
            min-height: 44px;
        }

        .mui-control-content .mui-loading {
            margin-top: 50px;
        }

        .mui-scroll-wrapper {
            position: absolute;
            top: 38px;
            height: 93vh;
        }

        .mui-segmented-control {
            border-color: #C9933D;
            position: fixed;
            top: 0;
            z-index: 999;
        }

        .mui-segmented-control .mui-control-item {
            color: #EADAA7;
            border-color: #F4581D;
            background: #E94707;
        }

        .mui-segmented-control .mui-control-item.mui-active {
            color: #D5C283;
            background: #F4581D;
        }

        .g-waiter_apply_today {
            margin-top: 4vh;
            text-align: center;
        }

        .g-waiter_apply_today .m-waiter_apply_today_title {
            color: #393835;
            font-size: 1rem;
            font-weight: bold;
        }

        .g-waiter_apply_today .m-waiter_apply_date {
            color: #767371;
            font-size: .8rem;
        }

        .g-waiter_apply_title {
            margin-top: 4vh;
            background: #FFFBF2;
            height: 6vh;
            line-height: 6vh;
            color: #484847;
            font-size: 1.2rem;
            display: flex;
            justify-content: space-between;
            padding-left: 4vw;
            padding-right: 4vw;
        }

        .g-waiter_apply_title .m-waiter_apply_date {
            font-size: 1rem;
        }

        .g-waiter_apply_title .m-shuxian {
            color: #E6E3DC;
        }

        .g-waiter_apply_title .m-waiter_apply_quantity {
            font-size: 1rem;
        }

        .g-waiter_apply_title span {
            color: #F25D56;
            padding-left: 2vw;
        }

        .g-waiter_apply_head {
            margin: 0;
            padding-left: 4vw;
            padding-right: 4vw;
            color: #545451;
            font-size: 1rem;
            height: 12vh;
            line-height: 12vh;
            /*margin-top: 4vh;*/
            border-bottom: 1px solid #E9E6E1;
            color: #545350;
            background: #FFFBF2;
            font-weight: bold;
        }

        .g-waiter_apply {
            margin: 0;
            /*padding-left: 4vw;*/
            padding: 0;
            padding-right: 4vw;
            color: #545451;
            font-size: 1rem;
            background: #FFFBF2;
            height: 12vh;
            line-height: 12vh;
            border-bottom: 1px solid #E9E6E1;
        }

        .g-waiter_apply:nth-last-of-type(1) {
            padding-bottom: 3vh;
        }

        .g-waiter_apply .m-waiter_apply_name {
            color: #D7D4D0;
        }

        .g-waiter_apply .m-waiter_apply_name p {
            margin: 0;
            height: 3vh;
            line-height: 3vh;
            font-size: .8rem;
        }

        .g-waiter_apply .m-waiter_apply_name .u-waiter_apply_name {
            width: 18vw;
        }

        .g-waiter_apply .m-waiter_apply_name img {
            border-radius: 50%;
            margin-top: 2vh;
        }

        /*在该状态栏下面的图片的大小*/

        .mui-slider .mui-slider-group .mui-slider-item img {
            width: 5vh;
        }

        .g-waiter_apply .m-waiter_apply_date {
            font-size: .8rem;
        }

        .g-waiter_apply .m-waiter_apply_operate {
            color: #F25D56;
        }

        .g-waiter_apply .m-waiter_apply_operate button {
            margin-top: 4vh;
        }

        form {
            display: none;
        }
    </style>
</head>

<body>
<div class="mui-content">
    <div id="slider" class="mui-slider">
        <div id="sliderSegmentedControl" class="mui-segmented-control">
            <a class="mui-control-item <#if flag != 2>mui-active</#if>" href="#new_waiter_apply">
                新申请服务员
            </a>
            <a class="mui-control-item <#if flag == 2>mui-active</#if>" href="#all_waiter_apply">
                全部申请记录
            </a>
        </div>
        <div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
        <div class="mui-slider-group">
            <div id="new_waiter_apply" class="mui-slider-item mui-control-content <#if flag != 2>mui-active</#if>">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <ul class="g-waiter_apply_head mui-row">
                        <li class="m-waiter_apply_name mui-col-xs-4 mui-text-left" data-index="name">
                            申请人
                        </li>
                        <li class="mui-col-xs-4 m-waiter_apply_date mui-text-center">
                            申请日期
                        </li>
                        <li class="mui-col-xs-4 mui-text-right m-waiter_apply_quantity">
                            状态
                        </li>
                    </ul>
                    <div class="mui-scroll">
                    <#if flag != 2>
                        <#if (list?size>0)>
                            <#list list as res>
                                <ul class="g-waiter_apply mui-row">
                                    <li class="m-waiter_apply_name mui-col-xs-3 mui-text-left" data-index="name">
                                        <div class="u-waiter_apply_name mui-text-center">
                                            <img src="${res.url}"/>
                                            <p>${res.name}</p>
                                        </div>

                                    </li>
                                    <li class="mui-col-xs-5 m-waiter_apply_date mui-text-center">
                                    ${res.apprDate}
                                    </li>
                                    <li class="mui-col-xs-4 mui-text-right m-waiter_apply_operate">
                                        <#if res.status==0>
                                            <button id="agree" class="mui-btn-green mui-btn" value="${res.id}">通过
                                            </button>
                                            <button id="refues" class="mui-btn-danger mui-btn" value="${res.id}">拒绝
                                            </button>
                                        <#elseif res.status==1>
                                            <button class="mui-btn-danger mui-btn">被驳回</button>
                                        <#elseif res.status==2>
                                            <button class="mui-btn-green mui-btn">已通过</button>
                                        </#if>
                                    </li>
                                </ul>
                            </#list>
                        <#else>
                            <div style="text-align: center">
                                <span style="color: #000;font-size: 1rem">暂无记录</span>
                            </div>
                        </#if>
                    </#if>
                    </div>
                </div>
            </div>
            <div id="all_waiter_apply" class="mui-slider-item mui-control-content <#if flag == 2>mui-active</#if>">
                <div id="scroll2" class="mui-scroll-wrapper">
                    <ul class="g-waiter_apply_head mui-row">
                        <li class="m-waiter_apply_name mui-col-xs-4 mui-text-left" data-index="name">
                            申请人
                        </li>
                        <li class="mui-col-xs-4 m-waiter_apply_name mui-text-center">
                            申请日期
                        </li>
                        <li class="mui-col-xs-4 mui-text-right m-waiter_apply_quantity">
                            状态
                        </li>
                    </ul>
                    <div class="mui-scroll">
                    <#if flag==2>
                        <#if (list?size>0)>
                            <#list list as res>
                                <#if res_index>

                                </#if>

                                <ul class="g-waiter_apply mui-row">
                                    <li class="m-waiter_apply_name mui-col-xs-3 mui-text-left" data-index="name">
                                        <div class="u-waiter_apply_name mui-text-center">
                                            <img src="${res.url}"/>
                                            <p>${res.name}</p>
                                        </div>
                                    </li>
                                    <li class="mui-col-xs-5 m-waiter_apply_date mui-text-center">
                                    ${res.apprDate}
                                    </li>
                                    <li class="mui-col-xs-4 mui-text-right m-waiter_apply_operate">
                                        <#if res.status==0>
                                            <button id="agree" class="mui-btn-green mui-btn" value="${res.id}">通过
                                            </button>
                                            <button id="refues" class="mui-btn-danger mui-btn" value="${res.id}">拒绝
                                            </button>
                                        <#elseif res.status==1>
                                            <button class="mui-btn-danger mui-btn">被驳回</button>
                                        <#elseif res.status==2>
                                            <button class="mui-btn-green mui-btn">已通过</button>
                                        </#if>
                                    </li>
                                </ul>
                            </#list>
                        <#else>
                            <div style="text-align: center">
                                <span style="color: #000;font-size: 1rem">暂无记录</span>
                            </div>
                        </#if>
                    </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="form" method="POST" action="${basePath}/restaurant/resWaiterApproveList.do">
    <input type="number" id="flag" name="flag">
</form>
<script src="/weixinResources/js/mui.min.js"></script>
<script src="/weixinResources/js/mui.js"></script>
<script src="/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
<script>

    <#if msg != ''>
    mui.alert("${msg}");
    </#if>

    mui.init({
        swipeBack: false
    });
    (function ($) {
        $('.mui-scroll-wrapper').scroll({
            indicators: true //是否显示滚动条
        });
        document.getElementById('slider').addEventListener('slide', function (e) {
            mui.LEBloadding();
            if (e.detail.slideNumber == 0) { //新申请服务员
                document.getElementById("flag").setAttribute("value", 1);
            } else if (e.detail.slideNumber == 1) { //历史申请
                document.getElementById("flag").setAttribute("value", 2);
            }
            document.getElementById("form").submit(); //提交表单
        });

        //同意申请
        document.getElementById('agree').onclick = function agreeApprove(e) {
            var servantId = e.target.value;
            var status = 2;
            handApprove(status, servantId)
        }

        //拒绝申请
        document.getElementById('refues').onclick = function agreeApprove(e) {
            var servantId = e.target.value;
            var status = 1;
            handApprove(status, servantId)
        }

        //请求服务器处理申请
        function handApprove(status, servantId) {

            if (!servantId || !status) {
                mui.alert("抱歉，服务员信息丢失！操作失败！");
                return;
            }
            mui.LEBloadding();
            mui.ajax({
                type: "get",
                url: "/restaurant/resWaiterApprove.do",
                async: true,
                data: {
                    status: status,
                    servantId: servantId
                },
                dataType: "jsonp",
                jsonp: "jsoncallback",
                timeout: 5000,
                success: function (data) {
                    if (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            mui.toast("操作成功！");
                            window.location.reload();//刷新页面
                        } else {
                            mui.alert(data.msg);
                        }
                    }
                },
                error: function (xhr, type, errorThrown) {
                    mui.alert("服务器忙，请稍后重试！");
                }
            });
        }

    })(mui);
</script>

</body>

</html>