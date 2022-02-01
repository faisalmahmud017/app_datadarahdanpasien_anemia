-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2021 at 08:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `treatment`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) UNSIGNED ZEROFILL NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `daftar_obat`
--

CREATE TABLE `daftar_obat` (
  `id_daftar_obat` varchar(11) NOT NULL,
  `tipe_obat` varchar(60) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `harga_obat` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `daftar_obat`
--

INSERT INTO `daftar_obat` (`id_daftar_obat`, `tipe_obat`, `nama_obat`, `harga_obat`) VALUES
('HO0001', 'Deferiprone TAB', 'Oferload TAB', '33000');

-- --------------------------------------------------------

--
-- Table structure for table `kebutuhan_darah_pasien`
--

CREATE TABLE `kebutuhan_darah_pasien` (
  `id_kebutuhan_darah` int(11) UNSIGNED ZEROFILL NOT NULL,
  `no_rek_medis` varchar(30) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `nik` varchar(30) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `gol_darah` varchar(9) NOT NULL,
  `jen_kel` varchar(20) NOT NULL,
  `darah_dibutuhkan` varchar(20) NOT NULL,
  `jml_cc_darah` varchar(20) NOT NULL,
  `jml_kantong` varchar(20) NOT NULL,
  `total_harga` varchar(30) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kebutuhan_darah_pasien`
--

INSERT INTO `kebutuhan_darah_pasien` (`id_kebutuhan_darah`, `no_rek_medis`, `nama`, `nik`, `tgl_lahir`, `gol_darah`, `jen_kel`, `darah_dibutuhkan`, `jml_cc_darah`, `jml_kantong`, `total_harga`, `tanggal`) VALUES
(00000000001, 'TRMA000001', 'asa', '123', '2021-05-13', 'O', 'Laki-laki', '1440', '12', '2', '1950000', '2021-05-07');

-- --------------------------------------------------------

--
-- Table structure for table `obat_kelator_besi`
--

CREATE TABLE `obat_kelator_besi` (
  `id_kelator_besi` int(11) UNSIGNED ZEROFILL NOT NULL,
  `no_rek_medis` varchar(30) NOT NULL,
  `nik` varchar(25) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jenis_jaminan` varchar(50) NOT NULL,
  `tipe_obat` varchar(40) NOT NULL,
  `berat_badan` varchar(9) NOT NULL,
  `jml_dosis` varchar(9) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `permohonan_jml_obat` varchar(9) NOT NULL,
  `realisasi_obat` varchar(30) NOT NULL,
  `harga` varchar(40) NOT NULL,
  `tgl_permintaan_obat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `obat_kelator_besi`
--

INSERT INTO `obat_kelator_besi` (`id_kelator_besi`, `no_rek_medis`, `nik`, `nama`, `tgl_lahir`, `jenis_jaminan`, `tipe_obat`, `berat_badan`, `jml_dosis`, `nama_obat`, `permohonan_jml_obat`, `realisasi_obat`, `harga`, `tgl_permintaan_obat`) VALUES
(00000000001, 'TRMA000001', '123', 'asa', '2021-05-13', 'TUNAI', 'Deferiprone TAB', '70', '315.0', 'Oferload TAB', '12', '12', '396000', '2021-05-06');

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

CREATE TABLE `pasien` (
  `nik` varchar(30) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jen_kel` varchar(15) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` text NOT NULL,
  `no_tlp` varchar(20) NOT NULL,
  `gol_darah` varchar(10) NOT NULL,
  `no_treatment` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`nik`, `nama`, `jen_kel`, `tgl_lahir`, `alamat`, `no_tlp`, `gol_darah`, `no_treatment`) VALUES
('123', 'asa', 'Laki-laki', '2021-05-13', 'jkt', '021', 'O', 'NTH00001');

-- --------------------------------------------------------

--
-- Table structure for table `tagihan_pasien`
--

CREATE TABLE `tagihan_pasien` (
  `id_tagihan` int(11) UNSIGNED ZEROFILL NOT NULL,
  `no_rek_medis` varchar(25) NOT NULL,
  `nik` varchar(25) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jen_kel` varchar(20) NOT NULL,
  `gol_darah` varchar(9) NOT NULL,
  `jenis_jaminan` varchar(20) NOT NULL,
  `jml_realisasi_obat` varchar(9) NOT NULL,
  `nama_obat` varchar(35) NOT NULL,
  `total_harga_obat` varchar(20) NOT NULL,
  `jml_kantong_darah` varchar(10) NOT NULL,
  `total_harga_darah` varchar(20) NOT NULL,
  `total_tagihan` varchar(20) NOT NULL,
  `tanggal_tagihan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tagihan_pasien`
--

INSERT INTO `tagihan_pasien` (`id_tagihan`, `no_rek_medis`, `nik`, `nama`, `jen_kel`, `gol_darah`, `jenis_jaminan`, `jml_realisasi_obat`, `nama_obat`, `total_harga_obat`, `jml_kantong_darah`, `total_harga_darah`, `total_tagihan`, `tanggal_tagihan`) VALUES
(00000000006, 'TRMA000001', '123', 'asa', 'Laki-laki', 'O', 'TUNAI', '12', 'Oferload TAB', '396000', '2', '1950000', 'Rp2.346.000,00', '2021-05-01');

-- --------------------------------------------------------

--
-- Table structure for table `treatment_rekam_medis`
--

CREATE TABLE `treatment_rekam_medis` (
  `no_rek_medis` varchar(30) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `nik` varchar(30) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `gol_darah` varchar(9) NOT NULL,
  `jen_kel` varchar(20) NOT NULL,
  `berat_badan` float NOT NULL,
  `tinggi_badan` int(5) NOT NULL,
  `kadar_hb` varchar(20) NOT NULL,
  `jml_darah` varchar(9) NOT NULL,
  `tgl_transfusi` date NOT NULL,
  `jenis_jaminan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `treatment_rekam_medis`
--

INSERT INTO `treatment_rekam_medis` (`no_rek_medis`, `nama`, `nik`, `tgl_lahir`, `gol_darah`, `jen_kel`, `berat_badan`, `tinggi_badan`, `kadar_hb`, `jml_darah`, `tgl_transfusi`, `jenis_jaminan`) VALUES
('TRMA000001', 'asa', '123', '2021-05-13', 'O', 'Laki-laki', 45, 45, '4', '1440', '2021-05-06', 'TUNAI');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `daftar_obat`
--
ALTER TABLE `daftar_obat`
  ADD PRIMARY KEY (`id_daftar_obat`);

--
-- Indexes for table `kebutuhan_darah_pasien`
--
ALTER TABLE `kebutuhan_darah_pasien`
  ADD PRIMARY KEY (`id_kebutuhan_darah`);

--
-- Indexes for table `obat_kelator_besi`
--
ALTER TABLE `obat_kelator_besi`
  ADD PRIMARY KEY (`id_kelator_besi`),
  ADD KEY `no_rek_medis` (`no_rek_medis`),
  ADD KEY `nik` (`nik`);

--
-- Indexes for table `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `tagihan_pasien`
--
ALTER TABLE `tagihan_pasien`
  ADD PRIMARY KEY (`id_tagihan`),
  ADD KEY `no_rek_medis` (`no_rek_medis`),
  ADD KEY `nik` (`nik`);

--
-- Indexes for table `treatment_rekam_medis`
--
ALTER TABLE `treatment_rekam_medis`
  ADD PRIMARY KEY (`no_rek_medis`),
  ADD KEY `nik` (`nik`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `kebutuhan_darah_pasien`
--
ALTER TABLE `kebutuhan_darah_pasien`
  MODIFY `id_kebutuhan_darah` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `obat_kelator_besi`
--
ALTER TABLE `obat_kelator_besi`
  MODIFY `id_kelator_besi` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tagihan_pasien`
--
ALTER TABLE `tagihan_pasien`
  MODIFY `id_tagihan` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `obat_kelator_besi`
--
ALTER TABLE `obat_kelator_besi`
  ADD CONSTRAINT `obat_kelator_besi_ibfk_1` FOREIGN KEY (`no_rek_medis`) REFERENCES `treatment_rekam_medis` (`no_rek_medis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `obat_kelator_besi_ibfk_2` FOREIGN KEY (`nik`) REFERENCES `pasien` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tagihan_pasien`
--
ALTER TABLE `tagihan_pasien`
  ADD CONSTRAINT `tagihan_pasien_ibfk_1` FOREIGN KEY (`no_rek_medis`) REFERENCES `treatment_rekam_medis` (`no_rek_medis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tagihan_pasien_ibfk_2` FOREIGN KEY (`nik`) REFERENCES `pasien` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `treatment_rekam_medis`
--
ALTER TABLE `treatment_rekam_medis`
  ADD CONSTRAINT `treatment_rekam_medis_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `pasien` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
