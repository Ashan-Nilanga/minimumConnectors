-- -----------------------------------------------------
-- Schema shortest_path
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MST` DEFAULT CHARACTER SET utf8 ;
USE `MST` ;

-- -----------------------------------------------------
-- Table `shortest_path`.`winner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MST`.`winner` (
  `winner_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `root` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`winner_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shortest_path`.`input_shortest_path`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MST`.`connections` (
  `winner_id` INT NOT NULL,
  `total_minimum_distance` INT NOT NULL,
  `link_1` VARCHAR(45) NOT NULL,
  `link_2` VARCHAR(45) NOT NULL,
  `link_3` VARCHAR(45) NOT NULL,
  `link_4` VARCHAR(45) NOT NULL,
  `link_5` VARCHAR(45) NOT NULL,
  `link_6` VARCHAR(45) NOT NULL,
  `link_7` VARCHAR(45) NOT NULL,
  `link_8` VARCHAR(45) NOT NULL,
  `link_9` VARCHAR(45) NOT NULL,
    FOREIGN KEY (`winner_id`)
    REFERENCES `MST`.`winner` (`winner_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shortest_path`.`distance_between_cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MST`.`distance_between_cities` (
  `winner_id` INT NOT NULL,
  `ab` INT NOT NULL,
  `ac` INT NOT NULL,
  `ad` INT NOT NULL,
  `ae` INT NOT NULL,
  `af` INT NOT NULL,
  `ag` INT NOT NULL,
  `ah` INT NOT NULL,
  `ai` INT NOT NULL,
  `aj` INT NOT NULL,
  `bc` INT NOT NULL,
  `bd` INT NOT NULL,
  `be` INT NOT NULL,
  `bf` INT NOT NULL,
  `bg` INT NOT NULL,
  `bh` INT NOT NULL,
  `bi` INT NOT NULL,
  `bj` INT NOT NULL,
  `cd` INT NOT NULL,
  `ce` INT NOT NULL,
  `cf` INT NOT NULL,
  `cg` INT NOT NULL,
  `ch` INT NOT NULL,
  `ci` INT NOT NULL,
  `cj` INT NOT NULL,
  `de` INT NOT NULL,
  `df` INT NOT NULL,
  `dg` INT NOT NULL,
  `dh` INT NOT NULL,
  `di` INT NOT NULL,
  `dj` INT NOT NULL,
  `ef` INT NOT NULL,
  `eg` INT NOT NULL,
  `eh` INT NOT NULL,
  `ei` INT NOT NULL,
  `ej` INT NOT NULL,
  `fg` INT NOT NULL,
  `fh` INT NOT NULL,
  `fi` INT NOT NULL,
  `fj` INT NOT NULL,
  `gh` INT NOT NULL,
  `gi` INT NOT NULL,
  `gj` INT NOT NULL,
  `hi` INT NOT NULL,
  `hj` INT NOT NULL,
  `ij` INT NOT NULL,
    FOREIGN KEY (`winner_id`)
    REFERENCES `shortest_path`.`winner` (`winner_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

