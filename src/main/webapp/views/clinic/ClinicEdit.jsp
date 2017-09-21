<%--
  Created by IntelliJ IDEA.
  User: Рустем
  Date: 02.09.2017
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/myStyle">
    <title>Редактировать</title>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        var name = "";
        $(document).ready(function(){
            name = $('#name').val();
        });
        function createPet() {
            if (validate()){
                $('#newPetName').css('background-color','');
            }
            else{
                alert("Введите кличку питомца");
                return false;
            }
        }
        //Отмена submit, если имя не изменилось
        function editClientName() {
            if ($('#newClientName').val() == name){
                alert("Имя не изменилось!");
                return false;
            }
            if($('#newClientName').val() == ''){
                alert("Имя пользователя должно содержать по крайней мере один символ")
                $('#newClientName').css('background-color','red');
                return false;
            }


        }
        function validate() {
            var result = true;
            if($('#newPetName').val() == ''){
                $('#newPetName').css('background-color','red');
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
        <div id="result"/>
        <div id="edit">
            <form action="${pageContext.servletContext.contextPath}/clinic/edit" method="post">
                <input type="hidden" name="id"  value="${client.id}">
                <table>
                    <tr>
                        <td>Имя клиента:</td>
                        <td>
                            <input type="text" name="clientName" id="newClientName"  value="${client.name}">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" align="center" name="editSubmit" value="Изменить" onclick="return editClientName()">
                        </td>
                    </tr>
                </table>
                <br>
                <h3>Питомцы</h3>
                <table>
                    <c:choose>
                        <c:when test="${client.pets.size()>0}">
                            <tr>
                                <td>ID</td>
                                <td>Имя питомца</td>
                                <td>Тип питомца</td>
                            </tr>
                            <c:forEach items="${client.pets}" var="pet" varStatus="status">
                               <tr>
                                   <td>
                                       <input type="hidden" name="petId" id="delId"  value=${pet.id} >
                                    </td>
                                    <td>
                                        <input type="text" name="petName" value="${pet.name}">
                                    </td>
                                    <td>
                                        ${pet.petType}
                                    </td>
                                   <td>
                                       <a href="${pageContext.servletContext.contextPath}/clinic/deletePet?id=${client.id}&petId=${pet.id}">Удалить</a>
                                   </td>
                               </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            Нет питомцев
                        </c:otherwise>
                    </c:choose>


                </table>
                <br>
                Добавить питомца
                <table>
                    <tr>
                        <td>Тип питомца</td>
                        <td>
                            <select  name="newPetType">
                                <option value="1">Собака</option>
                                <option value="2">Кошка</option>
                                <%--<option value="3">Любой</option>--%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Имя питомца</td>
                        <td>    <input type="text" name="newPetName" id="newPetName"></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" name="addPetSubmit" value="Добавить" onclick="createPet()">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
