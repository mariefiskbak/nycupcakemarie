-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: cupcakemmp
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `cupcakebuttom`
--

DROP TABLE IF EXISTS `cupcakebuttom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcakebuttom` (
  `buttom_id` int NOT NULL,
  `flavor` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `buttom_picture_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`buttom_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcakebuttom`
--

LOCK TABLES `cupcakebuttom` WRITE;
/*!40000 ALTER TABLE `cupcakebuttom` DISABLE KEYS */;
INSERT INTO `cupcakebuttom` VALUES (1,'Chokolade',5,'chokolademuffins-hjemmebagte.jpg'),(2,'Vanilje',5,'vaniljemuffin.jpg'),(3,'Muskatnød',5,'nutmegmuffins.jpg'),(4,'Pistacie',6,'pistachiomuffin.jpg'),(5,'Mandel',7,'almondmuffin.jpg');
/*!40000 ALTER TABLE `cupcakebuttom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupcaketopping`
--

DROP TABLE IF EXISTS `cupcaketopping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cupcaketopping` (
  `topping_id` int NOT NULL,
  `flavor` varchar(45) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `topping_picture_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`topping_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupcaketopping`
--

LOCK TABLES `cupcaketopping` WRITE;
/*!40000 ALTER TABLE `cupcaketopping` DISABLE KEYS */;
INSERT INTO `cupcaketopping` VALUES (1,'Chocolade',5,'chocolatetopping.jpg'),(2,'Blåbær',5,'blueberrytopping.png'),(3,'Hindbær',5,'raspberrytopping.jpg'),(4,'Krispy',6,'crispytopping.jpg'),(5,'Jordbær',6,'strawberrytopping.jpg'),(6,'Rom/rosin',7,'rumraisintopping.jpg'),(7,'Appelsin',8,'orangetopping.jpeg'),(8,'Citron',8,'lemontopping.jpg'),(9,'Blåskimmelost',9,'bluecheesetopping.jpg');
/*!40000 ALTER TABLE `cupcaketopping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_user1_idx` (`user_id`),
  KEY `fk_order_orderstatus1_idx` (`status_id`),
  CONSTRAINT `fk_order_orderstatus1` FOREIGN KEY (`status_id`) REFERENCES `orderstatus` (`status_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (3,2,21,'2012-12-12 00:00:00',1),(5,2,24,'2012-12-12 00:00:00',1),(6,2,21,'2012-12-12 00:00:00',1),(7,2,21,'2012-12-12 00:00:00',1),(8,2,22,'2012-12-12 00:00:00',1),(9,2,30,'2012-12-12 00:00:00',1),(10,2,13,'2012-12-12 00:00:00',1),(11,12,10,'2022-04-14 12:57:27',1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderline` (
  `orderline_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL,
  `buttom_id` int NOT NULL,
  `topping_id` int NOT NULL,
  PRIMARY KEY (`orderline_id`),
  KEY `fk_orderline_cupcaketopping1_idx` (`topping_id`),
  KEY `fk_orderline_cupcakebuttom1_idx` (`buttom_id`),
  KEY `fk_orderline_order1` (`order_id`),
  CONSTRAINT `fk_orderline_cupcakebuttom1` FOREIGN KEY (`buttom_id`) REFERENCES `cupcakebuttom` (`buttom_id`),
  CONSTRAINT `fk_orderline_cupcaketopping1` FOREIGN KEY (`topping_id`) REFERENCES `cupcaketopping` (`topping_id`),
  CONSTRAINT `fk_orderline_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES (311,3,1,1,1),(341,3,1,4,1),(519,5,1,1,9),(531,5,1,3,1),(611,6,1,1,1),(624,6,1,2,4),(711,7,1,1,1),(714,7,1,1,4),(811,8,1,1,1),(816,8,1,1,6),(958,9,2,5,8),(1055,10,1,5,5),(1111,11,1,1,1);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderstatus`
--

DROP TABLE IF EXISTS `orderstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderstatus` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderstatus`
--

LOCK TABLES `orderstatus` WRITE;
/*!40000 ALTER TABLE `orderstatus` DISABLE KEYS */;
INSERT INTO `orderstatus` VALUES (1,'Venter'),(2,'Afhentet'),(3,'Slettet');
/*!40000 ALTER TABLE `orderstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator'),(2,'Bruger');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `role_id` int NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `balance` int NOT NULL,
  `phone_no` int DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'4321',1,'Gui','Guisen',1000,78787878,'a@a.dk'),(2,'1212',2,'Yrsa','Yrsasen',427,12312312,'y@y.dk'),(12,'1234',2,'Mulle','Mullesen',990,56565656,'m@m.dk');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-15  9:45:52
