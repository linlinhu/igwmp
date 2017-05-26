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
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/style_left.css"/>
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/style_right.css"/>
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/mui.picker.css"/>
    <link rel="stylesheet" type="text/css" href="/weixinResources/css/mui.dtpicker.css"/>
    <title>销售统计</title>
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
            top: 44px;
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

        .g-sales_title {
            margin-top: 4vh;
            background: #FFFBF2;
            height: 8vh;
            line-height: 8vh;
            color: #484847;
            font-size: 1.2rem;
            display: flex;
            justify-content: space-between;
            /*padding-left: 4vw;*/
            /*padding-right: 4vw;*/
        }

        .g-sales_title .m-sales_date {
            padding-left: 4vw;
        }

        .g-sales_title .m-sales_quantity {
            padding-right: 4vw;
        }

        .g-sales_title .m-sales_year {
            font-size: 1rem;
            /*margin-left: 2vw;*/
        }

        .g-sales_title .m-sales_month {
            /*margin-right: 4vw;
            font-weight: 400;*/
            font-size: 1rem;
        }

        .g-sales_title span {
            color: #F25D56;
            padding-left: 2vw;
        }

        .g-sales_title .m-restaurant_date {
            padding-left: 4vw;
        }

        .g-sales_title button img {
            height: 4vh;
            width: 6vw;
            line-height: 3vh;
        }

        .g-sales_title button {
            height: 6vh;
            width: 14vw;
            margin-top: 1vh;
            margin-left: 4vw;
            background: transparent;
            border: 0;
            margin-right: 2vw;
        }

        .g-sales_head {
            height: 8vh;
            line-height: 8vh;
            font-size: 1rem;
            color: #343231;
            padding-left: 4vw;
        }

        .mui-scroll {
            margin-top: 4vh;
        }

        .g-sales_rank {
            margin: 0;
            padding-left: 4vw;
            padding-right: 4vw;
            color: #545451;
            font-size: 1rem;
            background: #FFFBF2;
        }

        .g-sales_rank {
            margin: 0;
            padding-left: 4vw;
            padding-right: 4vw;
            padding-bottom: 2vh;
            color: #545451;
            font-size: 1rem;
            background: #FFFBF2;
        }

        .g-sales_rank .m-sales_rank_term {
            padding-top: 2vh;
        }

        .g-sales_rank .m-sales_name {
            padding-top: 2vh;
        }

        .g-sales_rank .m-sales_quantity {
            padding-top: 2vh;
            color: #F25D56;
        }

        #scroll2 .mui-scroll {
            margin-top: 0;
        }

        form {
            display: none;
        }
    </style>
</head>

