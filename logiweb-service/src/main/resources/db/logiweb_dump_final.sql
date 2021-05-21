-- MySQL dump 10.13  Distrib 8.0.23, for osx10.15 (x86_64)
--
-- Host: localhost    Database: logiweb
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `CITY`
--

DROP TABLE IF EXISTS `CITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CITY` (
  `NAME` varchar(30) NOT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CITY`
--

LOCK TABLES `CITY` WRITE;
/*!40000 ALTER TABLE `CITY` DISABLE KEYS */;
INSERT INTO `CITY` VALUES ('Angarsk'),('Arkhangelsk'),('Astrakhan'),('Barnaul'),('Belgorod'),('Bryansk'),('Chelyabinsk'),('Ekaterinburg'),('Irkutsk'),('Izhevsk'),('Kaliningrad'),('Kazan'),('Kemerovo'),('Kirov'),('Krasnodar'),('Krasnoyarsk'),('Kursk'),('Moscow'),('Nizhniy Novgorod'),('Novokuznetsk'),('Novosibirsk'),('Omsk'),('Orenburg'),('Perm'),('Pskov'),('Rostov-Na-Dony'),('Saint Petersburg'),('Samara'),('Saratov'),('Sevastopol'),('Tomsk'),('Ufa'),('Vladivostok'),('Volgograd'),('Voronezh'),('Yaroslavl');
/*!40000 ALTER TABLE `CITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DRIVER`
--

DROP TABLE IF EXISTS `DRIVER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DRIVER` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(255) NOT NULL,
  `HOURS_WORKED` int NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  `LORRY` varchar(7) DEFAULT NULL,
  `ORDER_ID` bigint DEFAULT NULL,
  `USER_EMAIL` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK6bymrm3dpiiwrg1b5wrtf37dp` (`CITY`),
  KEY `FK8yirg8lrkqwgb7mghyicb9vu5` (`LORRY`),
  KEY `FKl9iuga1s18lff6r97qcd7b5x9` (`ORDER_ID`),
  KEY `FKtgfwj1o02v11lqe43jjoyrhpk` (`USER_EMAIL`)
) ENGINE=MyISAM AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DRIVER`
--

LOCK TABLES `DRIVER` WRITE;
/*!40000 ALTER TABLE `DRIVER` DISABLE KEYS */;
INSERT INTO `DRIVER` VALUES (71,'AlexeyEdited',0,'Tyan','RESTING','Ekaterinburg',NULL,NULL,NULL),(72,'Alexey',0,'Lol','RESTING','Angarsk','AA44444',272,'alekseytyan45@gmail.com'),(73,'Oleg',100,'Antonov','RESTING','Angarsk','AA44444',272,NULL),(74,'Example',50,'Driver','RESTING','Angarsk',NULL,NULL,NULL),(75,'dasdad',0,'asdasd','RESTING','Angarsk','BD12321',274,NULL),(76,'asdasd',0,'asdasd','RESTING','Angarsk','BD12321',274,NULL);
/*!40000 ALTER TABLE `DRIVER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (41);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOAD_LOGIWEB`
--

DROP TABLE IF EXISTS `LOAD_LOGIWEB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LOAD_LOGIWEB` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `WEIGHT` int DEFAULT NULL,
  `CITY_LOAD` varchar(30) NOT NULL,
  `CITY_UNLOAD` varchar(30) NOT NULL,
  `ORDER_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKod24imhs6vkpus1l841ayds8j` (`CITY_LOAD`),
  KEY `FKa82k8o8yy5ad2etkfr4u77k1u` (`CITY_UNLOAD`),
  KEY `FK3poo76dmn4k9ugewxyo4em78k` (`ORDER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=187 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOAD_LOGIWEB`
--

LOCK TABLES `LOAD_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `LOAD_LOGIWEB` DISABLE KEYS */;
INSERT INTO `LOAD_LOGIWEB` VALUES (181,'100','DELIVERED',300,'Angarsk','Chelyabinsk',272),(182,'Irk-Ekb','DELIVERED',100,'Irkutsk','Ekaterinburg',272),(184,'DSWE','PREPARED',0,'Angarsk','Irkutsk',274),(185,'RWER','PREPARED',0,'Angarsk','Krasnoyarsk',274),(186,'WERWE','PREPARED',0,'Irkutsk','Moscow',274);
/*!40000 ALTER TABLE `LOAD_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LORRY`
--

DROP TABLE IF EXISTS `LORRY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LORRY` (
  `ID` varchar(7) NOT NULL,
  `CAPACITY` int DEFAULT NULL,
  `IS_BROKEN` tinyint(1) DEFAULT '0',
  `SHIFT_TIME` int DEFAULT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKetfju870g47s100ui1jrj80qe` (`CITY`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LORRY`
--

LOCK TABLES `LORRY` WRITE;
/*!40000 ALTER TABLE `LORRY` DISABLE KEYS */;
INSERT INTO `LORRY` VALUES ('AA12345',1001,1,10,'Ekaterinburg'),('AA44444',1000,0,0,'Angarsk'),('AA11000',1000,0,1,'Angarsk'),('BD12321',1500,0,0,'Angarsk'),('BC44444',2000,0,0,'Angarsk');
/*!40000 ALTER TABLE `LORRY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAP`
--

DROP TABLE IF EXISTS `MAP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MAP` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `DISTANCE` int DEFAULT NULL,
  `FROM_CITY` varchar(30) NOT NULL,
  `TO_CITY` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK2rmiw4ycadric15h4knv8al` (`FROM_CITY`),
  KEY `FK7qivg8n5xy8vvdcxbfiys1nky` (`TO_CITY`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAP`
--

LOCK TABLES `MAP` WRITE;
/*!40000 ALTER TABLE `MAP` DISABLE KEYS */;
INSERT INTO `MAP` VALUES (1,47,'Angarsk','Irkutsk'),(4,1007,'Angarsk','Krasnoyarsk'),(14,259,'Novosibirsk','Tomsk'),(6,1794,'Irkutsk','Novokuznetsk'),(7,751,'Krasnoyarsk','Novokuznetsk'),(13,260,'Kemerovo','Novosibirsk'),(9,1794,'Irkutsk','Novokuznetsk'),(10,751,'Krasnoyarsk','Novokuznetsk'),(11,221,'Novokuznetsk','Kemerovo'),(12,410,'Novokuznetsk','Barnaul'),(15,582,'Krasnoyarsk','Tomsk'),(16,651,'Novosibirsk','Omsk'),(17,917,'Omsk','Chelyabinsk'),(18,206,'Chelyabinsk','Ekaterinburg'),(19,361,'Ekaterinburg','Perm'),(20,630,'Ekaterinburg','Izhevsk'),(21,364,'Izhevsk','Kazan'),(22,413,'Kazan','Kirov'),(23,697,'Kirov','Yaroslavl'),(24,959,'Yaroslavl','Arkhangelsk'),(25,269,'Yaroslavl','Moscow'),(26,721,'Moscow','Saint Petersburg'),(27,294,'Saint Petersburg','Pskov'),(28,423,'Moscow','Nizhniy Novgorod'),(29,416,'Chelyabinsk','Ufa'),(30,866,'Chelyabinsk','Samara'),(31,464,'Samara','Ufa'),(32,891,'Ufa','Saratov'),(33,374,'Saratov','Volgograd'),(34,1172,'Volgograd','Orenburg'),(35,744,'Volgograd','Krasnodar'),(36,841,'Saratov','Rostov-Na-Dony'),(37,256,'Belgorod','Voronezh'),(38,983,'Nizhniy Novgorod','Belgorod'),(39,490,'Tomsk','Barnaul'),(40,217,'Tomsk','Kemerovo'),(41,943,'Barnaul','Krasnoyarsk'),(42,1135,'Saint Petersburg','Nizhniy Novgorod'),(43,1233,'Moscow','Arkhangelsk'),(44,1273,'Nizhniy Novgorod','Arkhangelsk'),(45,840,'Krasnodar','Astrakhan');
/*!40000 ALTER TABLE `MAP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDER_LOGIWEB`
--

DROP TABLE IF EXISTS `ORDER_LOGIWEB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ORDER_LOGIWEB` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `IS_FINISHED` bit(1) DEFAULT NULL,
  `VERIFIED` tinyint(1) DEFAULT '0',
  `LORRY` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKm0f0law2r2p04o7gwm6y6b3hd` (`LORRY`)
) ENGINE=MyISAM AUTO_INCREMENT=275 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDER_LOGIWEB`
--

LOCK TABLES `ORDER_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `ORDER_LOGIWEB` DISABLE KEYS */;
INSERT INTO `ORDER_LOGIWEB` VALUES (272,_binary '',1,'AA44444'),(274,_binary '\0',1,'BD12321');
/*!40000 ALTER TABLE `ORDER_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PASSWORD_RESET_TOKEN`
--

DROP TABLE IF EXISTS `PASSWORD_RESET_TOKEN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PASSWORD_RESET_TOKEN` (
  `id` bigint NOT NULL,
  `expiryDate` date DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `USER_ID` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1v85nhk1nwuh3iotgmj9jlhxw` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PASSWORD_RESET_TOKEN`
--

LOCK TABLES `PASSWORD_RESET_TOKEN` WRITE;
/*!40000 ALTER TABLE `PASSWORD_RESET_TOKEN` DISABLE KEYS */;
/*!40000 ALTER TABLE `PASSWORD_RESET_TOKEN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRIVILEGE_LOGIWEB`
--

DROP TABLE IF EXISTS `PRIVILEGE_LOGIWEB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRIVILEGE_LOGIWEB` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRIVILEGE_LOGIWEB`
--

LOCK TABLES `PRIVILEGE_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `PRIVILEGE_LOGIWEB` DISABLE KEYS */;
INSERT INTO `PRIVILEGE_LOGIWEB` VALUES (21,'READ_PRIVILEGE'),(22,'WRITE_PRIVILEGE'),(24,'READ_PRIVILEGE'),(25,'WRITE_PRIVILEGE'),(27,'READ_PRIVILEGE'),(29,'READ_PRIVILEGE'),(31,'READ_PRIVILEGE');
/*!40000 ALTER TABLE `PRIVILEGE_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRIVILEGE_LOGIWEB_ROLE_LOGIWEB`
--

DROP TABLE IF EXISTS `PRIVILEGE_LOGIWEB_ROLE_LOGIWEB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRIVILEGE_LOGIWEB_ROLE_LOGIWEB` (
  `Privilege_id` bigint NOT NULL,
  `roles_id` bigint NOT NULL,
  KEY `FKmq1l17dvu9v6tb2r2ogor63fv` (`roles_id`),
  KEY `FK4uqt6qhpr99wp6v08kjl5w36x` (`Privilege_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRIVILEGE_LOGIWEB_ROLE_LOGIWEB`
--

LOCK TABLES `PRIVILEGE_LOGIWEB_ROLE_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `PRIVILEGE_LOGIWEB_ROLE_LOGIWEB` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRIVILEGE_LOGIWEB_ROLE_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLE_LOGIWEB`
--

DROP TABLE IF EXISTS `ROLE_LOGIWEB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROLE_LOGIWEB` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLE_LOGIWEB`
--

LOCK TABLES `ROLE_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `ROLE_LOGIWEB` DISABLE KEYS */;
INSERT INTO `ROLE_LOGIWEB` VALUES (23,'ROLE_ADMIN'),(26,'ROLE_EMPLOYEE'),(28,'ROLE_DRIVER'),(30,'ROLE_USER');
/*!40000 ALTER TABLE `ROLE_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLE_LOGIWEB_USER_LOGIWEB`
--

DROP TABLE IF EXISTS `ROLE_LOGIWEB_USER_LOGIWEB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROLE_LOGIWEB_USER_LOGIWEB` (
  `Role_id` bigint NOT NULL,
  `users_EMAIL` varchar(60) NOT NULL,
  KEY `FKjqv4n8aqs4xxhfgjtdw9h0x0t` (`users_EMAIL`),
  KEY `FKlouh4aqtuifqrutlfeta597gx` (`Role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLE_LOGIWEB_USER_LOGIWEB`
--

LOCK TABLES `ROLE_LOGIWEB_USER_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `ROLE_LOGIWEB_USER_LOGIWEB` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROLE_LOGIWEB_USER_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES_PRIVELEGES`
--

DROP TABLE IF EXISTS `ROLES_PRIVELEGES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROLES_PRIVELEGES` (
  `ROLE_ID` bigint NOT NULL,
  `PRIVILEGE_ID` bigint NOT NULL,
  KEY `FKloeh3eaetg8vue04yjn2cql6e` (`PRIVILEGE_ID`),
  KEY `FK6f2eqv5aat1ttofxf6ruv7l5k` (`ROLE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES_PRIVELEGES`
--

LOCK TABLES `ROLES_PRIVELEGES` WRITE;
/*!40000 ALTER TABLE `ROLES_PRIVELEGES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROLES_PRIVELEGES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_LOGIWEB`
--

DROP TABLE IF EXISTS `USER_LOGIWEB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USER_LOGIWEB` (
  `EMAIL` varchar(60) NOT NULL,
  `ENABLED` tinyint(1) DEFAULT '1',
  `PASSWORD` varchar(255) NOT NULL,
  `ROLE` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `EMAIL_CONFIRMED` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`EMAIL`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_LOGIWEB`
--

LOCK TABLES `USER_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `USER_LOGIWEB` DISABLE KEYS */;
INSERT INTO `USER_LOGIWEB` VALUES ('employee@gmail.com',1,'$2y$12$1RAG8d3H3dHl./J8Qu/ol.3NoJqNrDWwboNe86Sitfe94aEUOGzFK','ROLE_EMPLOYEE','Employee','Name2',0),('alekseytyan45@gmail.com',1,'$2a$10$TShU.7jhCNn7333NSKsMaOClUHhYVummfeCeZ5XxnrlDgo3Y/bTEi','ROLE_DRIVER','Example','Driver',0),('tuan.tuan.tuan13@gmail.com',1,'$2a$10$/B.qOW2q8M4ain65jYG.qeDXcHWx/SxHJTM/amjKU1FbH/y45Jujy','ROLE_EMPLOYEE','Aleksey','Tyan',1),('admin@gmail.com',1,'$2y$12$pTtmam2PUXCLp10rfNl/.OuxezpkFDDXhsRF9Gii5bsU3yS66bSku','ROLE_ADMIN','Alexey','Alexeu',1);
/*!40000 ALTER TABLE `USER_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS_ROLES`
--

DROP TABLE IF EXISTS `USERS_ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERS_ROLES` (
  `USER_EMAIL` varchar(60) NOT NULL,
  `ROLE_ID` bigint NOT NULL,
  KEY `FKcxsbky62848o4p1chmju6sdqp` (`ROLE_ID`),
  KEY `FK7bv0mqu6ltkewq8m9pj3jsis4` (`USER_EMAIL`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS_ROLES`
--

LOCK TABLES `USERS_ROLES` WRITE;
/*!40000 ALTER TABLE `USERS_ROLES` DISABLE KEYS */;
/*!40000 ALTER TABLE `USERS_ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VERIFICATION`
--

DROP TABLE IF EXISTS `VERIFICATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VERIFICATION` (
  `id` bigint NOT NULL,
  `expiryDate` date DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `USER_EMAIL` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjs3jn9avo0is95txri9edr65l` (`USER_EMAIL`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VERIFICATION`
--

LOCK TABLES `VERIFICATION` WRITE;
/*!40000 ALTER TABLE `VERIFICATION` DISABLE KEYS */;
INSERT INTO `VERIFICATION` VALUES (36,'2021-05-18','06f879ae-6d42-450d-a8ff-8b2dc1ad1da2','alekseytyan45@gmail.com');
/*!40000 ALTER TABLE `VERIFICATION` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-20 14:13:07
