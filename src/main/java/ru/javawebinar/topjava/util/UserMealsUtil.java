package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetween;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        //List<UserMealWithExceed> resultList = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(13,0), 2000);

        List<UserMealWithExceed> resultList = getFilteredWithExceededWithCycle(mealList, LocalTime.of(7, 0), LocalTime.of(13,0), 2000);

        resultList.forEach(System.out::println);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceededWithCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Map<LocalDate, Integer> summedCaloriesPerDay = new HashMap<>();
        LocalDate mealDate;
        int calories;

        for(UserMeal m : mealList) {
            mealDate = m.getDate();
            calories = m.getCalories();
            summedCaloriesPerDay.merge(mealDate, calories, Integer::sum);
        }

        List<UserMealWithExceed> resultList = new ArrayList<>();
        LocalTime mealTime;
        for(UserMeal m : mealList) {
            mealTime = m.getDateTime().toLocalTime();
            if (isBetween(mealTime, startTime, endTime)) {
                resultList.add(new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(),
                        summedCaloriesPerDay.get(m.getDate()) > caloriesPerDay
                                ? true
                                : false));
            }
        }
        return resultList;
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> summedCaloriesPerDay = mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate,
                        Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> resultList = mealList.stream()
                .filter(m -> isBetween(m.getTime(), startTime, endTime))
                .map(m -> new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(),
                        summedCaloriesPerDay.get(m.getDate()) > caloriesPerDay
                        ? true
                        : false))
                .collect(Collectors.toList());

        return resultList;
    }
}
