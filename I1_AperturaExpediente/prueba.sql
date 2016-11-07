-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-11-2016 a las 17:55:03
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expedientes`
--

CREATE TABLE `expedientes` (
  `idExpediente` int(11) NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `idRadar` int(11) NOT NULL,
  `velocidad` int(11) NOT NULL,
  `fechayHora` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `expedientes`
--

INSERT INTO `expedientes` (`idExpediente`, `matricula`, `idRadar`, `velocidad`, `fechayHora`) VALUES
(11, '12345', 1, 128, 'Sat Nov 05 17:52:05 CET 2016'),
(12, '54321', 2, 57, 'Sat Nov 05 17:52:06 CET 2016'),
(13, '54321', 2, 134, 'Sat Nov 05 17:52:11 CET 2016'),
(14, '45678', 1, 124, 'Sat Nov 05 17:52:14 CET 2016'),
(15, '12345', 2, 99, 'Sat Nov 05 17:52:17 CET 2016'),
(16, '45678', 2, 96, 'Sat Nov 05 17:52:18 CET 2016'),
(17, '54321', 2, 68, 'Sat Nov 05 17:52:19 CET 2016'),
(18, '45678', 2, 60, 'Sat Nov 05 17:53:14 CET 2016');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `dniPropietario` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`dniPropietario`) VALUES
('123456789'),
('987654321'),
('asdfghjkl');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `radar`
--

CREATE TABLE `radar` (
  `idRadar` int(10) NOT NULL,
  `localizacion` varchar(65000) NOT NULL,
  `velMax` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `radar`
--

INSERT INTO `radar` (`idRadar`, `localizacion`, `velMax`) VALUES
(1, '(Ciudad Real)', 120),
(2, 'Miguelturra', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `matricula` varchar(10) NOT NULL,
  `dniPropietario` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`matricula`, `dniPropietario`) VALUES
('12345', '123456789'),
('45678', '987654321'),
('54321', '123456789');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `expedientes`
--
ALTER TABLE `expedientes`
  ADD PRIMARY KEY (`idExpediente`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`dniPropietario`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`matricula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `expedientes`
--
ALTER TABLE `expedientes`
  MODIFY `idExpediente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
