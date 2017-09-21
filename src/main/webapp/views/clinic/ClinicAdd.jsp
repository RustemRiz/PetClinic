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

    <title>Добавить клиента</title>
    <link rel="stylesheet" href="../../css/myStyle"/>

    <script type="text/javascript" src="../../js/jquery-1.12.2.min.js" ;></script>
    <script type="text/javascript">
        function createUser() {
            if (validate()){
                $('#clientName').css('background-color','');
            }
            else{
                alert("Поля должны содержать хотя бы один символ!");
                return false;
            }
        }
        function validate() {
            var result = true;
            if($('#clientName').val() == ''){
                $('#clientName').css('background-color','red');
                result = false;
            }
            return result;
        }
    </script>
</head>
<body>
    <div id="preHeader">
        <h1 align="center">Клиника<br>домашних <br>животных </h1>
    </div>
    <div id="header">   </div>
    <div id="main">
        <div id="find">
            <form action="${pageContext.servletContext.contextPath}/clinic/add" method="post">
                <table align="center">
                    <tr>
                        <td>Имя клиента :</td>
                        <td> <input type="text" name="clientName" id="clientName"> </td>
                    </tr>
                    <tr>
                        <td> </td>
                        <td>
                            <input type="submit" align="center" value="Добавить" onclick="return createUser();">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
