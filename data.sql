CREATE DATABASE  IF NOT EXISTS `cvsdatabase` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cvsdatabase`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cvsdatabase
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `PersonId` bigint(20) NOT NULL,
  PRIMARY KEY (`PersonId`),
  CONSTRAINT `FK_sp56fgw3ubma5i9neuw0mxqmg` FOREIGN KEY (`PersonId`) REFERENCES `person` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `address` varchar(255) DEFAULT NULL,
  `dateOfBirth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phoneNo` bigint(20) DEFAULT NULL,
  `PersonId` bigint(20) NOT NULL,
  PRIMARY KEY (`PersonId`),
  CONSTRAINT `FK_f35m3tyjdot2fb65xi69dc4nj` FOREIGN KEY (`PersonId`) REFERENCES `person` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('c','6879','dfghj','Male',456789,4),('retyu','4rtyu','dgfhgj','Male',455,5),('','','',NULL,6787,7),('','','',NULL,7678,8),('','','',NULL,67,9);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugcategory`
--

DROP TABLE IF EXISTS `drugcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drugcategory` (
  `DrugCategory_Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DrugCategoryName` varchar(100) NOT NULL,
  PRIMARY KEY (`DrugCategory_Id`),
  UNIQUE KEY `UK_1nrx4te68404xl7loq20vhgy3` (`DrugCategoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugcategory`
--

LOCK TABLES `drugcategory` WRITE;
/*!40000 ALTER TABLE `drugcategory` DISABLE KEYS */;
INSERT INTO `drugcategory` VALUES (1,'cocaine'),(2,'drug1'),(4,'drug2');
/*!40000 ALTER TABLE `drugcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugproduct`
--

DROP TABLE IF EXISTS `drugproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drugproduct` (
  `Drug_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(100) NOT NULL,
  `Drug_Name` varchar(100) NOT NULL,
  `Path` varchar(100) NOT NULL,
  `Price` float NOT NULL,
  `Quantity` int(11) NOT NULL,
  `DrugCategory_Id` bigint(20) DEFAULT NULL,
  `CompanyName` varchar(100) NOT NULL,
  PRIMARY KEY (`Drug_Id`),
  KEY `FK_lnr5r1c0torw5vya7j6ckv3tj` (`DrugCategory_Id`),
  CONSTRAINT `FK_lnr5r1c0torw5vya7j6ckv3tj` FOREIGN KEY (`DrugCategory_Id`) REFERENCES `drugcategory` (`DrugCategory_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugproduct`
--

LOCK TABLES `drugproduct` WRITE;
/*!40000 ALTER TABLE `drugproduct` DISABLE KEYS */;
INSERT INTO `drugproduct` VALUES (1,'d','d','3.jpg',7,1,1,'ranbaxy'),(2,'d2','d2','4.jpg',657,417,1,'sun'),(3,'d3','d3','6.jpg',10,65762,2,'novartis'),(5,'high','D 4','1.jpg',4567,344,4,'Brandi'),(6,'sdfhgh','d5','2.jpg',54678,345,1,'wrer');
/*!40000 ALTER TABLE `drugproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `OrderItem_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Quantity_Ordered` int(11) DEFAULT NULL,
  `Total_Cost` int(11) DEFAULT NULL,
  `Drug_ID` int(11) DEFAULT NULL,
  `Order_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`OrderItem_ID`),
  KEY `FK_dovgg5s1f7klbf9otp0kys4kc` (`Drug_ID`),
  KEY `FK_7ahy2orc95105v9v3w1fiq2g6` (`Order_ID`),
  CONSTRAINT `FK_7ahy2orc95105v9v3w1fiq2g6` FOREIGN KEY (`Order_ID`) REFERENCES `orders` (`Order_ID`),
  CONSTRAINT `FK_dovgg5s1f7klbf9otp0kys4kc` FOREIGN KEY (`Drug_ID`) REFERENCES `drugproduct` (`Drug_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (10,56,0,1,3),(11,2,0,2,3),(12,8,0,1,4),(13,567,0,2,4),(26,6,0,1,6),(27,67,0,2,6),(28,5,0,1,7),(55,13,91,1,17),(56,2,1314,2,17),(57,16,112,1,18),(58,11,77,1,19),(59,1,657,2,20),(60,1,7,1,21),(61,1,7,1,22);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `Order_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Order_Total` int(11) DEFAULT NULL,
  `PersonId` bigint(20) DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Order_ID`),
  KEY `FK_14gjhmbhjy8r2d359yahxr3i8` (`PersonId`),
  CONSTRAINT `FK_14gjhmbhjy8r2d359yahxr3i8` FOREIGN KEY (`PersonId`) REFERENCES `customer` (`PersonId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (3,0,4,'paid'),(4,0,4,'paid'),(6,0,4,'paid'),(7,0,4,'paid'),(10,0,4,'paid'),(15,707,4,'paid'),(16,671,4,'paid'),(17,1405,4,'paid'),(18,112,4,'paid'),(19,77,4,'paid'),(20,657,4,'paid'),(21,7,4,'paid'),(22,7,4,'paid');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `Payment_ID` int(11) NOT NULL,
  `CVV` int(11) DEFAULT NULL,
  `Expiration_Date` varchar(20) DEFAULT NULL,
  `Credit_Card_Number` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Payment_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (6,768,'13/1','4657657846576789'),(7,988,'13/1','5454546554656578'),(10,987,'13/1','4567456756784465'),(15,567,'13/1','4578243543563456'),(16,445,'13/1','5768678967897898'),(17,456,'13/1','5678789757685768'),(18,546,'13/1','4567576856784567'),(19,888,'13/1','6666666666776667'),(20,777,'13/1','8888888888888888'),(21,345,'13/1','3546456743563456'),(22,324,'13/1','567567857685677');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `personID` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'admin fname','admin lname'),(2,NULL,NULL),(3,NULL,NULL),(4,'c','c'),(5,'c1','c1'),(6,NULL,NULL),(7,'',''),(8,'',''),(9,'',''),(10,NULL,NULL),(12,NULL,NULL),(14,NULL,NULL),(15,NULL,NULL),(16,NULL,NULL),(17,NULL,NULL),(18,NULL,NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `Supplier_Name` varchar(100) NOT NULL,
  `PersonId` bigint(20) NOT NULL,
  PRIMARY KEY (`PersonId`),
  UNIQUE KEY `UK_oblju1ty9fel0463juvxs6ij5` (`Supplier_Name`),
  CONSTRAINT `FK_41ibhwablwr6g966m1osuc9t1` FOREIGN KEY (`PersonId`) REFERENCES `person` (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (' ',10),(' ghj',17),(' hj',18),('bvn ',14),('fg ',15),('jn ',12),('n ',16),('s s',2),('s1 s1',3),('s2 s2',6);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_product`
--

DROP TABLE IF EXISTS `supplier_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_product` (
  `PersonId` bigint(20) NOT NULL,
  `drugId` int(11) NOT NULL,
  KEY `FK_6pvia4i2vu0pkecnac3vk7jmj` (`drugId`),
  KEY `FK_cag5tgi9xqs50dd8xsg7o262p` (`PersonId`),
  CONSTRAINT `FK_6pvia4i2vu0pkecnac3vk7jmj` FOREIGN KEY (`drugId`) REFERENCES `drugproduct` (`Drug_Id`),
  CONSTRAINT `FK_cag5tgi9xqs50dd8xsg7o262p` FOREIGN KEY (`PersonId`) REFERENCES `supplier` (`PersonId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_product`
--

LOCK TABLES `supplier_product` WRITE;
/*!40000 ALTER TABLE `supplier_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'a','admin','admin'),(2,'s','supplier','s'),(3,'s1','supplier','s1'),(4,'c','customer','c'),(5,'c1','customer','c1'),(6,'s2','supplier','s2'),(7,'','customer','k'),(8,'','customer','kk'),(9,'','customer','f'),(10,'','supplier','g'),(12,'','supplier','jhh'),(14,'','supplier','gjh'),(15,'','supplier','gbjhjk'),(16,'','supplier','h'),(17,'','supplier','hhhj'),(18,'','supplier','hh');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cvsdatabase'
--

--
-- Dumping routines for database 'cvsdatabase'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-24 16:35:28
