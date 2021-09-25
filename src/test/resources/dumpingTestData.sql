SET SQL_SAFE_UPDATES = 0;

--
-- Deleting data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
ALTER TABLE `transaction` DISABLE KEYS;
delete from `transaction`;
-- INSERT INTO `transaction` VALUES ('+',1,'4',3.8,'2021-08-14 20:02:28.431000','Default_Status',2),('-',3,'1',1.05,'2021-08-14 20:02:53.589000','Default_Status',2),('+',4,'100',95,'2021-08-14 20:19:05.963000','Default_Status',5),('-',5,'50',52.5,'2021-08-14 20:19:20.178000','Default_Status',5),('+',6,'50',47.5,'2021-08-14 20:19:20.221000','Default_Status',2);
ALTER TABLE `transaction` ENABLE KEYS;
UNLOCK TABLES;
commit;

--
-- Deleting data for table `account`
--

LOCK TABLES `account` WRITE;
ALTER TABLE `account` DISABLE KEYS;
delete from `account`;
-- INSERT INTO `account` VALUES (1,0,'2021-08-14 18:02:26.558000','soufiene.mail_01@gmail.com'),(2,50.25,'2021-08-14 18:14:26.845000','soufiene.mail_01@gmail.com'),(3,0,'2021-08-14 18:14:36.525000','soufiene.mail_01@gmail.com'),(4,0,'2021-08-14 20:18:21.062000','soufiene.mail_01@gmail.com'),(5,42.5,'2021-08-14 20:18:32.858000','james.mail_02@gmail.com'),(6,0,'2021-08-14 20:21:55.200000','soufiene.mail_01@gmail.com'),(7,0,'2021-08-14 20:22:02.259000','james.mail_02@gmail.com');
ALTER TABLE `account` ENABLE KEYS;
UNLOCK TABLES;
commit;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
ALTER TABLE `account` DISABLE KEYS;
-- delete from `account`;
INSERT INTO `account` VALUES (1,0,'2021-08-14 18:02:26.558000','soufiene.mail_01@gmail.com'),(2,50.25,'2021-08-14 18:14:26.845000','soufiene.mail_01@gmail.com'),(3,0,'2021-08-14 18:14:36.525000','soufiene.mail_01@gmail.com'),(4,0,'2021-08-14 20:18:21.062000','soufiene.mail_01@gmail.com'),(5,42.5,'2021-08-14 20:18:32.858000','james.mail_02@gmail.com'),(6,0,'2021-08-14 20:21:55.200000','soufiene.mail_01@gmail.com'),(7,0,'2021-08-14 20:22:02.259000','james.mail_02@gmail.com');
ALTER TABLE `account` ENABLE KEYS;
UNLOCK TABLES;
commit;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
ALTER TABLE `transaction` DISABLE KEYS;
-- delete from `transaction`;
INSERT INTO `transaction` VALUES ('+',1,'4',3.8,'2021-08-14 20:02:28.431000','Default_Status',2),('-',3,'1',1.05,'2021-08-14 20:02:53.589000','Default_Status',2),('+',4,'100',95,'2021-08-14 20:19:05.963000','Default_Status',5),('-',5,'50',52.5,'2021-08-14 20:19:20.178000','Default_Status',5),('+',6,'50',47.5,'2021-08-14 20:19:20.221000','Default_Status',2);
ALTER TABLE `transaction` ENABLE KEYS;
UNLOCK TABLES;
commit;