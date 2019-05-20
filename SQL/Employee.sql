DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS ROLE;

CREATE TABLE EMPLOYEE ( ID INTEGER NOT NULL AUTO_INCREMENT,
						NAME VARCHAR(100) NOT NULL,
						EMAIL VARCHAR(100) NOT NULL,
						PASSWORD VARCHAR(100) NOT NULL,
						USERID VARCHAR(100) NOT NULL,
						MANAGERID INTEGER NOT NULL,
                        ROLE_ID INTEGER NOT NULL,
						PRIMARY KEY(ID));
                        
CREATE TABLE ROLE ( ID INTEGER NOT NULL AUTO_INCREMENT,
					TITLE VARCHAR(20) NOT NULL,
                    ANNUALLEAVE INTEGER NOT NULL,
                    MEDICALLEAVE INTEGER NOT NULL,
                    PRIMARY KEY(ID));

INSERT INTO `leavemanagement`.`role` (`title`,`annualleave`, `medicalleave`) VALUES ('Manager', '24', '60');
INSERT INTO `leavemanagement`.`role` (`title`,`annualleave`, `medicalleave`) VALUES ('Admin', '14', '60');
INSERT INTO `leavemanagement`.`role` (`title`,`annualleave`, `medicalleave`) VALUES ('Staff', '16', '60');


INSERT INTO `leavemanagement`.`employee` (`name`,`email`, `password`, `userid`,`managerid`, `ROLE_ID`) VALUES ('Yuan kwan', 'yck@u.nus.edu','gay', 'HDJSH*Y"HD', '5', '3');
INSERT INTO `leavemanagement`.`employee` (`name`,`email`, `password`, `userid`,`managerid`, `ROLE_ID`) VALUES ('Chan Yuan kwan', 'cck@u.nus.edu','testp1', 'HDJSH*Y"HD', '5', '3');
INSERT INTO `leavemanagement`.`employee` (`name`,`email`, `password`, `userid`,`managerid`, `ROLE_ID`) VALUES ('Lao Yuan kwan', 'lck@u.nus.edu','testp1', 'HDJSH*Y"HD', '5', '2');
INSERT INTO `leavemanagement`.`employee` (`name`,`email`, `password`, `userid`,`managerid`, `ROLE_ID`) VALUES ('Tab Yuan kwan', 'tck@u.nus.edu','testp1', 'HDJSH*Y"HD', '5', '3');
INSERT INTO `leavemanagement`.`employee` (`name`,`email`, `password`, `userid`,`managerid`, `ROLE_ID`) VALUES ('Esther tan', 'etan@u.nus.edu','testp13', 'HDJSH*Y"HD', '1', '1');
INSERT INTO `leavemanagement`.`employee` (`name`,`email`, `password`, `userid`,`managerid`, `ROLE_ID`) VALUES ('Tan cher wah', 'tcw@u.nus.edu','testp13', 'HDJSH*Y"HD', '6', '1');