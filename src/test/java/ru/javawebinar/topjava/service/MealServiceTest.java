package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by max on 7/19/17.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setup() {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(100002, USER_ID);
        MATCHER.assertEquals(meal, MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() throws Exception {
        Meal meal = service.get(100005, USER_ID);
    }

    @Test
    public void delete() throws Exception {
        List<Meal> list = Arrays.asList(MEAL3, MEAL1);
        service.delete(100003, USER_ID);
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), list);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(100005, USER_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> list = Arrays.asList(MEAL5, MEAL4);
        Collection<Meal> actual = service.getBetweenDates(LocalDate.of(2016, Month.JANUARY, 02), LocalDate.of(2016, Month.JANUARY, 03), ADMIN_ID);
        MATCHER.assertCollectionEquals(actual, list);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> list = Arrays.asList(MEAL5, MEAL4);
        Collection<Meal> actual = service.getBetweenDateTimes(LocalDateTime.of(2016, Month.JANUARY, 02, 10, 0), LocalDateTime.of(2016, Month.JANUARY, 04, 10, 0), ADMIN_ID);
        MATCHER.assertCollectionEquals(actual, list);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> list = Arrays.asList(MEAL3, MEAL2, MEAL1);
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), list);
    }

    @Test
    public void update() throws Exception {
        Meal testMeal = new Meal(100003, LocalDateTime.of(2016, Month.JANUARY, 02, 15, 0),"Ланч", 360);
        service.update(testMeal, USER_ID);
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), Arrays.asList(MEAL3, testMeal, MEAL1));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() {
        Meal testMeal = new Meal(100003, LocalDateTime.of(2016, Month.JANUARY, 02, 15, 0),"Ланч", 360);
        service.update(testMeal, ADMIN_ID);
    }

    @Test
    public void save() throws Exception {
        Meal testMeal = new Meal(LocalDateTime.of(2016, Month.JANUARY, 02, 15, 0),"Ланч", 500);
        service.save(testMeal, USER_ID);
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), Arrays.asList(MEAL3, testMeal, MEAL2, MEAL1));
    }

}