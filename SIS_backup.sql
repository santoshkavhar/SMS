-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: SIS
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Canteen`
--

DROP TABLE IF EXISTS `Canteen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Canteen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(140) NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Canteen`
--

LOCK TABLES `Canteen` WRITE;
/*!40000 ALTER TABLE `Canteen` DISABLE KEYS */;
INSERT INTO `Canteen` VALUES (3000,'Provide cold coofee','santa','P'),(3001,'pohe 10 rupaya che kara','santa','P');
/*!40000 ALTER TABLE `Canteen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LACS`
--

DROP TABLE IF EXISTS `LACS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LACS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(140) NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LACS`
--

LOCK TABLES `LACS` WRITE;
/*!40000 ALTER TABLE `LACS` DISABLE KEYS */;
INSERT INTO `LACS` VALUES (1000,'Upgrade S/w and H/w ','santa','D'),(1001,'clean classroom no. 234','santa','P'),(1002,'clean 325','santa','P'),(1003,'clean 367','santa','P'),(1006,'upgrade the pc in 306','santa','P');
/*!40000 ALTER TABLE `LACS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LACS1`
--

DROP TABLE IF EXISTS `LACS1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LACS1` (
  `content` varchar(140) DEFAULT NULL,
  `user_name` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LACS1`
--

LOCK TABLES `LACS1` WRITE;
/*!40000 ALTER TABLE `LACS1` DISABLE KEYS */;
INSERT INTO `LACS1` VALUES ('deg tyhg','santa');
/*!40000 ALTER TABLE `LACS1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Library`
--

DROP TABLE IF EXISTS `Library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Library` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(140) NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4004 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Library`
--

LOCK TABLES `Library` WRITE;
/*!40000 ALTER TABLE `Library` DISABLE KEYS */;
INSERT INTO `Library` VALUES (4000,'Provide Bookname: Theory of Computation by Peter Linz','santa','P'),(4001,'Please increase no. of issue days','santa','D'),(4002,'issue 4 books at a time','santa','D'),(4003,'plwase[C[C[C[C   ease allow 4 books','santa','P');
/*!40000 ALTER TABLE `Library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login` (
  `user` varchar(10) DEFAULT NULL,
  `passwd` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES ('santa','12345');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login_A`
--

DROP TABLE IF EXISTS `Login_A`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login_A` (
  `user` varchar(10) DEFAULT NULL,
  `passwd` varchar(10) DEFAULT NULL,
  `dept` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login_A`
--

LOCK TABLES `Login_A` WRITE;
/*!40000 ALTER TABLE `Login_A` DISABLE KEYS */;
INSERT INTO `Login_A` VALUES ('admin','admin','admin'),('banta','banta123','Library'),('mamta','mess','Canteen'),('kanta','kanta123','SAH'),('raju','rastogi','LACS');
/*!40000 ALTER TABLE `Login_A` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SAH`
--

DROP TABLE IF EXISTS `SAH`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SAH` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(140) NOT NULL,
  `user_name` varchar(10) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2002 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SAH`
--

LOCK TABLES `SAH` WRITE;
/*!40000 ALTER TABLE `SAH` DISABLE KEYS */;
INSERT INTO `SAH` VALUES (2000,'Clean fountain once a week','santa','P'),(2001,'2','santa','P');
/*!40000 ALTER TABLE `SAH` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-21 19:51:41
