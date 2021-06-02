<%@page language="java" pageEncoding="UTF-8" %>
<%-- Импортировать JSTL-библиотеку --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Импортировать собственную библиотеку тегов --%>
<%@taglib prefix="ad" uri="http://lab9.by/tags" %>
<%-- Импортировать собственную библиотеку теговых файлов --%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Подтвердите удаление сообщения</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <meta http-equiv="Expires" content="Mon, 11 May 1998 0:00:00 GMT">
    <meta http-equiv="Last-Modified" content="Fri, Jan 25 2099
23:59:59 GMT">
</head>
<body>

<%-- Извлечь JavaBean требуемого объявления --%>
<%-- извлечем для дальнейшего перенаправления--%>
<ad:getAds id="${param.id}" var="ad" />

<div style="float: left; margin: 10px; margin-top: 20px; padding: 5px
0px; border: 1px solid black; background-color: #ccc; width: 150px; text-align: center">
    <a href="<c:url value="/doDeleteAd.jsp">
<c:param name="id" value="${ad.id}" />
</c:url>">Продолжить</a>
</div>
<div style="float: left; margin: 10px; margin-top: 20px; padding: 5px
0px; border: 1px solid black; background-color: #ccc; width: 150px; text-align: center">
    <a href="<c:url value="/cabinet.jsp">
</c:url>">Отставить</a>
</div>



</body>
</html>