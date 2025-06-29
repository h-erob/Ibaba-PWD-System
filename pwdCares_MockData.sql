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
INSERT INTO `admins` VALUES (1,'admin','123');
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,1,'2025-06-23','present'),(2,2,'2025-06-23','present'),(3,3,'2025-06-23','excused'),(4,4,'2025-06-23','excused'),(5,5,'2025-06-23','excused'),(6,1,'2025-06-25','present'),(7,2,'2025-06-25','present'),(8,3,'2025-06-25','present'),(9,4,'2025-06-25','present'),(10,5,'2025-06-25','excused'),(11,6,'2025-06-25','excused'),(12,1,'2025-06-27','present'),(13,4,'2025-06-27','present'),(14,7,'2025-06-27','excused'),(15,9,'2025-06-27','present'),(16,2,'2025-06-27','present'),(17,3,'2025-06-27','present'),(18,5,'2025-06-27','excused'),(19,6,'2025-06-27','present'),(20,8,'2025-06-27','excused'),(21,10,'2025-06-27','present'),(42,1,'2025-06-28','present'),(43,11,'2025-06-28','excused'),(44,4,'2025-06-28','present'),(45,7,'2025-06-28','present'),(46,12,'2025-06-28','absent'),(47,9,'2025-06-28','absent'),(48,2,'2025-06-28','present'),(49,3,'2025-06-28','present'),(50,5,'2025-06-28','present'),(51,14,'2025-06-28','excused'),(52,6,'2025-06-28','present'),(53,8,'2025-06-28','excused'),(54,15,'2025-06-28','present'),(55,13,'2025-06-28','present'),(56,10,'2025-06-28','present'),(57,20,'2025-06-28','present'),(58,1,'2025-06-29','present'),(59,4,'2025-06-29','present'),(60,7,'2025-06-29','present'),(61,12,'2025-06-29','absent'),(62,2,'2025-06-29','present'),(63,23,'2025-06-29','absent'),(64,3,'2025-06-29','present'),(65,5,'2025-06-29','present'),(66,17,'2025-06-29','absent'),(67,14,'2025-06-29','absent'),(68,16,'2025-06-29','absent'),(69,25,'2025-06-29','absent'),(70,6,'2025-06-29','present'),(71,8,'2025-06-29','absent'),(72,22,'2025-06-29','absent'),(73,15,'2025-06-29','absent'),(74,18,'2025-06-29','absent'),(75,13,'2025-06-29','absent'),(76,10,'2025-06-29','excused'),(77,19,'2025-06-29','absent'),(78,24,'2025-06-29','absent');
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
  `fill_up_date` date DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Agustin, Jana Beatrice, Balutan','13-7405-000-0000002','Psychosocial','2025-07-01','2025-06-20','2030-06-20','2004-06-29',20,'Female','Married','Binan','High School Graduate','None','B9 L20 Sulong Street Barangay Ibaba','09953649868','jeatricebana@gmail.com','Jana','Jana Paola Agustin','Sister','09461246598',1,'Alive'),(2,'Desalit, Joseph Ismael, Campos','03-6413-124-1234567','Diabetes','2025-07-02','2024-04-15','2029-04-15','2004-07-18',20,'Male','Single','Laguna','High School Graduate','None','B6 L19 Dandelion Street Brgy. Ibaba','09012340784','josephdesalit628@gmail.com','Joseph Desalit','Gemma Ruth Desalit','Mother','09784561789',0,'Alive'),(3,'Dudas, Alliah Kamyll, Ganiban','18-2920-321-325478','Psychosocial','2025-06-28','2017-01-28','2027-01-28','2004-11-25',20,'Female','Single','Laguna','High School Graduate','None','B4 L25 Bumbong Street Brgy. Ibaba','09546781248','alliahdudasganda@gmail.com','Alliah Dudas','Nenita Dudas','Mother','09457896542',0,'Renewed'),(4,'Buenaventura, Faith, Beltran','09-1457-356-7845692','Difficulty in Breathing','2025-06-01','2025-01-30','2030-01-30','2004-04-29',21,'Female','Single','Manila','High School Graduate','Entrepreneur','B6 L20 Lily Street Brgy. Ibaba','09472153657','faithbeltran@gmail.com','Faith Buenaventura','Jana Beatrice Agustin','Friend','09953649868',1,'Alive'),(5,'Enriquez, Janby, Pogi','69-2456-420-1234567','Autism','2025-06-29','2018-01-24','2028-01-24','1969-06-14',56,'Male','Single','Cavite','College Graduate','Marine','B2 L18 Niyugan Street Brgy. Ibaba','09694201234','janvypogi@gmail.com','Janvy Enriquez','Hans Casambros','Caretaker','09126578947',1,'Renewed'),(6,'Penus, Eliand John, Nolasco','15-1235-987-9874562','Autism','2025-07-02','2023-12-25','2028-12-25','2004-01-08',21,'Male','Married','Laguna','High School Graduate','None','B1 L13 Emerald Street Brgy. Ibaba','09064123546','eliandpenus@gmail.com','Eliand Penus','Emilie Penus','Mother','09124561234',1,'Alive'),(7,'Capacio, Peavey Iya, Jalayahay','06-1574-321-698754','Autism','2025-07-01','2025-06-12','2030-06-12','2005-05-22',20,'Female','Single','Manila','High School Graduate','None','B8 L9 Ambrocia Street Brgy. Ibaba','09941258749','peaveylabatete@gmail.com','Iya Capacio','Norman Capacio','Father','09781456547',0,'Alive'),(8,'Perucho, Earl Michael, Wowow','12-3456-789-1234567','Autism','2025-07-01','2015-01-08','2030-01-08','2005-09-20',19,'Male','Single','Cavite','High School Graduate','None','B3 L9 Rosario Street Brgy. Ibaba','09131234567','earlmichael@gmail.com','Earl Michael Perucho','Leigh Anne Perucho','Sister','09981246549',0,'Renewed'),(9,'Delos Santos, Paolo','03-0501-145-1111456','Autism','2018-06-28','2010-06-25','2020-01-22','2002-11-05',22,'Male','Single','Cavite','High School Graduate','Layout Artist','B4 L8 Lily Street Brgy. Ibaba','09784562316','pbdelossantos@gmail.com','Paolo Delos Santos','Roland-Elizabeth Delos Santos','Mother','09786541245',0,'Expired'),(10,'Serrano, Maria Antonette','12-1234-321-134567','Carpal Tunnel','2025-06-28','2023-09-12','2028-09-12','2000-06-21',25,'Female','Married','Paranaque','College Graduate','Radio Mixer','B8 L14 Crocus Street Brgy. Ibaba','09126543215','maanserano@gmail.com','Maan Serrano','Leyan Serrano','Mother','09781451234',0,'Alive'),(11,'Batumbakal, Jun Mar, Cantalino','17-1345-120-1234567','Dwarfism','2017-06-28','2018-06-21','2023-06-21','1996-01-23',29,'Male','Married','Pangasinan','College Graduate','Teacher','B8 L16 Olive Street Brgy. Ibaba','09123541237','junbatumbakal23@gmail.com','Jun Batumbakal','Melisa Batumbakal','Sister','09453216547',1,'Expired'),(12,'Dela Cruz, Juan, Magno','06-021-1995-0001432','Visual Impairment','2025-06-28','2023-04-10','2028-04-10','1995-02-15',30,'Male','Single','Manila','College Graduate','Freelance Writer','123 Sampaguita Street Brgy Ibaba','09171234567','juan.delacruz95@gmail.com','Juan Dela Cruz','Maria Domingo Dela Cruz','Mother','09178889999',1,'Alive'),(13,'Santos, Maria, Teresa','01-007-1980-0002345','Hearing Impairment','2025-02-28','2023-01-20','2028-01-20','1980-12-01',44,'Female','Married','San Pablo, Laguna','High School Graduate','Online Seller','B9 L12 Bayanihan Street Brgy. Ibaba','09981234567','maria.santos80@yahoo.com','MariaSantosOfficial','Ernesto Miranda Santos','Husband','09172223333',0,'Alive'),(14,'Lopez, Ana, Morales','02-003-2001-0009876','Intellectual Disability','2023-01-28','2021-03-05','2026-03-05','2001-07-21',23,'Female','Single','Antipolo','High School Graduate','None','B18 L14 Mahogany Street Brgy. Ibaba','09174561234','ana.lopez01@gmail.com','Ana Lopez','Lorna Devera Lopez','09179887766','Mother',1,'Alive'),(15,'Reyes, Michael, Torres','03-014-1972-0006789','Orthopedic Disability','2023-05-28','2020-07-15','2025-07-15','1972-11-30',52,'Male','Married','Davao','College Graduate','Call Center Agent','B3 L9 Mango Street Brgy. Ibaba','09175678901','mike.reyes72@outlook.com','Michael Reyes','Rowena Ilagan Reyes','Wife','09179994444',1,'Alive'),(16,'Mendoza, Carla Jean, Ramos','08-011-1996-0004321','Speech Impairment','2025-06-28','2023-03-15','2028-03-15','1996-06-12',29,'Female','Single','Lipa','College Graduate','Graphic Designer','B2 L5 Cherry Street Brgy. Ibaba','09172220001','carlamendoza96@gmail.com','Carla Mendoza','Marissa Ramos Mendoza','Mother','09175556666',0,'Alive'),(17,'Garcia, Antonio Miguel, Perez','03-015-1985-0008765','Orthopedic Disability','2025-06-28','2021-09-01','2026-09-01','1985-10-05',39,'Male','Married','Batangas','High School Graduate','Tricycle Driver','B1 L3 Mahogany Street Brgy. Ibaba','09173334444','antonio.garcia85@yahoo.com','Tony Garcia','Roselyn Perez Garcia','Wife','09179998888',1,'Alive'),(18,'Rivera, Jasmine Claire, Nolasco','07-005-1998-0001543','Psychosocial Disability','2022-06-28','2022-02-14','2027-02-14','1998-04-28',27,'Female','Single','San Fernando','College Undergraduate','None','B5 L7 Acacia Street Brgy. Ibaba','09176667777','jclaire.rivera@gmail.com','Jas Rivera','Norma Nolasco Rivera','Mother','09170001111',1,'Alive'),(19,'Torres, Marvin Elias, De Castro','05-009-1970-0006201','Low Vision','2021-06-28','2020-11-05','2025-11-05','1970-01-17',55,'Male','Married','Legazpi','College Graduate','High School Teacher','B4 L8 Narra Street Brgy. Ibaba','09170007755','marv.torres70@gmail.com','Marvin Torres','Luisa De Castro Torres','Wife','09179997766',0,'Alive'),(20,'Santos, Erika Denise, Cruz','04-012-1992-0003109','Hearing Impairment','2025-03-28','2023-08-08','2028-08-08','1992-09-19',32,'Female','Single','Taguig','College Graduate','Librarian','B7 L2 Rosewood Street Brgy. Ibaba','09178880012','erikasantos92@gmail.com','Erika D. Santos','Cynthia Cruz Santos','Mother','09170001234',0,'Deceased'),(21,'Cruz, Nathaniel James, Soriano','09-003-1994-0007845','Autism Spectrum Disorder','2021-06-28','2022-02-22','2027-02-22','1994-05-18',31,'Male','Single','Quezon','College Undergraduate','None','B3 L6 Maple Street Brgy. Ibaba','09178901123','nathan.cruz94@gmail.com','Nathaniel J. Cruz','Teresa Soriano Cruz','Mother','09179993321',1,'Deceased'),(22,'Ramos, Eloisa Marie, Bautista','01-005-1989-0006622','Visual Impairment','2021-06-15','2021-06-15','2026-06-15','1989-11-25',35,'Female','Married','Caloocan','High School Graduate','Sari-sari Store Owner','B1 L1 Pine Street Brgy. Ibaba','09178884455','eloisa.ramos89@yahoo.com','Eloisa M. Ramos','Leo Bautista Ramos','Husband','09175556677',0,'Alive'),(23,'Dizon, Miguel Rafael, Ortega','04-016-2000-0003311','Intellectual Disability','2022-09-07','2022-09-09','2027-09-09','2000-01-30',25,'Male','Single','Malabon','Special Education Graduate','None','B6 L4 Bamboo Street Brgy. Ibaba','09171112233','migs.dizon00@gmail.com','Miguel Dizon','Liza Ortega Dizon','Mother','09176665522',0,'Alive'),(24,'Villanueva, Hannah Joyce, Rivera','06-020-1991-0004200','Psychosocial Disability','2023-04-02','2023-04-02','2028-04-02','1991-09-14',33,'Female','Separated','Makati','College Graduate','Admin Assistant','B9 L9 Coconut Street Brgy. Ibaba','09174445566','hannah.villanueva91@gmail.com','Hannah Joyce Rivera','Carolina Rivera Villanueva','Mother','09179992288',1,'Alive'),(25,'Navarro, Jerome Angelo, Manalo','07-002-1982-0007214','Hearing Impairment','2025-06-28','2025-06-28','2030-06-28','1982-03-04',43,'Male','Married','Binan, Laguna','High School Graduate','Delivery Staff','B2 L3 Lemon Street Brgy. Ibaba','09178886699','jerome.navarro82@gmail.com','Jerome Navarro','Grace Manalo Navarro','Wife','09173338844',1,'Alive');
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
INSERT INTO `members_conditions` VALUES (1,0,0,0,1,0,1,0,0,0,0,''),(2,1,0,0,0,0,0,0,0,0,0,''),(3,0,0,0,0,0,0,0,0,0,0,''),(4,0,0,0,0,0,1,0,0,0,0,''),(5,0,0,0,0,0,0,0,0,1,0,''),(6,1,1,1,1,1,1,1,1,1,1,''),(7,0,0,0,0,1,0,0,0,0,0,''),(8,0,0,0,0,0,0,1,0,0,0,''),(9,0,0,0,0,0,1,0,0,0,0,''),(10,0,0,0,0,0,1,0,0,0,0,''),(11,0,0,1,0,0,0,0,1,0,0,''),(12,0,0,0,0,0,0,0,0,0,0,''),(13,0,0,0,0,0,0,0,0,0,0,''),(14,0,1,0,0,0,0,0,0,0,0,''),(15,0,0,0,0,0,0,1,0,0,0,''),(16,0,0,0,0,0,0,0,0,0,0,''),(17,0,0,0,0,1,0,0,0,0,0,''),(18,0,1,0,0,0,0,0,0,0,0,''),(19,0,0,0,0,0,0,0,0,0,0,''),(20,0,0,0,0,0,0,0,0,0,0,''),(21,0,1,1,0,0,0,0,0,0,0,''),(22,0,0,0,0,0,0,0,0,0,0,''),(23,0,0,0,0,0,0,0,0,0,0,''),(24,0,0,1,0,0,0,0,0,0,0,''),(25,0,0,0,0,1,0,1,0,0,0,'');
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members_household`
--

LOCK TABLES `members_household` WRITE;
/*!40000 ALTER TABLE `members_household` DISABLE KEYS */;
INSERT INTO `members_household` VALUES (5,5,'Paolo De los Santos','Husband',58,'1966-11-05','Married','College Graduate','Layout Artist'),(6,6,'Emilie Penus','Mother',63,'1961-10-18','Married','College Undergraduate','None'),(28,2,'Gemma Ruth Desalit','Mother',40,'1776-07-20','Married','College Graduate','Vendor'),(29,1,'Mary Jane Agustin','Mother',48,'1978-04-13','Married','High School Undergrad','None'),(30,1,'Ronald Agustin','Father',56,'1969-03-26','Married','College Undergrad','None'),(31,1,'Jana Paola Agustin','Sister',21,'2003-09-16','Single','College Undergrad','None'),(32,11,'Melisa Batumbakal','Sister',31,'1994-01-23','Widowed','College Graduate','Social Media Manager'),(33,12,'Maria Domingo Dela Cruz','Mother',58,'1967-03-02','Widowed','College Graduate','Retired'),(34,12,'Ana Domingo Dela Cruz','Sister',25,'1999-09-25','Single','College Graduate','Nurse'),(35,13,'Ernesto Miranda Santos','Husband',48,'1977-06-10','Married','College Graduate','Engineer'),(36,14,'Lorna Devera Lopez','Mother',52,'1973-04-20','Widowed','College Graduate','Public School Teacher'),(37,16,'Marissa Ramos Mendoza','Mother',54,'1971-05-20','Married','College Graduate','Housewife'),(38,17,'Roselyn Perez Garcia','Wife',39,'1985-08-22','Married','High School Graduate','Housewife'),(39,17,'Lucas Perez Garcia','Son',12,'2013-06-03','Single','Grade School','None'),(40,18,'Norma Nolasco Rivera','Mother',60,'1965-01-09','Widowed','High School Graduate','Sari-sari Owner'),(41,18,'Paolo Nolasco Rivera','Brother',35,'1989-10-10','Married','College Graduate','IT Specialist'),(48,21,'Teresa Soriano Cruz','Mother',59,'1965-11-21','Married','College Graduate','Office Clerk'),(49,21,'Daniel Soriano Cruz','Brother',31,'1993-06-30','Married','College Graduate','Software Engineer'),(50,24,'Carolina Rivera Villanueva','Mother',63,'1961-07-08','Married','College Graduate','Government Employee'),(51,25,'Grace Manalo Navarro','Wife',41,'1984-06-01','Married','High School','Housewife'),(52,25,'Janine Manalo Navarro','Daughter',15,'2009-11-20','Single','High School','N/A');
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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members_medications`
--

