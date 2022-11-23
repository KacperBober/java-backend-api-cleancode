CREATE DATABASE sprintdb_JamesW;

CREATE TABLE test
(
	ID int primary key auto_increment,
    test varchar (100)
);

INSERT INTO test
( test )
Values
( 'Test successful');

CREATE TABLE JobRoles
(
	ID int primary key auto_increment,
    Name varchar (100)
);
