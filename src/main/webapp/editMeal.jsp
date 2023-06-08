<jsp:useBean id="meal" scope="request" type="com.crevan.manager.model.Meal"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>CaloriesManager-Meal</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<hr>
<h3>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h3>
<hr>
<br>
<form method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <label>
        Date and Time: <input type="datetime-local" name="date" value="${meal.dateTime}">
    </label>
    <label>
        Description: <input type="text" name="description" value="${meal.description}">
    </label>
    <label>
        Calories: <input type="number" name="calories" value="${meal.calories}">
    </label>
    <button type="submit">Save</button>
    <button type="button" onclick="history.back()">Back</button>
</form>
</body>
</html>
