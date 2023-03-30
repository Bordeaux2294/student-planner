CREATE DATABASE  IF NOT EXISTS `studentplannerdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `studentplannerdb`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: studentplannerdb
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `events`
--

DROP TABLE IF EXISTS events;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  eid int NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  ename varchar(45) DEFAULT NULL,
  sdatetime datetime DEFAULT NULL,
  edatetime datetime DEFAULT NULL,
  `status` enum('Upcoming','Ongoing','Past') NOT NULL DEFAULT 'Upcoming',
  reminder enum('Set','Not Set') NOT NULL DEFAULT 'Not Set',
  PRIMARY KEY (eid),
  KEY username_idx (username),
  CONSTRAINT username FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (1,'lo','math','2024-01-01 00:00:00','2025-01-01 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (2,'lo','sport','2023-04-01 09:00:00','2023-04-01 17:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (3,'lo','Studying','2023-03-31 12:00:00','2023-03-31 18:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (4,'lo','Studying','2023-03-31 12:00:00','2023-03-31 18:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (5,'lo','chill','2023-04-16 04:00:00','2023-04-17 05:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (6,'lo','meetup','2023-05-04 05:00:00','2023-05-04 10:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (7,'lo','party','2023-04-03 00:00:00','2023-04-05 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (8,'lo','party','2023-04-03 00:00:00','2023-04-05 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (9,'lo','k','2024-01-01 00:00:00','2025-01-01 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (10,'lo','k','2024-01-01 00:00:00','2025-01-01 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (11,'lo','aksl','2025-01-01 00:00:00','2026-01-01 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (12,'lo','afave','2024-01-01 00:00:00','2025-01-01 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (13,'lo','hjhk','2024-01-01 00:00:00','2025-01-01 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (14,'lo','hike','2023-05-01 00:00:00','2023-06-01 00:00:00','Upcoming','Not Set');
INSERT INTO events (eid, username, ename, sdatetime, edatetime, status, reminder) VALUES (15,'lo','beach','2023-04-01 00:00:00','2023-04-01 00:00:00','Upcoming','Set');

--
-- Table structure for table `reminder`
--

DROP TABLE IF EXISTS reminder;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE reminder (
  eid int NOT NULL,
  `datetime` datetime DEFAULT NULL,
  rid int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (rid),
  KEY eid_idx (eid),
  CONSTRAINT eid FOREIGN KEY (eid) REFERENCES `events` (eid)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reminder`
--

INSERT INTO reminder (eid, datetime, rid) VALUES (13,'2023-04-01 00:00:00',3);
INSERT INTO reminder (eid, datetime, rid) VALUES (13,'2023-04-01 00:00:00',4);
INSERT INTO reminder (eid, datetime, rid) VALUES (14,'2023-06-01 02:00:00',5);
INSERT INTO reminder (eid, datetime, rid) VALUES (14,'2023-06-01 02:00:00',6);
INSERT INTO reminder (eid, datetime, rid) VALUES (15,'2023-08-01 00:00:00',7);

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS users;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE users (
  username varchar(16) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

INSERT INTO users (username, password) VALUES ('Dee','la');
INSERT INTO users (username, password) VALUES ('ex','ex');
INSERT INTO users (username, password) VALUES ('hel','lo');
INSERT INTO users (username, password) VALUES ('lo','hi');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
