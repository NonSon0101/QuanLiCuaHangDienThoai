-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: QuanLiCuaHangDiDong
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BaoHanh`
--

DROP TABLE IF EXISTS `BaoHanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BaoHanh` (
  `mabh` int NOT NULL AUTO_INCREMENT,
  `mahd` int DEFAULT NULL,
  `ngaylap` date DEFAULT NULL,
  `ngayhethan` date DEFAULT NULL,
  PRIMARY KEY (`mabh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BaoHanh`
--

LOCK TABLES `BaoHanh` WRITE;
/*!40000 ALTER TABLE `BaoHanh` DISABLE KEYS */;
/*!40000 ALTER TABLE `BaoHanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DienThoai`
--

DROP TABLE IF EXISTS `DienThoai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DienThoai` (
  `madt` int NOT NULL AUTO_INCREMENT,
  `tendt` varchar(30) DEFAULT NULL,
  `mansx` int DEFAULT NULL,
  `soluong` int DEFAULT NULL,
  `giaban` int DEFAULT NULL,
  `phantramgiam` int DEFAULT NULL,
  PRIMARY KEY (`madt`),
  KEY `fk_HangSX` (`mansx`),
  CONSTRAINT `fk_HangSX` FOREIGN KEY (`mansx`) REFERENCES `HangSanXuat` (`mahsx`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DienThoai`
--

