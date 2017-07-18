package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {

    public static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal save (Meal meal, int userId) {
        LOG.info("save meal " + meal.getId() + " of user " + userId);
        return service.save(meal, userId);
    }

    public void delete (int id, int userId) {
        LOG.info("delete meal " + id + " of user " + userId);
        service.delete(id, userId);
    }

    public Meal get (int id, int userId) {
        LOG.info("get meal " + id + " of user " + userId);
        return service.get(id, userId);
    }

    public List<MealWithExceed> getFiltered(int id, String sd, String ed, String st, String et) {
        LocalDate startDate = (sd.equals("") ? LocalDate.MIN : LocalDate.parse(sd));
        LocalDate endDate = (ed.equals("") ? LocalDate.MAX : LocalDate.parse(ed));
        LocalTime startTime = (st.equals("") ? LocalTime.MIN : LocalTime.parse(st));
        LocalTime endTime = (et.equals("") ? LocalTime.MAX : LocalTime.parse(et));
        return MealsUtil.getFilteredMeals(getAll(id), startDate, endDate, startTime, endTime);
    }

    public List<MealWithExceed> getAll(int userId) {
        LOG.info("get all meals for user " + userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), 2000);
    }

    public void update(Meal meal, int userId) {
        LOG.info("update meal " + meal.getId() + " of user " + userId);
        service.update(meal, userId);
    }
}