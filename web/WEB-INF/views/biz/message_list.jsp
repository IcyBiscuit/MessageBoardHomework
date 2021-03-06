<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>留言板</title>
    <link rel="stylesheet" href="<c:url value="/css/index.css"/>">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>
<header>
    <div class="container">
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <nav>
                    <a href="<c:url value="/my/message"/>">我的留言</a>
                </nav>
                <nav>
                    <a href="<c:url value="/my/info"/> ">我的信息</a>
                </nav>
            </c:when>
            <c:otherwise>
                <nav>
                    <a href="<c:url value="/login"/> ">登录</a>
                    <a href="<c:url value="/regist"/>">注册</a>
                </nav>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<section class="banner">
    <div class="container">
        <div>
            <h1>慕课网留言板</h1>
            <p>
                慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。 </p>
        </div>
    </div>
</section>
<section class="main">
    <div class="container">
        <c:forEach items="${messages}" var="message">
            <div class="alt-item">
                <div class="alt-head">
                    <div class="alt-info">
                        <span>作者：<a href="">${message.username}</a></span>
                        <span>时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${message.createTime}"/></span>
                    </div>
                </div>
                <div class="alt-content">
                    <h3>${message.title}</h3>
                    <p>${message.content}</p>
                </div>

                <c:if test="${sessionScope.user !=null}">
                    <div class="alt-admin">
                        <form id="admin${message.id}" method="post" action="">
                            <input type="hidden" name="messageId" value="${message.id}">
                            <button class="modify" messageid="${message.id}">修改留言</button>
                            <button class="delete" messageid="${message.id}">删除留言</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</section>
<section class="page">
    <div class="container">
        <% if (null != request.getSession().getAttribute("user")) {%>
        <div id="fatie">
            <a href="<c:url value="/addmessage"/> ">
                <button>点我留言</button>
            </a>
        </div>
        <%} else { %>
        <div id="fatie">
            请<a href="<c:url value="/login"/>">
            <button>登录</button>
        </a>后留言
        </div>
        <% } %>


        <div id="pagefy">
            <ul>
                <form id="messageForm" action="<c:url value="/message/listall"/>" method="post">
                    <input type="hidden" id="page" name="page" value="${page}">
                    <input type="hidden" id="last" name="last" value="${last}">
                    <li><a href="javascript:void(0)" onclick="submitMessageForm('first')">首页</a></li>
                    <li><a href="javascript:void(0)" onclick="submitMessageForm('pre')">上一页</a></li>
                    <li><a href="javascript:void(0)">当前第${page}页</a></li>
                    <li><a href="javascript:void(0)" onclick="submitMessageForm('next')">下一页</a></li>
                    <li><a href="javascript:void(0)" onclick="submitMessageForm('last')">尾页</a></li>
                </form>
            </ul>
        </div>
    </div>
</section>
<footer>
    copy@慕课网
</footer>
</body>

<script type="application/javascript">
    $('.modify').click(
        function () {
            var messageId = $(this).attr("messageid");
            $("#admin" + messageId).attr("action", "<c:url value="/message/admin/modify"/> ").submit();
        }
    )

    $('.delete').click(
        function () {
            var messageId = $(this).attr("messageid");
            $("#admin" + messageId).attr("action", "<c:url value="/message/admin/delete"/> ").submit();
        }
    )

    function submitMessageForm(flag) {
        if ('first' == flag) {
            document.getElementById('page').value = 1;
        } else if ('pre' == flag) {
            var current = Number(document.getElementById('page').value);
            if (current > 1) {
                document.getElementById('page').value = current - 1;
            }
        } else if ('next' == flag) {
            var current = Number(document.getElementById('page').value);
            var last = Number(document.getElementById('last').value);
            if (current < last) {
                document.getElementById('page').value = current + 1;
            }
        } else if ('last' == flag) {
            var last = Number(document.getElementById('last').value);
            document.getElementById('page').value = last < 1 ? 1 : last;
        }
        document.getElementById('messageForm').submit();
    }
</script>
</html>