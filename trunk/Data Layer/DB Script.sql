-- MySQL dump 10.11
--
-- Host: localhost    Database: Seletiene
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt

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
-- Table structure for table `adicional`
--

DROP TABLE IF EXISTS `Adicional`;
CREATE TABLE `Adicional` (
  `id_adicional` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(30) NOT NULL,
  `precio` float unsigned NOT NULL,
  PRIMARY KEY  (`id_adicional`),
  KEY `ad_index` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adicional`
--

LOCK TABLES `Adicional` WRITE;
/*!40000 ALTER TABLE `Adicional` DISABLE KEYS */;
INSERT INTO `Adicional` VALUES (1,'Adicional_1',1000),(2,'Adicional_2',2000),(3,'Adicional_3',3000),(4,'Adicional_4',4000),(5,'Adicional_5',5000);
/*!40000 ALTER TABLE `Adicional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gama`
--

DROP TABLE IF EXISTS `Gama`;
CREATE TABLE `Gama` (
  `id_gama` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(20) NOT NULL,
  `costo_adicional` float NOT NULL,
  PRIMARY KEY  (`id_gama`),
  UNIQUE KEY `Index_3` (`nombre`),
  KEY `nom_index` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gama`
--

LOCK TABLES `Gama` WRITE;
/*!40000 ALTER TABLE `Gama` DISABLE KEYS */;
INSERT INTO `Gama` VALUES (1,'Alta',0.13),(2,'Media',0.12),(3,'Baja',0.1);
/*!40000 ALTER TABLE `Gama` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `Pais`;
CREATE TABLE `Pais` (
  `id_pais` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(30) NOT NULL,
  `precio_adicional` float NOT NULL,
  PRIMARY KEY  (`id_pais`),
  UNIQUE KEY `Index_3` (`nombre`),
  KEY `nomp_index` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pais`
--

LOCK TABLES `Pais` WRITE;
/*!40000 ALTER TABLE `Pais` DISABLE KEYS */;
INSERT INTO `Pais` VALUES (1,'USA',5000),(2,'España',7000),(3,'China',5000),(4,'Taiwan',2000);
/*!40000 ALTER TABLE `Pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pro_adi`
--

DROP TABLE IF EXISTS `Pro_Adi`;
CREATE TABLE `Pro_Adi` (
  `id_producto` int(10) unsigned NOT NULL,
  `id_adicional` int(10) unsigned NOT NULL,
  KEY `FK_pro_adi_1` (`id_producto`),
  KEY `FK_pro_adi_2` (`id_adicional`),
  CONSTRAINT `FK_pro_adi_1` FOREIGN KEY (`id_producto`) REFERENCES `Producto` (`id_producto`),
  CONSTRAINT `FK_pro_adi_2` FOREIGN KEY (`id_adicional`) REFERENCES `Adicional` (`id_adicional`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pro_adi`
--

LOCK TABLES `Pro_Adi` WRITE;
/*!40000 ALTER TABLE `Pro_Adi` DISABLE KEYS */;
INSERT INTO `Pro_Adi` VALUES (2,1),(3,2),(5,3),(6,4),(8,5),(9,1),(11,2),(12,3),(12,4),(8,1),(9,2),(11,1);
/*!40000 ALTER TABLE `Pro_Adi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pro_pro`
--

DROP TABLE IF EXISTS `Pro_Pro`;
CREATE TABLE `Pro_Pro` (
  `producto` int(10) unsigned NOT NULL,
  `id_producto` int(10) unsigned NOT NULL,
  KEY `FK_Producto_Pro_1` (`id_producto`),
  KEY `FK_Producto_Pro_2` (`producto`),
  CONSTRAINT `FK_Producto_Pro_2` FOREIGN KEY (`producto`) REFERENCES `Producto` (`id_producto`) ON DELETE CASCADE,
  CONSTRAINT `FK_Producto_Pro_1` FOREIGN KEY (`id_producto`) REFERENCES `Producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pro_pro`
--

LOCK TABLES `Pro_Pro` WRITE;
/*!40000 ALTER TABLE `Pro_Pro` DISABLE KEYS */;
INSERT INTO `Pro_Pro` VALUES (3,4),(2,5),(1,9),(8,6),(9,6),(11,5),(11,8),(12,3),(12,6),(12,9);
/*!40000 ALTER TABLE `Pro_Pro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `Producto`;
CREATE TABLE `Producto` (
  `id_producto` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(30) NOT NULL,
  `id_pais` int(10) unsigned NOT NULL,
  `precio` float unsigned NOT NULL,
  `id_gama` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_producto`),
  KEY `Index_2` (`nombre`),
  KEY `FK_Producto_1` (`id_pais`),
  KEY `FK_producto_2` (`id_gama`),
  CONSTRAINT `FK_producto_2` FOREIGN KEY (`id_gama`) REFERENCES `Gama` (`id_gama`),
  CONSTRAINT `FK_Producto_1` FOREIGN KEY (`id_pais`) REFERENCES `Pais` (`id_pais`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `producto`
--

LOCK TABLES `Producto` WRITE;
/*!40000 ALTER TABLE `Producto` DISABLE KEYS */;
INSERT INTO `Producto` VALUES (1,'Esfero',1,113,1),(2,'Esfero',1,112,2),(3,'Esfero',3,113,3),(4,'Borrador',2,13,1),(5,'Borrador',2,12,2),(6,'Borrador',2,10,3),(7,'Lápiz',4,113,1),(8,'Lápiz',4,112,2),(9,'Lápiz',3,110,3),(10,'Cartuchera',3,1130,1),(11,'Cartuchera',3,1120,2),(12,'Cartuchera',4,1100,3);
/*!40000 ALTER TABLE `Producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-03-05  1:45:54