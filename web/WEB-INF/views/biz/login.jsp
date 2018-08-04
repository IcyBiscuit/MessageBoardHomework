<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/> ">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"/>

    <%--<script type="text/javascript">--%>
        <%--function changeImg() {--%>
            <%--var img = document.getElementById("img");--%>
            <%--img.src = "/verificationCode.do?date=" + new Date();--%>
        <%--}--%>

        <%--function checkVerificationCode() {--%>
            <%--var verificationCode = document.getElementById('verificationCode').value;--%>
            <%--var flag = (getCookie('v_c_v') == verificationCode);--%>
            <%--if (!flag) {--%>
                <%--alert('验证码输入错误');--%>
            <%--}--%>
            <%--return flag;--%>
        <%--}--%>

        <%--function getCookie(cookie_name) {--%>
            <%--var allCookies = document.cookie;--%>
            <%--var cookie_pos = allCookies.indexOf(cookie_name);   //如果找到了索引，就代表cookie存在--%>
            <%--if (cookie_pos != -1) {--%>
                <%--cookie_pos += cookie_name.length + 1;--%>
                <%--var cookie_end = allCookies.indexOf(";", cookie_pos);--%>
                <%--if (cookie_end == -1) {--%>
                    <%--cookie_end = allCookies.length;--%>
                <%--}--%>
                <%--return unescape(allCookies.substring(cookie_pos, cookie_end));--%>
            <%--}--%>
            <%--return null;--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body>
<div class="login">
    <div class="header">
        <h1>
            <a href="<c:url value="/login"/> ">登录</a>
            <a href="/regPrompt.do">注册</a>
        </h1>
        <button></button>
    </div>
    <form action="<c:url value="/login/verify"/> " method="post">
        <div class="name">
            <input type="text" id="name" name="username" placeholder="请输入登录用户名">
            <p></p>
        </div>
        <div class="pwd">
            <input type="password" id="pwd" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
            <p>${verifyerrmsg}</p>
        </div>

        <div class="idcode">
            <%--<input type="text" id="verificationCode" placeholder="请输入验证码">--%>
            <%--<a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;换一张</a>--%>
            <%--<span><img id="img" src="/verificationCode.do"/></span>--%>
            <input type="text" name="kaptcha" value=""/>
            <img src="<c:url value="/Kaptcha"/> " id="kaptchaImage" title="看不清，点击换一张"/>
            <script type="text/javascript">
                $('#kaptchaImage').click(function () {
                    $(this).attr('src', '<c:url value="/Kaptcha?"/>' + Math.floor(Math.random() * 100));
                });
            </script>
            <br/>
            <small>看不清，点击换一张</small>
                <p>${kaptchaerrmsg}</p>



            <div class="clear"></div>
        </div>
        <div class="autoLogin">
            <label for="">
                <input type="checkbox" checked="checked">
                下次自动登录
            </label>
            <a href="">忘记密码</a>
        </div>
        <div class="btn-red">
            <input type="submit" value="登录" id="login-btn">
        </div>
    </form>
</div>
</body>
</html>