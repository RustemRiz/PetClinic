<%--
  Created by IntelliJ IDEA.
  User: Рустем
  Date: 02.09.2017
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/myStyle">
    <title>Редактировать</title>
</head>
<body>
    <div id="preHeader">
        <h1 align="center">Клиника<br>домашних <br>животных </h1>
    </div>
    <div id="header">   </div>

    <div id="main">
        <div id="find">
            <form action="${pageContext.servletContext.contextPath}/clinic/edit" method="post">
                <input type="hidden" name="id" value="${client.id}">
            <table align="center">
                <tr>
                    <td>Client Name :</td>
                    <td>
                        <input type="text" name="clientName" value="${client.name}">
                    </td>
                </tr>
                <tr>
                    <td>Pet name :</td>
                    <td>
                        <input type="text" name="petName" value="${client.pet.name}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" align="center" value="Добавить">
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
</body>
</html>
