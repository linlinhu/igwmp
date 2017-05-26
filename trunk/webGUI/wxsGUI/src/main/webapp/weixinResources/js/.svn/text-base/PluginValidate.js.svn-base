function validate(el_validate, el_send_btn, time_set, phone, key) {
    // el_validate.keyup(function () {
    //     var length_val = 6;
    //     if ($(this).val().length >= length_val) {
    //         el_validate.attr("readonly", "readonly");
    //     } else {
    //         el_validate.removeAttr("readonly");
    //     }
    // })

    el_send_btn.click(function () {
        var time = time_set;

        var date = new Date();
        date.setTime(date.getTime() + time * 1000); //只能这么写，10表示10秒钟
        console.log("date:" + date)

        var mobile = phone.val();
        if(!(/^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/.test(mobile))) {
            mui.alert("请填写正确的手机号码 ");
            return false;
        }
        var validCode = true;

        var $code = $(this);
        if (validCode) {
            validCode = false;
            // phone.attr("disabled", "disabled ");
            el_send_btn.attr("disabled", "disabled");
            el_validate.val("");
            el_validate.removeAttr("readonly");
            var t = setInterval(function () {
                time--;
                $.cookie(key, time, {
                    expires: date
                });

                console.log("计时2：" + $.cookie(key));
                el_send_btn.html(time + "秒后重新发送 ");
                if (time == 0) {
                    clearInterval(t);
                    el_send_btn.removeAttr("disabled");
                    $code.html("重新获取验证码 ");
                    validCode = true;
                }
            }, 1000)
            sendVerifyCode(phone.val(),t,el_send_btn);
        }
    })

    return true;
}


function validatePhone(mobile) {
    if (!(/^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/.test(mobile))) {
        return false;
    }
    return true;
}