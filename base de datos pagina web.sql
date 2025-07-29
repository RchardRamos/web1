/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 11.2.0-MariaDB : Database - bebidas_artesanales
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bebidas_artesanales` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `bebidas_artesanales`;

/*Table structure for table `compras` */

DROP TABLE IF EXISTS `compras`;

CREATE TABLE `compras` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total` double NOT NULL CHECK (`total` >= 0),
  `fecha_compra` datetime NOT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  `total_coniva` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_compras_usuario` (`usuario_id`),
  CONSTRAINT `fk_compras_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `compras` */

/*Table structure for table `detalle_compra` */

DROP TABLE IF EXISTS `detalle_compra`;

CREATE TABLE `detalle_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL CHECK (`cantidad` >= 1),
  `precio_unitario` double NOT NULL CHECK (`precio_unitario` > 0),
  `compra_id` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detalle_compra_compra` (`compra_id`),
  KEY `fk_detalle_compra_producto` (`producto_id`),
  CONSTRAINT `fk_detalle_compra_compra` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_detalle_compra_producto` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `detalle_compra` */

/*Table structure for table `productos` */

DROP TABLE IF EXISTS `productos`;

CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `precio` double NOT NULL CHECK (`precio` > 0),
  `stock` int(11) NOT NULL CHECK (`stock` >= 0),
  `tipo` varchar(50) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `productos` */

insert  into `productos`(`id`,`nombre`,`descripcion`,`precio`,`stock`,`tipo`,`imagen`) values (1,'Cerveza Quitu','Cerveza artesanal con sabores andinos',13,60000,'Cerveza','img/cerveza.jpg'),(2,'Mistela de Guayusa','Bebida artesanal con guayusa y licor',12,50000,'Mistela','img/mistela.jpg'),(3,'Chicha de Oro','Chicha fermentada con maíz y frutas',10,60000,'Chicha de Oro','img/chicha.jpg'),(4,'Caña Serrana','Ron ecuatoriano destilado artesanalmente',15,50000,'Ron','img/ron.jpg'),(5,'PURO','Alcohol destilado de Caña',5,0,'PURO','');

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `roles` */

insert  into `roles`(`id`,`nombre`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `usuarios` */

insert  into `usuarios`(`id`,`username`,`password`,`email`,`nombre_completo`) values (3,'ricardo123','$2a$10$oWKW/ZshNUddVNNzYOGvReUZ6RXc0Bz8bWgNfZOwloUpR6XtYj2My','ricardonuevo@email.com',NULL),(4,'ricardo1234','$2a$10$pUq0d5tr5RaauoQMNOZEU.NBlc4IAoNtLQXFx7RvyB0LaIsET2jSS','richard@mail.com',NULL),(5,'Pedro Veloz','$2a$10$PwbfecDG8.phsc8OeOqDp.VbKqvTH3yxb3rb7uN/99R3FQPe/g8XS','pedro@mail.com',NULL);

/*Table structure for table `usuarios_roles` */

DROP TABLE IF EXISTS `usuarios_roles`;

CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `fk_usuarios_roles_rol` (`rol_id`),
  CONSTRAINT `fk_usuarios_roles_rol` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_usuarios_roles_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `usuarios_roles` */

insert  into `usuarios_roles`(`usuario_id`,`rol_id`) values (3,1),(4,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
