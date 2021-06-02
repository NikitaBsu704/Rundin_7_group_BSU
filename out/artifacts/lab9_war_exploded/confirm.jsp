<%--
  Created by IntelliJ IDEA.
  User: AndKruz
  Date: 16.05.2021
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Импортировать собственную библиотеку теговых файлов --%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%-- Импортировать собственную библиотеку тегов --%>
<%@taglib prefix="ad" uri="http://lab9.by/tags" %>

<%-- Если пользователь не аутентифицирован, то просмотр страницы невозможен --%>
<c:if test="${sessionScope.authUser==null}">
    <c:redirect url="/index.jsp" />
</c:if>

<ad:getAds id="${param.id}" var="Delete1" />


<%-- Удалить его из системы--%>


<html>
<head>
    <title>Подтвердите удаление</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="Expires" content="Mon, 11 May 1998 0:00:00 GMT">
    <meta http-equiv="Last-Modified" content="Fri, Jan 25 2099
23:59:59 GMT">
</head>
<body>

<h1>Подтвердите удаление</h1>

<div style="float: left; margin: 10px; margin-top: 20px; padding: 5px
0px; border: 1px solid black; background-color: #ccc; width: 150px; text-align: center">
    <a href="<c:url value="/doDeleteAd.jsp">
<c:param name="id" value="${Delete1.id}" />
</c:url>">Удалить</a>
</div>

<div style="float: left; margin: 10px; margin-top: 20px; padding: 5px
0px; border: 1px solid black; background-color: #ccc; width: 150px; text-align: center">
    <a href="<c:url value="/cabinet.jsp">
</c:url>">отставить</a>
</div>



</body>
</html>