CREATE DATABASE  IF NOT EXISTS `studentplannerdb`; 
USE `studentplannerdb`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username varchar(16) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO users (username, password) VALUES ('ex','ex');
INSERT INTO users (username, password) VALUES ('hel','lo');
INSERT INTO users (username, password) VALUES ('lo','hi');

DROP TABLE IF EXISTS events;
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
  CONSTRAINT username_fk FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

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
CREATE TABLE reminder (
  eid int NOT NULL,
  `datetime` datetime DEFAULT NULL,
  rid int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (rid),
  KEY eid_idx (eid),
  CONSTRAINT eid FOREIGN KEY (eid) REFERENCES `events` (eid)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

INSERT INTO reminder (eid, datetime, rid) VALUES (13,'2023-04-01 00:00:00',3);
INSERT INTO reminder (eid, datetime, rid) VALUES (13,'2023-04-01 00:00:00',4);
INSERT INTO reminder (eid, datetime, rid) VALUES (14,'2023-06-01 02:00:00',5);
INSERT INTO reminder (eid, datetime, rid) VALUES (14,'2023-06-01 02:00:00',6);
INSERT INTO reminder (eid, datetime, rid) VALUES (15,'2023-08-01 00:00:00',7);

