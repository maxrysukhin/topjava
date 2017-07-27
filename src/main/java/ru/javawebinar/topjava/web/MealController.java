package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by max on 1/25/17.
 */
@RequestMapping("/meals")
@Controller
public class MealController {
    private static final Logger LOG = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private MealService service;

    @GetMapping
    public String getAll(Model model) {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/delete{id}")
    public String delete(@PathVariable int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete meal {} of User {}", id, userId);
        service.delete(id, userId);
        return "redirect:/meals";
    }

    @GetMapping("/update{id}")
    public String update(@PathVariable int id, Model model) {
        int userId = AuthorizedUser.id();
        LOG.info("delete meal {} of User {}", id, userId);
        model.addAttribute("meal", service.get(id, userId));
        return "meal";
    }

    @GetMapping("/update")
    public String create() {
        int userId = AuthorizedUser.id();
        LOG.info("create meal {} of User {}", userId);
        return "meal";
    }

    @PostMapping("/update{id}")
    public String edit(HttpServletRequest request) {

        final Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            LOG.info("Create {}", meal);
            service.save(meal, AuthorizedUser.id());
        } else {
            LOG.info("Update {}", meal);
            meal.setId(Integer.valueOf(request.getParameter("id")));
            service.update(meal, AuthorizedUser.id());
        }
        return "redirect:/meals";
    }
}