<body>
<div class="mui-content">
    <div id="slider" class="mui-slider">
        <div id="sliderSegmentedControl" class=" mui-segmented-control">
            <a class="mui-control-item <#if flag != 2>mui-active</#if>" href="#sales_today">
                当日销量
            </a>
            <a class="mui-control-item <#if flag == 2>mui-active</#if>" href="#sales_accumulative">
                累计销量
            </a>
        </div>
        <div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
        <div class="mui-slider-group">
            <div id="sales_today" class="mui-slider-item mui-control-content <#if flag != 2>mui-active</#if>">
                <div id="scroll1" class="mui-scroll-wrapper">
                    <div class="g-sales_title">
                        <div class="m-sales_date mui-pull-left"></div>
                        <div class="m-sales_quantity mui-pull-right">今日销售 <span>
                            <#if total != ''>
                                ${total} 两
                            </#if>
                        </span></div>
                    </div>
                    <div class="mui-scroll">
                    <#if (list?size>0)>
                        <#list list as res>
                            <ul class="g-sales_rank mui-row">
                                <li class="m-sales_rank_term mui-col-xs-4 mui-text-left" data-index="term">
                                ${we_index+1}
                                </li>
                                <li class="mui-col-xs-4 m-sales_name mui-text-center">
                                ${res.name}
                                </li>
                                <li class="mui-col-xs-4 mui-text-right m-sales_quantity">
                                ${res.amount} 两
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
            <div id="sales_accumulative" class="mui-slider-item mui-control-content <#if flag == 2>mui-active</#if>">
                <div id="scroll2" class="mui-scroll-wrapper">
                    <div class="g-sales_title">
                        <div class="m-restaurant_date" id="result">
                            <#if curDate != ''>
                                ${curDate}
                            </#if>
                        </div>
                        <label>
                            <button class="btn mui-btn-mini" data-options='{"type":"month"}'>
                                <img src="/weixinResources/img/canlendar.png"/>
                            </button>
                        </label>
                    </div>
                    <div class="g-sales_head">
                        该月累计销售
                    </div>

                    <div class="mui-scroll">
                    <#if (list?size>0)>
                        <#list list as res>
                            <ul class="g-sales_rank mui-row">
                                <li class="m-sales_rank_term mui-col-xs-4 mui-text-left" data-index="term">
                                ${we_index+1}
                                </li>
                                <li class="mui-col-xs-4 m-sales_name mui-text-center">
                                ${res.name}
                                </li>
                                <li class="mui-col-xs-4 mui-text-right m-sales_quantity">
                                ${res.amount} 两
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
        </div>
    </div>
</div>
<form id="form" method="POST" action="${basePath}/restaurant/restaurantSale.do">
    <input type="text" id="curDate" name="curDate" value="${queryDate}">
    <input type="number" id="flag" name="flag">
</form>
<script src="/weixinResources/js/mui.min.js"></script>
<script src="/weixinResources/js/mui.js"></script>
<script src="/weixinResources/js/jquery-2.1.4.min.js"></script>
<script src="/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
<script src="/weixinResources/js/dropdown.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="/weixinResources/js/mui.picker.min.js"></script>
<script>

    <#if msg != ''>
    mui.alert("${msg}");
    </#if>

    var myDate = new Date();
    var date_today = myDate.getFullYear() + "年" + (myDate.getMonth() + 1) + "月" + myDate.getDate() + "日";
    var date_month = myDate.getFullYear() + "年" + (myDate.getMonth() + 1) + "月";
    Emin_getOnlyDom('.m-sales_date').innerHTML = date_today;
    Emin_getId('result').innerHTML = date_month;

    mui.init({
        swipeBack: false
    });


    (function ($) {
        $.init();
        var result = $('#result')[0];
        var btns = $('.btn');
        btns.each(function (i, btn) {
            btn.addEventListener('tap', function () {
                var optionsJson = this.getAttribute('data-options') || '{}';
                var options = JSON.parse(optionsJson);
                var picker = new $.DtPicker(options);
                picker.show(function (rs) {
                    result.innerText = rs.y.text + '年' + rs.m.text + '月';
                    picker.dispose();
                    document.getElementById("curDate").setAttribute("value", rs.value + "-01");
                    document.getElementById("flag").setAttribute("value", 2);
                    document.getElementById("form").submit();
                });
            }, false);
        });
    })(mui);
    (function ($) {
        $('.mui-scroll-wrapper').scroll({
            indicators: true //是否显示滚动条
        });
        document.getElementById('slider').addEventListener('slide', function (e) {
            mui.LEBloadding();
            if (e.detail.slideNumber == 0) { //当日销量
                document.getElementById("flag").setAttribute("value", 1);
            } else if (e.detail.slideNumber == 1) { //累计销量
                document.getElementById("flag").setAttribute("value", 2);
                var curDate = document.getElementById("curDate").value;
                if (!curDate) {
                    var date = new Date();
                    document.getElementById("curDate").setAttribute("value", dateFormat(date, "yyyy-MM-dd"));
                }
            }
            document.getElementById("form").submit(); //提交表单
        });
    })(mui);

</script>

</body>

</html>