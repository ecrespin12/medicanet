-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: medicanet
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.37-MariaDB

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
CREATE DATABASE medicanet_db;
USE medicanet_db;
--
-- Table structure for table `tbl_cmd_centro_medico`
--

DROP TABLE IF EXISTS `tbl_cmd_centro_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cmd_centro_medico` (
  `cmd_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cmd_nombre` varchar(150) NOT NULL,
  `cmd_latitud` decimal(10,0) NOT NULL,
  `cmd_altitud` decimal(10,0) NOT NULL,
  PRIMARY KEY (`cmd_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cmd_centro_medico`
--

LOCK TABLES `tbl_cmd_centro_medico` WRITE;
/*!40000 ALTER TABLE `tbl_cmd_centro_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cmd_centro_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cme_consulta_medica`
--

DROP TABLE IF EXISTS `tbl_cme_consulta_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cme_consulta_medica` (
  `cme_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cme_codper` int(11) NOT NULL,
  `cme_codmed` int(11) NOT NULL,
  `cme_codcmd` int(11) NOT NULL,
  `cme_fecha_crea` datetime NOT NULL,
  PRIMARY KEY (`cme_codigo`),
  KEY `FK_CME_PER_idx` (`cme_codper`),
  KEY `FK_CME_MED_idx` (`cme_codmed`),
  KEY `FK_CME_CMD_idx` (`cme_codcmd`),
  CONSTRAINT `FK_CME_CMD` FOREIGN KEY (`cme_codcmd`) REFERENCES `tbl_cme_consulta_medica` (`cme_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CME_MED` FOREIGN KEY (`cme_codmed`) REFERENCES `tbl_med_medico` (`med_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CME_PER` FOREIGN KEY (`cme_codper`) REFERENCES `tbl_per_persona` (`per_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cme_consulta_medica`
--

LOCK TABLES `tbl_cme_consulta_medica` WRITE;
/*!40000 ALTER TABLE `tbl_cme_consulta_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cme_consulta_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_dcm_detalle_consulta_medica`
--

DROP TABLE IF EXISTS `tbl_dcm_detalle_consulta_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_dcm_detalle_consulta_medica` (
  `dcm_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `dcm_nombre` varchar(100) NOT NULL,
  `dcm_descripcion` varchar(2000) NOT NULL,
  `dcm_codcme` int(11) NOT NULL,
  PRIMARY KEY (`dcm_codigo`),
  KEY `FK_DCM_CME_idx` (`dcm_codcme`),
  CONSTRAINT `FK_DCM_CME` FOREIGN KEY (`dcm_codcme`) REFERENCES `tbl_cme_consulta_medica` (`cme_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_dcm_detalle_consulta_medica`
--

LOCK TABLES `tbl_dcm_detalle_consulta_medica` WRITE;
/*!40000 ALTER TABLE `tbl_dcm_detalle_consulta_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_dcm_detalle_consulta_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_emd_especialidad_medica`
--

DROP TABLE IF EXISTS `tbl_emd_especialidad_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_emd_especialidad_medica` (
  `emd_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `emd_nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`emd_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_emd_especialidad_medica`
--

LOCK TABLES `tbl_emd_especialidad_medica` WRITE;
/*!40000 ALTER TABLE `tbl_emd_especialidad_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_emd_especialidad_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_emm_especialidad_medica_medico`
--

DROP TABLE IF EXISTS `tbl_emm_especialidad_medica_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_emm_especialidad_medica_medico` (
  `emm_codemd` int(11) NOT NULL,
  `emm_codmed` int(11) NOT NULL,
  PRIMARY KEY (`emm_codemd`,`emm_codmed`),
  KEY `FK_EMM_MED_idx` (`emm_codmed`),
  CONSTRAINT `FK_EMM_EMD` FOREIGN KEY (`emm_codemd`) REFERENCES `tbl_emd_especialidad_medica` (`emd_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_EMM_MED` FOREIGN KEY (`emm_codmed`) REFERENCES `tbl_med_medico` (`med_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_emm_especialidad_medica_medico`
--

LOCK TABLES `tbl_emm_especialidad_medica_medico` WRITE;
/*!40000 ALTER TABLE `tbl_emm_especialidad_medica_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_emm_especialidad_medica_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_far_farmacia`
--

DROP TABLE IF EXISTS `tbl_far_farmacia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_far_farmacia` (
  `far_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `far_nombre` varchar(150) NOT NULL,
  `far_latitud` decimal(10,0) NOT NULL,
  `far_altitud` decimal(10,0) NOT NULL,
  `far_correo` varchar(150) NOT NULL,
  `far_clave` varchar(25) NOT NULL,
  `far_estado` varchar(1) NOT NULL,
  PRIMARY KEY (`far_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_far_farmacia`
--

LOCK TABLES `tbl_far_farmacia` WRITE;
/*!40000 ALTER TABLE `tbl_far_farmacia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_far_farmacia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_fem_farmacia_entrega_medicamento`
--

DROP TABLE IF EXISTS `tbl_fem_farmacia_entrega_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_fem_farmacia_entrega_medicamento` (
  `fem_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fem_codfar` int(11) NOT NULL,
  `fem_codmdc` int(11) NOT NULL,
  `fem_codcme` int(11) NOT NULL,
  `fem_cantidad` varchar(10) NOT NULL,
  `fem_fecha_entrega` datetime NOT NULL,
  PRIMARY KEY (`fem_codigo`),
  KEY `FK_FEM_FAR_idx` (`fem_codfar`),
  KEY `FK_FEM_MDC_idx` (`fem_codmdc`),
  KEY `FK_FEM_CME_idx` (`fem_codcme`),
  CONSTRAINT `FK_FEM_CME` FOREIGN KEY (`fem_codcme`) REFERENCES `tbl_cme_consulta_medica` (`cme_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FEM_FAR` FOREIGN KEY (`fem_codfar`) REFERENCES `tbl_far_farmacia` (`far_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FEM_MDC` FOREIGN KEY (`fem_codmdc`) REFERENCES `tbl_mdc_medicamento` (`mdc_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_fem_farmacia_entrega_medicamento`
--

LOCK TABLES `tbl_fem_farmacia_entrega_medicamento` WRITE;
/*!40000 ALTER TABLE `tbl_fem_farmacia_entrega_medicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_fem_farmacia_entrega_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hme_historial_medico`
--

DROP TABLE IF EXISTS `tbl_hme_historial_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hme_historial_medico` (
  `hem_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `hem_codper` int(11) NOT NULL,
  `hem_codthm` int(11) NOT NULL,
  `hem_descripcion` varchar(1000) NOT NULL,
  `hem_fecha_crea` datetime NOT NULL,
  PRIMARY KEY (`hem_codigo`),
  KEY `FK_HME_PER_idx` (`hem_codper`),
  KEY `FK_HME_THM_idx` (`hem_codthm`),
  CONSTRAINT `FK_HME_PER` FOREIGN KEY (`hem_codper`) REFERENCES `tbl_per_persona` (`per_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_HME_THM` FOREIGN KEY (`hem_codthm`) REFERENCES `tbl_thm_tipo_historial_medico` (`thm_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hme_historial_medico`
--

LOCK TABLES `tbl_hme_historial_medico` WRITE;
/*!40000 ALTER TABLE `tbl_hme_historial_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_hme_historial_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_mac_medico_atiende_centro_medico`
--

DROP TABLE IF EXISTS `tbl_mac_medico_atiende_centro_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mac_medico_atiende_centro_medico` (
  `mac_codcmd` int(11) NOT NULL,
  `mac_codmed` int(11) NOT NULL,
  PRIMARY KEY (`mac_codcmd`,`mac_codmed`),
  KEY `FK_MAC_MED_idx` (`mac_codmed`),
  CONSTRAINT `FK_MAC_CMD` FOREIGN KEY (`mac_codcmd`) REFERENCES `tbl_cmd_centro_medico` (`cmd_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MAC_MED` FOREIGN KEY (`mac_codmed`) REFERENCES `tbl_med_medico` (`med_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_mac_medico_atiende_centro_medico`
--

LOCK TABLES `tbl_mac_medico_atiende_centro_medico` WRITE;
/*!40000 ALTER TABLE `tbl_mac_medico_atiende_centro_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_mac_medico_atiende_centro_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_mdc_medicamento`
--

DROP TABLE IF EXISTS `tbl_mdc_medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mdc_medicamento` (
  `mdc_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `mdc_nombre` varchar(100) NOT NULL,
  `mdc_descripcion` varchar(2000) NOT NULL,
  `mdc_medida` varchar(10) NOT NULL,
  PRIMARY KEY (`mdc_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_mdc_medicamento`
--

LOCK TABLES `tbl_mdc_medicamento` WRITE;
/*!40000 ALTER TABLE `tbl_mdc_medicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_mdc_medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_med_medico`
--

DROP TABLE IF EXISTS `tbl_med_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_med_medico` (
  `med_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `med_codper` int(11) NOT NULL,
  PRIMARY KEY (`med_codigo`),
  KEY `FK_MED_PER_idx` (`med_codper`),
  CONSTRAINT `FK_MED_PER` FOREIGN KEY (`med_codper`) REFERENCES `tbl_per_persona` (`per_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_med_medico`
--

LOCK TABLES `tbl_med_medico` WRITE;
/*!40000 ALTER TABLE `tbl_med_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_med_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_per_persona`
--

DROP TABLE IF EXISTS `tbl_per_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_per_persona` (
  `per_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `per_nombre` varchar(150) NOT NULL,
  `per_apellidos` varchar(150) NOT NULL,
  `per_fecha_nace` datetime NOT NULL,
  `per_correo` varchar(250) NOT NULL,
  `per_clave` varchar(25) NOT NULL,
  `per_codigo_activa` varchar(10) DEFAULT NULL,
  `per_fecha_hora_emite_codigo` datetime DEFAULT NULL,
  `per_estado` varchar(1) NOT NULL,
  `per_dui` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`per_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_per_persona`
--

LOCK TABLES `tbl_per_persona` WRITE;
/*!40000 ALTER TABLE `tbl_per_persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_per_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rme_receta_medica`
--

DROP TABLE IF EXISTS `tbl_rme_receta_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rme_receta_medica` (
  `rme_codmdc` int(11) NOT NULL,
  `rme_codcme` int(11) NOT NULL,
  `rme_indicaciones` varchar(500) NOT NULL,
  `rme_cantidad` decimal(10,0) NOT NULL,
  PRIMARY KEY (`rme_codmdc`,`rme_codcme`),
  KEY `FK_RME_CME_idx` (`rme_codcme`),
  CONSTRAINT `FK_RME_CME` FOREIGN KEY (`rme_codcme`) REFERENCES `tbl_cme_consulta_medica` (`cme_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_RME_MDC` FOREIGN KEY (`rme_codmdc`) REFERENCES `tbl_mdc_medicamento` (`mdc_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rme_receta_medica`
--

LOCK TABLES `tbl_rme_receta_medica` WRITE;
/*!40000 ALTER TABLE `tbl_rme_receta_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_rme_receta_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_thm_tipo_historial_medico`
--

DROP TABLE IF EXISTS `tbl_thm_tipo_historial_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_thm_tipo_historial_medico` (
  `thm_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `thm_nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`thm_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_thm_tipo_historial_medico`
--

LOCK TABLES `tbl_thm_tipo_historial_medico` WRITE;
/*!40000 ALTER TABLE `tbl_thm_tipo_historial_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_thm_tipo_historial_medico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-21 15:11:05
