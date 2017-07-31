package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by max on 7/31/17.
 */

@RestController
@RequestMapping(value = MealAjaxController.AJAX_URL)
public class MealAjaxController extends AbstractMealController {
    static final String AJAX_URL = "/ajax/meals";

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrUpdate(@RequestParam("id") int id,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories,
                               @RequestParam("dateTime") LocalDateTime dateTime) {
        Meal meal = new Meal(id, dateTime, description, calories);
        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
    }

    @Override
    @GetMapping("/filter")
    public List<MealWithExceed> getBetween(
        @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
        @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
            return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
