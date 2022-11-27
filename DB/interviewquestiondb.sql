-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema interviewquestiondb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `interviewquestiondb` ;

-- -----------------------------------------------------
-- Schema interviewquestiondb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `interviewquestiondb` DEFAULT CHARACTER SET utf8 ;
USE `interviewquestiondb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL,
  `street` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` VARCHAR(45) NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(45) BINARY NULL,
  `about_me` TEXT NULL,
  `date_created` DATETIME NULL,
  `address_id` INT NULL,
  `avatar_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question` ;

CREATE TABLE IF NOT EXISTS `question` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `date_created` DATETIME NULL,
  `date_updated` DATETIME NULL,
  `question` TEXT NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_questions_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_questions_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answer` ;

CREATE TABLE IF NOT EXISTS `answer` (
  `id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `question_id` INT NOT NULL,
  `answer` TEXT NOT NULL,
  `date_created` DATETIME NULL,
  `date_updated` DATETIME NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answers_user1_idx` (`user_id` ASC),
  INDEX `fk_answers_questions1_idx` (`question_id` ASC),
  CONSTRAINT `fk_answers_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `industry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `industry` ;

CREATE TABLE IF NOT EXISTS `industry` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company` ;

CREATE TABLE IF NOT EXISTS `company` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `logo_url` VARCHAR(1000) NULL,
  `enabled` TINYINT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_company_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_company_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_opening`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_opening` ;

