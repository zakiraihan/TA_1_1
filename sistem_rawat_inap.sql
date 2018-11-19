-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Nov 2018 pada 11.36
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistem_rawat_inap`
--
CREATE DATABASE IF NOT EXISTS `sistem_rawat_inap`` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sistem_rawat_inap`;
-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter`
--

CREATE TABLE `dokter` (
  `id` bigint(20) NOT NULL,
  `nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_jaga`
--

CREATE TABLE `jadwal_jaga` (
  `id` bigint(20) NOT NULL,
  `daftar_hari_jaga` varchar(255) NOT NULL,
  `id_dokter` bigint(20) NOT NULL,
  `status_dokter` varchar(255) NOT NULL,
  `id_paviliun` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jadwal_jaga`
--

INSERT INTO `jadwal_jaga` (`id`, `daftar_hari_jaga`, `id_dokter`, `status_dokter`, `id_paviliun`) VALUES
(1, 'Senin', 1, 'Available mungkin?', 1),
(2, 'Senin', 2, 'Available mungkin?', 2),
(3, 'Selasa', 3, 'Available mungkin?', 3),
(4, 'Rabu', 4, 'Available mungkin?', 4),
(5, 'Kamis', 5, 'Available mungkin?', 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kamar`
--

CREATE TABLE `kamar` (
  `id` bigint(20) NOT NULL,
  `id_pasien` bigint(20) NOT NULL,
  `nomor_kamar` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `id_paviliun` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kamar`
--

INSERT INTO `kamar` (`id`, `id_pasien`, `nomor_kamar`, `status`, `id_paviliun`) VALUES
(1, 3001, 1, 1, 1),
(2, 3003, 2, 1, 1),
(3, 3006, 3, 1, 1),
(4, 3009, 1, 1, 4),
(5, 3012, 2, 1, 4),
(6, 3015, 3, 1, 4),
(7, 3018, 1, 1, 5),
(8, 3021, 2, 1, 5),
(9, 3024, 3, 1, 5),
(10, 0, 1, 0, 2),
(11, 0, 2, 0, 2),
(12, 0, 3, 0, 2),
(13, 0, 1, 0, 3),
(14, 0, 2, 0, 3),
(15, 0, 3, 0, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `id` bigint(20) NOT NULL,
  `nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `paviliun`
--

CREATE TABLE `paviliun` (
  `id` bigint(20) NOT NULL,
  `nama_paviliun` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `tipe_pasien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `paviliun`
--

INSERT INTO `paviliun` (`id`, `nama_paviliun`, `status`, `tipe_pasien`) VALUES
(1, 'Melati', 1, 'Cacar Api'),
(2, 'Kamboja', 0, 'Diabetus'),
(3, 'Kasablanka', 0, 'Demam Berdarah'),
(4, 'Anggrek', 1, 'Tifus'),
(5, 'Lili', 1, 'Diare');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemeriksaan`
--

CREATE TABLE `pemeriksaan` (
  `id` bigint(20) NOT NULL,
  `id_dokter` bigint(20) NOT NULL,
  `id_pasien` bigint(20) NOT NULL,
  `pemeriksaan` varchar(255) NOT NULL,
  `waktu` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pemeriksaan`
--

INSERT INTO `pemeriksaan` (`id`, `id_dokter`, `id_pasien`, `pemeriksaan`, `waktu`) VALUES
(1, 1, 1, 'Memeriksa', '2018-11-30 02:22:39'),
(2, 1, 3001, 'Memeriksa', '2018-11-30 02:22:39'),
(3, 2, 3003, 'Memeriksa', '2018-11-30 02:22:39'),
(4, 3, 3006, 'Memeriksa', '2018-11-30 02:22:39'),
(5, 4, 3009, 'Memeriksa', '2018-11-30 02:22:39'),
(6, 5, 3012, 'Memeriksa', '2018-11-30 02:22:39');

-- --------------------------------------------------------

--
-- Struktur dari tabel `request_obat`
--

CREATE TABLE `request_obat` (
  `id` bigint(20) NOT NULL,
  `id_pasien` bigint(20) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `nama_obat` varchar(255) NOT NULL,
  `id_pemeriksaan` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `request_obat`
--

INSERT INTO `request_obat` (`id`, `id_pasien`, `jumlah`, `nama_obat`, `id_pemeriksaan`) VALUES
(1, 3001, 2, 'Parasetamol', 2),
(2, 3003, 2, 'Parasetamol', 3),
(3, 3006, 2, 'Parasetamol', 4),
(4, 3009, 2, 'Parasetamol', 5),
(5, 3012, 2, 'Parasetamol', 6);

-- --------------------------------------------------------

--
-- Struktur dari tabel `request_pasien`
--

CREATE TABLE `request_pasien` (
  `id` bigint(20) NOT NULL,
  `assign` int(11) NOT NULL,
  `id_pasien` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `request_pasien`
--

INSERT INTO `request_pasien` (`id`, `assign`, `id_pasien`) VALUES
(1, 0, 20),
(2, 0, 21),
(3, 0, 22),
(4, 0, 23),
(5, 0, 24),
(6, 0, 25),
(7, 0, 26),
(8, 1, 27),
(9, 1, 28),
(10, 1, 29),
(11, 1, 30),
(12, 1, 31),
(13, 1, 32);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user_role`
--

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user_role`
--

INSERT INTO `user_role` (`id`, `password`, `role`, `username`) VALUES
(1, '$2a$10$ncZkvopRnbEG8e1U.bk4.uWOFHWHknapnD6AUSUHb21wBv51hiH8e', 'Admin', 'admin123'),
(2, '$2a$10$1rMQGYjg.NrMA75ldaVlPOtZP76I44fvmGPGB/kYXXuRC5YKKIrpu', 'Dokter', 'dokter123');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `jadwal_jaga`
--
ALTER TABLE `jadwal_jaga`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr77v6sofux8337tjpbn3jf86x` (`id_paviliun`);

--
-- Indeks untuk tabel `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkjr0f0qqrt72u0fnuxxq4nwnl` (`id_paviliun`);

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `paviliun`
--
ALTER TABLE `paviliun`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pemeriksaan`
--
ALTER TABLE `pemeriksaan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `request_obat`
--
ALTER TABLE `request_obat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4j63d1m8piu9n0dfbhj1jiu9n` (`id_pemeriksaan`);

--
-- Indeks untuk tabel `request_pasien`
--
ALTER TABLE `request_pasien`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_aphxiciwirrvuc0y7y2s2rufj` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `dokter`
--
ALTER TABLE `dokter`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `jadwal_jaga`
--
ALTER TABLE `jadwal_jaga`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `kamar`
--
ALTER TABLE `kamar`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `pasien`
--
ALTER TABLE `pasien`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `paviliun`
--
ALTER TABLE `paviliun`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `pemeriksaan`
--
ALTER TABLE `pemeriksaan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `request_obat`
--
ALTER TABLE `request_obat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `request_pasien`
--
ALTER TABLE `request_pasien`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT untuk tabel `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `jadwal_jaga`
--
ALTER TABLE `jadwal_jaga`
  ADD CONSTRAINT `FKr77v6sofux8337tjpbn3jf86x` FOREIGN KEY (`id_paviliun`) REFERENCES `paviliun` (`id`);

--
-- Ketidakleluasaan untuk tabel `kamar`
--
ALTER TABLE `kamar`
  ADD CONSTRAINT `FKkjr0f0qqrt72u0fnuxxq4nwnl` FOREIGN KEY (`id_paviliun`) REFERENCES `paviliun` (`id`);

--
-- Ketidakleluasaan untuk tabel `request_obat`
--
ALTER TABLE `request_obat`
  ADD CONSTRAINT `FK4j63d1m8piu9n0dfbhj1jiu9n` FOREIGN KEY (`id_pemeriksaan`) REFERENCES `pemeriksaan` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
