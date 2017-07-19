DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, calories, datetime) VALUES
  (100000, 'Завтрак', 500, '2016-01-02 10:00:00');
INSERT INTO meals (user_id, description, calories, datetime) VALUES
  (100000, 'Обед', 1000, '2016-01-02 13:00:00');
INSERT INTO meals (user_id, description, calories, datetime) VALUES
  (100000, 'Ужин', 500, '2016-01-02 20:00:00');
INSERT INTO meals (user_id, description, calories, datetime) VALUES
  (100001, 'Завтрак', 500, '2016-01-02 10:00:00');
INSERT INTO meals (user_id, description, calories, datetime) VALUES
  (100001, 'Обед', 1000, '2016-01-03 13:00:00');
INSERT INTO meals (user_id, description, calories, datetime) VALUES
  (100001, 'Ужин', 510, '2016-01-04 20:00:00');