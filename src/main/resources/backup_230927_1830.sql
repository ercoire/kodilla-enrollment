-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (x86_64)
--
-- Host: localhost    Database: kodilla_enrollment
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course_enrollment_notification`
--

DROP TABLE IF EXISTS `course_enrollment_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_enrollment_notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_enrollment_notification`
--

LOCK TABLES `course_enrollment_notification` WRITE;
/*!40000 ALTER TABLE `course_enrollment_notification` DISABLE KEYS */;
INSERT INTO `course_enrollment_notification` VALUES (3,'ballet for kids','girlfromgables@apple.com','Annie Shirley'),(4,'ballet for kids','helenjones@office.com','Helen Jones'),(5,'ballet for kids','jenny.n@gmail.com','Jenifer Norton'),(7,'West Coast Swing 2','westie@yahoo.com','Linda West'),(8,'West Coast Swing 3','c.jones@mail.com','Cathy Jones'),(9,'West Coast Swing 1','girlfromgables@apple.com','Annie Shirley'),(10,'West Coast Swing 1','jen_ave@domain.com','Jennifer Avery'),(11,'West Coast Swing 1','westie@yahoo.com','Linda West');
/*!40000 ALTER TABLE `course_enrollment_notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=kasia@`localhost`*/ /*!50003 TRIGGER `NOTIFICATIONS_CREATE` AFTER INSERT ON `course_enrollment_notification` FOR EACH ROW BEGIN
    INSERT INTO COURSE_NOTIFICATION_AUD (EVENT_DATE, EVENT_TYPE, NOTIFICATION_ID, NEW_COURSE, new_EMAIL,
                                         new_STUDENT_NAME)
        VALUE (CURTIME(), 'INSERT', NEW.ID, NEW.course, NEW.email, NEW.student_name);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=kasian@`localhost`*/ /*!50003 TRIGGER `NOTIFICATIONS_DELETE` AFTER DELETE ON `course_enrollment_notification` FOR EACH ROW BEGIN
    INSERT INTO COURSE_NOTIFICATION_AUD (EVENT_DATE, EVENT_TYPE, NOTIFICATION_ID, OLD_COURSE, OLD_EMAIL,
                                         OLD_STUDENT_NAME)
        VALUE(CURTIME(), 'INSERT', OLD.ID, OLD.course, OLD.email, OLD.student_name);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `day` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `price_per_month` int DEFAULT NULL,
  `starting_date` date DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (37,'MONDAY','test',50,'2023-09-30',150,'2023-05-01','19:00:00.000000','title'),(38,'MONDAY','Monday test',40,'2023-11-01',190,'2023-01-01','18:40:00.000000','test title'),(40,'SATURDAY','open level',90,'2023-09-30',150,'2023-09-28','18:00:00.000000','back to basics'),(41,'SUNDAY','tips&tricks',120,'2023-09-30',180,'2023-09-28','15:45:00.000000','level up!'),(42,'MONDAY','for those who want to get (or develop) stage experience',60,'2023-10-19',50,'2023-08-31','17:00:00.000000','choreo class'),(43,'THURSDAY','for Intermediate and Advanced',50,'2024-02-19',90,'2023-09-11','16:00:00.000000','West Coast Swing 3'),(44,'TUESDAY','everyone 40+ is welcome',70,'2023-10-01',75,'2023-09-03','18:30:00.000000','senior group'),(45,'SATURDAY','from 0 to dancer, intensive',480,'2023-10-07',120,'2023-10-06','11:00:00.000000','Weekender intro'),(47,'TUESDAY','intro level (Newcomer included)',60,'2023-12-02',130,'2023-09-23','17:00:00.000000','West Coast Swing 1'),(48,'WEDNESDAY','Novice level',60,'2023-09-29',110,'2023-09-17','19:00:00.000000','West Coast Swing 2'),(109,'MONDAY','Newcomer and up',60,'2024-01-23',100,'2023-10-03','20:00:00.000000','West Coast Swing booster'),(110,'THURSDAY','*',50,'2023-09-30',120,'2023-09-03','21:00:00.000000','wcs test'),(114,'MONDAY','*',60,'2024-03-17',60,'2023-09-17','21:00:00.000000','trigger  test');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `COURSES_CREATE` AFTER INSERT ON `courses` FOR EACH ROW BEGIN
    INSERT INTO COURSES_AUD (EVENT_DATE, EVENT_TYPE, COURSE_ID, new_day, new_description, new_duration, new_end_date,
                             NEW_PRICE_PER_MONTH, new_starting_date, new_time, new_title)
        VALUE (CURTIME(), 'INSERT', NEW.ID, NEW.day, NEW.description, NEW.duration, NEW.end_date, NEW.price_per_month,
               NEW.starting_date, NEW.time, NEW_TITLE);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `COURSES_UPDATE` AFTER UPDATE ON `courses` FOR EACH ROW BEGIN
    INSERT INTO COURSES_AUD (EVENT_DATE, EVENT_TYPE, COURSE_ID, new_day, new_description, new_duration, new_end_date,
                             NEW_PRICE_PER_MONTH, new_starting_date, new_time, new_title, old_day, old_description,
                             old_duration, old_end_date, old_price_per_month, old_starting_date, old_time, old_title)
        VALUE (CURTIME(), 'UPDATE', OLD.ID, NEW.day, NEW.description, NEW.duration, NEW.end_date, NEW.price_per_month,
               NEW.starting_date, NEW.time, NEW_TITLE, OLD.day, OLD.description, OLD.duration, OLD.end_date,
               OLD.price_per_month, OLD.starting_date, OLD.time, OLD.title);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `COURSES_DELETE` AFTER DELETE ON `courses` FOR EACH ROW BEGIN
    INSERT INTO COURSES_AUD (EVENT_DATE, EVENT_TYPE, COURSE_ID, old_day, old_description, old_duration, old_end_date,
                             old_price_per_month, old_starting_date, old_time, old_title)
        VALUE (CURTIME(), 'DELETE', OLD.ID, OLD.day, OLD.description, OLD.duration, OLD.end_date, OLD.price_per_month,
               OLD.starting_date, OLD.time, OLD.title);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `COURSES_AUD`
--

DROP TABLE IF EXISTS `COURSES_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `COURSES_AUD` (
  `EVENT_ID` int NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` datetime NOT NULL,
  `EVENT_TYPE` varchar(255) DEFAULT NULL,
  `COURSE_ID` int DEFAULT NULL,
  `OLD_TITLE` varchar(255) DEFAULT NULL,
  `NEW_TITLE` varchar(255) DEFAULT NULL,
  `OLD_DAY` varchar(255) DEFAULT NULL,
  `NEW_DAY` varchar(255) DEFAULT NULL,
  `OLD_DESCRIPTION` varchar(255) DEFAULT NULL,
  `NEW_DESCRIPTION` varchar(255) DEFAULT NULL,
  `OLD_DURATION` int DEFAULT NULL,
  `NEW_DURATION` int DEFAULT NULL,
  `OLD_END_DATE` date DEFAULT NULL,
  `NEW_END_DATE` date DEFAULT NULL,
  `OLD_STARTING_DATE` date DEFAULT NULL,
  `NEW_STARTING_DATE` date DEFAULT NULL,
  `OLD_PRICE_PER_MONTH` int DEFAULT NULL,
  `NEW_PRICE_PER_MONTH` int DEFAULT NULL,
  `OLD_TIME` time(6) DEFAULT NULL,
  `NEW_TIME` time(6) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COURSES_AUD`
--

LOCK TABLES `COURSES_AUD` WRITE;
/*!40000 ALTER TABLE `COURSES_AUD` DISABLE KEYS */;
INSERT INTO `COURSES_AUD` VALUES (1,'2023-09-27 12:09:13','DELETE',111,'west test 2',NULL,'FRIDAY',NULL,'*',NULL,45,NULL,'2023-09-12',NULL,'2023-09-05',NULL,50,NULL,'06:00:00.000000',NULL),(2,'2023-09-27 12:10:28','INSERT',114,'notification  test',NULL,'MONDAY','MONDAY','*','*',40,40,'2024-03-17','2024-03-17','2023-09-17','2023-09-17',60,60,'21:00:00.000000','21:00:00.000000'),(3,'2023-09-27 12:20:57','UPDATE',114,'trigger  test',NULL,'MONDAY','MONDAY','*','*',40,60,'2024-03-17','2024-03-17','2023-09-17','2023-09-17',60,60,'21:00:00.000000','21:00:00.000000');
/*!40000 ALTER TABLE `COURSES_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses_to_teachers`
--

DROP TABLE IF EXISTS `courses_to_teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses_to_teachers` (
  `course_id` bigint NOT NULL,
  `teacher_id` bigint NOT NULL,
  KEY `FK548sunjdrtb8y9k6kn5wrbmlg` (`teacher_id`),
  KEY `FKmkaf658y43oqq1qge2r16r0f6` (`course_id`),
  CONSTRAINT `FK548sunjdrtb8y9k6kn5wrbmlg` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`),
  CONSTRAINT `FKmkaf658y43oqq1qge2r16r0f6` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses_to_teachers`
--

LOCK TABLES `courses_to_teachers` WRITE;
/*!40000 ALTER TABLE `courses_to_teachers` DISABLE KEYS */;
INSERT INTO `courses_to_teachers` VALUES (40,27),(42,23),(38,27),(38,28),(41,24),(110,38),(110,29),(44,31),(44,35),(48,28),(114,23);
/*!40000 ALTER TABLE `courses_to_teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6ooq278k2bs5xi8t5o6oort1v` (`student_id`),
  KEY `FK8nlm4urshp5drsk0nlkprig36` (`course_id`),
  CONSTRAINT `FK6ooq278k2bs5xi8t5o6oort1v` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FK8nlm4urshp5drsk0nlkprig36` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (17,150,37,'2023-09-19',28),(18,50,37,NULL,28),(19,50,37,'2023-09-06',28),(20,50,37,'2023-09-06',28),(24,180,38,'2023-01-09',33),(25,150,38,'2023-09-14',32),(26,90,41,'2023-09-13',33),(27,80,41,'2023-10-02',47),(29,150,40,'2023-09-15',47),(30,100,45,'2023-09-25',30),(31,95,45,'2023-09-18',37),(32,120,38,'2023-09-26',44),(33,100,43,'2023-09-30',30),(55,120,114,'2023-07-26',91);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=kasia@`localhost`*/ /*!50003 TRIGGER `PAYMENTS_CREATE` AFTER INSERT ON `payments` FOR EACH ROW BEGIN
    INSERT INTO PAYMENTS_AUD (EVENT_DATE, EVENT_TYPE, ID, NEW_AMOUNT, NEW_COURSE_ID, NEW_PAYMENT_DATE, NEW_STUDENT_ID)
        VALUE (CURRENT_TIME, 'INSERT', NEW.ID, NEW.amount, NEW.course_id, NEW.payment_date, NEW.student_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=kasia@`localhost`*/ /*!50003 TRIGGER `PAYMENTS_DELETE` AFTER DELETE ON `payments` FOR EACH ROW BEGIN
    INSERT INTO PAYMENTS_AUD (EVENT_DATE, EVENT_TYPE, ID, OLD_AMOUNT, OLD_COURSE_ID, OLD_PAYMENT_DATE, OLD_STUDENT_ID)
        VALUE (CURTIME(), 'DELETE', OLD.ID, OLD.amount, OLD.course_id, OLD.payment_date, OLD.student_id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `PAYMENTS_AUD`
--

DROP TABLE IF EXISTS `PAYMENTS_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PAYMENTS_AUD` (
  `EVENT_ID` int NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` datetime NOT NULL,
  `EVENT_TYPE` varchar(255) DEFAULT NULL,
  `ID` int DEFAULT NULL,
  `OLD_AMOUNT` varchar(255) DEFAULT NULL,
  `NEW_AMOUNT` varchar(255) DEFAULT NULL,
  `OLD_COURSE_ID` varchar(255) DEFAULT NULL,
  `NEW_COURSE_ID` varchar(255) DEFAULT NULL,
  `OLD_PAYMENT_DATE` date DEFAULT NULL,
  `NEW_PAYMENT_DATE` date DEFAULT NULL,
  `OLD_STUDENT_ID` varchar(255) DEFAULT NULL,
  `NEW_STUDENT_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAYMENTS_AUD`
--

LOCK TABLES `PAYMENTS_AUD` WRITE;
/*!40000 ALTER TABLE `PAYMENTS_AUD` DISABLE KEYS */;
INSERT INTO `PAYMENTS_AUD` VALUES (1,'2023-09-27 12:19:56','INSERT',55,NULL,'120',NULL,'114',NULL,'2023-07-26',NULL,'91');
/*!40000 ALTER TABLE `PAYMENTS_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_to_course`
--

DROP TABLE IF EXISTS `student_to_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_to_course` (
  `student_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  KEY `FK5aogcas74tx03ydb8bp3imoyk` (`course_id`),
  KEY `FKmvrc9qq8sqe65e9ar8dmqcqyj` (`student_id`),
  CONSTRAINT `FK5aogcas74tx03ydb8bp3imoyk` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKmvrc9qq8sqe65e9ar8dmqcqyj` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_to_course`
--

LOCK TABLES `student_to_course` WRITE;
/*!40000 ALTER TABLE `student_to_course` DISABLE KEYS */;
INSERT INTO `student_to_course` VALUES (32,41),(32,38),(29,38),(29,38),(47,38),(47,44),(28,37),(28,38),(28,38),(28,44),(28,41),(88,43),(44,44),(44,40),(44,37),(30,38),(30,44),(33,42),(33,38),(33,41),(33,47),(91,47),(37,43),(37,38),(37,44),(37,48),(37,47);
/*!40000 ALTER TABLE `student_to_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (28,'John','Smith','john@smith.eu'),(29,'Adam','Brown','smartguy55@yahoo.com'),(30,'Jenifer','Norton','jenny.n@gmail.com'),(32,'Susan','Addams','suzzie_add@gmail.com'),(33,'Annie','Shirley','girlfromgables@apple.com'),(37,'Linda','West','westie@yahoo.com'),(44,'Helen','Jones','helenjones@office.com'),(47,'Marie-Ann','Smith-Brown','marie@myowncompany.com'),(74,'Ben','Morris','ben@morris.com'),(88,'Cathy','Jones','c.jones@mail.com'),(90,'Amy','Roth','amyroth@mail.org'),(91,'Jennifer','Avery','jen_ave@domain.com');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `STUDENTS_CREATE` AFTER INSERT ON `students` FOR EACH ROW BEGIN
    INSERT INTO STUDENTS_AUD (EVENT_DATE, EVENT_TYPE, STUDENT_ID, new_firstname, new_lastname, new_email)
        VALUE (CURTIME(), 'INSERT', NEW.ID, NEW.firstname, NEW.lastname, NEW.email);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `STUDENTS_UPDATE` AFTER UPDATE ON `students` FOR EACH ROW BEGIN
    INSERT INTO STUDENTS_AUD (EVENT_DATE, EVENT_TYPE, STUDENT_ID, new_firstname, new_lastname, new_email, old_firstname,
                              old_lastname, old_email)
        VALUE (CURTIME(), 'UPDATE', OLD.ID, NEW.firstname, NEW.lastname, NEW.email, OLD.firstname, OLD.lastname,
               old.email);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `STUDENTS_DELETE` AFTER DELETE ON `students` FOR EACH ROW BEGIN
    INSERT INTO STUDENTS_AUD (EVENT_DATE, EVENT_TYPE, STUDENT_ID, old_firstname, old_lastname, old_email)
        VALUE (curtime(), 'DELETE', old.id, OLD.firstname, OLD.lastname, old.email);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `STUDENTS_AUD`
--

DROP TABLE IF EXISTS `STUDENTS_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `STUDENTS_AUD` (
  `EVENT_ID` int NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` datetime NOT NULL,
  `EVENT_TYPE` varchar(255) DEFAULT NULL,
  `STUDENT_ID` int DEFAULT NULL,
  `OLD_FIRSTNAME` varchar(255) DEFAULT NULL,
  `NEW_FIRSTNAME` varchar(255) DEFAULT NULL,
  `OLD_LASTNAME` varchar(255) DEFAULT NULL,
  `NEW_LASTNAME` varchar(255) DEFAULT NULL,
  `OLD_EMAIL` varchar(255) DEFAULT NULL,
  `NEW_EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STUDENTS_AUD`
--

LOCK TABLES `STUDENTS_AUD` WRITE;
/*!40000 ALTER TABLE `STUDENTS_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `STUDENTS_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (23,'new to the team','Gertrude','Mulligan'),(24,'just a few words','Madison','Ford'),(27,'volcano of energy','Will','Stevens'),(28,'experienced dancer, teacher and performer; began as a 5y.o. kid who had to move all the time. This journey led him to multiple wins and titles throughout the years\n\n','Milo','Brooks'),(29,'TBD','Leo','Cromwell'),(30,'TBD','Brigit','Owen'),(31,'\"anyone can dance!\"','Olivia','Johns'),(34,'passionate for teaching!','Maggie','Smith'),(35,'be yourself- dance!','Tom','Miller'),(38,'Hi!','Sandy','Garcia');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `TEACHERS_CREATE` AFTER INSERT ON `teachers` FOR EACH ROW BEGIN
    INSERT INTO TEACHERS_AUD (EVENT_DATE, EVENT_TYPE, TEACHER_ID, NEW_DESCRIPTION, NEW_FIRSTNAME, NEW_LASTNAME)
        VALUE (CURRENT_TIME, 'INSERT', NEW.ID, NEW.DESCRIPTION, NEW.FIRSTNAME, NEW.LASTNAME);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `TEACHERS_UPDATE` AFTER UPDATE ON `teachers` FOR EACH ROW BEGIN
    INSERT INTO TEACHERS_AUD(EVENT_DATE, EVENT_TYPE, TEACHER_ID, NEW_DESCRIPTION, NEW_FIRSTNAME, NEW_LASTNAME,
                             OLD_DESCRIPTION, OLD_FIRSTNAME, OLD_LASTNAME)
    VALUES (CURTIME(), 'UPDATE', OLD.ID, NEW.DESCRIPTION, NEW.FIRSTNAME, NEW.LASTNAME, OLD.DESCRIPTION,
            OLD.FIRSTNAME, OLD.LASTNAME);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kasia`@`localhost`*/ /*!50003 TRIGGER `TEACHERS_DELETE` AFTER DELETE ON `teachers` FOR EACH ROW BEGIN
    INSERT INTO TEACHERS_AUD (EVENT_DATE, EVENT_TYPE, TEACHER_ID, OLD_DESCRIPTION, OLD_FIRSTNAME, OLD_LASTNAME)
        VALUE (CURTIME(), 'DELETE', OLD.ID, OLD.DESCRIPTION, OLD.FIRSTNAME, OLD.LASTNAME);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `TEACHERS_AUD`
--

DROP TABLE IF EXISTS `TEACHERS_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEACHERS_AUD` (
  `EVENT_ID` int NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` datetime NOT NULL,
  `EVENT_TYPE` varchar(255) DEFAULT NULL,
  `TEACHER_ID` int DEFAULT NULL,
  `OLD_FIRSTNAME` varchar(255) DEFAULT NULL,
  `NEW_FIRSTNAME` varchar(255) DEFAULT NULL,
  `OLD_LASTNAME` varchar(255) DEFAULT NULL,
  `NEW_LASTNAME` varchar(255) DEFAULT NULL,
  `OLD_DESCRIPTION` varchar(255) DEFAULT NULL,
  `NEW_DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEACHERS_AUD`
--

LOCK TABLES `TEACHERS_AUD` WRITE;
/*!40000 ALTER TABLE `TEACHERS_AUD` DISABLE KEYS */;
INSERT INTO `TEACHERS_AUD` VALUES (1,'2023-09-27 12:08:22','UPDATE',37,'Mat','Matty','Anderson','Anderson','tbd','tbd'),(2,'2023-09-27 12:17:17','DELETE',37,'Matty',NULL,'Anderson',NULL,'tbd',NULL);
/*!40000 ALTER TABLE `TEACHERS_AUD` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-27 14:31:12
