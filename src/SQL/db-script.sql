CREATE DATABASE sprintdb_JamesW;
USE sprintdb_JamesW;

CREATE TABLE test
(
	ID int primary key auto_increment,
    test varchar (100)
);

INSERT INTO test
( test )
Values
( 'Test successful');

CREATE TABLE `JobRoles` (
  `JobRoleID` int NOT NULL AUTO_INCREMENT,
  `JobRole` varchar(100) DEFAULT NULL,
  `JobRoleSpec` varchar(1000) DEFAULT NULL,
  `JobRoleSpecLink` varchar(255) DEFAULT NULL,
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `sprintdb_JamesW`.`JobRoles`
(`JobRoleID`,
`JobRole`,
`JobRoleSpec`,
`JobRoleSpecLink`)
VALUES
(1,
"Software Engineer",
"Software Engineer does some coding",
"https://en.wikipedia.org/wiki/Software_engineering");
