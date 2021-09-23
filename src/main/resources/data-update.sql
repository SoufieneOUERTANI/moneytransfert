-----------------------------------------------------------------------------------------------------
-- This file is used in case of "spring.jpa.hibernate.ddl-auto = update" in application.properties --
-- to init some data samples                                                                       --
-----------------------------------------------------------------------------------------------------
INSERT INTO role (name) VALUES ('ROLE_EMPLOYEE');
INSERT INTO role (name) VALUES ('ROLE_MANAGER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

-- INSERT INTO `user` VALUES (1,'soufiene.mail_01@gmail.com','Soufiene','OUERTANI','$2a$10$fCFe3vct4X14KXAGGQ0gUueNXOvnjLpegXjR5gaykjkGq9ji3bXS6','soufiene.mail_01@gmail.com'),(2,'James.mail_02@gmail.com','James','pack','$2a$10$wKzg.amv98CayW3fP8EPg.Bdq1xwFbPGofLvM33gA55LHu2cR/DXe','James.mail_02@gmail.com');
INSERT INTO `user` VALUES (1,'soufiene.mail_01@gmail.com','Soufiene','OUERTANI','$2a$12$/CafDalDSe9qKg1CdU.NmezfSij4RVw0MHAN5QwNUmrE71uhNCJdq','soufiene.mail_01@gmail.com'),(2,'James.mail_02@gmail.com','James','pack','$2a$12$xe/B4h6hBumKddJociD2Le2fvD61y3KJj3OOkN/aIzOy4zsf2p8Ke','James.mail_02@gmail.com');

INSERT INTO `users_roles` VALUES (1,1),(2,1);
INSERT INTO `client` VALUES ('james.mail_02@gmail.com',NULL,NULL,'James','pack'),('soufiene.mail_01@gmail.com',NULL,NULL,'Soufiene','OUERTANI');
INSERT INTO `account` VALUES (1,0,'2021-08-14 18:02:26.558000','soufiene.mail_01@gmail.com'),(2,50.25,'2021-08-14 18:14:26.845000','soufiene.mail_01@gmail.com'),(3,0,'2021-08-14 18:14:36.525000','soufiene.mail_01@gmail.com'),(4,0,'2021-08-14 20:18:21.062000','soufiene.mail_01@gmail.com'),(5,42.5,'2021-08-14 20:18:32.858000','james.mail_02@gmail.com'),(6,0,'2021-08-14 20:21:55.200000','soufiene.mail_01@gmail.com'),(7,0,'2021-08-14 20:22:02.259000','james.mail_02@gmail.com');
INSERT INTO `transaction` VALUES ('+',1,'4',3.8,'2021-08-14 20:02:28.431000','Default_Status',2),('-',3,'1',1.05,'2021-08-14 20:02:53.589000','Default_Status',2),('+',4,'100',95,'2021-08-14 20:19:05.963000','Default_Status',5),('-',5,'50',52.5,'2021-08-14 20:19:20.178000','Default_Status',5),('+',6,'50',47.5,'2021-08-14 20:19:20.221000','Default_Status',2);

