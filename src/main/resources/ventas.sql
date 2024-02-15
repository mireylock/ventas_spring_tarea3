-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ventas
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `ventas` DEFAULT CHARACTER SET utf8mb4 ;
USE `ventas` ;

-- -----------------------------------------------------
-- Table `ventas`.`comercial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas`.`comercial` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `nombre` VARCHAR(100) NOT NULL,
                                                    `apellido1` VARCHAR(100) NOT NULL,
                                                    `apellido2` VARCHAR(100) NULL,
                                                    `comisión` FLOAT NULL,
                                                    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas`.`cliente` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `nombre` VARCHAR(100) NOT NULL,
                                                  `apellido1` VARCHAR(100) NOT NULL,
                                                  `apellido2` VARCHAR(100) NULL,
                                                  `email` VARCHAR(100) NULL,
                                                  `ciudad` VARCHAR(100) NULL,
                                                  `categoría` INT NULL,
                                                  PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas`.`pedido` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `total` DOUBLE NOT NULL,
                                                 `fecha` DATE NULL,
                                                 `id_comercial` INT NOT NULL,
                                                 `id_cliente` INT NOT NULL,
                                                 PRIMARY KEY (`id`),
                                                 INDEX `fk_pedido_id_comercialx` (`id_comercial`),
                                                 INDEX `fk_pedido_cliente1_idx` (`id_cliente`),
                                                 CONSTRAINT `fk_pedido_comercial`
                                                     FOREIGN KEY (`id_comercial`)
                                                         REFERENCES `ventas`.`comercial` (`id`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION,
                                                 CONSTRAINT `fk_pedido_cliente1`
                                                     FOREIGN KEY (`id_cliente`)
                                                         REFERENCES `ventas`.`cliente` (`id`)
                                                         ON DELETE NO ACTION
                                                         ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas`.`cliente_has_comercial`
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `ventas`.`cliente_has_comercial`
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `ventas`.`cliente_has_comercial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ventas`.`cliente_has_comercial` (
                                                                `id_comercial` INT NOT NULL,
                                                                `id_cliente` INT NOT NULL,
                                                                `fecha_asociacion` DATE NULL,
                                                                `prioridad` INT NULL,
                                                                PRIMARY KEY (`id_comercial`, `id_cliente`),
                                                                INDEX `fk_cliente_has_comercial_cliente1_idx` (`id_cliente`),
                                                                INDEX `fk_cliente_has_comercial_comercial1_idx` (`id_comercial`),
                                                                CONSTRAINT `fk_cliente_has_comercial_comercial1`
                                                                    FOREIGN KEY (`id_comercial`)
                                                                        REFERENCES `ventas`.`comercial` (`id`)
                                                                        ON DELETE NO ACTION
                                                                        ON UPDATE NO ACTION,
                                                                CONSTRAINT `fk_cliente_has_comercial_cliente1`
                                                                    FOREIGN KEY (`id_cliente`)
                                                                        REFERENCES `ventas`.`cliente` (`id`)
                                                                        ON DELETE NO ACTION
                                                                        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;




-- Insert some sample data
INSERT INTO `ventas`.`comercial` (`nombre`, `apellido1`, `apellido2`, `comisión`) VALUES
                                                                                  ('John', 'Doe', 'Sr.', 0.05),
                                                                                  ('Jane', 'Smith', 'Ms.', 0.07),
                                                                                ('Mary', 'White', 'Ms.', 0.07),
                                                                                ('Sally', 'Santos', 'Ms.', 0.07);



INSERT INTO `ventas`.`cliente` (`nombre`, `apellido1`, `apellido2`, `email`, `ciudad`, `categoría`) VALUES
                                                                                                        ('Michael', 'Johnson', 'Jr.', 'michael@example.com', 'New York', 1),
                                                                                                        ('Emily', 'Williams', NULL, 'emily@example.com', 'Los Angeles', 2);

INSERT INTO `ventas`.`pedido` (`total`, `fecha`, `id_comercial`, `id_cliente`) VALUES
                                                                                   (100.50, '2024-02-14', 1, 1),
                                                                                   (75.25, '2024-02-13', 2, 2);

INSERT INTO `ventas`.`cliente_has_comercial` (`id_comercial`, `id_cliente`, `fecha_asociacion`, `prioridad`) VALUES
                                                                                                                 (1, 1, '2024-01-01', 1),
                                                                                                                 (2, 2, '2024-01-15', 2);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


# -- MySQL Workbench Forward Engineering
#
# SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
# SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
# SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
#
# -- -----------------------------------------------------
# -- Schema ventas
# -- -----------------------------------------------------
#
# -- -----------------------------------------------------
# -- Schema ventas
# -- -----------------------------------------------------
# CREATE SCHEMA IF NOT EXISTS `ventas` DEFAULT CHARACTER SET utf8mb3 ;
# USE `ventas` ;
#
# -- -----------------------------------------------------
# -- Table `ventas`.`comercial`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `ventas`.`comercial` (
#   `id` INT NOT NULL,
#   `nombre` VARCHAR(100) NOT NULL,
#   `apellido1` VARCHAR(100) NOT NULL,
#   `apellido2` VARCHAR(100) NULL,
#   `comisión` FLOAT NULL,
#   PRIMARY KEY (`id`))
# ENGINE = InnoDB;
#
#
# -- -----------------------------------------------------
# -- Table `ventas`.`cliente`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `ventas`.`cliente` (
#   `id` INT NOT NULL,
#   `nombre` VARCHAR(100) NOT NULL,
#   `apellido1` VARCHAR(100) NOT NULL,
#   `apellido2` VARCHAR(100) NULL,
#   `email` VARCHAR(100) NULL,
#   `ciudad` VARCHAR(100) NULL,
#   `categoría` INT NULL,
#   PRIMARY KEY (`id`))
# ENGINE = InnoDB;
#
#
# -- -----------------------------------------------------
# -- Table `ventas`.`pedido`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `ventas`.`pedido` (
#   `id` INT NOT NULL,
#   `total` DOUBLE NOT NULL,
#   `fecha` DATE NULL,
#   `id_comercial` INT NOT NULL,
#   `id_cliente` INT NOT NULL,
#   PRIMARY KEY (`id`, `id_comercial`, `id_cliente`),
#   INDEX `fk_pedido_id_comercialx` (`id_comercial` ASC) VISIBLE,
#   INDEX `fk_pedido_cliente1_idx` (`id_cliente` ASC) VISIBLE,
#   CONSTRAINT `fk_pedido_comercial`
#     FOREIGN KEY (`id_comercial`)
#     REFERENCES `ventas`.`comercial` (`id`)
#     ON DELETE NO ACTION
#     ON UPDATE NO ACTION,
#   CONSTRAINT `fk_pedido_cliente1`
#     FOREIGN KEY (`id_cliente`)
#     REFERENCES `ventas`.`cliente` (`id`)
#     ON DELETE NO ACTION
#     ON UPDATE NO ACTION)
# ENGINE = InnoDB;
#
#
# -- -----------------------------------------------------
# -- Table `ventas`.`cliente_has_comercial`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `ventas`.`cliente_has_comercial` (
#   `id_comercial` INT NOT NULL,
#   `id_cliente` INT NOT NULL,
#   `fecha_asociacion` DATE NULL,
#   `prioridad` INT NULL,
#   PRIMARY KEY (`id_comercial`, `id_cliente`),
#   INDEX `fk_cliente_has_comercial_cliente1_idx` (`id_cliente` ASC) VISIBLE,
#   INDEX `fk_cliente_has_comercial_comercial1_idx` (`id_comercial` ASC) VISIBLE,
#   CONSTRAINT `fk_cliente_has_comercial_comercial1`
#     FOREIGN KEY (`id_comercial`)
#     REFERENCES `ventas`.`comercial` (`id`)
#     ON DELETE NO ACTION
#     ON UPDATE NO ACTION,
#   CONSTRAINT `fk_cliente_has_comercial_cliente1`
#     FOREIGN KEY (`id_cliente`)
#     REFERENCES `ventas`.`cliente` (`id`)
#     ON DELETE NO ACTION
#     ON UPDATE NO ACTION)
# ENGINE = InnoDB;
#
#
# SET SQL_MODE=@OLD_SQL_MODE;
# SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
# SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
