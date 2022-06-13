-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 12, 2022 lúc 03:39 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `manage_fruit`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fruit`
--

CREATE TABLE `fruit` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quality` int(11) NOT NULL,
  `price` float NOT NULL,
  `typefruit` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `fruit`
--

INSERT INTO `fruit` (`id`, `name`, `quality`, `price`, `typefruit`, `image`) VALUES
(1, 'Apple', 100, 6000, 'beauty', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBxCaPufCdMRmkzOxbcyaPuOa23H13wX9NERilKgsgWJCHFZ1i6HDunn_vdcLQkkm1g34&usqp=CAU'),
(2, 'tomato', 500, 1500, 'potato', 'https://stickershop.line-scdn.net/stickershop/v1/product/3111701/LINEStorePC/main.png;compress=true'),
(3, 'single apple', 100, 3000, 'fruit', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxRWVKmgC4cGb-dxwSvQaLVHXDL-Jfw3KOZQ&usqp=CAU');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `fruit`
--
ALTER TABLE `fruit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `fruit`
--
ALTER TABLE `fruit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
