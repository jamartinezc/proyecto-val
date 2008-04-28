-- MySQL dump 10.11
--
-- Host: localhost    Database: liceoval1
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
-- Table structure for table `analista`
--

DROP TABLE IF EXISTS `analista`;
CREATE TABLE `analista` (
  `idAnalista` int(10) unsigned NOT NULL auto_increment,
  `idUsuario` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idAnalista`),
  KEY `FK_Analista_1` (`idUsuario`),
  CONSTRAINT `FK_Analista_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `analista`
--

LOCK TABLES `analista` WRITE;
/*!40000 ALTER TABLE `analista` DISABLE KEYS */;
INSERT INTO `analista` VALUES (1,22),(2,23),(3,24),(4,25),(5,26);
/*!40000 ALTER TABLE `analista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
CREATE TABLE `estados` (
  `idEstado` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY  (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'Ganado'),(2,'Perdido'),(3,'Pendiente/Aprobar'),(4,'Pendiente/Presentar'),(5,'No Presentado'),(6,'Nota Examen'),(7,'Nota Pendiente'),(8,'Pendiente/Calificar');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE `estudiante` (
  `idEstudiante` int(10) unsigned NOT NULL default '0',
  `idGrado` int(10) unsigned default NULL,
  `idTaller` int(10) unsigned NOT NULL,
  `fechaInicioGrado` datetime NOT NULL,
  `idUsuario` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idEstudiante`),
  UNIQUE KEY `Index_5` (`idUsuario`),
  KEY `FK_Estudiante_1` (`idTaller`),
  KEY `FK_estudiante_2` (`idUsuario`),
  KEY `FK_estudiante_3` (`idGrado`),
  CONSTRAINT `FK_Estudiante_1` FOREIGN KEY (`idTaller`) REFERENCES `taller` (`idTaller`),
  CONSTRAINT `FK_estudiante_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `FK_estudiante_3` FOREIGN KEY (`idGrado`) REFERENCES `grado` (`idGrado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (290,11,2,'2008-01-02 00:00:00',1),(291,11,2,'2008-01-02 00:00:00',2),(292,11,2,'2008-01-04 00:00:00',3),(293,11,2,'2008-02-02 00:00:00',4),(294,11,2,'2008-02-02 00:00:00',5),(295,11,1,'2008-03-02 00:00:00',6),(296,11,1,'2008-01-04 00:00:00',7),(297,11,3,'2008-01-04 00:00:00',8),(298,11,2,'2008-03-02 00:00:00',9),(299,11,1,'2008-01-02 00:00:00',10),(300,10,3,'2008-01-03 00:00:00',11),(301,10,2,'2008-01-04 00:00:00',13),(302,10,1,'2008-02-02 00:00:00',15),(303,10,3,'2008-03-02 00:00:00',16),(304,10,2,'2008-01-03 00:00:00',17),(305,10,1,'2008-01-01 00:00:00',18),(306,10,3,'2008-03-02 00:00:00',19),(307,10,2,'2008-02-02 00:00:00',20),(308,10,1,'2008-01-04 00:00:00',21),(309,11,2,'2008-04-13 00:00:00',26);
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examen`
--

DROP TABLE IF EXISTS `examen`;
CREATE TABLE `examen` (
  `idExamen` int(10) unsigned NOT NULL auto_increment,
  `tema` varchar(50) NOT NULL,
  `idMateria` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idExamen`),
  UNIQUE KEY `Index_2` USING BTREE (`tema`),
  KEY `FK_examen_1` (`idMateria`),
  CONSTRAINT `FK_examen_1` FOREIGN KEY (`idMateria`) REFERENCES `materia` (`idMateria`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examen`
--

LOCK TABLES `examen` WRITE;
/*!40000 ALTER TABLE `examen` DISABLE KEYS */;
INSERT INTO `examen` VALUES (1,'Funciones y gráficas',2),(2,'Sucesiones, límites y continuidad',2),(3,'Derivadas, máximos y mínimos',2),(4,'Antiderivadas e integrales',2),(5,'Áreas y volúmenes',2),(6,'Técnicas de integración',2),(7,'Tipos de Variable',20),(8,'Caracterización de variables',20),(9,'Tipos de Medidas',20),(10,'Eventos',20),(11,'Técnicas de conteo',20),(12,'Cálculo de probabilidades',20),(13,'Teoría de conjuntos',1),(14,'Inecuaciones',1),(15,'Funciones trigonométricas, logaritmo y exponencial',1),(16,'Identidades Trigonométricas',1),(17,'Cónicas',1),(18,'Matrices',1),(19,'Medidas de tendencia central',19),(20,'Medidas de dispersión',19),(21,'Análisis combinatorio',19),(22,'Principios de probabilidad',19),(23,'Teor¡a de conjuntos',19),(24,'Movimiento uniforme y acelerado',13),(25,'Movimiento parabólico',13),(26,'Leyes de Newton',13),(27,'Movimiento circular uniforme',13),(28,'Trabajo y Energia',13),(29,'Choques',13),(30,'Movimiento armónico simple',14),(31,'Péndulos',14),(32,'Efecto Doppler',14),(33,'Óptica',14),(34,'Eléctroestática y Leyes de Kirchhoff',14),(35,'Circuitos y Magnetismo',14),(36,'Tiempos: resente y pasado simple',11),(37,'Tiempos perfectos',11),(38,'Adverbios de cantidad y cuantificadores',11),(39,'Verbos: Can, Could, May, Might',11),(40,'Voz pasiva',11),(41,'Infinitivos y gerundios',11),(42,'Adverbios: manera, tiempo y espacio',12),(43,'Sustantivos compuestos',12),(44,'Conectores: contraste, adición y tiempo',12),(45,'Adjetivos terminados en ING y ED',12),(46,'Claúsulas de relación',12),(47,'Número de Avogadro y pesos moleculares',15),(48,'Nomemclatura y Fórmulas: mínima y molecular',15),(49,'Configuración electrónica y funciones químicas',15),(50,'Enlaces químicos',15),(51,'Balanceo de Ecuaciones y estequiometría',15),(52,'Gases y cinética química',15),(53,'Cadenas Carbonadas',16),(54,'Isomería e Hidrocarburos',16),(55,'Nomenclatura',16),(56,'Compuestos Orgánicos: nitrogenados y oxigenados',16),(57,'Proteínas y ácidos nucléicos',16),(58,'Vitaminas, enzimas y hormonas',16),(59,'Ramas de la Filosofía',17),(60,'Pensamiento Socrático',17),(61,'Pensamiento Platónico',17),(62,'Pensamiento Aristotélico',17),(63,'Teoría del conocimiento, metafísica y lógica',17),(64,'Filosofía Cristiana',17),(65,'Racionalismo: Descartes',18),(66,'Empirismo inglés e idealismo alemán',18),(67,'Ética y Moral',18),(68,'Hegel y Marx',18),(69,'Nietzsche y el Superhombre',18),(70,'Los estadios en Kierkegaard',18),(71,'Filosofía Latinoamericana',18),(72,'Gramática: Estudio del verbo',3),(73,'Normas ICONTEC',3),(74,'Siglas y Abreviaturas',3),(75,'Hitos de la literatura',3),(76,'Literatura Europea',3),(77,'Prueba Ortográfica',3),(78,'Mitología y Relatos Épicos',4),(79,'Estructura Narrativa: Narrador Focalizador',4),(80,'Micro-relato: Caracterización y estructura',4),(81,'La Novela del Siglo XX',4),(82,'El Ensayo',4),(83,'Literatura contemporánea',4),(84,'Cine e Historietas Gráficas',4),(85,'Ateísmo y otras clases de creencias',25),(86,'Misión y vocación del Ser Humano',25),(87,'El Pecado',25),(88,'Principales religiones del mundo',25),(89,'Sectas Cristianas',25),(90,'Misión Cristiana en la Sociedad',26),(91,'Pecado Social',26),(92,'El Bien y los Actos Aceptables',26),(93,'Jesús y los Evangelios',26),(94,'Números Reales',23),(95,'Fundamentos Algebráicos',23),(96,'Factorización',23),(97,'Ecuaciones Lineales',23),(98,'Triángulos Congruentes y Semejantes',23),(99,'Sistemas de Ecuaciones',24),(100,'Potenciación y Radicación',24),(101,'Números Complejos',24),(102,'Progresiones Aritméticas y Geométricas',24),(114,'Europa Medieval',9),(115,'Revoluciones en los siglos XVIII y XIX',9),(116,'Revolución Francesa',9),(117,'Revolución Industrial',9),(118,'Historia de Asia y África',9),(119,'Primera Guerra Mundial',10),(120,'Crisis del 29',10),(121,'Segunda Guerra Mundial',10),(122,'Guerra Fría',10),(123,'Descolonización de Asia y África',10),(124,'Responsabilidad Social',21),(125,'Derechos del niño, la mujer y la familia',21),(126,'Escuelas Éticas',21),(127,'Sistema Óseo',21),(128,'Sistema Muscular',21),(129,'Nutrición',21),(130,'Derecho Internacional Humanitario',22),(131,'Tribus Urbanas y Culturas Juveniles',22),(132,'Ética Moral Profesional',22),(133,'Enfermedades comunes',22),(134,'Órganos y su cuidado',22),(135,'Primeros Auxilios',22);
/*!40000 ALTER TABLE `examen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examenmes`
--

DROP TABLE IF EXISTS `examenmes`;
CREATE TABLE `examenmes` (
  `idExamenMes` int(10) unsigned NOT NULL auto_increment,
  `mes` int(10) unsigned NOT NULL,
  `presentados` int(10) unsigned NOT NULL,
  `ganados` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idExamenMes`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examenmes`
--

LOCK TABLES `examenmes` WRITE;
/*!40000 ALTER TABLE `examenmes` DISABLE KEYS */;
/*!40000 ALTER TABLE `examenmes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examenplaneado`
--

DROP TABLE IF EXISTS `examenplaneado`;
CREATE TABLE `examenplaneado` (
  `idExamenPlaneado` int(10) unsigned NOT NULL auto_increment,
  `idExamen` int(10) unsigned NOT NULL,
  `idEstado` int(10) unsigned NOT NULL,
  `idPlaneacionSemanal` int(10) unsigned NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY  (`idExamenPlaneado`),
  KEY `FK_examenplaneado_2` (`idEstado`),
  KEY `FK_examenplaneado_3` (`idPlaneacionSemanal`),
  KEY `FK_ExamenPlaneado_1` (`idExamen`),
  CONSTRAINT `FK_ExamenPlaneado_1` FOREIGN KEY (`idExamen`) REFERENCES `examen` (`idExamen`),
  CONSTRAINT `FK_examenplaneado_2` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`),
  CONSTRAINT `FK_examenplaneado_3` FOREIGN KEY (`idPlaneacionSemanal`) REFERENCES `planeacionsemanal` (`idPlaneacionSemanal`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examenplaneado`
--

LOCK TABLES `examenplaneado` WRITE;
/*!40000 ALTER TABLE `examenplaneado` DISABLE KEYS */;
/*!40000 ALTER TABLE `examenplaneado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examensolicitado`
--

DROP TABLE IF EXISTS `examensolicitado`;
CREATE TABLE `examensolicitado` (
  `idExamenSolicitado` int(10) unsigned NOT NULL auto_increment,
  `idEstudiante` int(10) unsigned NOT NULL,
  `idAnalista` int(10) unsigned NOT NULL,
  `nota` float NOT NULL,
  `fecha` datetime NOT NULL,
  `idEstado` int(10) unsigned NOT NULL,
  `idRegistro` int(10) unsigned NOT NULL,
  `idExamen` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idExamenSolicitado`),
  KEY `FK_examenSolicitado_1` (`idAnalista`),
  KEY `FK_examensolicitado_2` (`idRegistro`),
  KEY `FK_examensolicitado_3` (`idExamen`),
  KEY `FK_examensolicitado_4` (`idEstado`),
  KEY `FK_examensolicitado_5` (`idEstudiante`),
  CONSTRAINT `FK_examenSolicitado_1` FOREIGN KEY (`idAnalista`) REFERENCES `analista` (`idAnalista`),
  CONSTRAINT `FK_examensolicitado_2` FOREIGN KEY (`idRegistro`) REFERENCES `registro` (`idRegistro`) ON DELETE CASCADE,
  CONSTRAINT `FK_examensolicitado_3` FOREIGN KEY (`idExamen`) REFERENCES `examen` (`idExamen`),
  CONSTRAINT `FK_examensolicitado_4` FOREIGN KEY (`idEstado`) REFERENCES `estados` (`idEstado`),
  CONSTRAINT `FK_examensolicitado_5` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examensolicitado`
--

LOCK TABLES `examensolicitado` WRITE;
/*!40000 ALTER TABLE `examensolicitado` DISABLE KEYS */;
INSERT INTO `examensolicitado` VALUES (4,290,1,0,'2008-04-20 20:13:54',5,4,13);
/*!40000 ALTER TABLE `examensolicitado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `excelenciataller`
--

DROP TABLE IF EXISTS `excelenciataller`;
CREATE TABLE `excelenciataller` (
  `idTaller` int(10) unsigned NOT NULL,
  `idEstudiante` int(10) unsigned NOT NULL,
  `presentados` int(10) unsigned NOT NULL,
  `ganados` int(10) unsigned NOT NULL,
  `presentadosTaller` int(10) unsigned NOT NULL,
  `ganadosTaller` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idTaller`),
  KEY `FK_excelenciataller_2` (`idEstudiante`),
  CONSTRAINT `FK_excelenciataller_1` FOREIGN KEY (`idTaller`) REFERENCES `taller` (`idTaller`),
  CONSTRAINT `FK_excelenciataller_2` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `excelenciataller`
--

LOCK TABLES `excelenciataller` WRITE;
/*!40000 ALTER TABLE `excelenciataller` DISABLE KEYS */;
/*!40000 ALTER TABLE `excelenciataller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grado`
--

DROP TABLE IF EXISTS `grado`;
CREATE TABLE `grado` (
  `idGrado` int(10) unsigned NOT NULL default '0',
  `nombre` varchar(10) NOT NULL,
  PRIMARY KEY  USING BTREE (`idGrado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grado`
--

LOCK TABLES `grado` WRITE;
/*!40000 ALTER TABLE `grado` DISABLE KEYS */;
INSERT INTO `grado` VALUES (10,'Décimo'),(11,'Once');
/*!40000 ALTER TABLE `grado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
CREATE TABLE `materia` (
  `idMateria` int(10) unsigned NOT NULL auto_increment,
  `nombre` varchar(30) NOT NULL,
  `idTutor` int(10) unsigned NOT NULL,
  `idAnalista` int(10) unsigned NOT NULL,
  `idGrado` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idMateria`),
  KEY `FK_Materia_1` (`idAnalista`),
  KEY `FK_Materia_2` (`idTutor`),
  KEY `FK_materia_3` (`idGrado`),
  KEY `Index_5` (`nombre`,`idAnalista`,`idGrado`),
  CONSTRAINT `FK_Materia_1` FOREIGN KEY (`idAnalista`) REFERENCES `analista` (`idAnalista`),
  CONSTRAINT `FK_Materia_2` FOREIGN KEY (`idTutor`) REFERENCES `tutor` (`idTutor`),
  CONSTRAINT `FK_materia_3` FOREIGN KEY (`idGrado`) REFERENCES `grado` (`idGrado`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `materia`
--

LOCK TABLES `materia` WRITE;
/*!40000 ALTER TABLE `materia` DISABLE KEYS */;
INSERT INTO `materia` VALUES (1,'Matemáticas',1,1,10),(2,'Matemáticas',1,1,11),(3,'Lengua Castellana',2,2,10),(4,'Lengua Castellana',2,2,11),(9,'Historia',2,4,10),(10,'Historia',2,4,11),(11,'Inglés',2,2,10),(12,'Inglés',2,2,11),(13,'Física',1,1,10),(14,'Física',1,1,11),(15,'Química',3,3,10),(16,'Química',3,3,11),(17,'Filosofía',2,4,10),(18,'Filosofía',2,4,11),(19,'Estadística',1,1,10),(20,'Estadística',1,1,11),(21,'Comportamiento y Salud',3,3,10),(22,'Comportamiento y Salud',3,3,11),(23,'Bases de Matemáticas',1,1,10),(24,'Bases de Matemáticas',1,1,11),(25,'Religión',2,2,10),(26,'Religión',2,2,11);
/*!40000 ALTER TABLE `materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiaplaneada`
--

DROP TABLE IF EXISTS `materiaplaneada`;
CREATE TABLE `materiaplaneada` (
  `idMateriaPlaneada` int(10) unsigned NOT NULL auto_increment,
  `idMateria` int(10) unsigned NOT NULL,
  `idExamenMes` int(10) unsigned NOT NULL,
  `idPlaneacionAnual` int(10) unsigned NOT NULL,
  `mesInicio` int(10) unsigned NOT NULL,
  `mesFin` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idMateriaPlaneada`),
  KEY `FK_MateriaPlaneada_2` (`idExamenMes`),
  KEY `FK_materiaplaneada_3` (`idPlaneacionAnual`),
  KEY `FK_MateriaPlaneada_1` (`idMateria`),
  CONSTRAINT `FK_MateriaPlaneada_1` FOREIGN KEY (`idMateria`) REFERENCES `materia` (`idMateria`),
  CONSTRAINT `FK_MateriaPlaneada_2` FOREIGN KEY (`idExamenMes`) REFERENCES `examenmes` (`idExamenMes`),
  CONSTRAINT `FK_materiaplaneada_3` FOREIGN KEY (`idPlaneacionAnual`) REFERENCES `planeacionanual` (`idPlaneacionAnual`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `materiaplaneada`
--

LOCK TABLES `materiaplaneada` WRITE;
/*!40000 ALTER TABLE `materiaplaneada` DISABLE KEYS */;
/*!40000 ALTER TABLE `materiaplaneada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `padre`
--

DROP TABLE IF EXISTS `padre`;
CREATE TABLE `padre` (
  `idPadre` int(10) unsigned NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `correo` varchar(20) NOT NULL,
  `idEstudiante` int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`idPadre`),
  KEY `FK_padre_1` (`idEstudiante`),
  CONSTRAINT `FK_padre_1` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `padre`
--

LOCK TABLES `padre` WRITE;
/*!40000 ALTER TABLE `padre` DISABLE KEYS */;
/*!40000 ALTER TABLE `padre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planeacionanual`
--

DROP TABLE IF EXISTS `planeacionanual`;
CREATE TABLE `planeacionanual` (
  `idPlaneacionAnual` int(10) unsigned NOT NULL auto_increment,
  `idEstudiante` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idPlaneacionAnual`),
  UNIQUE KEY `FK_planeacionAnual_1` USING BTREE (`idEstudiante`),
  CONSTRAINT `FK_planeacionAnual_1` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `planeacionanual`
--

LOCK TABLES `planeacionanual` WRITE;
/*!40000 ALTER TABLE `planeacionanual` DISABLE KEYS */;
/*!40000 ALTER TABLE `planeacionanual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planeacionsemanal`
--

DROP TABLE IF EXISTS `planeacionsemanal`;
CREATE TABLE `planeacionsemanal` (
  `idPlaneacionSemanal` int(10) unsigned NOT NULL auto_increment,
  `semana` int(10) unsigned NOT NULL,
  `idGrado` int(10) unsigned NOT NULL,
  `idEstudiante` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idPlaneacionSemanal`),
  KEY `FK_PlaneacionSemanal_1` (`idGrado`),
  KEY `FK_planeacionsemanal_2` (`idEstudiante`),
  CONSTRAINT `FK_PlaneacionSemanal_1` FOREIGN KEY (`idGrado`) REFERENCES `grado` (`idGrado`),
  CONSTRAINT `FK_planeacionsemanal_2` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `planeacionsemanal`
--

LOCK TABLES `planeacionsemanal` WRITE;
/*!40000 ALTER TABLE `planeacionsemanal` DISABLE KEYS */;
/*!40000 ALTER TABLE `planeacionsemanal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
CREATE TABLE `registro` (
  `idEstudiante` int(10) unsigned NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `idRegistro` int(10) unsigned NOT NULL auto_increment,
  `idMateria` int(10) unsigned NOT NULL,
  `vecesDevuelta` int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`idRegistro`),
  UNIQUE KEY `Index_4` (`idMateria`,`idEstudiante`),
  KEY `FK_registro_2` (`idMateria`),
  KEY `FK_registro_1` (`idEstudiante`),
  CONSTRAINT `FK_registro_1` FOREIGN KEY (`idEstudiante`) REFERENCES `estudiante` (`idEstudiante`) ON DELETE CASCADE,
  CONSTRAINT `FK_registro_2` FOREIGN KEY (`idMateria`) REFERENCES `materia` (`idMateria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES (290,0,4,1,0),(303,1,5,16,0),(292,1,6,26,0),(309,1,7,19,0);
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretariaacademica`
--

DROP TABLE IF EXISTS `secretariaacademica`;
CREATE TABLE `secretariaacademica` (
  `idSecretariaAcademica` int(10) unsigned NOT NULL auto_increment,
  `idUsuario` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idSecretariaAcademica`),
  KEY `FK_SecretariaAcademica_1` (`idUsuario`),
  CONSTRAINT `FK_SecretariaAcademica_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `secretariaacademica`
--

LOCK TABLES `secretariaacademica` WRITE;
/*!40000 ALTER TABLE `secretariaacademica` DISABLE KEYS */;
INSERT INTO `secretariaacademica` VALUES (1,12),(2,14),(3,26);
/*!40000 ALTER TABLE `secretariaacademica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taller`
--

DROP TABLE IF EXISTS `taller`;
CREATE TABLE `taller` (
  `idTaller` int(10) unsigned NOT NULL auto_increment,
  `idTutor` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idTaller`),
  UNIQUE KEY `Index_2` (`idTutor`),
  CONSTRAINT `FK_taller_1` FOREIGN KEY (`idTaller`) REFERENCES `taller` (`idTaller`),
  CONSTRAINT `FK_taller_2` FOREIGN KEY (`idTutor`) REFERENCES `tutor` (`idTutor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `taller`
--

LOCK TABLES `taller` WRITE;
/*!40000 ALTER TABLE `taller` DISABLE KEYS */;
INSERT INTO `taller` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `taller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
CREATE TABLE `tutor` (
  `idTutor` int(10) unsigned NOT NULL auto_increment,
  `idUsuario` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idTutor`),
  KEY `FK_Tutor_1` (`idUsuario`),
  CONSTRAINT `FK_Tutor_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
INSERT INTO `tutor` VALUES (1,22),(3,23),(2,24),(4,26);
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idUsuario` int(10) unsigned NOT NULL auto_increment,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `login` varchar(20) NOT NULL,
  `clave` varchar(14) NOT NULL,
  PRIMARY KEY  (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'María Camila','Hernández Olarte','mchernandezo','mlkiop99'),(2,'Juan Pablo','Perdomo Garzón','jpperdomog','aqwesd46'),(3,'Karen Julieth','Rodríguez Bohórquez','kjrodriguezb','ikjlut31'),(4,'Lorenzo','Caviedes Lacompte','lcaviedesl','uhjngt78'),(5,'Elizabeth','Cavieds Lacompte','ecaviedesl','nmaqpd56'),(6,'Lina María','Gómez Alcazar','lmgomeza','phjkqg09'),(7,'Juan Camilo','García Avellaneda','jcgarciaa','zxcvrt83'),(8,'Ximena','Ballesteros López','xballesterosl','zasqwx89'),(9,'Julián Camilo','Pérez Sierra','jcperezs','qkoshd58'),(10,'Lorena','Perdomo Fonseca','lperdomof','ijhwqp93'),(11,'Alejandro','Cubillos Triana','acubillost','ewpsbgid78'),(12,'Mireya','Suárez Ramirez','msuarezr','jsehgc11'),(13,'María José','Porto Forero','mjportof','awhbsd86'),(14,'Jorge','Pedraza Córdoba','jpedrazac','hyrtui75'),(15,'Katherine','Del Río García','kdelriog','wqtrey90'),(16,'Gabriel','Heredia Jiménez','gherediaj','iupear76'),(17,'Hana Manuela','Hoffman Triada','hmhoffmant','uytred57'),(18,'Daniel','Gómez Toro','dgomezt','nhujms23'),(19,'Juan Camilo','Triana Herrera','jctrianah','uyhsqw44'),(20,'Santiago','Quintero López','squinterol','poiuyt65'),(21,'Juan Camilo','Martínez Castro','jcmartinezc','iuolkj75'),(22,'Eridson','Rodríguez Casas','erodriguezc','ognbco79'),(23,'Omar','Baracaldo Toquica','obaracaldot','jghytf36'),(24,'Alexandra','Bernal Bonfante','abernalb','kalswq96'),(25,'Fredy','Torres Garzón','ftorresg','qdwfeg35'),(26,'Mario','Linares Vasquez','mlinaresv','ssh22'),(28,'davor','tañito','damontic','gelosa'),(29,'Jorge','Martinez','george','swat'),(30,'Angela','Franco','gelosa','david'),(32,'Tatiana','Franco','tati','elmasbuscado');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'liceoval1'
--
DELIMITER ;;
/*!50003 DROP PROCEDURE IF EXISTS `devolverCurso` */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`liceoval`@`%`*/ /*!50003 PROCEDURE `devolverCurso`(IN est INT)
BEGIN



delete from registro where codigo=est;



delete from planeacionsemanal where codigo=est;



delete from planeacionanual where codigo=est;



END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
/*!50003 DROP PROCEDURE IF EXISTS `devolverMateria` */;;
/*!50003 SET SESSION SQL_MODE="STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`liceoval`@`%`*/ /*!50003 PROCEDURE `devolverMateria`(IN est INT,IN mat INT)
BEGIN



declare ps int;



declare cod int;



SELECT cod=examen.codigo FROM materia JOIN examen WHERE materia.codigo=mat and materia=mat;



select ps=idPlaneacionSemanal from planeacionsemanal where codigo=est;



DELETE FROM examenplaneado WHERE codigo=cod and planeacionSemanal=ps;



END */;;
/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE*/;;
DELIMITER ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2008-04-28  3:03:10
