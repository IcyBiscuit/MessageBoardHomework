<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改留言</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/> ">
    <link rel="stylesheet" href="<c:url value="/css/add.css"/> ">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/message/listall"/> ">
                慕课网留言板
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h1>Hello, ${user.name}!</h1>
    </div>
    <div class="page-header">
        <h3>
            <small>修改留言</small>
        </h3>
    </div>
    <form class="form-horizontal" action="<c:url value="/message/admin/modify/submit"/> " method="post">
        <input type="hidden" name="messageId" value="${message.id}">
        <div class="form-group">
            <label for="inputTitle" class="col-sm-2 control-label">标题 ：</label>
            <div class="col-sm-8">
                <input name="title" class="form-control" id="inputTitle" value="${message.title}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputContent" class="col-sm-2 control-label">内容 ：</label>
            <div class="col-sm-8">
                <textarea name="content" class="form-control" rows="3" id="inputContent"
                          placeholder="Content">${message.content}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">修改留言</button>&nbsp;&nbsp;&nbsp;
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a class="btn btn-default" href="<c:url value="/message/listall"/> ">查看留言</a>
            </div>
        </div>
    </form>
</div>
<footer class="text-center">
    copy@imooc
</footer>
</body>
</html>
