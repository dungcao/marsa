-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 02, 2014 at 01:01 PM
-- Server version: 5.5.37
-- PHP Version: 5.3.10-1ubuntu3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `marketplace`
--

-- --------------------------------------------------------

--
-- Table structure for table `log_pub_timestamp`
--

CREATE TABLE IF NOT EXISTS `log_pub_timestamp` (
  `stream_uuid` varchar(50) NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  `messageid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`stream_uuid`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `log_received_data`
--

CREATE TABLE IF NOT EXISTS `log_received_data` (
  `user` varchar(25) NOT NULL,
  `stream_uuid` varchar(100) NOT NULL,
  `date_time` datetime NOT NULL,
  `data_size` bigint(20) DEFAULT '0',
  `message_count` int(11) DEFAULT '0',
  PRIMARY KEY (`user`,`stream_uuid`,`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `log_subscription`
--

CREATE TABLE IF NOT EXISTS `log_subscription` (
  `user` varchar(25) NOT NULL,
  `stream_uuid` varchar(100) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user`,`stream_uuid`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `log_sub_timestamp`
--

CREATE TABLE IF NOT EXISTS `log_sub_timestamp` (
  `username` varchar(25) NOT NULL,
  `stream_uuid` varchar(50) NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  `messageId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`,`stream_uuid`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Categories`
--

CREATE TABLE IF NOT EXISTS `tbl_Categories` (
  `ID` varchar(20) NOT NULL,
  `Term` varchar(50) NOT NULL,
  `Scheme` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ContractModel`
--

CREATE TABLE IF NOT EXISTS `tbl_ContractModel` (
  `ID` varchar(25) NOT NULL,
  `Name` varchar(150) DEFAULT NULL,
  `SourceURL` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_CostAPIHandle`
--

CREATE TABLE IF NOT EXISTS `tbl_CostAPIHandle` (
  `sid` varchar(25) NOT NULL,
  `vid` int(11) NOT NULL,
  `price` float NOT NULL,
  `objNum` int(11) NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`,`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_CostDataSize`
--

CREATE TABLE IF NOT EXISTS `tbl_CostDataSize` (
  `sid` varchar(25) NOT NULL,
  `vid` int(11) NOT NULL,
  `price` float NOT NULL,
  `size` smallint(6) NOT NULL,
  `unit` varchar(10) NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`,`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_CostDataUnit`
--

CREATE TABLE IF NOT EXISTS `tbl_CostDataUnit` (
  `sid` varchar(25) NOT NULL,
  `vid` int(11) NOT NULL,
  `price` float NOT NULL,
  `streamid` varchar(30) NOT NULL,
  `pplan` varchar(10) NOT NULL,
  `value` int(11) NOT NULL,
  `unit` varchar(10) NOT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`,`vid`,`streamid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_CostPeriod`
--

CREATE TABLE IF NOT EXISTS `tbl_CostPeriod` (
  `sid` varchar(25) NOT NULL,
  `vid` int(11) NOT NULL,
  `exp` float NOT NULL,
  `fvalue` int(11) NOT NULL,
  `tvalue` int(11) NOT NULL,
  `repeatBy` varchar(10) NOT NULL,
  PRIMARY KEY (`sid`,`vid`,`fvalue`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_CostSubscription`
--

CREATE TABLE IF NOT EXISTS `tbl_CostSubscription` (
  `sid` varchar(25) NOT NULL,
  `vid` int(11) NOT NULL,
  `price` float NOT NULL,
  `duration` int(11) NOT NULL,
  `unit` varchar(10) NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`,`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_CostTimeplan`
--

CREATE TABLE IF NOT EXISTS `tbl_CostTimeplan` (
  `sid` varchar(25) NOT NULL,
  `vid` int(11) NOT NULL,
  `price` float NOT NULL,
  `duration` smallint(6) NOT NULL,
  `unit` varchar(10) NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sid`,`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_DataType`
--

CREATE TABLE IF NOT EXISTS `tbl_DataType` (
  `ID` varchar(20) NOT NULL,
  `Type` varchar(25) NOT NULL,
  `SourceURL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Devices`
--

CREATE TABLE IF NOT EXISTS `tbl_Devices` (
  `Id` varchar(25) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `SourceUrl` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_QoS`
--

CREATE TABLE IF NOT EXISTS `tbl_QoS` (
  `Id` varchar(20) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `SourceURL` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Services`
--

CREATE TABLE IF NOT EXISTS `tbl_Services` (
  `UUID` varchar(20) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Provider` varchar(100) NOT NULL,
  `Description` text,
  `CostURL` varchar(100) DEFAULT NULL,
  `Databus` varchar(100) DEFAULT NULL,
  `CategoryListIds` varchar(100) DEFAULT NULL,
  `ContractListIds` varchar(100) DEFAULT NULL,
  `QoSListIds` varchar(100) DEFAULT NULL,
  `owner` varchar(25) DEFAULT NULL,
  `registered_date` datetime DEFAULT NULL,
  `subscriber_number` int(11) DEFAULT '0',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Streams`
--

CREATE TABLE IF NOT EXISTS `tbl_Streams` (
  `StreamUUID` varchar(30) NOT NULL,
  `ServiceUUID` varchar(20) NOT NULL,
  `Description` tinytext,
  `CostURL` varchar(100) DEFAULT NULL,
  `DeviceID` varchar(20) DEFAULT NULL,
  `DataTypeID` varchar(20) DEFAULT NULL,
  `Databus` varchar(100) NOT NULL,
  `TimeSeries` int(11) DEFAULT NULL,
  `Uncertainly` int(11) DEFAULT NULL,
  `DataOrigin` varchar(100) DEFAULT NULL,
  `DataRate` int(11) DEFAULT NULL,
  `Latency` int(11) DEFAULT NULL,
  `CategoryID` varchar(20) DEFAULT NULL,
  `ContractID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`StreamUUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_subscription`
--

CREATE TABLE IF NOT EXISTS `tbl_subscription` (
  `serviceUUID` varchar(20) NOT NULL,
  `streamUUID` varchar(30) NOT NULL,
  `paymentType` varchar(10) DEFAULT NULL,
  `Costmodel` varchar(100) DEFAULT NULL,
  `Contractmodel` varchar(100) DEFAULT NULL,
  `subscriber` varchar(25) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL COMMENT 'Only use for time plan',
  `enddate` datetime DEFAULT NULL COMMENT 'Only use for time plan',
  `active` bit(1) DEFAULT b'1',
  `subscriptiontime` datetime NOT NULL,
  `unsubscriptiontime` datetime DEFAULT NULL,
  PRIMARY KEY (`serviceUUID`,`streamUUID`,`subscriptiontime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE IF NOT EXISTS `tbl_users` (
  `user` varchar(25) NOT NULL,
  `pass` varchar(35) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` text,
  `bill_address` text,
  `cardNumber` varchar(20) DEFAULT NULL,
  `cardType` varchar(45) DEFAULT NULL,
  `cardName` varchar(45) DEFAULT NULL,
  `cardExpire` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `role` char(1) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `util_logged_users`
--

CREATE TABLE IF NOT EXISTS `util_logged_users` (
  `user` varchar(25) NOT NULL,
  `session_id` varchar(35) DEFAULT NULL,
  `logged_time` datetime NOT NULL,
  `appname` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
