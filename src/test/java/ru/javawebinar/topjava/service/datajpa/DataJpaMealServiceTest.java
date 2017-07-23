package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.Profiles.DATA_JPA;

@ActiveProfiles(DATA_JPA)
public class DataJpaMealServiceTest extends MealServiceTest {
}
