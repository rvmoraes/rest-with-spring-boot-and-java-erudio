CREATE TABLE IF NOT EXISTS `person` (
	`id` bigint(20) not null AUTO_INCREMENT,
  `first_name` varchar(80) not null,
  `last_name` varchar(80) not null,
  `address` varchar(100) not null,
  `gender` varchar(6) not null,
  PRIMARY KEY(`id`)
);