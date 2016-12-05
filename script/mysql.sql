CREATE DATABASE  IF NOT EXISTS `lms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lms`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lms
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `dueDate` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `totalScore` varchar(45) DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  `file_id` int(11) DEFAULT NULL,
  `submit_by` varchar(45) NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_assignment_course1_idx` (`course_id`),
  KEY `fk_assignment_file1_idx` (`file_id`),
  CONSTRAINT `fk_assignment_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_assignment_file1` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'assignment1','2016-12-17','public','10',NULL,1,NULL,'',NULL),(2,'assignment1','2016-12-17','public','10','20%',1,NULL,'',NULL),(3,'assignment1','2016-12-17','public','10','20%',1,NULL,'',NULL),(4,'assignment1','2016-12-10','Published','10','20%',1,NULL,'File',NULL),(6,'assignment1','2016-12-10','Published','50','20%',12,NULL,'File','asaa'),(7,'assignment2','2016-12-08','Unpublished','10','30%',12,NULL,'Email','asa'),(8,'assignment3','2016-12-03','Published','100','5%',12,NULL,'Email','qsaas');
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment_response`
--

DROP TABLE IF EXISTS `assignment_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_response` (
  `user_id` int(11) NOT NULL,
  `assignment_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `file_id` int(11) DEFAULT NULL,
  `submit_time` datetime(6) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`assignment_id`),
  KEY `fk_user_has_assignment_assignment1_idx` (`assignment_id`),
  KEY `fk_user_has_assignment_user1_idx` (`user_id`),
  KEY `fk_user_has_assignment_course1_idx` (`course_id`),
  KEY `fk_user_has_assignment_file1_idx` (`file_id`),
  CONSTRAINT `fk_user_has_assignment_assignment1` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_assignment_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_assignment_file1` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_assignment_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_response`
--

LOCK TABLES `assignment_response` WRITE;
/*!40000 ALTER TABLE `assignment_response` DISABLE KEYS */;
/*!40000 ALTER TABLE `assignment_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `code` varchar(45) NOT NULL,
  `semester` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'123e','14:00-15:00e','enpm611e','Summer2017'),(3,'123z','14:00-15:00','enpm614','Fall2016'),(4,'45321','45312','123','Fall2016'),(5,'5','5','5','Fall2016'),(6,'2','2','2','2'),(8,'66','66','667','Fall2016'),(9,'1212','1212','12','Select'),(10,'1212','1212','12','Select'),(12,'s4','14:00-15:00','enpm613','Fall2016'),(13,'541','4141','5','Winter2016');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `upload_time` datetime(6) NOT NULL,
  `path` varchar(45) NOT NULL,
  `uploader_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syllabus`
--

DROP TABLE IF EXISTS `syllabus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syllabus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_syllabus_file1_idx` (`file_id`),
  KEY `fk_syllabus_course1_idx` (`course_id`),
  CONSTRAINT `fk_syllabus_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_syllabus_file1` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syllabus`
--

LOCK TABLES `syllabus` WRITE;
/*!40000 ALTER TABLE `syllabus` DISABLE KEYS */;
/*!40000 ALTER TABLE `syllabus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `role` varchar(45) NOT NULL,
  `account_non_locked` tinyint(1) NOT NULL DEFAULT '1',
  `login_attempts` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'student1','1518596cc02033138901826200c26c60',1,'ROLE_STU',1,0),(2,'admin','b594510740d2ac4261c1b2fe87850d08',1,'ROLE_ADMIN',1,0),(3,'instructor1','e820b3ccd0518ef85581594be163fb92',1,'ROLE_INSTR',1,0),(4,'instructor2','d1d664b63ca62c765efc46705bbd4eb9',1,'ROLE_INSTR',1,0),(5,'student2','a47dba34dd6dc11e1a62f3d48c585bd7',1,'ROLE_STU',1,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_courses`
--

DROP TABLE IF EXISTS `users_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_courses` (
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `course_grade` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`course_id`),
  KEY `fk_user_has_course_course1_idx` (`course_id`),
  KEY `fk_user_has_course_user_idx` (`user_id`),
  CONSTRAINT `fk_user_has_course_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_course_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_courses`
--

LOCK TABLES `users_courses` WRITE;
/*!40000 ALTER TABLE `users_courses` DISABLE KEYS */;
INSERT INTO `users_courses` VALUES (1,1,0),(1,3,0),(1,12,0),(3,1,0),(3,3,0),(3,12,0);
/*!40000 ALTER TABLE `users_courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-05 10:17:32
