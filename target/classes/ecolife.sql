CREATE DATABASE  IF NOT EXISTS `ecolife` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecolife`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: ecolife
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `cus_id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_dni` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cus_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cus_surname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cus_username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cus_password` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cus_phone` int(9) NOT NULL,
  `cus_email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`cus_id`),
  UNIQUE KEY `cus_username_UNIQUE` (`cus_username`),
  UNIQUE KEY `cus_id_UNIQUE` (`cus_dni`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'11111111A','sergio','garcia','sergio','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',666555444,'sg@gmail.com'),(3,'12345678O','prueba','prueba','prueba','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',666555999,'prueba2@gmail.com'),(4,'0','admin','admin','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',0,'admin@admin.com'),(5,'21212121F','prueba2','prueba2','prueba2','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',666555922,'prueba22@gmail.com');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `oit_ord_id` int(11) NOT NULL,
  `oit_pro_id` int(11) NOT NULL,
  `oit_unit_price` decimal(5,2) NOT NULL,
  `oit_quantity` double NOT NULL,
  PRIMARY KEY (`oit_ord_id`,`oit_pro_id`),
  KEY `oit_pro_fk_idx` (`oit_pro_id`),
  CONSTRAINT `oit_ord_fk` FOREIGN KEY (`oit_ord_id`) REFERENCES `orders` (`ord_id`),
  CONSTRAINT `oit_pro_fk` FOREIGN KEY (`oit_pro_id`) REFERENCES `products` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (47,1,2.50,0.5),(47,2,2.00,1),(47,3,1.00,2),(47,4,1.55,1),(47,7,2.00,1),(48,1,2.50,0.5),(48,2,2.00,1),(51,1,2.50,0.5),(51,7,2.00,2);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `ord_id` int(11) NOT NULL AUTO_INCREMENT,
  `ord_cus_id` int(11) NOT NULL,
  `ord_date` date NOT NULL,
  `ord_total` decimal(7,2) NOT NULL,
  PRIMARY KEY (`ord_id`),
  KEY `ord_cus_fk_idx` (`ord_cus_id`),
  CONSTRAINT `ord_cus_fk` FOREIGN KEY (`ord_cus_id`) REFERENCES `customers` (`cus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (47,3,'2020-02-07',8.80),(48,3,'2020-02-07',3.25),(51,5,'2020-02-08',5.25);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(45) NOT NULL,
  `pro_price` double NOT NULL,
  `pro_weight` double NOT NULL,
  `pro_cover` varchar(45) NOT NULL,
  `pro_category` enum('fruta_y_verdura','carne','huevos_y_lacteos') NOT NULL,
  `pro_description` varchar(100) DEFAULT NULL,
  `pro_type` enum('por_peso','por_unidad') NOT NULL DEFAULT 'por_peso',
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'mandarinas',2.5,1,'mandarinas.png','fruta_y_verdura','mandarinas','por_peso'),(2,'platanos',2,1,'platanos.png','fruta_y_verdura','platanos','por_peso'),(3,'hamburguesa de pollo',1,0.2,'hamburguesaPollo.png','carne','hamburguesa de pollo','por_unidad'),(4,'peras',1.55,1,'peras.png','fruta_y_verdura','peras frescas','por_peso'),(7,'huevos ecologicos 1/2 docena',2,1,'huevos.png','huevos_y_lacteos','huevos de gallinas alimentadas con cereales ecologicos','por_unidad'),(8,'queso bio burgos 2x100Gr',3.4,1,'queso-burgos.png','huevos_y_lacteos','queso fresco ecologico','por_unidad'),(9,'carne picada mixta ternera/cerdo',7.5,1,'carne-picada-mixta.png','carne','carne picada hecha de cerdos y terneras criadas en campos ecologicos','por_peso');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-09 17:30:37