LOCK TABLES `members_medications` WRITE;
/*!40000 ALTER TABLE `members_medications` DISABLE KEYS */;
INSERT INTO `members_medications` VALUES (1,4,'albuterol'),(2,5,'Antipsychotics'),(3,5,'Risperidone'),(4,5,'Aripiprazole'),(5,6,'Cetirizine'),(6,6,'Cetirizine'),(7,6,'Cetirizine'),(8,6,'Cetirizine'),(9,6,'Cetirizine'),(10,6,'Cetirizine'),(11,6,'Cetirizine'),(12,6,'Cetirizine'),(13,6,'Cetirizine'),(14,6,'Cetirizine'),(15,6,'Robust'),(16,6,'Cetirizine'),(17,6,'Cetirizine'),(18,6,'Cetirizine'),(19,6,'Cetirizine'),(20,11,'Aspirin'),(21,11,'Clopidogrel'),(22,11,'Calcitonin'),(23,11,'Teriparatide'),(24,11,'Growth hormone injections'),(25,12,'Latanoprost Eye Drops'),(26,12,'Vitamin A Supplements'),(27,14,'Risperidone'),(28,14,'Vitamin B Complex'),(31,15,'Calcium Supplements'),(32,15,'Diclofenac'),(33,17,'Paracetamol'),(34,17,'Diclofenac Sodium'),(35,18,'Sertraline'),(36,18,'Melatonin'),(43,21,'Risperidone'),(44,21,'Omega-3 Softgels'),(45,24,'Sertraline'),(46,24,'Alprazolam'),(47,24,'Vitamin C'),(48,25,'Counterirritants'),(49,25,'Naproxen sodium'),(50,25,'Diuretics'),(51,25,'Angiotensin II receptor blockers');
/*!40000 ALTER TABLE `members_medications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `yearly_attendance`
--

DROP TABLE IF EXISTS `yearly_attendance`;
/*!50001 DROP VIEW IF EXISTS `yearly_attendance`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `yearly_attendance` AS SELECT 
 1 AS `member_id`,
 1 AS `year`,
 1 AS `total_present`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `yearly_attendance`
--

/*!50001 DROP VIEW IF EXISTS `yearly_attendance`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `yearly_attendance` AS select `attendance`.`member_id` AS `member_id`,year(`attendance`.`attendance_date`) AS `year`,count(0) AS `total_present` from `attendance` where (`attendance`.`status` = 'present') group by `attendance`.`member_id`,year(`attendance`.`attendance_date`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-29 19:01:53
