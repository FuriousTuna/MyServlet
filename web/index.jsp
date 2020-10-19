<%@page import="ru.appline.logic.Model" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 15.10.2020
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Домашняя страница по работе с пользователями</h1>
<p1>Введите ID пользователя (0  - для вывода всего списка пользователей)</p1>
<br>
<p1>Доступно: <%
  Model model = Model.getInstance();
  out.print(model.getFromList().size());
%>
</p1>

<form method="get" action="get">
  <label>ID:
    <input type="text" name="id"><br>
  </label>
  <button type="submit">Поиск</button>

  <a href="addUser.html">Создать нового пользователя</a>
</form>
</body>
<head>
    <title>Title</title>
  </head>
</html>
