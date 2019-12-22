-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-09-2019 a las 22:53:57
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `basedatos1`
--
CREATE DATABASE IF NOT EXISTS `basedatos1` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `basedatos1`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inicio`
--

DROP TABLE IF EXISTS `inicio`;
CREATE TABLE `inicio` (
  `Id` varchar(11) COLLATE latin1_spanish_ci NOT NULL,
  `Nombre` varchar(33) COLLATE latin1_spanish_ci NOT NULL,
  `Numero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `inicio`
--

INSERT INTO `inicio` (`Id`, `Nombre`, `Numero`) VALUES
('AAA_02', 'Alicia', 99),
('AAA_05', 'Pedro', 666),
('AAA_44', 'Alejandro', 19),
('AAA_55', 'Aaron', 90),
('AAA_99', 'Alejandra', 98);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `inicio`
--
ALTER TABLE `inicio`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Numero` (`Numero`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
