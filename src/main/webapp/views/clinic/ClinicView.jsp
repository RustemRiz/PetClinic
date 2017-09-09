<%--
  Created by IntelliJ IDEA.
  User: Рустем
  Date: 02.09.2017
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/myStyle">
    <title>Клиника домашних животных</title>
</head>
<body>
<div id="preHeader">
    <h1 align="center">Клиника<br>домашних <br>животных </h1>
</div>
<div id="header">   </div>
file:/C:/Users/Рустем/IdeaProjects/lesson16/src/main/java/ru/lesson/servlets/ClinicViewServlete.java
<div id="main">
    <div id="find">
        <form action="${pageContext.servletContext.contextPath}/clinic/view" method="GET">
            <input type="submit" name="addClient"  id="addButton" value="Добавить">
        </form>  
        <form action="${pageContext.servletContext.contextPath}/clinic/view" method="post">
            <table>
                <tr>
                    <td>Имя клиента</td>
                    <td>   <input type="text" name="findClientName">       </td>
                </tr>
                <tr>
                    <td>Имя питомца</td>
                    <td>    <input type="text" name="findPetName"></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="search" value="Найти">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id = "result">
        <c:choose>
        <c:when test="${clients.size()>0}">
            <h1 align="center">Клиенты</h1>
            <table align="center" border="2">
                <tr>
                    <td>ID</td>
                    <td>Хозяин</td>
                    <td>Питомец</td>
                    <td>Действия</td>
            </tr>
            <c:forEach items = "${clients}" var = "client" varStatus = "status">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.name}</td>
                    <td>${client.pet.name}</td>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/clinic/edit?id=${client.id}">Редактировать</a>
                        <a href="${pageContext.servletContext.contextPath}/clinic/delete?id=${client.id}">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
            </c:when>
            <c:otherwise>
                <h3>Клиенты не найдены!</h3>
            </c:otherwise>

            </c:choose>
        </table>
    </div>

</body>
</html>