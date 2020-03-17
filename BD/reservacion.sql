-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-03-2020 a las 15:50:19
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reservacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacios`
--

CREATE TABLE `espacios` (
  `id_espacio` int(11) UNSIGNED NOT NULL,
  `nombre_espacio` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `tipo_espacio` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `lugares` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `espacios`
--

INSERT INTO `espacios` (`id_espacio`, `nombre_espacio`, `tipo_espacio`, `lugares`) VALUES
(9, 'Aula 70', 'Laboratorio de Computo', 40),
(10, 'Aula 40', 'Laboratorio de Computo', 40),
(14, 'Cafetería', 'Sala familiar', 100),
(15, 'Aul 70', 'Laboratorio de Computo', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id_reserva` int(11) NOT NULL,
  `nombre_espacio` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `horario` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `nombre_usuario` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `lugares_disponibles` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id_reserva`, `nombre_espacio`, `horario`, `fecha`, `nombre_usuario`, `lugares_disponibles`) VALUES
(1, 'Aula 70', '7:00-9:00', '2020-03-19', 'Erick Vega', 40),
(2, 'Aula 70', '9:00-11:00', '2020-03-19', 'Erick Vega', 40),
(3, 'Aula 70', '18:00-20:00', '2020-03-19', 'Erick Vega', 45),
(6, 'Cafetería', '18:00-20:00', '2020-04-02', 'Monica Manchuco', 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(30) NOT NULL,
  `nombre_usuario` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `tipo` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `contra` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `grupo` varchar(20) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `tipo`, `contra`, `grupo`) VALUES
(1, 'Erick Renato Vega Cerón', 'Administrador', '1', '17'),
(123, 'Marlon Brandon', 'Usuario', '123456', ''),
(123456, 'Monica Manchuco', 'Usuario', '123456', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `espacios`
--
ALTER TABLE `espacios`
  ADD PRIMARY KEY (`id_espacio`),
  ADD UNIQUE KEY `nombre_espacio` (`nombre_espacio`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `espacios`
--
ALTER TABLE `espacios`
  MODIFY `id_espacio` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
