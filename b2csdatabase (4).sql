-- phpMyAdmin SQL Dump ------DataBase used : MySql
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 20, 2017 at 12:26 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `b2csdatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `company_details`
--

CREATE TABLE `company_details` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gstin` varchar(100) NOT NULL,
  `contact` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_details`
--

INSERT INTO `company_details` (`id`, `name`, `address`, `gstin`, `contact`) VALUES
(1, 'Dummy Company', 'my address', '33443JFKD9', '998989898');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customerCustomerName` varchar(200) NOT NULL,
  `customerMobileNumber` varchar(11) NOT NULL,
  `customerGstinNumber` varchar(50) NOT NULL,
  `customerAdderss` text NOT NULL,
  `advanceBalance` float NOT NULL DEFAULT '0',
  `dueBalance` float NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customerCustomerName`, `customerMobileNumber`, `customerGstinNumber`, `customerAdderss`, `advanceBalance`, `dueBalance`) VALUES
(24, 'chandan', '8759448989', '943NJF4NJ', 'snw we e ee', 0, 0),
(23, 'cus', '393', '4545MNJM4', 'add', 0, 50),
(20, 'ravvv', '9898989898', '9878ADJS90N', 'dfsafsdf', 20, 4),
(21, 'ashis', '9898989898', '434JFDD33333', 'deecec3dec3c', 0, 0),
(18, 'cusss', '3434343', 'dfsdfsdf', 'ff4f4f4f4f', 526, 6);

-- --------------------------------------------------------

--
-- Table structure for table `dealer`
--

CREATE TABLE `dealer` (
  `id` int(11) NOT NULL,
  `dealer_company_name` varchar(100) DEFAULT NULL,
  `dealer_company_addr` varchar(100) DEFAULT NULL,
  `dealer_company_gstn` varchar(100) DEFAULT NULL,
  `dealer_company_contact` varchar(60) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dealer`
--

INSERT INTO `dealer` (`id`, `dealer_company_name`, `dealer_company_addr`, `dealer_company_gstn`, `dealer_company_contact`) VALUES
(4, 'name', 'gsstin', 'contact', 'address'),
(7, 'dealer1', 'gstin1', 'adresss1', 'adress'),
(8, 'chandan', 'dfdfdfdfd eee', '56552', '986532'),
(9, 'name2', 'adress2', 'gstin2', 'contact2'),
(10, 'name3', 'adeess3', 'gstin3', 'contact3');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(100) DEFAULT NULL,
  `item_hsn` varchar(50) DEFAULT '0',
  `qty_type` varchar(50) DEFAULT 'non',
  `gst_rate` float DEFAULT '0',
  `cgst` float DEFAULT NULL,
  `sgst` float DEFAULT NULL,
  `cess` float DEFAULT '0',
  `stock` float DEFAULT '0',
  `cost_price` float DEFAULT NULL,
  `selling_price` float DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`item_id`, `item_name`, `item_hsn`, `qty_type`, `gst_rate`, `cgst`, `sgst`, `cess`, `stock`, `cost_price`, `selling_price`) VALUES
(19, 'agarbatti', '3553', 'PCS-PIECES', 18, 399.96, 39.96, 4, 37, 4444, 44),
(23, 'oil', '20313', 'PCS-PIECES', 18, 4.5, 4.5, 0, -62, 50, 57),
(24, 'sabuns', '3422s', 'PCS-PIECES', 12, 0.76, 0.76, 0, 152, 31, 36),
(25, 'laptop', '45826', 'BOX-BOX', 5, 0.625, 0.625, 0, -13, 25, 30);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_invoice`
--

CREATE TABLE `purchase_invoice` (
  `id` int(11) NOT NULL,
  `dealer_name` varchar(100) DEFAULT NULL,
  `gstin` varchar(100) DEFAULT NULL,
  `invoice_no` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `amount` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_invoice`
--

