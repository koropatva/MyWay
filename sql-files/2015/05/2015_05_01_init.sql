CREATE DATABASE IF NOT EXISTS `phone_dictionary` DEFAULT CHARACTER SET utf8;

USE `phone_dictionary`;

#Table structure for table `client`

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#Data for the table `client`

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clientId` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#Table structure for table `phone`

DROP TABLE IF EXISTS `phone`;

CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clientId` int(11) NULL DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `phoneType` enum('HOME','WORK') DEFAULT NULL,
  `creationTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#Data for the table `phones`

#byrkovskiy@gmail.com

#koropatvaua@gmail.com
