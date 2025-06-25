-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: members_system
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'admin','12345678');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `attendance_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `attendance_date` date DEFAULT NULL,
  `status` enum('present','absent','excused') NOT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,1,'2025-06-23','present'),(2,2,'2025-06-23','present'),(3,3,'2025-06-23','absent'),(4,4,'2025-06-23','absent'),(5,5,'2025-06-23','excused'),(6,1,'2025-06-25','present'),(7,2,'2025-06-25','absent'),(8,3,'2025-06-25','present'),(9,4,'2025-06-25','present'),(10,5,'2025-06-25','excused'),(11,6,'2025-06-25','absent');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `pwd_id_number` varchar(50) DEFAULT NULL,
  `disability_type` varchar(100) DEFAULT NULL,
  `date_issued` date DEFAULT NULL,
  `id_valid_until` date DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `age` int DEFAULT NULL,
  `sex` enum('Male','Female','Other') DEFAULT NULL,
  `civil_status` varchar(50) DEFAULT NULL,
  `place_of_birth` varchar(100) DEFAULT NULL,
  `education_level` varchar(100) DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `address` text,
  `mobile_number` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fb_account_name` varchar(100) DEFAULT NULL,
  `guardian_name` varchar(100) DEFAULT NULL,
  `guardian_relation` varchar(50) DEFAULT NULL,
  `guardian_mobile` varchar(20) DEFAULT NULL,
  `takes_medications` tinyint(1) DEFAULT '0',
  `status` enum('Alive','Deceased','Renewed','Expired') DEFAULT 'Alive',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Agustin, Jana Beatrice, Balutan','13-7405-000-0000002','Psychosocial','2025-06-20','2030-06-20','2004-06-29',20,'Female','Married','Binan','High School Graduate','None','B9 L20 Sulong Street Barangay Ibaba','09953649868','jeatricebana@gmail.com','Jana','Jana Paola Agustin','Sister','09461246598',0,'Alive'),(2,'Desalit, Joseph Ismael, Campos','03-6413-124-1234567','Diabetes','2024-04-15','2029-04-15','2004-07-18',20,'Male','Single','Laguna','High School Graduate','None','B6 L19 Dandelion Street Brgy. Ibaba','09012340784','josephdesalit628@gmail.com','Joseph Desalit','Gemma Ruth Desalit','Mother','09784561789',0,'Alive'),(3,'Dudas, Alliah Kamyll, Ganiban','18-2920-321-325478','Psychosocial','2017-01-28','2022-01-28','2004-11-25',20,'Female','Single','Laguna','High School Graduate','None','B4 L25 Bumbong Street Brgy. Ibaba','09546781248','alliahdudasganda@gmail.com','Alliah Dudas','Nenita Dudas','Mother','09457896542',0,'Alive'),(4,'Buenaventura, Faith, Beltran','09-1457-356-7845692','Difficulty in Breathing','2025-01-30','2030-01-30','2004-04-29',21,'Female','Single','Manila','High School Graduate','Entrepreneur','B6 L20 Lily Street Brgy. Ibaba','09472153657','faithbeltran@gmail.com','Faith Buenaventura','Jana Beatrice Agustin','Friend','09953649868',1,'Alive'),(5,'Enriquez, Janby, Pogi','69-2456-420-1234567','Autism','2018-01-24','2023-01-24','1969-06-14',56,'Male','Single','Cavite','College Graduate','Marine','B2 L18 Niyugan Street Brgy. Ibaba','09694201234','janvypogi@gmail.com','Janvy Enriquez','Hans Casambros','Caretaker','09126578947',1,'Alive'),(6,'Penus, Eliand John, Nolasco','15-1235-987-9874562','Autism','2023-12-25','2028-12-25','2004-01-08',21,'Male','Married','Laguna','High School Graduate','None','B1 L13 Emerald Street Brgy. Ibaba','09064123546','eliandpenus@gmail.com','Eliand Penus','Emilie Penus','Mother','09124561234',1,'Alive');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members_conditions`
--

DROP TABLE IF EXISTS `members_conditions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members_conditions` (
  `member_id` int NOT NULL,
  `diabetes` tinyint(1) DEFAULT '0',
  `stroke` tinyint(1) DEFAULT '0',
  `heart_problems` tinyint(1) DEFAULT '0',
  `cancer` tinyint(1) DEFAULT '0',
  `high_blood` tinyint(1) DEFAULT '0',
  `lung_problems` tinyint(1) DEFAULT '0',
  `arthritis` tinyint(1) DEFAULT '0',
  `osteoporosis` tinyint(1) DEFAULT '0',
  `epilepsy` tinyint(1) DEFAULT '0',
  `kidney_problems` tinyint(1) DEFAULT '0',
  `other_conditions` text,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `members_conditions_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members_conditions`
--

LOCK TABLES `members_conditions` WRITE;
/*!40000 ALTER TABLE `members_conditions` DISABLE KEYS */;
INSERT INTO `members_conditions` VALUES (1,0,0,0,1,0,1,0,0,0,0,''),(2,1,0,0,0,0,0,0,0,0,0,''),(3,0,0,0,0,0,0,0,0,0,0,''),(4,0,0,0,0,0,1,0,0,0,0,''),(5,0,0,0,0,0,0,0,0,1,0,''),(6,1,1,1,1,1,1,1,1,1,1,'');
/*!40000 ALTER TABLE `members_conditions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members_household`
--

DROP TABLE IF EXISTS `members_household`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members_household` (
  `household_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `relationship` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `civil_status` varchar(50) DEFAULT NULL,
  `education_level` varchar(100) DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`household_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `members_household_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members_household`
--

LOCK TABLES `members_household` WRITE;
/*!40000 ALTER TABLE `members_household` DISABLE KEYS */;
INSERT INTO `members_household` VALUES (5,5,'Paolo De los Santos','Husband',58,'1966-11-05','Married','College Graduate','Layout Artist'),(6,6,'Emilie Penus','Mother',63,'1961-10-18','Married','College Undergraduate','None'),(25,1,'Mary Jane Agustin','Mother',48,'1978-04-13','Married','High School Undergrad','None'),(26,1,'Ronald Agustin','Father',56,'1969-03-26','Married','College Undergrad','None'),(27,1,'Jana Paola Agustin','Sister',21,'2003-09-16','Single','College Undergrad','None'),(28,2,'Gemma Ruth Desalit','Mother',40,'1776-07-20','Married','College Graduate','Vendor');
/*!40000 ALTER TABLE `members_household` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members_medications`
--

DROP TABLE IF EXISTS `members_medications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members_medications` (
  `med_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `medication_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`med_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `members_medications_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members_medications`
--

LOCK TABLES `members_medications` WRITE;
/*!40000 ALTER TABLE `members_medications` DISABLE KEYS */;
INSERT INTO `members_medications` VALUES (1,4,'albuterol'),(2,5,'Antipsychotics'),(3,5,'Risperidone'),(4,5,'Aripiprazole'),(5,6,'Cetirizine'),(6,6,'Cetirizine'),(7,6,'Cetirizine'),(8,6,'Cetirizine'),(9,6,'Cetirizine'),(10,6,'Cetirizine'),(11,6,'Cetirizine'),(12,6,'Cetirizine'),(13,6,'Cetirizine'),(14,6,'Cetirizine'),(15,6,'Robust'),(16,6,'Cetirizine'),(17,6,'Cetirizine'),(18,6,'Cetirizine'),(19,6,'Cetirizine');
/*!40000 ALTER TABLE `members_medications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-25 20:45:17
