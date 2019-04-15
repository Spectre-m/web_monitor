$(document).ready(function () {
    var check = "";
    $("#sub1").click(function () {
            var user = $("#username").val();
            var chec = $("#check").val();
            var email = $("#email").val();
            var password = $("#password1").val();
            var password1 = $("#password2").val();
            var sum = 0;
            if (user.length == 0 || email.length == 0) {
                alert("请补全信息，重新提交！");
                sum++;
                return false;
            }
            else if (password.length < 5) {
                alert("新密码长度不应小于5");
                sum++;
                return false;
            }
            else if (password != password1) {
                alert("前后密码不一致！");
                sum++;
                return false;
            }
            if (sum == 0)
                $("#sub1").val("正在修改...");
            var passwd = $.md5(password);
            $.ajax({
                url: "/changePassword",
                type: "POST",
                data: {'username': user, 'password': passwd, "verificationCode": chec},
                success: function (data) {
                    if (data == "success") {
                        window.location.href = "login.html";
                    }
                    else
                        sweetAlert("error", "修改失败", "error");


                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(("no " + XMLHttpRequest.readyState + " " + XMLHttpRequest.status));
                }
            });
        }
    );
    $("#sub3").click(function () {
        var user = $("#username").val();
        var emil = $("#email").val();
        var msg = $("#sub3").val();
        if (msg == "获取验证码") {
            $.ajax({
                url: "/getVerificationCode",
                type: "POST",
                data: {'email': emil, 'username': user},
                success: function (data) {
                    if (data == "success") {
                        $("#sub3").val("已获取验证码！");
                    } else
                        sweetAlert("error", "发送失败", "error");


                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(("no " + XMLHttpRequest.readyState + " " + XMLHttpRequest.status));
                }
            });
        }
        setTimeout(function () {
            $("#sub3").val("获取验证码");
            check = "";
        }, 10000);

    });
    $("#sub4").click(function () {
        window.location.href = "login.html";
    })
});