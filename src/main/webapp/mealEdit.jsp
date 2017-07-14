<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 7/13/17
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Meal</title>
</head>
<body>
<p style="margin-bottom: 20px"><a href="meals">Back to Meals</a>
<form method="POST" action='meals' name="frmAddMeal">
    <input hidden="true"
            type="text" name="mealid" id="mealid"
            value="<c:out value="${meal.id}" />" />
    <br />
    <label for="mealdate">Date: </label>
    <input required="true"
        type="datetime-local" name="mealdate" id="mealdate"
        value="<c:out value="${meal.dateTime}" />" />
    <br />
    <label for="mealdescription">Description: </label>
    <input required="true" type="text" name="mealdescription" id="mealdescription" value="<c:out value="${meal.description}" />" />
    <br />
    <label for="mealcalories">Calories: </label>
    <input required="true" type="text" id="mealcalories" name="mealcalories" value="<c:out value="${meal.calories}" />" />
    <br />
    <input type="submit" value="Add Meal" />
</form>
</body>
</html>
