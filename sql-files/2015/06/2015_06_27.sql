CREATE DATABASE IF NOT EXISTS `myway`DEFAULT CHARACTER SET utf8;

USE `myway`;

CREATE TABLE `criteria` (   `id` int(11) NOT NULL AUTO_INCREMENT,   `name` varchar(255) DEFAULT NULL,   `description` varchar(255) DEFAULT NULL,   `type` ENUM('PLACES', 'MONEY','DURATION_TIME','START_POINT'), 'modifed_time' TIMESTAMP DEFAULT CURRENT_TIME, 'creation_time' TIMESTAMP DEFAULT CURRENT_TIME,    PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `criteria_block` (   `id` int(11) NOT NULL AUTO_INCREMENT,   `criteriaId` int(11),   `blockId` int(11), 'modifed_time' TIMESTAMP DEFAULT CURRENT_TIME, 'creation_time' TIMESTAMP DEFAULT CURRENT_TIME,    PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `block` (   `id` int(11) NOT NULL AUTO_INCREMENT,   `label` varchar(255) DEFAULT NULL,   `shortDescription` varchar(255) DEFAULT NULL,  `pictureUrl` varchar(255) DEFAULT NULL,   `destinationId` int(11) DEFAULT NULL, 'modifed_time' TIMESTAMP DEFAULT CURRENT_TIME, 'creation_time' TIMESTAMP DEFAULT CURRENT_TIME,    PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `destination` (   `id` int(11) NOT NULL AUTO_INCREMENT,   `label` varchar(255) DEFAULT NULL,   `description` text DEFAULT NULL,  `pictureUrl` varchar(255) DEFAULT NULL,   `locationX` varchar(255) DEFAULT NULL, `locationY` varchar(255) DEFAULT NULL, 'modifed_time' TIMESTAMP DEFAULT CURRENT_TIME, 'creation_time' TIMESTAMP DEFAULT CURRENT_TIME,    PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