LOCK TABLES `DienThoai` WRITE;
/*!40000 ALTER TABLE `DienThoai` DISABLE KEYS */;
INSERT INTO `DienThoai` VALUES (1,'Samsung galaxy z-plip',1,50,35000000,10),(2,'Samsung galaxy z-plip3',1,50,35000000,10),(3,'Samsung galaxy z-fold',20,20,45000000,10),(4,'Samsung galaxy tab A7',1,30,23000000,5),(5,'iPhone 15 pro',2,40,30000000,10),(6,'iPhone 15',2,40,22000000,10),(7,'iPhone 13',2,20,19900000,30),(8,'Oppo Reno 7',3,60,6000000,10),(9,'Xiaomi redmi note 11',4,33,10000000,5),(10,'OnePlus phone',5,20,13000000,10),(11,'Sony phone',6,25,5000000,10),(13,'iPhone 12',2,20,15000000,5),(15,'xiaomi note 24',4,10,10000000,2),(16,'xiaomi note 23 ',4,10,10000000,2);
/*!40000 ALTER TABLE `DienThoai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HangSanXuat`
--

DROP TABLE IF EXISTS `HangSanXuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HangSanXuat` (
  `mahsx` int NOT NULL AUTO_INCREMENT,
  `tenhsx` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`mahsx`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HangSanXuat`
--

LOCK TABLES `HangSanXuat` WRITE;
/*!40000 ALTER TABLE `HangSanXuat` DISABLE KEYS */;
INSERT INTO `HangSanXuat` VALUES (1,'SamSung'),(2,'Iphone'),(3,'Oppo'),(4,'xiaomi'),(5,'OnePlus'),(6,'Sony'),(7,'LG'),(8,'Motorola'),(9,'Nokia'),(10,'Google (Pixel)'),(11,'Belkin'),(12,'Anker'),(13,'Spigen'),(14,'OtterBox'),(15,'JBL'),(16,'Sony'),(17,'Logitech'),(18,'PopSockets'),(19,'AUKEY'),(20,'Samsung');
/*!40000 ALTER TABLE `HangSanXuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HoaDon`
--

DROP TABLE IF EXISTS `HoaDon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HoaDon` (
  `mahd` int NOT NULL AUTO_INCREMENT,
  `makh` int DEFAULT NULL,
  `manv` int DEFAULT NULL,
  `ngaylap` date DEFAULT NULL,
  PRIMARY KEY (`mahd`),
  KEY `fk_hdkh` (`makh`),
  CONSTRAINT `fk_hdkh` FOREIGN KEY (`makh`) REFERENCES `KhachHang` (`makh`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HoaDon`
--

LOCK TABLES `HoaDon` WRITE;
/*!40000 ALTER TABLE `HoaDon` DISABLE KEYS */;
INSERT INTO `HoaDon` VALUES (2,7,2,'2023-01-15');
/*!40000 ALTER TABLE `HoaDon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KhachHang`
--

DROP TABLE IF EXISTS `KhachHang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `KhachHang` (
  `makh` int NOT NULL AUTO_INCREMENT,
  `hokh` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tenkh` varchar(10) DEFAULT NULL,
  `loaikh` int DEFAULT NULL,
  `sodienthoai` varchar(11) DEFAULT NULL,
  `diachi` text,
  PRIMARY KEY (`makh`),
  KEY `fk_lkh` (`loaikh`),
  CONSTRAINT `fk_lkh` FOREIGN KEY (`loaikh`) REFERENCES `LoaiKhachHang` (`maloaikh`),
  CONSTRAINT `fk_loaikh` FOREIGN KEY (`loaikh`) REFERENCES `LoaiKhachHang` (`maloaikh`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KhachHang`
--

LOCK TABLES `KhachHang` WRITE;
/*!40000 ALTER TABLE `KhachHang` DISABLE KEYS */;
INSERT INTO `KhachHang` VALUES (7,'Trần Đình','Duy',1,'0913645143','HCM'),(8,'Trương Thảo','Nguyên',2,'0903164421','Phan Thiết'),(9,'Trần Văn','Bảo',1,'0934156984','Phú Yên');
/*!40000 ALTER TABLE `KhachHang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoaiKhachHang`
--

DROP TABLE IF EXISTS `LoaiKhachHang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LoaiKhachHang` (
  `maloaikh` int NOT NULL AUTO_INCREMENT,
  `tenloaikh` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`maloaikh`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoaiKhachHang`
--

LOCK TABLES `LoaiKhachHang` WRITE;
/*!40000 ALTER TABLE `LoaiKhachHang` DISABLE KEYS */;
INSERT INTO `LoaiKhachHang` VALUES (1,'thường'),(2,'thành viên'),(3,'VIP');
/*!40000 ALTER TABLE `LoaiKhachHang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoaiPhuKien`
--

DROP TABLE IF EXISTS `LoaiPhuKien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LoaiPhuKien` (
  `malpk` int NOT NULL AUTO_INCREMENT,
  `tenlpk` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`malpk`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoaiPhuKien`
--

LOCK TABLES `LoaiPhuKien` WRITE;
/*!40000 ALTER TABLE `LoaiPhuKien` DISABLE KEYS */;
INSERT INTO `LoaiPhuKien` VALUES (1,'sạc dự phòng'),(2,'cáp sạc'),(3,'Tai nghe'),(4,'Ốp lưng'),(5,'khác');
/*!40000 ALTER TABLE `LoaiPhuKien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NhanVien`
--

DROP TABLE IF EXISTS `NhanVien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NhanVien` (
  `manv` int NOT NULL AUTO_INCREMENT,
  `tennv` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `chucvu` varchar(20) DEFAULT NULL,
  `sodienthoai` varchar(11) DEFAULT NULL,
  `diachi` text,
  PRIMARY KEY (`manv`),
  KEY `fk_chucvu` (`chucvu`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NhanVien`
--

LOCK TABLES `NhanVien` WRITE;
/*!40000 ALTER TABLE `NhanVien` DISABLE KEYS */;
INSERT INTO `NhanVien` VALUES (1,'Nguyễn Thùy Vy','Quản lí','0911346875','HCM'),(2,'Nguyễn Văn Tèo','Nhân viên','0942365123','Đồng Nai');
/*!40000 ALTER TABLE `NhanVien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PhuKien`
--

DROP TABLE IF EXISTS `PhuKien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PhuKien` (
  `mapk` int NOT NULL AUTO_INCREMENT,
  `tenpk` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `loaipk` int DEFAULT NULL,
  `soluong` int DEFAULT NULL,
  `giaban` int DEFAULT NULL,
  `phantramgiam` int DEFAULT NULL,
  `mansx` int DEFAULT NULL,
  PRIMARY KEY (`mapk`),
  KEY `fk_HangSXPK` (`mansx`),
  KEY `fk_loaipk` (`loaipk`),
  CONSTRAINT `fk_HangSXPK` FOREIGN KEY (`mansx`) REFERENCES `HangSanXuat` (`mahsx`),
  CONSTRAINT `fk_loaipk` FOREIGN KEY (`loaipk`) REFERENCES `LoaiPhuKien` (`malpk`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PhuKien`
--

LOCK TABLES `PhuKien` WRITE;
/*!40000 ALTER TABLE `PhuKien` DISABLE KEYS */;
INSERT INTO `PhuKien` VALUES (22,'Cáp sạc iphone Belkin',2,100,799000,0,11),(23,'Sạc dự phòng Anker',1,30,800000,5,12),(24,'Tai nghe Spigen',3,20,200000,0,13),(25,'Ốp lưng iphone OtterBox',4,30,300000,5,14),(26,'Loa bluetooth JBL',5,17,1000000,10,15),(27,'Tai nghe wireless Sony',3,20,2000000,0,16),(28,'Bàn Phím Logitech',5,5,3000000,10,17),(29,'Tay cầm điện thoại PopSockets',5,20,1500000,0,18),(30,'tai nghe wireless AUKEY',3,19,600000,0,19);
/*!40000 ALTER TABLE `PhuKien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muadt`
--

DROP TABLE IF EXISTS `muadt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muadt` (
  `mahd` int NOT NULL,
  `madt` int NOT NULL,
  `soluong` int DEFAULT NULL,
  PRIMARY KEY (`mahd`,`madt`),
  KEY `fk_muadt` (`madt`),
  CONSTRAINT `fk_hddt` FOREIGN KEY (`mahd`) REFERENCES `HoaDon` (`mahd`),
  CONSTRAINT `fk_muadt` FOREIGN KEY (`madt`) REFERENCES `DienThoai` (`madt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muadt`
--

LOCK TABLES `muadt` WRITE;
/*!40000 ALTER TABLE `muadt` DISABLE KEYS */;
INSERT INTO `muadt` VALUES (2,2,3);
/*!40000 ALTER TABLE `muadt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muapk`
--

DROP TABLE IF EXISTS `muapk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muapk` (
  `mahd` int NOT NULL,
  `mapk` int NOT NULL,
  `soluong` int DEFAULT NULL,
  PRIMARY KEY (`mahd`,`mapk`),
  KEY `fk_muapk` (`mapk`),
  CONSTRAINT `fk_hdpk` FOREIGN KEY (`mahd`) REFERENCES `HoaDon` (`mahd`),
  CONSTRAINT `fk_muapk` FOREIGN KEY (`mapk`) REFERENCES `PhuKien` (`mapk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muapk`
--

LOCK TABLES `muapk` WRITE;
/*!40000 ALTER TABLE `muapk` DISABLE KEYS */;
INSERT INTO `muapk` VALUES (2,22,1);
/*!40000 ALTER TABLE `muapk` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-16 14:40:26
