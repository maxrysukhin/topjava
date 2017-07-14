<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>

    <table style="border: 1px solid #777; margin-bottom: 30px">
        <thead>
            <tr>
                <th style="border: 1px solid #777; padding: 5px 20px">Дата</th>
                <th style="border: 1px solid #777; padding: 5px 20px">Описание</th>
                <th style="border: 1px solid #777; padding: 5px 20px">Калории</th>
                <th style="padding: 5px 20px"></th>
                <th style="padding: 5px 20px"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="meal" items="${meals}">
                <c:if test="${meal.exceed == true}">
                    <tr style="color: red">
                </c:if>
                <c:if test="${meal.exceed == false}">
                    <tr style="color: forestgreen">
                </c:if>
                    <td style="border: 1px solid #777; padding: 5px 20px"><c:out value="${meal.dateTime}" /></td>
                    <td style="border: 1px solid #777; text-align: center"><c:out value="${meal.description}" /></td>
                    <td style="border: 1px solid #777; padding: 5px 10px; text-align: right"><c:out value="${meal.calories}" /></td>
                    <td><a href="meals?action=edit&mealTime=<c:out value="${meal.dateTime}"/>"><button>Edit</button></a></td>
                    <td><a href="meals?action=delete&mealTime=<c:out value="${meal.dateTime}"/>"><button>Delete</button></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="meals?action=insert"><button>Add Meal</button></a>
</body>
</html>
