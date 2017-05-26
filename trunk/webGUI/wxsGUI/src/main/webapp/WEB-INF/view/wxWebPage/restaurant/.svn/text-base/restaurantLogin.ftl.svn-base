<#assign basePath=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <base id="basePath" href="${basePath}">
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="/weixinResources/css/mui.min.css">
    <title>饭店端</title>
    <style>
        body {
            height: 100vh;
            width: 100vw;
            padding: 0;
            margin: 0;
            background-image: url(/weixinResources/img/background1.png);
            background-size: cover;
            background-repeat: no-repeat;
            font-family: "微软雅黑";
        }

        .logo {
            position: absolute;
            width: 30vw;
            top: 10%;
            left: 35%;
        }

        .input_box {
            position: absolute;
            width: 90%;
            margin: 5%;
            top: 30%;
        }

        .input_box .input_title {
            color: #515151;
            margin-bottom: 1vh;
        }

        #loginBtn {
            width: 100%;
            background: #E94709;
            border: 1px solid #E94709;
            line-height: 30px;
            font-weight: bold;
            -webkit-user-modify: read-write-plaintext-only;
            color: #FDFAF9;
        }

        ul li {
            margin-top: 2vh;
        }

        ul li:nth-of-type(1) {
            margin-top: 0;
        }

        ul li:nth-of-type(2) {
            margin-top: 0;
        }

        ul li input[type=text] {
            height: 6vh;
            border: 1px solid #e9e9e9;
        }

        .m-validate {
            margin-top: 6vh;
        }

        .m-title {
            height: 6vh;
            line-height: 6vh;
            font-size: .8rem;
        }

        .send_btn {
            height: 6vh;
            background: #E94709;
            text-align: center;
            font-size: .8rem;
            color: #fff;
        }


    </style>
</head>

<body>

<form action="/resLog/login.do" method="post">
    <div class="mui-content">
        <img class="logo" src="/weixinResources/img/e_mark1.png"/>
        <div class="input_box " style="border-radius:4px;">
            <ul class="mui-table-view mui-row m-validate">
                <li class="mui-col-xs-3 mui-text-center m-title">手机号码</li>
                <li class="mui-col-xs-8">
                    <input type="tel" name="phoneNum" id="phoneNum" maxlength="11" value="${phoneNum}" required
                           pattern="^1[34578]\d{9}$"/>
                </li>
                <li class="mui-col-xs-3 mui-text-center m-title">验证码</li>
                <li class="mui-col-xs-4">
                    <input type="tel" name="verifyCode" id="validate" maxlength="6" value="" required
                           pattern="^[0-9]*$"/>
                </li>
                <li class="mui-col-xs-4" style="margin-left: 4vw;">
                    <button class="mui-btn send_btn" id="smsSend">获取验证码</button>
                </li>
            </ul>
        </div>
        <div class="input_box" style="border-radius:4px;top:65% ;">
            <input type="submit" value="登录" id="loginBtn"/>
        </div>
    </div>
</form>
<script type="text/javascript" src="/weixinResources/js/mui.min.js"></script>
<script src="/weixinResources/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/weixinResources/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
<script src="/weixinResources/js/PluginValidate.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var storage = window.localStorage;
//    $(document).ready(function () {
//        //读取 localStage 本地存储，填充用户名密码,如果自动登录有值直接跳转；
//        //相反，跳转到本页面,等待登陆处理
//        var account = storage["account"];
//        var pwd = storage["pwd"];
//        if (( ("" != account) || (null != account)) && (("" != pwd) || (null != pwd))) {
//            //lacoste  已经保存 登陆信息 直接登陆
//            window.location = "";
//        }
//    });

    //记住密码
    function rememberPass(userAccount, userPass) {
        //存储到loaclStage
        storage["account"] = userAccount;
        storage["pwd"] = userPass;
    }

    <#if msg != ''>
    mui.alert("${msg}");
    </#if>

    validate($("#validate"), $("#smsSend"), 60, $("#phoneNum"), "remind");
    //获取验证码
    function sendVerifyCode(phone,t,btnVerify) {
        mui.ajax({
            type: "post",
            url: "/resLog/sendVerifyCode.do",
            async: true,
            data: {
                phoneNum: phone
            },
            dataType: "jsonp",
            jsonp: "jsoncallback",
            timeout: 5000,
            success: function (data) {
                if (data) {
                    data = JSON.parse(data);
                    if (data.success) {
                        mui.toast("验证码发送成功，注意查收！");
                    } else {
                        clearInterval(t);
                        btnVerify.removeAttr("disabled");
                        mui.alert(data.msg);
                    }
                }
            },
            error: function (xhr, type, errorThrown) {
                mui.alert("服务器忙，请稍后重试！");
            }
        });
    }
</script>
</body>

</html>