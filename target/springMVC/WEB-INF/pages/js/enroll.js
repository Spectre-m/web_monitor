$(document).ready(function () {
    var check = "";
    var agent = navigator.userAgent.toLowerCase();
    //IE
    if (agent.indexOf("net") > 0) {
        var obj1 = document.getElementById('a1');
        obj1.style.left = "13.5%";
        obj1.style.position = "absolute";

    }
    if (agent.indexOf("chrome/") > 0) {
        var x = agent.indexOf("chrome/");
        var num = agent.charAt(x + 8);
        if (num < 4) {
            var obj1 = document.getElementById('a1');
            obj1.style.left = "13.5%";
            obj1.style.position = "absolute";


        }
    }
    $("#sub2").click(function () {
        window.location.href = "login.html";
    });
    $("#sub1").click(function () {
        var user = $("#username").val();
        var telephone = $("#phone").val();
        var emil = $("#email").val();
        var password = $("#password").val();
        var password1 = $("#password2").val();
        var chec = $("#check").val();
        var sum = 0;
        if (user.length == 0) {
            alert("请填写账号！");
            sum++;
            return false;
        }
        else if (telephone.length != 11) {
            alert("请输入正确的电话号码");
            return false;
        }
        else if (emil.length == 0) {
            alert("请输入正确的邮箱");
            return false;
        }
        else if (password.length < 5) {
            alert("密码长度不应小于5");
            sum++;
            return false;
        }
        else if (password != password1) {
            alert("前后密码不一致！");
            sum++;
            return false;
        }
        if (sum == 0) {
            setTimeout(function () {
                $("#sub1").val("正在注册...");
            }, 5000);
            var passwd = $.md5(password);
            $.ajax({
                url: "/enroll",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({'username': user, 'password': passwd, 'phone': telephone, 'email': emil}),
                success: function (data) {
                    if (data == "success") {
                        sweetAlert("success", "注册成功", "successful");
                        window.location.href = "login.html";
                    }
                    else
                        sweetAlert("error", "注册失败，请稍后重试", "error");


                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(("no " + XMLHttpRequest.readyState + " " + XMLHttpRequest.status));
                }
            });
        }
    });
    $("#sub3").click(function () {
        var msg= $("#sub3").val();
        if(msg=="获取验证码") {
            $.ajax({
                url: "/getVerficationCode",
                type: "POST",
                contentType: "application/json",
                success: function (data) {
                    if (data == "success") {
                        $("#sub3").val("已获取验证码！");
                    } else
                        $("#msg").html("无法获取！请稍后重试");


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

});