DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS ROLE;

CREATE TABLE `leavemanagement`.employee (
`id` varchar(100) NOT NULL,
`name` varchar(100) NOT NULL,
`email` varchar(100) NOT NULL,
`userid` varchar(100) NOT null,
`managerid` varchar(100) NULL,
`role_id` integer NULL,
PRIMARY KEY(id));

CREATE TABLE ROLE ( ID INTEGER NOT NULL AUTO_INCREMENT,
					TITLE VARCHAR(20) NOT NULL,
                    ANNUALLEAVE INTEGER NOT NULL,
                    MEDICALLEAVE INTEGER NOT NULL,
                    PRIMARY KEY(ID));

INSERT INTO `leavemanagement`.`role` (`title`,`annualleave`, `medicalleave`) VALUES ('Manager', '24', '60');
INSERT INTO `leavemanagement`.`role` (`title`,`annualleave`, `medicalleave`) VALUES ('Admin', '14', '60');
INSERT INTO `leavemanagement`.`role` (`title`,`annualleave`, `medicalleave`) VALUES ('Staff', '16', '60');

INSERT INTO `leavemanagement`.`employee` (`id`, `name`,`email`,`userid`, `managerid`, `role_id`) VALUES ('bff135d5-848a-4c68-a313-7d44a34ba92b', 'Esther Tan', 'etan@u.nus.edu', 'HJK20110', 'bff135d5-848a-4c68-a313-7d44a34ba92b', 1);
INSERT INTO `leavemanagement`.`employee` (`id`, `name`,`email`,`userid`, `managerid`, `role_id`) VALUES ('bff135d5-848a-4c68-a313-7d44a34ba92c', 'Apa Yuen kwan', 'yk@u.nus.edu', 'HJK20111', 'bff135d5-848a-4c68-a313-7d44a34ba92b', 3); 
INSERT INTO `leavemanagement`.`employee` (`id`, `name`,`email`,`userid`, `managerid`, `role_id`) VALUES ('bff135d5-848a-4c68-a313-7d44a34ba92d', 'Tan Yuen kwan', 'tk@u.nus.edu', 'HJK20113', 'bff135d5-848a-4c68-a313-7d44a34ba92b', 2); 

