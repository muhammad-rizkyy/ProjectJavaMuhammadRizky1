-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 03, 2022 at 05:42 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectmuhammadrizky`
--

-- --------------------------------------------------------

--
-- Table structure for table `brandmotor`
--

CREATE TABLE `brandmotor` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brandmotor`
--

INSERT INTO `brandmotor` (`id`, `nama`) VALUES
(1, 'Yamaha'),
(2, 'Honda'),
(3, 'Suzuki'),
(5, 'Kawasaki');

-- --------------------------------------------------------

--
-- Table structure for table `jenismotor`
--

CREATE TABLE `jenismotor` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `klasifikasi` enum('ABS','NO ABS') DEFAULT NULL,
  `silinder` int(11) DEFAULT NULL,
  `diameter` double(11,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jenismotor`
--

INSERT INTO `jenismotor` (`id`, `nama`, `brand_id`, `klasifikasi`, `silinder`, `diameter`) VALUES
(1, 'Scoopy', 2, 'ABS', 125, 4.50),
(3, 'Vario', 2, 'ABS', 125, 3.40),
(5, 'MxKing', 1, 'ABS', 150, 14.00),
(10, 'Aerox', 1, 'ABS', 155, 15.00),
(11, 'NMax', 1, 'ABS', 155, 15.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brandmotor`
--
ALTER TABLE `brandmotor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jenismotor`
--
ALTER TABLE `jenismotor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brandmotor`
--
ALTER TABLE `brandmotor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `jenismotor`
--
ALTER TABLE `jenismotor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
