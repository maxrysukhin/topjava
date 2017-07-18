package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    public static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    //private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        Meal meal = new Meal();

        for (int i = 0; i < 3; i++) {
            meal = MealsUtil.MEALS.get(i);
            this.save(meal, 1);
        }

        for (int i = 3; i < 6; i++) {
            meal = MealsUtil.MEALS.get(i);
            this.save(meal, 2);
        }

    }

    @Override
    public Meal save(Meal meal, int userId) {
        LOG.info("save meal " + meal.getId() + " of user " + userId);

        Map<Integer, Meal> meals = repository.getOrDefault(userId, new HashMap<>());

        if (meal.isNew()) {
            meal.setId(counter.getAndIncrement());
            meals.put(meal.getId(), meal);
            repository.put(userId, meals);
            return meal;
        } else {
            if (meals.containsKey(meal.getId())) {
                meals.put(meal.getId(), meal);
                repository.put(userId, meals);
                return meal;
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete meal " + id + " of user " + userId);
        Map<Integer, Meal> meals = repository.get(id);
        if (meals.containsKey(id)) {
            meals.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        LOG.info("get meal " + id + " of user " + userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        LOG.info("get all meals of user " + userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals.values().stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

