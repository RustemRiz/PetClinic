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

    <script type="text/javascript" src="../../js/jquery-1.12.2.min.js";></script>
    <script type="text/javascript">
        function createUser() {
            if (validate()){

                $('#clientName').css('background-color','');

//                $.ajax('clinic/add',{
//                    method : 'post',
//                    data: {
//                        clientName : $('#clientName').val(),
//                        petName :$('#petName').val()
//                    }
//                });
//                return true;
            }
            else{
                alert("Поля должны содержать хотя бы один символ!");
                return false;
            }        }
        function validate() {
            var result = true;
            if($('#clientName').val() == ''){
                $('#clientName').css('background-color','red');
                result = false;
            }
            if ($('#petName').val() ==''){
                $('#petName').css('background-color','red');
                alert("Имя пиомца должно содержать хотя бы один символ!");
                result = false;
            }
            return result;
        }
    </script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/clinic/add" method="post">
    <table>
        <tr>
            <td>Имя клиента :</td>
            <td> <input type="text" name="clientName" id="clientName"> </td>
        </tr>
        <tr>
            <td>Кличка питомца :</td>
            <td>  <input type="text" name="petName" id="petName">  </td>
        </tr>
        <tr>
            <td>Вид питомца</td>
            <td>
                <select name id="petType">
                    <option value='1'>Собака</option>
                    <option value="">Кошка</option>
                </select>
            </td>
        </tr>
        <tr>
            <td> </td>
            <td>
                <input type="submit" value="Добавить" onclick="return createUser();">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
