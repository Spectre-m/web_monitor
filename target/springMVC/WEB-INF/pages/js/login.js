$(document).ready(function() {

	var agent = navigator.userAgent.toLowerCase();
	//IE
	if(agent.indexOf("net") > 0) {

			var obj2 = document.getElementById('text'); 
			obj2.style.fontSize="55px";
			var obj3=document.getElementById("login1");
			obj3.style.minHeight="300px";
			obj3.style.minWidth="700px";
	}
	if(agent.indexOf("chrome/")>0){
		var x=agent.indexOf("chrome/");
		var num=agent.charAt(x+8);
		if(num<4){

			var obj2 = document.getElementById('text'); 
			obj2.style.fontSize="55px";
			var obj3=document.getElementById("login1");
			obj3.style.minHeight="300px";
			obj3.style.minWidth="700px";
 			
 			
		}
	}
	$("#sub1").click(function(){
		 var user=$("#username").val();
		 var pass=$("#password").val();
		 if(user.length==0){
		 	alert("请输入用户名！");
		 	return false;
		 }
		 else if(pass.length==0){
		 	alert("请输入密码！");
		 	return false;
		 }
        var passwd = $.md5(pass);
        $.ajax({
            url: "/login",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({'username': user, 'password': passwd}),
            success: function (data) {
                if(data=="success"){
                    window.location.href ="main_view.html";
                    localStorage.setItem("username",user);
                }
                else{
                    sweetAlert("wrong", "密码错误", "error");
				}



            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(("no " + XMLHttpRequest.readyState + " " + XMLHttpRequest.status));
            }
        });
	});
	$("#sub2").click(function() {
		window.location.href = "enroll.html";
	});

});