<%--
  Created by IntelliJ IDEA.
  User: apexLee
  Date: 2022-12-26
  Time: 오후 4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <tr>
    <td>id</td>
    <td>userName</td>
    <td>age</td>
  </tr>
  <c:forEach var="item" items="${members}">
    <tr>
      <td>${item.id}</td>
      <td>${item.userName}</td>
      <td>${item.age}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
