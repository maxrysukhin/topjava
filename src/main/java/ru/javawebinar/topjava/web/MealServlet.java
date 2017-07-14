package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.meals;
import static ru.javawebinar.topjava.util.IdGenerator.getId;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;

/**
 * Created by max on 7/13/17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    private static String INSERT_OR_EDIT = "/mealEdit.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");

        String forward = "";
        String action = request.getParameter("action");

        if (action == null) {
            forward = LIST_MEALS;
            request.setAttribute("meals", getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000));
        } else if (action.equalsIgnoreCase("delete")){
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("mealTime"));

            for (Meal m : meals) {
                if (m.getDateTime().equals(dateTime)) {
                    meals.remove(m);
                    break;
                }
            }
            forward = LIST_MEALS;
            request.setAttribute("meals", getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000));
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("mealTime"));

            for (Meal m : meals) {
                if (m.getDateTime().equals(dateTime)) {
                    request.setAttribute("meal", m);
                    break;
                }
            }
        } else {
            forward = INSERT_OR_EDIT;
        }
        request.getRequestDispatcher(forward).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Meal meal = new Meal();

        String mealId = request.getParameter("mealid");
        int calories = Integer.parseInt(request.getParameter("mealcalories"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("mealdate"));

        meal.setCalories(calories);
        meal.setDateTime(dateTime);
        meal.setDescription(request.getParameter("mealdescription"));

        if (mealId.equals("")) {
            meal.setId(getId());
            meals.add(meal);
        } else {
            int index;
            for (Meal m : meals) {
                if (m.getId() == Integer.parseInt(mealId)) {
                    index = meals.indexOf(m);
                    meals.set(index, meal);
                    break;
                }
            }
        }

        request.setAttribute("meals", getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    }
}
