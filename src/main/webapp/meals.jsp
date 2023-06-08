<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CaloriesManager-Meals</title>
</head>
<body>
<h2>Meals</h2>
<hr>
<h3><a href="index.html">Home</a></h3>
<hr>
<br>
<input type="button" value="Add Meal" onclick="window.location.href = 'meals?action=create'">
<br>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="meals" scope="request" type="java.util.List"/>
    <c:forEach items="${meals}" var="meal">
        <tr style="color: ${meal.excess ? "red" : "green"}">
            <td><c:out value="${meal.date} ${meal.time}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td>
                <a href="meals?action=edit&id=${meal.id}">Edit</a>
                <a href="meals?action=delete&id=${meal.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>

</body>
</html>
