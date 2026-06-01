/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Angoly Camila Araujo Mayen 9959-24-17623
 * Created: 1/06/2026
 */

USE `sig`;

CREATE TABLE `carreras` (
  `codigo_carrera` varchar(5) NOT NULL,
  `nombre_carrera` varchar(45) NOT NULL,
  `codigo_facultad` varchar(5) DEFAULT NULL,
  `estatus_carrera` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`codigo_carrera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

