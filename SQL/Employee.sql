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

CREATE TABLE `leavemanagement`.role ( 
`id` INTEGER NOT NULL AUTO_INCREMENT,
`title` VARCHAR(20) NOT NULL,
`annualleave` INTEGER NOT NULL,
`medicalleave` INTEGER NOT NULL,
PRIMARY KEY(id));
                    
CREATE TABLE `leavemanagement`.leavedetail (
`leaveid` integer NOT NULL,
`category` varchar(255) NULL,
`comment` varchar(255) NULL,
`contact_details` varchar(255) NULL,
`end_date` datetime NULL,
`reason` varchar(255) NULL,
`start_date` datetime NULL,
`status` varchar(255) NULL,
`staff_id` integer NULL,
PRIMARY KEY(leaveid));


INSERT INTO `leavemanagement`.`role` (`id`,`title`,`annualleave`, `medicalleave`) VALUES ('1','Manager', '24', '60');
INSERT INTO `leavemanagement`.`role` (`id`,`title`,`annualleave`, `medicalleave`) VALUES ('2','Admin', '14', '60');
INSERT INTO `leavemanagement`.`role` (`id`,`title`,`annualleave`, `medicalleave`) VALUES ('3','Staff', '16', '60');
 
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('1', 'manly007@company.com', '8','Manly Man Men', '12345678','manly007','1');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('2', 'man2@company.com', '1','Manly2', '12345678','man2','3');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('3', 'mental3@company.com', '1','Mantal Mental', '12345678','Mental3','3');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('4', 'women4@company.com', '8','Woman Womb Women', '12345678','women4','1');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('5', 'worn5@company.com', '4','Worn Woe Wot', '12345678','worn5','3');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('6', 'wilhemina6@company.com', '4','Wilhemina Willyou', '12345678','wilhemina6','3');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('7', 'sysedmin@company.com', '8','Edison Da Edmin', '12345678','edmin','2');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `name`, `password`, `userid`, `role_id`) VALUES ('8', 'ceo@company.com','Big Boss', '12345678','SeeEeeOoo','2');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('9', 'etan@company.com', '8','Esther Tan', '12345678','etan','1');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('10', 'yk@company.com', '9','Tan Yuen Kwan', '12345678','yuenkwan','3');
INSERT INTO `leavemanagement`.`employee` (`id`,`email`, `managerid`, `name`, `password`, `userid`, `role_id`) VALUES ('11', 'tk@company.com', '9','Yuen Tan Kwan', '12345678','tankwan','3');

INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`, `contact_detail`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('1','Annual Leave','some info1','2019-06-19','Holiday','2019-06-17','Applied/Updated','2');
INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`,`contact_detail`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('2','Annual Leave','whatever','2019-06-28','MYOB','2019-06-24','Applied/Updated','7');
INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`,`contact_detail`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('3','Annual Leave','google it','2019-05-03','relax lah','2019-05-02','Approved','1');
INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`, `comment`, `contact_detail`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('4','Annual Leave','your attitude','whatever','2019-05-09','whatever','2019-05-06','Rejected','3');
INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`, `contact_detail`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('5','Medical Leave','mobile','2019-06-16','A fever you can\'t sweat out','2019-06-13','Approved','3');
INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('6','Annual Leave','2019-06-07','Break Time','2019-06-05','Applied/Updated','6');
INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('7','Annual Leave','2019-06-04','none','2019-06-03','Cancel','5');
INSERT INTO `leavemanagement`.`leavedetail` (`id`,`category`, `end_date`, `reason`, `start_date`, `status`, `staff_id`) VALUES ('8','Annual Leave','2019-06-014','nil','2019-06-10','Deleted','7');
