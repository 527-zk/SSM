<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>基于SSM框架的后台管理系统</title>

<meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#" style="font-size: 32px"><b>SSM</b>后台管理系统</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg" style="font-size:20px">忘记密码</p>
				<div>
				    <p>邮箱</p>
					<input type="text" name="email" class="form-control"
						placeholder="地址">
						<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<br>
				<div>
				    <p>验证码</p>
					<input type="text" name="captcha" class="form-control"
						placeholder="6位数字">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<br>
                <div>
                    <p>新密码</p>
					<input type="password" name="newPassword" class="form-control"
						placeholder="新密码">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<br>
                <div>
                    <button class="btn btn-primary btn-block btn-flat"
                        onclick="getCaptcha()">
                        获取验证码
                    </button>
                </div>
                <br>
                <div >
                    <button class="btn btn-primary btn-block btn-flat"
                        onclick="modifyPassword()">
                        修改密码
                    </button>
                </div>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
	<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script> $.widget.bridge('uibutton', $.ui.button);	</script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script>
        function getCaptcha(){
            var addr = $("input[name='email']").val();
            var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            if(!pattern.test(addr)){
                alert('邮箱地址格式有误!');
                return;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/forget/getCaptcha.do",
                data:{"address":addr},
                method:"post",
                success:function (data) {
                    console.log("success to send email...");
                    //window.location.reload();
                }
            })

        }
        function modifyPassword(){
            var captcha = $("input[name='captcha']").val();
            var newPassword = $("input[name='newPassword']").val();
            var addr = $("input[name='email']").val();
            if(addr==null){
                alert('邮箱地址为空！');
            }
            if(captcha==null){
                alert('验证码为空');
            }
            if(newPassword==null){
                alert('新密码为空');
            }
            var pattern=/^[0-9]{6}$/;
            if(!pattern.test(captcha)){
                alert('验证码不合法');
            }
            var sleep = function(time) {
                var startTime = new Date().getTime() + parseInt(time, 10);
                while(new Date().getTime() < startTime) {}
            };
            $.ajax({
                url: "${pageContext.request.contextPath}/forget/modifyPassword.do",
                data:{"captcha":captcha,
                      "newPassword":newPassword,
                       "address":addr },
                method:"post",
                dataType:'json',
                success:function (response) {
                    alert(response.message);
                    console.log("completed...");
                    sleep(2000);
                    window.location.href='${pageContext.request.contextPath}/login.jsp';
                }
            })
        }
    </script>
</body>

</html>
