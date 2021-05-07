-- MySQL dump 10.13  Distrib 8.0.23, for osx10.15 (x86_64)
--
-- Host: localhost    Database: logiweb

-- Date: 01/05/2021
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
INSERT INTO `CITY` VALUES ('Angarsk'),('Arkhangelsk'),('Astrakhan'),('Barnaul'),('Belgorod'),('Bryansk'),('Chelyabinks'),('Ekaterinburg'),('Irkutsk'),('Izhevsk'),('Kaliningrad'),('Kazan'),('Kemerovo'),('Kirov'),('Krasnodar'),('Krasnoyarsk'),('Kursk'),('Moscow'),('Nizhniy Novgorod'),('Novokuznetsk'),('Novosibirks'),('Omsk'),('Orenburg'),('Perm'),('Pskov'),('Rostov-Na-Dony'),('Saint Petersburg'),('Samara'),('Saratov'),('Sevastopol'),('Tomsk'),('Ufa'),('Vladivostok'),('Volgograd'),('Voronezh'),('Yaroslavl');
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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DRIVER`
--

LOCK TABLES `DRIVER` WRITE;
/*!40000 ALTER TABLE `DRIVER` DISABLE KEYS */;
INSERT INTO `DRIVER` VALUES (1,'Alexey',123,'Tyan','DUTY','Angarsk',NULL,NULL,'alexey@gmail.com'),(2,'Oleg',69,'Kek','DRIVING','Moscow',NULL,NULL,'oleg@gmail.com'),(3,'Olga',0,'Rum','UNLOADING','Los-Angeles',NULL,NULL,'olga@gmail.com'),(4,'Petr',10,'Perviy','DUTY','Saint Petersburg',NULL,NULL,'petr@gmail.com'),(5,'Michail',66,'Dum','DUTY','Voronezh',NULL,NULL,'michail@gmail.com');
/*!40000 ALTER TABLE `DRIVER` ENABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOAD_LOGIWEB`
--

LOCK TABLES `LOAD_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `LOAD_LOGIWEB` DISABLE KEYS */;
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
INSERT INTO `LORRY` VALUES ('AA12345',1000,0,1,'Angarsk'),('LK89891',500,0,100,'Los-Angeles'),('MN78124',1500,0,50,'Moscow'),('DM98765',100,0,200,'Saint Petersburg'),('RI20101',2000,0,160,'Voronezh');
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
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAP`
--

LOCK TABLES `MAP` WRITE;
/*!40000 ALTER TABLE `MAP` DISABLE KEYS */;
INSERT INTO `MAP` VALUES (1,47,'Angarsk','Irkutsk'),(4,1007,'Angarsk','Krasnoyarsk'),(14,259,'Novosibirsk','Tomsk'),(6,1794,'Irkutsk','Novokuznetsk'),(7,751,'Krasnoyarsk','Novokuznetsk'),(13,260,'Kemerovo','Novosibirsk'),(9,1794,'Irkutsk','Novokuznetsk'),(10,751,'Krasnoyarsk','Novokuznetsk'),(11,221,'Novokuznetsk','Kemerovo'),(12,410,'Novokuznetsk','Barnaul'),(15,582,'Krasnoyarsk','Tomsk'),(16,651,'Novosibirsk','Omsk'),(17,917,'Omsk','Chelyabinsk'),(18,206,'Chelyabinsk','Ekaterinburg'),(19,361,'Ekaterinburg','Perm'),(20,630,'Ekaterinburg','Izhevsk'),(21,364,'Izhevsk','Kazan'),(22,413,'Kazan','Kirov'),(23,697,'Kirov','Yaroslavl'),(24,959,'Yaroslavl','Arkhangelsk'),(25,269,'Yaroslavl','Moscow'),(26,721,'Moscow','Saint Petersburg'),(27,294,'Saint Petersburg','Pskov'),(28,423,'Moscow','Nizhniy Novgorod'),(29,416,'Chelyabinsk','Ufa'),(30,866,'Chelyabinsk','Samara'),(31,464,'Samara','Ufa'),(32,891,'Ufa','Saratov'),(33,374,'Saratov','Volgograd'),(34,1172,'Volgograd','Orenburg'),(35,744,'Volgograd','Krasnodar'),(36,841,'Saratov','Rostov-Na-Dony'),(37,256,'Belgorod','Voronezh'),(38,983,'Nizhniy Novgorod','Belgorod');
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
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDER_LOGIWEB`
--

LOCK TABLES `ORDER_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `ORDER_LOGIWEB` DISABLE KEYS */;
INSERT INTO `ORDER_LOGIWEB` VALUES (41,_binary '\0',0,NULL),(42,_binary '\0',0,NULL),(43,_binary '\0',0,NULL),(44,_binary '\0',0,NULL),(45,_binary '\0',0,NULL),(46,_binary '\0',0,NULL),(47,_binary '\0',0,NULL),(48,_binary '\0',0,NULL),(49,_binary '\0',1,NULL);
/*!40000 ALTER TABLE `ORDER_LOGIWEB` ENABLE KEYS */;
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
  PRIMARY KEY (`EMAIL`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_LOGIWEB`
--

LOCK TABLES `USER_LOGIWEB` WRITE;
/*!40000 ALTER TABLE `USER_LOGIWEB` DISABLE KEYS */;
INSERT INTO `USER_LOGIWEB` VALUES ('employee@gmail.com',1,'$2y$12$1RAG8d3H3dHl./J8Qu/ol.3NoJqNrDWwboNe86Sitfe94aEUOGzFK','ROLE_EMPLOYEE'),('alexey@gmail.com',1,'$2a$10$8GT.FOUFrZWib3c79RRIeOe0jyy20oYfSAsilOP9lo1WrEslv/.h2','ROLE_DRIVER'),('oleg@gmail.com',1,'$2a$10$cVz1sNKeU49jDHtk.HIGCuFIMnmp9cnwf4ly77dax.ETlr3H408Bq','ROLE_DRIVER'),('olga@gmail.com',1,'$2a$10$aryLVNYAYI6TZx9Yvm4hne0raDk8ruqtBKQOC4HSirAtbKXosIXYm','ROLE_DRIVER'),('petr@gmail.com',1,'$2a$10$9c37q0hrnjoQO1ncMhg7PemhheqbqYSIVYwPEQNgAA.2YulZqNl2m','ROLE_DRIVER'),('michail@gmail.com',1,'$2a$10$K2fSOz3RBcu80qycDdZoVOA89JxexeRghGc1yLy6JMM5LyhtcebBW','ROLE_DRIVER');
/*!40000 ALTER TABLE `USER_LOGIWEB` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-01 17:39:31