CREATE TABLE IF NOT EXISTS `job_opening` (
  `id` INT NOT NULL,
  `company_id` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `url_post` VARCHAR(100) NULL,
  `enabled` TINYINT NOT NULL,
  `address_id` INT NOT NULL,
  `role_filled` TINYINT NULL,
  `posted` DATETIME NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_opening_company1_idx` (`company_id` ASC),
  INDEX `fk_job_opening_address1_idx` (`address_id` ASC),
  INDEX `fk_job_opening_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_job_opening_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_opening_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_opening_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_has_question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company_has_question` ;

CREATE TABLE IF NOT EXISTS `company_has_question` (
  `company_id` INT NOT NULL,
  `question_id` INT NOT NULL,
  PRIMARY KEY (`company_id`, `question_id`),
  INDEX `fk_company_has_questions_questions1_idx` (`question_id` ASC),
  INDEX `fk_company_has_questions_company1_idx` (`company_id` ASC),
  CONSTRAINT `fk_company_has_questions_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_has_questions_questions1`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question_has_category` ;

CREATE TABLE IF NOT EXISTS `question_has_category` (
  `question_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`question_id`, `category_id`),
  INDEX `fk_questions_has_category_category1_idx` (`category_id` ASC),
  INDEX `fk_questions_has_category_questions1_idx` (`question_id` ASC),
  CONSTRAINT `fk_questions_has_category_questions1`
    FOREIGN KEY (`question_id`)
    REFERENCES `question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_questions_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `industry_has_company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `industry_has_company` ;

CREATE TABLE IF NOT EXISTS `industry_has_company` (
  `industry_id` INT NOT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`industry_id`, `company_id`),
  INDEX `fk_industry_has_company_company1_idx` (`company_id` ASC),
  INDEX `fk_industry_has_company_industry1_idx` (`industry_id` ASC),
  CONSTRAINT `fk_industry_has_company_industry1`
    FOREIGN KEY (`industry_id`)
    REFERENCES `industry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_industry_has_company_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `answer_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answer_rating` ;

CREATE TABLE IF NOT EXISTS `answer_rating` (
  `answer_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `upvote` TINYINT NULL,
  `rating_date` DATETIME NULL,
  PRIMARY KEY (`answer_id`, `user_id`),
  INDEX `fk_answer_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_answer_has_user_answer1_idx` (`answer_id` ASC),
  CONSTRAINT `fk_answer_has_user_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `answer_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `answer_comment` ;

CREATE TABLE IF NOT EXISTS `answer_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_date` DATETIME NULL,
  `comment_text` TEXT NULL,
  `user_id` INT NOT NULL,
  `answer_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answer_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_answer_comment_answer1_idx` (`answer_id` ASC),
  INDEX `fk_answer_comment_answer_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_answer_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_comment_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `answer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_comment_answer_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `answer_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS interviewuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'interviewuser'@'localhost' IDENTIFIED BY 'interviewuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'interviewuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (1, '4245 Smoky Hill ', NULL, 'Denver', 'Colorado', '80015', 1);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (2, '90212 East West Drive', NULL, 'Pueblo', 'Colorado', '80001', 1);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (3, '8923 Blood Meridian ave ', NULL, 'Westminister', 'Colorado', '72569', 1);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (4, '98321 Old Man & the Sea Drive', NULL, 'Thornton', 'Colorado', '78321', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (1, 'admin', 'admin', 1, 'ADMIN', 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'admin@gmail.com', 'I\'m an Admin', NULL, 1, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (2, 'user', 'user', 1, 'USER', 'user', 'user', 'user@gmail.com', 'I\'m a User', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (3, 'Ethan', 'Lauzon', 1, 'ADMIN', 'ethanlauzon', 'wombat1', 'ethanlauzon@gmail.com', 'SD Graduate', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (4, 'Josh', 'Ingram', 1, 'ADMIN', 'joshingram', 'wombat1', 'joshingram@gmail.com', 'SD Graduate', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (5, 'Celicia', 'Slafter', 1, 'ADMIN', 'celiciaslafter', 'wombat1', 'celiciaslafter@gmail.com', 'SD Graduate', NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (6, 'Steve', 'Dave', 1, 'USER', 'stevedave', 'wombat1', 'stevedave@gmail.com', 'Construction Worker', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `question`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (1, 1, NULL, NULL, 'How do I start my apprenticeship?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (2, 3, NULL, NULL, 'Is Java \"pass by reference\" or \"pass by value\"?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (3, 4, NULL, NULL, 'Name and describe the four pillars of OOP', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (4, 5, NULL, NULL, 'Describe various data structures (stacks, queues, maps, linked list)', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (5, 6, NULL, NULL, 'why are you interested in this company?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (6, 3, NULL, NULL, 'what\'s a weakness of yours?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (7, 4, NULL, NULL, 'what questions do you have for the company?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (8, 5, NULL, NULL, 'What happens when you hit enter on your browser?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (9, 6, NULL, NULL, 'Define \"Referential Integrity\"', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `answer` (`id`, `user_id`, `question_id`, `answer`, `date_created`, `date_updated`, `enabled`) VALUES (1, 2, 1, 'You can start a tech school or get hired by an electrical company', NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `industry`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (1, 'Construction', 1);
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (2, 'Manufacturing', 1);
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (3, 'Tech', 1);
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (4, 'blah', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `company`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (1, 'CEC', 'Electrical company ', NULL, 1, 1);
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (2, 'Weyland-Yutani Corporation', 'Building better worlds', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Favp.fandom.com%2Fwiki%2FWeyland-Yutani_Corporation&psig=AOvVaw0T51L50kjsTKwvtoEhRQVT&ust=1669581733593000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCNCDlePazPsCFQAAAAAdAAAAABAE', 1, 2);
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (3, 'Omni Consumer Products', 'Global Mega-Corporation', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.crunchbase.com%2Forganization%2Fomni-consumer-products&psig=AOvVaw0Mz2tC5EWpvAHmMHPly5UI&ust=1669581781991000&source=images&cd=vfe&ved=0CA4QjRxqFwoTCPjJ0PnazPsCFQAAAAAdAAAAABAE', 1, 3);
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (4, 'SIlver Shamrock Novelties', 'A factory that builds masks', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fvillains.fandom.com%2Fwiki%2FSilver_Shamrock&psig=AOvVaw1FkbhmZz1sN3KYhJKmCAUh&ust=1669582046846000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCKjAyPjbzPsCFQAAAAAdAAAAABAE', 1, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (1, 'Electrical', 'Commerical, Residental, Industrial', 1);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (2, 'Tech', 'Programming and Security', 1);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (3, 'Manufacturing', 'Making of Items', 1);
INSERT INTO `category` (`id`, `name`, `description`, `enabled`) VALUES (4, 'General', 'General questions', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_opening`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `job_opening` (`id`, `company_id`, `description`, `url_post`, `enabled`, `address_id`, `role_filled`, `posted`, `user_id`) VALUES (1, 1, 'Hiring Electricians', NULL, 1, 1, NULL, NULL, 1);
INSERT INTO `job_opening` (`id`, `company_id`, `description`, `url_post`, `enabled`, `address_id`, `role_filled`, `posted`, `user_id`) VALUES (2, 4, 'Hiring Mask Makers', NULL, 1, 4, 1, NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `company_has_question`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `question_has_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `industry_has_company`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (1, 1);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (2, 3);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (2, 4);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 2, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `answer_comment` (`id`, `comment_date`, `comment_text`, `user_id`, `answer_id`, `in_reply_to_id`) VALUES (1, NULL, 'thanks!', 1, 1, 1);

COMMIT;

