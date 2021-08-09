--+---------+----------+-----------------------------+
--| john    | fun123   | ROLE_EMPLOYEE               |
--| mary    | fun123   | ROLE_EMPLOYEE, ROLE_MANAGER |
--| susan   | fun123   | ROLE_EMPLOYEE, ROLE_ADMIN   |
--+---------+----------+-----------------------------+
delete from `user`;
INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES
('john','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Doe','john@ouertani.com'),
('mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Public','mary@ouertani.com'),
('susan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Susan','Adams','susan@ouertani.com');


commit;
delete from `role`;

INSERT INTO `role` (name)
VALUES
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');
commit;
delete from `users_roles`;
INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);
commit;
