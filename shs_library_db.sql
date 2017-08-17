-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 17, 2017 at 04:40 PM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shs_library_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `privilege`
--

CREATE TABLE `privilege` (
  `PrivilegeId` int(11) NOT NULL,
  `Privilege` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `privilege`
--

INSERT INTO `privilege` (`PrivilegeId`, `Privilege`) VALUES
(1, 'User'),
(2, 'Library Manager'),
(3, 'Library Staff'),
(4, 'Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `publication`
--

CREATE TABLE `publication` (
  `PublicationId` int(11) NOT NULL,
  `Publication` varchar(140) NOT NULL,
  `Author` varchar(100) DEFAULT NULL,
  `Publisher` varchar(100) DEFAULT NULL,
  `PublicationTypeId` int(11) NOT NULL,
  `StatusId` int(11) NOT NULL DEFAULT '1',
  `Location` varchar(45) NOT NULL,
  `Year` int(11) NOT NULL,
  `BorrowedUntil` datetime DEFAULT NULL,
  `ReservedUntil` datetime DEFAULT NULL,
  `ImageLocation` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`PublicationId`, `Publication`, `Author`, `Publisher`, `PublicationTypeId`, `StatusId`, `Location`, `Year`, `BorrowedUntil`, `ReservedUntil`, `ImageLocation`) VALUES
(1, 'ACM transactions on internet technology', NULL, 'Association for Computing Machinery', 2, 1, 'TK5105.875.I57', 2001, NULL, NULL, NULL),
(2, 'An Investigation on a multi-fear recognition model using facial features and survival horror games', 'Bagus, Brandaly', 'Chichester', 3, 1, 'CDTG006579', 2015, NULL, NULL, NULL),
(3, 'Angels & Demons', 'Brown, Dan', 'Picador', 1, 3, '813.54', 2000, NULL, NULL, NULL),
(4, 'Evaluation of the suitability of grade B low carbon steel to low pH fluids', 'Bagus, Brandaly', 'Chichester', 3, 1, 'TU07669', 1997, NULL, NULL, NULL),
(5, 'Forbes', NULL, 'Chichester', 2, 1, 'Periodicals Section', 1918, NULL, NULL, NULL),
(6, 'GSM-controlled home security alarm system using IR/LASER based sensors and auto tracking IP camera', 'Chua, Kenneth', 'Chichester', 3, 1, 'TU21505', 2016, NULL, NULL, NULL),
(8, 'Harry Potter and the Chamber of Secrets', 'Rowling, J.K.', 'Readers Digest Association', 1, 1, '823.914', 1999, NULL, NULL, NULL),
(9, 'Harry Potter and the Goblet of Fire', 'Rowling, J.K.', 'Readers Digest Association', 1, 1, '823.914', 2000, NULL, NULL, NULL),
(10, 'Internet-based control mobile robot for methane gas detection and neutralization', 'Mariquit, Eden', 'Chichester', 3, 1, 'TU18755', 2014, NULL, NULL, NULL),
(11, 'Journal of information studies\r\n', NULL, 'Summit Media', 2, 1, 'HM258 .T643 ', 2005, NULL, NULL, NULL),
(12, 'PC Magazine', NULL, 'Orion Publishing Group', 2, 1, 'Periodicals Section', 1986, NULL, NULL, NULL),
(13, 'Readers Digest', NULL, 'Pocket books', 2, 1, 'Periodicals Section', 1922, NULL, NULL, NULL),
(14, 'Rounding the Mark\r\n', 'Mark, Joseph', 'PC Communications Corp.', 1, 1, '853.914', 2006, NULL, NULL, NULL),
(15, 'Study of the catalytic activities of activated carbon-supported catalysts and manganese oxide catalysts for complete oxidation of xylene', 'Bagus, Brandaly', 'Chichester', 3, 1, 'CDTG004654', 2009, NULL, NULL, NULL),
(16, 'Tara Road', 'Binchy, Maeve', 'Harcourt', 1, 1, '823.914', 1998, NULL, NULL, NULL),
(17, 'The Da Vinci code', 'Brown, Dan', 'Picador', 1, 1, '813.54', 2003, NULL, NULL, NULL),
(18, 'The Lost Symbol', 'Brown, Dan', 'Picador', 1, 1, '813.54', 2009, NULL, NULL, NULL),
(19, 'The Name of the Rose', 'Ball, Lucio', 'Scholastic', 1, 1, '853.914', 1986, NULL, NULL, NULL),
(20, 'Top Gear Philippines', NULL, 'Scholastic', 2, 1, 'Periodicals Section', 2004, NULL, NULL, NULL),
(21, 'Wired', NULL, 'The University of Tokyo', 2, 1, 'Periodicals Section', 1993, NULL, NULL, NULL),
(22, 'Wired marketing : energizing business for e-commerce', 'Jones, John', 'Readers Digest Association', 1, 1, 'HF5415.1265 .H37 2001', 2001, NULL, NULL, NULL),
(23, 'Women in agriculture : their nutritional knowledge & roles in addressing hidden hunger', 'Camilleri, Andrea', 'Chichester', 3, 1, 'CDTG005471', 2013, NULL, NULL, NULL),
(25, 'adasdasd', 'Chua, Kenneth', 'Scholastic', 1, 1, '', 1917, NULL, NULL, NULL),
(26, 'Harry Potter and the Prisoner of Azkaban', 'Rowling, J.K.', 'Readers Digest Association', 1, 1, '8080', 1997, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `publicationtags`
--

CREATE TABLE `publicationtags` (
  `PublicationId` int(11) NOT NULL,
  `TagId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicationtags`
--

INSERT INTO `publicationtags` (`PublicationId`, `TagId`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 3),
(5, 4),
(6, 5),
(8, 6),
(9, 6),
(10, 7),
(11, 7),
(12, 7),
(13, 7),
(14, 8),
(15, 9),
(16, 10),
(17, 13),
(18, 11),
(19, 14),
(20, 15),
(21, 15),
(22, 15),
(23, 15),
(26, 6),
(26, 7);

-- --------------------------------------------------------

--
-- Table structure for table `publicationtransaction`
--

CREATE TABLE `publicationtransaction` (
  `PublicationTransactionId` int(11) NOT NULL,
  `PublicationTransactionDateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserId` int(11) NOT NULL,
  `PublicationId` int(11) NOT NULL,
  `DateBorrowed` datetime DEFAULT NULL,
  `DateReturned` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicationtransaction`
--

INSERT INTO `publicationtransaction` (`PublicationTransactionId`, `PublicationTransactionDateTime`, `UserId`, `PublicationId`, `DateBorrowed`, `DateReturned`) VALUES
(2, '2017-08-15 00:23:05', 5, 16, '2017-08-15 00:41:38', NULL),
(3, '2017-08-17 16:14:07', 1, 3, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `publicationtype`
--

CREATE TABLE `publicationtype` (
  `PublicationTypeId` int(11) NOT NULL,
  `PublicationType` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicationtype`
--

INSERT INTO `publicationtype` (`PublicationTypeId`, `PublicationType`) VALUES
(1, 'Book'),
(2, 'Magazine'),
(3, 'Thesis');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `ReviewId` int(11) NOT NULL,
  `Review` varchar(150) NOT NULL,
  `UserId` int(11) NOT NULL,
  `PublicationId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`ReviewId`, `Review`, `UserId`, `PublicationId`) VALUES
(1, 'This is good book', 1, 1),
(2, 'good one', 2, 3),
(3, 'this is bad', 1, 3),
(4, 'this is bad', 1, 3),
(5, 'are you kidding', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `RoomId` int(11) NOT NULL,
  `Capacity` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`RoomId`, `Capacity`, `Status`) VALUES
(1, 4, 1),
(2, 4, 1),
(3, 6, 1),
(4, 6, 1),
(5, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `roomslot`
--

CREATE TABLE `roomslot` (
  `RoomSlotId` int(11) NOT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roomslot`
--

INSERT INTO `roomslot` (`RoomSlotId`, `StartTime`, `EndTime`) VALUES
(0, '07:00:00', '07:29:59'),
(1, '07:30:00', '07:59:59'),
(2, '08:00:00', '08:29:59'),
(3, '08:30:00', '08:59:59'),
(4, '09:00:00', '09:29:59'),
(5, '09:30:00', '09:59:59'),
(6, '10:00:00', '10:29:59'),
(7, '10:30:00', '10:59:59'),
(8, '11:00:00', '11:29:59'),
(9, '11:30:00', '11:59:59'),
(10, '12:00:00', '12:29:59'),
(11, '12:30:00', '12:59:59'),
(12, '13:00:00', '13:29:59'),
(13, '13:30:00', '13:59:59'),
(14, '14:00:00', '14:29:59'),
(15, '14:30:00', '14:59:59'),
(16, '15:00:00', '15:29:59'),
(17, '15:30:00', '15:59:59'),
(18, '16:00:00', '16:29:59'),
(19, '16:30:00', '16:59:59'),
(20, '17:00:00', '17:29:59'),
(21, '17:30:00', '17:59:59'),
(22, '18:00:00', '18:29:59'),
(23, '18:30:00', '18:59:59'),
(24, '19:00:00', '19:29:59'),
(25, '19:30:00', '19:59:59');

-- --------------------------------------------------------

--
-- Table structure for table `roomtransaction`
--

CREATE TABLE `roomtransaction` (
  `RoomTransactionId` int(11) NOT NULL,
  `RoomTransactionDateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `User_UserId` int(11) NOT NULL,
  `Room_RoomId` int(11) NOT NULL,
  `RoomSlotId` int(11) NOT NULL,
  `RoomReserveDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roomtransaction`
--

INSERT INTO `roomtransaction` (`RoomTransactionId`, `RoomTransactionDateTime`, `User_UserId`, `Room_RoomId`, `RoomSlotId`, `RoomReserveDate`) VALUES
(3, '2017-08-17 17:25:06', 1, 3, 6, '2017-08-10'),
(4, '2017-08-17 17:25:06', 1, 3, 7, '2017-08-10');

-- --------------------------------------------------------

--
-- Table structure for table `securityquestion`
--

CREATE TABLE `securityquestion` (
  `SecurityQuestionId` int(11) NOT NULL,
  `SecurityQuestion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `securityquestion`
--

INSERT INTO `securityquestion` (`SecurityQuestionId`, `SecurityQuestion`) VALUES
(1, 'What is your pet?');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `StatusId` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`StatusId`, `Status`) VALUES
(1, 'Available'),
(2, 'Out'),
(3, 'Reserved');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `TagId` int(11) NOT NULL,
  `Tag` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`TagId`, `Tag`) VALUES
(1, 'Automobile'),
(2, 'Business'),
(3, 'Chemistry'),
(4, 'Communication'),
(5, 'Computer Science'),
(6, 'Fantasy'),
(7, 'Fiction'),
(8, 'General'),
(9, 'History'),
(10, 'Internet'),
(11, 'Marketing'),
(12, 'Mystery'),
(13, 'Love Stories'),
(14, 'Sociology'),
(15, 'Technology');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserId` int(11) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `MiddleInitial` varchar(2) NOT NULL,
  `Username` varchar(45) NOT NULL,
  `PasswordHash` varchar(42) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Birthday` date DEFAULT NULL,
  `IdentificationNumber` varchar(45) NOT NULL,
  `SecurityQuestionId` int(11) NOT NULL,
  `AnswerHash` varchar(42) NOT NULL,
  `Privilege_PrivilegeId` int(11) NOT NULL,
  `UserTypeId` int(11) DEFAULT NULL,
  `Created_On` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_attempts` int(11) NOT NULL DEFAULT '0',
  `IsLocked` tinyint(1) NOT NULL DEFAULT '0',
  `IsTemporary` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `FirstName`, `LastName`, `MiddleInitial`, `Username`, `PasswordHash`, `Email`, `Birthday`, `IdentificationNumber`, `SecurityQuestionId`, `AnswerHash`, `Privilege_PrivilegeId`, `UserTypeId`, `Created_On`, `login_attempts`, `IsLocked`, `IsTemporary`) VALUES
(1, 'Maynard', 'Si', 'C.', 'user', '*2470C0C06DEE42FD1618BB99005ADCA2EC9D1E19', 'maynard_si@dlsu.edu.ph', NULL, '1', 1, '*22862EE2EE01FA513A35093FAD0986CB4C460E6B', 1, 1, '2017-08-16 10:48:28', 0, 0, 0),
(2, 'Neil', 'Romblon', 'V.', 'manager', '*2470C0C06DEE42FD1618BB99005ADCA2EC9D1E19', 'neil_romblon@dlsu.edu.ph', NULL, '2', 1, '*22862EE2EE01FA513A35093FAD0986CB4C460E6B', 2, NULL, '2017-08-16 10:48:28', 0, 0, 0),
(3, 'Luis', 'Madrigal', 'Q.', 'staff', '*2470C0C06DEE42FD1618BB99005ADCA2EC9D1E19', 'luis_madrigal@dlsu.edu.ph', NULL, '3', 1, '*22862EE2EE01FA513A35093FAD0986CB4C460E6B', 3, NULL, '2017-08-16 10:48:28', 3, 1, 0),
(4, 'System', 'Administrator', 'D.', 'administrator', '*2470C0C06DEE42FD1618BB99005ADCA2EC9D1E19', 'admin@dlsu.edu.ph', NULL, '4', 1, '*22862EE2EE01FA513A35093FAD0986CB4C460E6B', 4, NULL, '2017-08-16 10:48:28', 0, 0, 0),
(5, 'John', 'Jones', 'I', 'user2', '*2470C0C06DEE42FD1618BB99005ADCA2EC9D1E19', 'user@mail.com', NULL, '12312323', 1, '*22862EE2EE01FA513A35093FAD0986CB4C460E6B', 1, 2, '2017-08-16 10:48:28', 5, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `usertype`
--

CREATE TABLE `usertype` (
  `UserTypeId` int(11) NOT NULL,
  `UserType` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`UserTypeId`, `UserType`) VALUES
(1, 'Student'),
(2, 'Faculty');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `privilege`
--
ALTER TABLE `privilege`
  ADD PRIMARY KEY (`PrivilegeId`);

--
-- Indexes for table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`PublicationId`),
  ADD KEY `fk_Publication_PublicationType1_idx` (`PublicationTypeId`),
  ADD KEY `fk_Publication_Status1_idx` (`StatusId`);

--
-- Indexes for table `publicationtags`
--
ALTER TABLE `publicationtags`
  ADD PRIMARY KEY (`PublicationId`,`TagId`),
  ADD KEY `fk_Tags_has_Publication_Publication1_idx` (`PublicationId`),
  ADD KEY `fk_Tags_has_Publication_Tags1_idx` (`TagId`);

--
-- Indexes for table `publicationtransaction`
--
ALTER TABLE `publicationtransaction`
  ADD PRIMARY KEY (`PublicationTransactionId`),
  ADD KEY `fk_PublicationTransaction_User1_idx` (`UserId`),
  ADD KEY `fk_PublicationTransaction_Publication1_idx` (`PublicationId`);

--
-- Indexes for table `publicationtype`
--
ALTER TABLE `publicationtype`
  ADD PRIMARY KEY (`PublicationTypeId`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`ReviewId`),
  ADD KEY `UserId` (`UserId`),
  ADD KEY `PublicationId` (`PublicationId`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`RoomId`),
  ADD KEY `fk_Room_Status1_idx` (`Status`);

--
-- Indexes for table `roomslot`
--
ALTER TABLE `roomslot`
  ADD PRIMARY KEY (`RoomSlotId`);

--
-- Indexes for table `roomtransaction`
--
ALTER TABLE `roomtransaction`
  ADD PRIMARY KEY (`RoomTransactionId`),
  ADD KEY `fk_RoomTransaction_User1_idx` (`User_UserId`),
  ADD KEY `fk_RoomTransaction_Room1_idx` (`Room_RoomId`),
  ADD KEY `fk_RoomTransaction_RoomSlot1_idx` (`RoomSlotId`);

--
-- Indexes for table `securityquestion`
--
ALTER TABLE `securityquestion`
  ADD PRIMARY KEY (`SecurityQuestionId`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`StatusId`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`TagId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD KEY `fk_User_SecurityQuestion1_idx` (`SecurityQuestionId`),
  ADD KEY `fk_User_Privilege1_idx` (`Privilege_PrivilegeId`),
  ADD KEY `UserType` (`UserTypeId`);

--
-- Indexes for table `usertype`
--
ALTER TABLE `usertype`
  ADD PRIMARY KEY (`UserTypeId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `privilege`
--
ALTER TABLE `privilege`
  MODIFY `PrivilegeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `publication`
--
ALTER TABLE `publication`
  MODIFY `PublicationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `publicationtransaction`
--
ALTER TABLE `publicationtransaction`
  MODIFY `PublicationTransactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `publicationtype`
--
ALTER TABLE `publicationtype`
  MODIFY `PublicationTypeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `ReviewId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `RoomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `roomtransaction`
--
ALTER TABLE `roomtransaction`
  MODIFY `RoomTransactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `securityquestion`
--
ALTER TABLE `securityquestion`
  MODIFY `SecurityQuestionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `StatusId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tags`
--
ALTER TABLE `tags`
  MODIFY `TagId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `usertype`
--
ALTER TABLE `usertype`
  MODIFY `UserTypeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `fk_Publication_PublicationType1` FOREIGN KEY (`PublicationTypeId`) REFERENCES `publicationtype` (`PublicationTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Publication_Status1` FOREIGN KEY (`StatusId`) REFERENCES `status` (`StatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `publicationtags`
--
ALTER TABLE `publicationtags`
  ADD CONSTRAINT `fk_Tags_has_Publication_Publication1` FOREIGN KEY (`PublicationId`) REFERENCES `publication` (`PublicationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Tags_has_Publication_Tags1` FOREIGN KEY (`TagId`) REFERENCES `tags` (`TagId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `publicationtransaction`
--
ALTER TABLE `publicationtransaction`
  ADD CONSTRAINT `fk_PublicationTransaction_Publication1` FOREIGN KEY (`PublicationId`) REFERENCES `publication` (`PublicationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_PublicationTransaction_User1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `fk_publicationId_review` FOREIGN KEY (`PublicationId`) REFERENCES `publication` (`PublicationId`),
  ADD CONSTRAINT `fk_userId_review` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `fk_Room_Status1` FOREIGN KEY (`Status`) REFERENCES `status` (`StatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `roomtransaction`
--
ALTER TABLE `roomtransaction`
  ADD CONSTRAINT `fk_RoomTransaction_Room1` FOREIGN KEY (`Room_RoomId`) REFERENCES `room` (`RoomId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_RoomTransaction_RoomSlot1` FOREIGN KEY (`RoomSlotId`) REFERENCES `roomslot` (`RoomSlotId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_RoomTransaction_User1` FOREIGN KEY (`User_UserId`) REFERENCES `user` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_UserType_UserTypeId` FOREIGN KEY (`UserTypeId`) REFERENCES `usertype` (`UserTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_User_Privilege1` FOREIGN KEY (`Privilege_PrivilegeId`) REFERENCES `privilege` (`PrivilegeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_User_SecurityQuestion1` FOREIGN KEY (`SecurityQuestionId`) REFERENCES `securityquestion` (`SecurityQuestionId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `delete_temporary` ON SCHEDULE EVERY 1 DAY STARTS '2017-08-17 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM `user` WHERE IsTemporary = true AND DATEDIFF(CURRENT_DATE,Created_On) > 0$$

CREATE DEFINER=`root`@`localhost` EVENT `logattempt_reset` ON SCHEDULE EVERY 15 MINUTE STARTS '2017-08-17 15:50:56' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE `user` 
SET login_attempts = 0, IsLocked = false
WHERE login_attempts >=5 AND IsLocked = true$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
