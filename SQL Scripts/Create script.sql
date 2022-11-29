CREATE DATABASE `sprintdb_JamesW` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `Bands` (
  `BandLevelID` int NOT NULL AUTO_INCREMENT,
  `BandLevel` int NOT NULL,
  `BandName` varchar(45) NOT NULL,
  PRIMARY KEY (`BandLevelID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Capabilities` (
  `CapabilityID` int NOT NULL AUTO_INCREMENT,
  `Capability` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CapabilityID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `JobFamily` (
  `FamilyID` int NOT NULL AUTO_INCREMENT,
  `FamilyName` varchar(45) DEFAULT NULL,
  `capabilityID` int NOT NULL,
  PRIMARY KEY (`FamilyID`),
  KEY `capabilityID_fk_idx` (`capabilityID`),
  CONSTRAINT `capabilityID_fk` FOREIGN KEY (`capabilityID`) REFERENCES `Capabilities` (`CapabilityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `JobRoles` (
  `JobRoleID` int NOT NULL AUTO_INCREMENT,
  `JobRole` varchar(100) DEFAULT NULL,
  `JobFamily` varchar(45) DEFAULT NULL,
  `JobRoleSpec` varchar(1000) DEFAULT NULL,
  `JobRoleSpecLink` varchar(45) DEFAULT NULL,
  `JobBandLevelID` int DEFAULT NULL,
  `JobCapabilityID` int DEFAULT NULL,
  `JobFamilyID` int DEFAULT NULL,
  PRIMARY KEY (`JobRoleID`),
  KEY `JobBandlevelID_fk_idx` (`JobBandLevelID`),
  KEY `JobCapabilityID_fk_idx` (`JobCapabilityID`),
  KEY `JobFamilyID_fk_idx` (`JobFamilyID`),
  CONSTRAINT `JobBandlevelID_fk` FOREIGN KEY (`JobBandLevelID`) REFERENCES `Bands` (`BandLevelID`),
  CONSTRAINT `JobCapabilityID_fk` FOREIGN KEY (`JobCapabilityID`) REFERENCES `Capabilities` (`CapabilityID`),
  CONSTRAINT `JobFamilyID_fk` FOREIGN KEY (`JobFamilyID`) REFERENCES `JobFamily` (`FamilyID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `JobTraining` (
  `TrainingID` tinyint NOT NULL,
  `JobBandID` int DEFAULT NULL,
   `TrainingLevel` varchar(45) DEFAULT NULL,
  `CourseName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TrainingID`),
  KEY `JobBandID_fk_idx` (`JobBandID`),
  CONSTRAINT `JobBandID_fk` FOREIGN KEY (`JobBandID`) REFERENCES `JobFamily` (`FamilyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `EmployeeFact` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `JobRoleID` int DEFAULT NULL,
  `HireDate` date DEFAULT NULL,
  `VacationHours` int DEFAULT NULL,
  `SickLeaveHours` int DEFAULT NULL,
  `CurrentFlag` int DEFAULT NULL,
  `rowguid` text,
  `Salary (GBP)` int DEFAULT NULL,
  `Location` text,
PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Employees` (
  `EmployeeID` int NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `NationalIDNumber` varchar(15) DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  `MaritalStatus` char(1) DEFAULT NULL,
  `Gender` char(1) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Competencies` (
  `CompetencyID` int NOT NULL AUTO_INCREMENT,
  `CompetencyName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CompetencyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `CompetencyBand` (
  `CompetencyBandID` int NOT NULL AUTO_INCREMENT,
  `bandLevelID` int DEFAULT NULL,
  `competencyID` int DEFAULT NULL,
  `SubHeading` varchar(150) DEFAULT NULL,
  `Information` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`CompetencyBandID`),
  KEY `fk_CompetencyBand_Competencies1_idx` (`competencyID`),
  KEY `fk_CompetencyBand_Bands1_idx` (`bandLevelID`),
  CONSTRAINT `fk_CompetencyBand_Bands1` FOREIGN KEY (`bandLevelID`) REFERENCES `Bands` (`BandLevelID`),
  CONSTRAINT `fk_CompetencyBand_Competencies1` FOREIGN KEY (`competencyID`) REFERENCES `Competencies` (`CompetencyID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `test` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `test` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