INSERT INTO `purchase_invoice` (`id`, `dealer_name`, `gstin`, `invoice_no`, `date`, `amount`) VALUES
(2, 'ddf', 'df', 'df4fgtgr5g', NULL, 518793),
(3, '', '', '', NULL, 0),
(4, '', '', '', NULL, 0),
(5, '', '', '', NULL, 0),
(6, 'hjhjh', 'hj', 'sdsds', NULL, 40),
(7, 'hjhjh', 'hj', 'sdsds', NULL, 40),
(8, 'hjhjh', 'hj', 'sdsds', NULL, 480),
(9, 'ddf', 'df', '2wwe2wew', NULL, 120),
(10, 'ddf', 'df', '3f3f', NULL, 300),
(11, 'ddf', 'df', 'dw2ew', NULL, 350),
(12, 'ssss', 'sssss', 'sssss', '2017-08-30', 150);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_invoice_details`
--

CREATE TABLE `purchase_invoice_details` (
  `id` int(11) NOT NULL,
  `invoice_no` varchar(100) NOT NULL,
  `item_name` varchar(200) NOT NULL,
  `item_code` varchar(100) NOT NULL,
  `hsn` varchar(50) NOT NULL,
  `quantity` varchar(20) NOT NULL,
  `price` float NOT NULL,
  `gst_amount` float NOT NULL,
  `igst_amount` float NOT NULL,
  `total` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_invoice_details`
--

INSERT INTO `purchase_invoice_details` (`id`, `invoice_no`, `item_name`, `item_code`, `hsn`, `quantity`, `price`, `gst_amount`, `igst_amount`, `total`) VALUES
(1, 'sad', 'sad', 'asd', 'asd', '3', 3, 0, 0, 0),
(2, 'df4fgtgr5g', 'fdddgdgf', '0.0', '4444', '33 KGS', 1089, 0, 0, 0),
(3, 'df4fgtgr5g', 'fgffg', '0.0', '444', '2332 PCS', 517704, 0, 0, 0),
(4, 'sdsds', 'soap', '0.0', '20311', '2 CTN-CARTONS', 40, 0, 0, 0),
(5, 'sdsds', 'soap', '0.0', '20311', '2 CTN-CARTONS', 40, 0, 0, 0),
(6, 'sdsds', 'soap', '0.0', '20311', '2 CTN-CARTONS', 40, 0, 0, 0),
(7, 'sdsds', 'soap', '0.0', '20311', '2 CTN-CARTONS', 40, 0, 0, 0),
(8, 'sdsds', 'oil', '0.0', '20313', '4 PCS', 200, 0, 0, 0),
(9, 'sdsds', 'oil', '0.0', '20313', '4 PCS', 200, 0, 0, 0),
(10, '2wwe2wew', 'soap', '0.0', '20311', '3 PCS-PIECES', 60, 0, 0, 60),
(11, '2wwe2wew', 'soap', '0.0', '20311', '3 PCS-PIECES', 60, 0, 0, 60),
(12, '3f3f', 'oil', '23', '20313', '3 PCS-PIECES', 150, 0, 0, 150),
(13, '3f3f', 'oil', '23', '20313', '3 PCS-PIECES', 150, 0, 0, 150),
(14, 'dw2ew', 'oil', '23', '20313', '3 PCS-PIECES', 150, 0, 0, 150),
(15, 'dw2ew', 'oil', '23', '20313', '4 PCS-PIECES', 200, 0, 0, 200),
(16, 'sssss', 'oil', '23', '20313', '3 PCS-PIECES', 150, 0, 0, 150);

-- --------------------------------------------------------

--
-- Table structure for table `selling_invoice`
--

