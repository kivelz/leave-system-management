CREATE TABLE `leavemanagement`.`employee` (
`id` INT NOT NULL AUTO_INCREAMENT, 
`name` VARCHAR(100) NOT NULL,
`email` VARCHAR(50) NOT NULL,
`password` VARCHAR(50) NOT NULL,
`managerid` INT NOT NULL,
PRIMARY KEY(`id`));

CREATE TABLE `leavemanagement`.`role` (
`id` INT NOT NULL AUTO_INCREAMENT,
`title` VARCHAR(10) NOT NULL,
`annualleave` INT NOT NULL,
`medicalleave` INT NOT NULL,
PRIMARY KEY(`id`));
`