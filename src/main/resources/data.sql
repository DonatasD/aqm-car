INSERT INTO car (id, color, model) VALUES
  (1, "Red", "Toyota"),
  (2, "Yellow", "AUDI"),
  (3, "Blue", "Ford"),
  (4, "Green", "Lexus");

INSERT INTO role (id, name) VALUES
  (1, "USER");
INSERT INTO user (id, username, password) VALUES
  (1, "user", "$2a$10$FyHT9ITAfJaZE0zr1wZ02.5tPb20xEFvIWqTekn2Lpd5Zqxs5xK5u"),
  (2, "user1", "$2a$10$yiOz4mP3QfKQglI9LWGt9uTSBYKwE0mf7TVl.0gY8T5GK875wVqB2");
INSERT INTO user_role (user_id, role_id) VALUES
  (1, 1),
  (2, 1);
INSERT INTO booking (id, booked_from, booked_to, car_id, user_id) VALUES
  (1, "2017-11-07 10:00:00", "2017-11-12 11:00:00", 1, 1),
  (2, "2017-11-08 13:00:00", "2017-11-08 15:00:00", 2, 1),
  (3, "2017-11-09 19:00:00", "2017-11-15 23:00:00", 4, 1),
  (4, "2017-11-07 10:00:00", "2017-11-12 11:00:00", 3, 2),
  (5, "2017-12-08 13:00:00", "2017-12-08 15:00:00", 1, 2),
  (6, "2017-12-09 19:00:00", "2017-12-15 23:00:00", 2, 2);