CREATE TABLE `selling_invoice` (
  `invoice_number` int(11) NOT NULL,
  `invoice_date` date DEFAULT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  `cutomer_number` varchar(20) DEFAULT NULL,
  `customer_gstin` varchar(30) DEFAULT NULL,
  `billing_addr` varchar(400) DEFAULT NULL,
  `total_sgst` float DEFAULT NULL,
  `total_cgst` float DEFAULT NULL,
  `total_igst` float DEFAULT NULL,
  `taxable_value` float DEFAULT NULL,
  `cess` float DEFAULT NULL,
  `amount` float DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `selling_invoice`
--

INSERT INTO `selling_invoice` (`invoice_number`, `invoice_date`, `customer_name`, `cutomer_number`, `customer_gstin`, `billing_addr`, `total_sgst`, `total_cgst`, `total_igst`, `taxable_value`, `cess`, `amount`) VALUES
(100022, '2017-09-02', 'raviu', '2323232323', NULL, NULL, NULL, NULL, NULL, 19.8, 19.8, 415.8),
(100023, '2017-09-02', 'raviuaf', '22222222', NULL, NULL, NULL, NULL, NULL, 8.8, 8.8, 382.8),
(100024, '2017-09-02', 'ravi', '22222', NULL, NULL, NULL, NULL, NULL, 4.4, 4.4, 92.4),
(100025, '2017-09-05', 'fd', '333', NULL, NULL, NULL, NULL, NULL, 0, 0, 1943.84),
(100026, '2017-09-06', 'dfsdf', '4353', NULL, NULL, NULL, NULL, NULL, 0, 0, 17784.8),
(100027, '2017-09-06', '44ffgff', '4444', NULL, NULL, NULL, NULL, NULL, 0, 0, 35569.6),
(100028, '2017-09-06', '34', '34', NULL, NULL, NULL, NULL, NULL, 0, 0, 17784.8),
(100029, '2017-09-06', '', '', NULL, NULL, NULL, NULL, NULL, 0, 0, 624),
(100030, '2017-09-06', 'dfs', '343433', NULL, NULL, NULL, NULL, NULL, 0, 0, 1656),
(100031, '2017-09-07', 'udal', '9988998899', NULL, NULL, NULL, NULL, NULL, 0, 0, 631.68),
(100014, '2017-08-30', 'wewe', '33433', NULL, NULL, NULL, NULL, NULL, 3199.68, 3199.68, 21173.7),
(100015, '2017-08-30', 'wewe', '33433', NULL, NULL, NULL, NULL, NULL, 168, 168, 768),
(100016, '2017-08-30', 'sada', '34343334', NULL, NULL, NULL, NULL, NULL, 0, 0, 132),
(100017, '2017-08-31', 'wdd', '344444', NULL, NULL, NULL, NULL, NULL, 1, 1, 21),
(100018, '2017-08-31', '', '', NULL, NULL, NULL, NULL, NULL, 112, 112, 512),
(100019, '2017-08-31', 'dhgid', '343434', NULL, NULL, NULL, NULL, NULL, 3255.68, 3255.68, 21297.7),
(100020, '2017-08-31', 'dadfsdfs', '33434334', NULL, NULL, NULL, NULL, NULL, 112, 112, 3548),
(100021, '2017-08-31', 'dadfsdfs', '33434334', NULL, NULL, NULL, NULL, NULL, 112, 112, 2789),
(100032, '2017-09-07', 'raviii', '292929922', NULL, NULL, NULL, NULL, NULL, 43.43, 0, 745.65),
(100033, '2017-09-08', 'cus', '393', NULL, NULL, NULL, NULL, NULL, 3.75, 0, 101.25),
(100034, '2017-09-08', 'qqqqq', '2222q', NULL, NULL, NULL, NULL, NULL, 879.84, 0, 1847.68),
(100035, '2017-09-08', 'sd', '', NULL, NULL, NULL, NULL, NULL, 4.56, 0, 121.68),
(100036, '2017-09-08', 'sss', '', NULL, NULL, NULL, NULL, NULL, 4.56, 0, 121.68),
(100037, '2017-09-08', 'w', '', NULL, NULL, NULL, NULL, NULL, 4.56, 0, 121.68),
(100038, '2017-09-08', '2s', '', NULL, NULL, NULL, NULL, NULL, 27, 0, 252),
(100039, '2017-09-08', 'f', '', NULL, NULL, NULL, NULL, NULL, 4.56, 0, 121.68),
(100040, '2017-09-08', 'ww', '', NULL, NULL, NULL, NULL, NULL, 50.16, 0, 2843.28),
(100041, '2017-09-08', '2', '', NULL, NULL, NULL, NULL, NULL, 18, 0, 150),
(100042, '2017-09-08', 'w', '', NULL, NULL, NULL, NULL, NULL, 18, 0, 150),
(100043, '2017-09-08', 'w', '', NULL, NULL, NULL, NULL, NULL, 3.04, 0, 78.08),
(100044, '2017-09-08', 's', '', NULL, NULL, NULL, NULL, NULL, 3.04, 0, 78.08),
(100045, '2017-09-08', 'w', '', NULL, NULL, NULL, NULL, NULL, 3.04, 0, 78.08),
(100046, '2017-09-08', 'dfl dkng sdkfnsdf s gdsf g', '89898989898', '3434340000', 'safgefafgsdfgfdgs sfg sdfg sdf gdfsg dfg dfg dfg dfg dfg dfg dfg df dfgfdg sfgfg', 34, 34, 34, 3434, 44, 78.08),
(100047, '2017-09-08', 'sdfsdfdsf sdfs ', '333', 'dsfdd', 'dfsdfsdfsd fsd fsdd fsdf sdf sdf sdf sdf sd fsdf sdfsd fsdf sd', 2.28, 2.28, 0, 4.56, 0, 121.68),
(100048, '2017-09-08', 'dsfdgs fsg s', '343', 'dsaf', 'dsadf dasf sadg sfg sfg sfd gdsf gdf gdf gdsfg sdf df df gds  dfd  ', 18, 18, 0, 36, 0, 372),
(100049, '2017-09-08', 'dsfdgs fsg s', '343', 'dsaf', 'dsadf dasf sadg sfg sfg sfd gdsf gdf gdf gdsfg sdf df df gds  dfd  ', 18, 18, 0, 36, 0, 372),
(100050, '2017-09-08', 'sag fsfdg dfg', '444', 'fdgd', 'dfgd', 13.5, 13.5, 0, 27, 0, 252),
(100051, '2017-09-08', 'chandan', '8759448989', 'noon', 'snw we e ee', 12.8, 12.8, 0, 25.6, 0, 368),
(100052, '2017-09-11', 'cusss', '3434343', 'dfsdfsdf', 'ff4f4f4f4f', 12.53, 12.53, 0, 25.06, 0, 336.68);

-- --------------------------------------------------------

--
-- Table structure for table `selling_invoice_detail`
--

CREATE TABLE `selling_invoice_detail` (
  `id` int(11) NOT NULL,
  `invoice_number` int(11) NOT NULL,
  `item_code` int(11) NOT NULL,
  `item_name` varchar(200) NOT NULL,
  `hsn` varchar(50) DEFAULT '0',
  `quantity` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `cgst_rate` float NOT NULL,
  `cgst_amount` float NOT NULL,
  `sgst_rate` float NOT NULL,
  `sgst_amount` float NOT NULL,
  `igst` float NOT NULL,
  `total` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `selling_invoice_detail`
--

INSERT INTO `selling_invoice_detail` (`id`, `invoice_number`, `item_code`, `item_name`, `hsn`, `quantity`, `price`, `cgst_rate`, `cgst_amount`, `sgst_rate`, `sgst_amount`, `igst`, `total`) VALUES
(47, 100017, 14, 'sdsd', '3', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(46, 100017, 14, 'sdsd', '3', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(45, 100016, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(44, 100016, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(43, 100015, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(42, 100015, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(41, 100015, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(40, 100014, 19, 'fgffg', '444', '2 PCS', 0, 0, 0, 0, 0, 0, 0),
(39, 100014, 19, 'fgffg', '444', '2 PCS', 0, 0, 0, 0, 0, 0, 0),
(38, 100013, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(37, 100013, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(36, 100013, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(35, 100012, 14, 'sdsd', '3', '11 BAG', 0, 0, 0, 0, 0, 0, 0),
(34, 100012, 14, 'sdsd', '3', '11 BAG', 0, 0, 0, 0, 0, 0, 0),
(33, 100012, 14, 'sdsd', '3', '11 BAG', 0, 0, 0, 0, 0, 0, 0),
(32, 100012, 14, 'sdsd', '3', '11 BAG', 0, 0, 0, 0, 0, 0, 0),
(31, 100012, 14, 'sdsd', '3', '11 BAG', 0, 0, 0, 0, 0, 0, 0),
(30, 100011, 18, 'fgdfg', '44', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(29, 100011, 18, 'fgdfg', '44', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(28, 100011, 14, 'sdsd', '3', '3 BAG', 0, 0, 0, 0, 0, 0, 0),
(26, 100010, 14, 'sdsd', '3', '11 BAG', 0, 0, 0, 0, 0, 0, 0),
(27, 100010, 14, 'sdsd', '3', '11 BAG', 0, 0, 0, 0, 0, 0, 0),
(48, 100017, 14, 'sdsd', '3', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(49, 100017, 14, 'sdsd', '3', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(50, 100017, 14, 'sdsd', '3', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(51, 100018, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(52, 100018, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(53, 100019, 19, 'fgffg', '444', '2 PCS', 0, 0, 0, 0, 0, 0, 0),
(54, 100019, 19, 'fgffg', '444', '2 PCS', 0, 0, 0, 0, 0, 0, 0),
(55, 100019, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(56, 100019, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(57, 100020, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(58, 100020, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(59, 100020, 21, 'sfsf', '33', '23 BOX', 0, 0, 0, 0, 0, 0, 0),
(60, 100020, 21, 'sfsf', '33', '23 BOX', 0, 0, 0, 0, 0, 0, 0),
(61, 100020, 21, 'sfsf', '33', '23 BOX', 0, 0, 0, 0, 0, 0, 0),
(62, 100020, 21, 'sfsf', '33', '23 BOX', 0, 0, 0, 0, 0, 0, 0),
(63, 100021, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(64, 100021, 16, 'gfgfg', '4', '2 LTR', 0, 0, 0, 0, 0, 0, 0),
(65, 100021, 21, 'sfsf', '33', '23 BOX', 0, 0, 0, 0, 0, 0, 0),
(66, 100021, 21, 'sfsf', '33', '23 BOX', 0, 0, 0, 0, 0, 0, 0),
(67, 100021, 21, 'sfsf', '33', '23 BOX', 0, 0, 0, 0, 0, 0, 0),
(68, 100022, 18, 'fgdfg', '44', '3 BAG', 0, 0, 0, 0, 0, 0, 0),
(69, 100022, 18, 'fgdfg', '44', '3 BAG', 0, 0, 0, 0, 0, 0, 0),
(70, 100022, 18, 'fgdfg', '44', '3 BAG', 0, 0, 0, 0, 0, 0, 0),
(71, 100023, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(72, 100023, 21, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(73, 100023, 1, 'sfsf', '33', '2 BOX', 0, 0, 0, 0, 0, 0, 0),
(74, 100023, 18, 'fgdfg', '44', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(75, 100023, 18, 'fgdfg', '44', '2 BAG', 0, 0, 0, 0, 0, 0, 0),
(76, 100024, 18, 'fgdfg', '44', '1 BAG', 0, 0, 0, 0, 0, 0, 0),
(77, 100024, 18, 'fgdfg', '44', '1 BAG', 0, 0, 0, 0, 0, 0, 0),
(78, 100027, 18, 'fgdfg', '44', '4 BAG', 4444, 2.5, 1.1, 2.5, 1.1, 0, 17784.8),
(79, 100027, 18, 'fgdfg', '44', '4 BAG', 4444, 2.5, 1.1, 2.5, 1.1, 0, 17784.8),
(80, 100028, 18, 'fgdfg', '44', '2 BAG', 4444, 2.5, 1.1, 2.5, 1.1, 0, 8892.4),
(81, 100028, 18, 'fgdfg', '44', '2 BAG', 4444, 2.5, 1.1, 2.5, 1.1, 0, 8892.4),
(82, 100029, 16, 'gfgfg', '4', '2 LTR', 100, 14, 28, 14, 28, 0, 312),
(83, 100029, 16, 'gfgfg', '4', '2 LTR', 100, 14, 28, 14, 28, 0, 312),
(84, 100030, 16, 'gfgfg', '4', '3 LTR', 100, 14, 42, 14, 42, 0, 552),
(85, 100030, 16, 'gfgfg', '4', '3 LTR', 100, 14, 42, 14, 42, 0, 552),
(86, 100030, 16, 'gfgfg', '4', '3 LTR', 100, 14, 42, 14, 42, 0, 552),
(87, 100031, 24, 'sabuns', '3422s', '3 PCS', 36, 6, 2.28, 6, 2.28, 0, 121.68),
(88, 100031, 23, 'oil', '20313', '5 PCS', 57, 9, 22.5, 9, 22.5, 0, 510),
(89, 100032, 24, 'sabuns', '3422s', '3 PCS', 36, 6, 2.28, 6, 2.28, 0, 121.68),
(90, 100032, 25, 'laptop', '45826', '3 BOX', 30, 2.5, 1.875, 2.5, 1.875, 0, 101.25),
(91, 100032, 24, 'sabuns', '3422s', '6 PCS', 36, 6, 4.56, 6, 4.56, 0, 270.72),
(92, 100032, 23, 'oil', '20313', '3 PCS', 57, 9, 0, 9, 0, 26, 252),
(93, 100033, 25, 'laptop', '45826', '3 BOX', 30, 2.5, 1.875, 2.5, 1.875, 0, 101.25),
(94, 100034, 19, 'agarbatti', '3553', '2 PCS', 44, 9, 799.92, 9, 79.92, 0, 1847.68),
(95, 100035, 24, 'sabuns', '3422s', '3 PCS', 36, 6, 2.28, 6, 2.28, 0, 121.68),
(96, 100036, 24, 'sabuns', '3422s', '3 PCS', 36, 6, 2.28, 6, 2.28, 0, 121.68),
(97, 100037, 24, 'sabuns', '3422s', '3 PCS', 36, 6, 2.28, 6, 2.28, 0, 121.68),
(98, 100042, 23, 'oil', '20313', '2 PCS', 57, 9, 9, 9, 9, 0, 150),
(99, 100043, 24, 'sabuns', '3422s', '2 PCS', 36, 6, 1.52, 6, 1.52, 0, 78.08),
(100, 100046, 24, 'sabuns', '3422s', '2 PCS', 36, 6, 1.52, 6, 1.52, 0, 78.08),
(101, 100050, 23, 'oil', '20313', '3 PCS', 57, 9, 13.5, 9, 13.5, 0, 252),
(102, 100051, 24, 'sabuns', '3422s', '5 PCS', 36, 6, 3.8, 6, 3.8, 0, 218),
(103, 100051, 23, 'oil', '20313', '2 PCS', 57, 9, 9, 9, 9, 0, 150),
(104, 100052, 24, 'sabuns', '3422s', '3 PCS', 36, 6, 2.28, 6, 2.28, 0, 121.68),
(105, 100052, 25, 'laptop', '45826', '2 BOX', 30, 2.5, 1.25, 2.5, 1.25, 0, 65),
(106, 100052, 23, 'oil', '20313', '2 PCS', 57, 9, 9, 9, 9, 0, 150);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company_details`
--
ALTER TABLE `company_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dealer`
--
ALTER TABLE `dealer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `purchase_invoice`
--
ALTER TABLE `purchase_invoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `purchase_invoice_details`
--
ALTER TABLE `purchase_invoice_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `selling_invoice`
--
ALTER TABLE `selling_invoice`
  ADD PRIMARY KEY (`invoice_number`);

--
-- Indexes for table `selling_invoice_detail`
--
ALTER TABLE `selling_invoice_detail`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company_details`
--
ALTER TABLE `company_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `dealer`
--
ALTER TABLE `dealer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `purchase_invoice`
--
ALTER TABLE `purchase_invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `purchase_invoice_details`
--
ALTER TABLE `purchase_invoice_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `selling_invoice`
--
ALTER TABLE `selling_invoice`
  MODIFY `invoice_number` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100053;
--
-- AUTO_INCREMENT for table `selling_invoice_detail`
--
ALTER TABLE `selling_invoice_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
