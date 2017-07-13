<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>

    <table style="border: 1px solid #777">
        <thead>
            <tr>
                <th hidden="true">ID</th>
                <th style="border: 1px solid #777">Дата</th>
                <th style="border: 1px solid #777">Описание</th>
                <th style="border: 1px solid #777">Калории</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="meal" items="${mealList}">
                <c:if test="${meal.exceed == true}">
                    <tr style="color: red">
                </c:if>
                <c:if test="${meal.exceed == false}">
                    <tr style="color: forestgreen">
                </c:if>
                    <td style="border: 1px solid #777; padding: 5px 20px"><c:out value="${meal.dateTime}" /></td>
                    <td style="border: 1px solid #777; text-align: center"><c:out value="${meal.description}" /></td>
                    <td style="border: 1px solid #777; padding: 5px 10px; text-align: right"><c:out value="${meal.calories}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
