-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema elsabor
--

CREATE DATABASE IF NOT EXISTS elsabor;
USE elsabor;

--
-- Definition of table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `idcategory` int(10) unsigned NOT NULL auto_increment,
  `description` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY  (`idcategory`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`idcategory`,`description`,`name`) VALUES 
 (1,'Ninguna','Categoria 1'),
 (2,'Ninguna','Categoria 2'),
 (3,'Ninguna','Categoria 3'),
 (4,'Ninguna','Categoria 4');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- Definition of table `paymentoption`
--

DROP TABLE IF EXISTS `paymentoption`;
CREATE TABLE `paymentoption` (
  `idpaymentoption` int(10) unsigned NOT NULL auto_increment,
  `discount` double NOT NULL,
  `name` varchar(45) NOT NULL,
  `idproduct` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idpaymentoption`),
  KEY `FK_paymentoption_1` (`idproduct`),
  CONSTRAINT `FK_paymentoption_1` FOREIGN KEY (`idproduct`) REFERENCES `product` (`idproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymentoption`
--

/*!40000 ALTER TABLE `paymentoption` DISABLE KEYS */;
INSERT INTO `paymentoption` (`idpaymentoption`,`discount`,`name`,`idproduct`) VALUES 
 (1,10.5,'Pago1',1),
 (2,0.5,'Pago2',2),
 (3,4.1,'Pago3',3),
 (4,0.7,'Pago4',4);
/*!40000 ALTER TABLE `paymentoption` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `idproduct` int(10) unsigned NOT NULL auto_increment,
  `description` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `idsupplier` int(10) unsigned NOT NULL,
  `idcategory` int(10) unsigned default NULL,
  PRIMARY KEY  (`idproduct`),
  KEY `FK_product_1` (`idcategory`),
  KEY `FK_product_2` (`idsupplier`),
  CONSTRAINT `FK_product_1` FOREIGN KEY (`idcategory`) REFERENCES `category` (`idcategory`) ON DELETE SET NULL,
  CONSTRAINT `FK_product_2` FOREIGN KEY (`idsupplier`) REFERENCES `supplier` (`idsupplier`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`idproduct`,`description`,`name`,`idsupplier`,`idcategory`) VALUES 
 (1,'Ninguna','Producto1',1,1),
 (2,'Ninguna','Producto2',2,1),
 (3,'Ninguna','Producto3',3,4),
 (4,'Ninguna','Producto4',2,3);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Definition of table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `idsupplier` int(10) unsigned NOT NULL auto_increment,
  `address` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY  (`idsupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`idsupplier`,`address`,`name`,`phone`) VALUES 
 (1,'Calle X,Y Z','Proveedor 1','2985647'),
 (2,'Calle X,Y Z','Proveedor 2','5698748'),
 (3,'Calle X,Y Z','Proveedor 3','1254896'),
 (4,'Calle X,Y Z','Proveedor 4','1456987');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
