package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

public class MealTestData {

    public static final Meal MEAL1 = new Meal(100002, LocalDateTime.of(2016, Month.JANUARY, 02, 10, 0),"Завтрак", 500);
    public static final Meal MEAL2 = new Meal(100003, LocalDateTime.of(2016, Month.JANUARY, 02, 13, 0),"Обед", 1000);
    public static final Meal MEAL3 = new Meal(100004, LocalDateTime.of(2016, Month.JANUARY, 02, 20, 0),"Ужин", 500);
    public static final Meal MEAL4 = new Meal(100005, LocalDateTime.of(2016, Month.JANUARY, 02, 10, 0),"Завтрак", 500);
    public static final Meal MEAL5 = new Meal(100006, LocalDateTime.of(2016, Month.JANUARY, 03, 13, 0),"Обед", 1000);
    public static final Meal MEAL6 = new Meal(100007, LocalDateTime.of(2016, Month.JANUARY, 04, 20, 0),"Ужин", 510);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            ((expected, actual) -> expected == actual ||
                    expected.toString().equals(actual.toString()))
    );
}
