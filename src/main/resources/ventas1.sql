-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ventas1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ventas1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ventas1` DEFAULT CHARACTER SET utf8mb3 ;
USE `ventas1` ;

-- -----------------------------------------------------
-- Table `ventas1`.`comercial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas1`.`comercial` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido1` VARCHAR(100) NOT NULL,
  `apellido2` VARCHAR(100) NULL,
  `comision` FLOAT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas1`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas1`.`cliente` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido1` VARCHAR(100) NOT NULL,
  `apellido2` VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  `ciudad` VARCHAR(100) NULL,
  `categoria` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas1`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas1`.`pedido` (
  `id` INT NOT NULL,
  `total` DOUBLE NOT NULL,
  `fecha` DATE NULL,
  `comercial_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`, `comercial_id`, `cliente_id`),
  INDEX `fk_pedido_comercial_idx` (`comercial_id` ASC) VISIBLE,
  INDEX `fk_pedido_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_comercial`
    FOREIGN KEY (`comercial_id`)
    REFERENCES `ventas1`.`comercial` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `ventas1`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas1`.`comercial_has_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas1`.`comercial_has_cliente` (
  `comercial_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  `fecha_asociacion` DATE NULL,
  `prioridad` INT NULL,
  PRIMARY KEY (`comercial_id`, `cliente_id`),
  INDEX `fk_comercial_has_cliente_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_comercial_has_cliente_comercial1_idx` (`comercial_id` ASC) VISIBLE,
  CONSTRAINT `fk_comercial_has_cliente_comercial1`
    FOREIGN KEY (`comercial_id`)
    REFERENCES `ventas1`.`comercial` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comercial_has_cliente_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `ventas1`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
