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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company` ;

CREATE TABLE IF NOT EXISTS `company` (
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `enabled` TINYINT NOT NULL,
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
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (3, '200 E Colfax Ave', NULL, 'Denver', 'Colorado', '80203', 1);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (4, '1340 Pennsylvania St', NULL, 'Denver', 'Colorado', '80203', 1);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (5, 'Bowsers Castle Drive', NULL, 'Koopa Kingdom', 'Antarctica', '65234', 1);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `enabled`) VALUES (6, 'Mushroom Kingdom Drive', NULL, 'Toad Town', 'Mushroom Kingdom', '66352', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (1, 'Mario', 'Mario', 1, 'ADMIN', 'mariomario', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'MarioMario@gmail.com', 'It\'s a-me, Mario!', '1983-04-04 12:00:00', 1, 'http://img3.wikia.nocookie.net/__cb20131025223058/fantendo/images/2/25/Mario_Artwork_-_Super_Mario_3D_World.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (2, 'King', 'Bowser', 1, 'USER', 'kingbowser', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'KingBowser@gmail.com', 'Hear this! I will kidnap Peach OVER and OVER until I pull it off! And no one can stop me! Losing is not an option! And neither is giving up!', '1983-04-04 12:00:00', 5, 'https://styles.redditmedia.com/t5_2r3bv/styles/communityIcon_h3gz0gscg4o71.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (3, 'Princess', 'Peach', 1, 'USER', 'princesspeach', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'princesspeach@gmail.com', 'Princess of the Mushroom Kingdom', '1983-04-04 12:00:00', NULL, 'https://static-new.miraheze.org/greatcharacterswiki/thumb/e/e2/Peach.png/290px-Peach.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (4, 'Dry', 'Bones', 1, 'USER', 'drybones', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'drybones@gmail.com', 'My bones are so dry', '1983-04-04 12:00:00', NULL, 'https://oyster.ignimgs.com/mediawiki/apis.ign.com/new-super-mario-bros-u/2/23/Dry-Bones-nintendo-villains-9411346-600-600.jpeg');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (5, 'Lugi', 'Mario', 1, 'USER', 'lugimario', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'lugimario@gmail.com', 'Brother of Mario', '1983-04-04 12:00:00', NULL, 'https://pbs.twimg.com/media/EegRMg2XgAE85VH?format=jpg&name=large');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (6, 'Toad', NULL, 1, 'USER', 'toad', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 'toad@gmail.com', 'Attendant of Princess Peach', '1983-04-04 12:00:00', NULL, 'https://www.nicepng.com/png/detail/967-9670418_toad-mario-clipart-toad-mario-bros-super-mario.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (7, 'Toadette', NULL, 1, 'USER', 'toadette', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, '1983-04-04 12:00:00', NULL, 'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/0e738c17-7f3c-422e-8225-f8c782b08626/deg7wm3-a463e506-f80a-4095-bf0f-ba34ba6629a0.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzBlNzM4YzE3LTdmM2MtNDIyZS04MjI1LWY4Yzc4MmIwODYyNlwvZGVnN3dtMy1hNDYzZTUwNi1mODBhLTQwOTUtYmYwZi1iYTM0YmE2NjI5YTAucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.Kv3KDyG4P3bibJZRAmWqEHkn_SnoD2-n_RNVcSMIl_U');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (8, 'Yoshi', NULL, 1, 'USER', 'yoshi', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, '1983-04-04 12:00:00', NULL, 'https://e1.pngegg.com/pngimages/649/608/png-clipart-3d-yoshi-yoshi-3d-illustration.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (9, 'Wario', NULL, 1, 'USER', 'wario', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, '1983-04-04 12:00:00', NULL, 'https://i.redd.it/p7s4igeq58291.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (10, 'Shyguy', NULL, 1, 'USER', 'shyguy', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, '1983-04-04 12:00:00', NULL, 'https://www.clipartmax.com/png/middle/106-1061323_mario-kart-shy-guy.png');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (11, 'admin', 'admin', 1, 'ADMIN', 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', NULL, NULL, '1983-04-04 12:00:00', NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `enabled`, `role`, `username`, `password`, `email`, `about_me`, `date_created`, `address_id`, `avatar_url`) VALUES (12, 'user', 'user', 1, 'USER', 'user', '$2a$10$dwkFb8sHZGCHkGmu5kE.qOu.3.WB/LsLtiSfgtZWFraABnyWN/VWq', NULL, NULL, '1983-04-04 12:00:00', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `question`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (1, 1, '2022-11-30 08:11:9', NULL, 'How do I start my apprenticeship?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (2, 2, '2022-11-30 08:11:9', NULL, 'Is Java \"pass by reference\" or \"pass by value\"?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (3, 3, '2022-11-30 08:11:9', NULL, 'Name and describe the four pillars of OOP', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (4, 4, '2022-11-30 08:11:9', NULL, 'Describe various data structures (stacks, queues, maps, linked list)', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (5, 5, '2022-11-30 08:11:9', NULL, 'why are you interested in this company?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (6, 6, '2022-11-30 08:11:9', NULL, 'what\'s a weakness of yours?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (7, 7, '2022-11-30 08:11:9', NULL, 'what questions do you have for the company?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (8, 8, '2022-11-30 08:11:9', NULL, 'What happens when you hit enter on your browser?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (9, 9, '2022-11-30 08:11:9', NULL, 'Define \"Referential Integrity\"', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (10, 10, '2022-11-30 08:11:9', NULL, 'What is encapsulation', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (11, 1, '2022-11-30 08:11:9', NULL, 'Explain the difference between Public and Private access modifiers', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (12, 2, '2022-11-30 08:11:9', NULL, 'What are Constructors in Java?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (13, 3, '2022-11-30 08:11:9', NULL, 'What are access modifiers in Java', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (14, 4, '2022-11-30 08:11:9', NULL, 'What is the difference between a local variable and an instance variable?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (15, 5, '2022-11-30 08:11:9', NULL, 'What is the difference between break and continue statements?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (16, 6, '2022-11-30 08:11:9', NULL, 'What is inheritance in Java', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (17, 7, '2022-11-30 08:11:9', NULL, 'Can you overrive a private or static method in Java?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (18, 8, '2022-11-30 08:11:9', NULL, 'Define Wrapper Classes in Java.', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (19, 9, '2022-11-30 08:11:9', NULL, 'What is the final keyword in Java?', 1);
INSERT INTO `question` (`id`, `user_id`, `date_created`, `date_updated`, `question`, `enabled`) VALUES (20, 10, '2022-11-30 08:11:9', NULL, 'Explain ‘this’ keyword in Java.', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `answer` (`id`, `user_id`, `question_id`, `answer`, `date_created`, `date_updated`, `enabled`) VALUES (1, 2, 1, 'You can start a tech school or get hired by an electrical company', '2022-12-01 14:30:9', NULL, 1);
INSERT INTO `answer` (`id`, `user_id`, `question_id`, `answer`, `date_created`, `date_updated`, `enabled`) VALUES (2, 3, 7, 'Prior to your interview make sure you do some research on the company you are applying to and write down some questions you have for them. It shows your interest in the job and company.', '2022-12-01 14:30:9', NULL, 1);
INSERT INTO `answer` (`id`, `user_id`, `question_id`, `answer`, `date_created`, `date_updated`, `enabled`) VALUES (3, 5, 3, 'Abstraction, Polymorphism, Inheritance, Encapsulation. APIE is a good way to remember it by.', '2022-12-02 16:00:00', NULL, 1);
INSERT INTO `answer` (`id`, `user_id`, `question_id`, `answer`, `date_created`, `date_updated`, `enabled`) VALUES (4, 2, 3, 'Pillar one is kidnapping Princess Peach and Pillar two is defeating Mario. Their is no third or fourth pillar', '2022-12-02 16:30:12', NULL, 1);
INSERT INTO `answer` (`id`, `user_id`, `question_id`, `answer`, `date_created`, `date_updated`, `enabled`) VALUES (5, 6, 3, 'All I know is that APIE\'s letters stand for something but I\'m not quite sure :/', '2022-12-01 14:30:9', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `industry`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (1, 'Construction', 1);
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (2, 'Manufacturing', 1);
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (3, 'Electrical Engineering', 1);
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (4, 'Utilities', 1);
INSERT INTO `industry` (`id`, `name`, `enabled`) VALUES (5, 'Programming', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `company`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (1, 'Mario Brothers Plumbing', 'Construction Company', 'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/f35fb770-7717-41ec-a9d6-0f1d73aba11a/d6u90ok-4c950a64-bd75-4c3f-9b5f-b2f160c4887f.png/v1/fill/w_1024,h_768,q_80,strp/mario_bros__plumbing_by_blazbaros_d6u90ok-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NzY4IiwicGF0aCI6IlwvZlwvZjM1ZmI3NzAtNzcxNy00MWVjLWE5ZDYtMGYxZDczYWJhMTFhXC9kNnU5MG9rLTRjOTUwYTY0LWJkNzUtNGMzZi05YjVmLWIyZjE2MGM0ODg3Zi5wbmciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.eBSyAKIBV_W1U--9W1PYPFzXblujRfb6b7OSOVVWfcs', 1, 1);
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (2, 'Diamond Software', 'Building better worlds', 'https://cdn.shopify.com/s/files/1/2703/8128/files/ezgif-frame-001_780x.jpg?v=1661265317', 1, 2);
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (3, 'Mario Toy Company', 'Global Mega-Corporation', 'https://www.godisageek.com/wp-content/uploads/mario-feature.jpg', 1, 3);
INSERT INTO `company` (`id`, `name`, `description`, `logo_url`, `enabled`, `address_id`) VALUES (4, 'WarioWare, Inc.', 'Game Developer', 'https://nerdbacon.com/wp-content/uploads/2016/01/Screen-Shot-2016-01-25-at-11.01.59-AM-542x300.png', 1, 4);

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
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (1, 5);
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (2, 5);
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (3, 5);
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (4, 5);
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (2, 8);
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (3, 7);
INSERT INTO `company_has_question` (`company_id`, `question_id`) VALUES (2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `question_has_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (1, 1);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (7, 4);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (3, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (4, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (5, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (6, 4);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (8, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (9, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (10, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (11, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (12, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (13, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (14, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (15, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (16, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (17, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (18, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (19, 2);
INSERT INTO `question_has_category` (`question_id`, `category_id`) VALUES (20, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `industry_has_company`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (1, 1);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (2, 2);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (3, 3);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (4, 3);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (5, 4);
INSERT INTO `industry_has_company` (`industry_id`, `company_id`) VALUES (1, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 3, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 6, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 4, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 5, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 7, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 8, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (1, 9, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 1, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 4, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 5, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 6, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 7, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 8, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 9, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 10, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 11, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (4, 12, 0, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (3, 1, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (3, 3, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (3, 4, 1, NULL);
INSERT INTO `answer_rating` (`answer_id`, `user_id`, `upvote`, `rating_date`) VALUES (5, 1, 0, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `answer_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `interviewquestiondb`;
INSERT INTO `answer_comment` (`id`, `comment_date`, `comment_text`, `user_id`, `answer_id`, `in_reply_to_id`, `enabled`) VALUES (1, '2022-12-03 18:25:16', 'thanks!', 1, 1, 1, 1);
INSERT INTO `answer_comment` (`id`, `comment_date`, `comment_text`, `user_id`, `answer_id`, `in_reply_to_id`, `enabled`) VALUES (2, '2022-12-03 18:25:16', 'Close but not quite.', 8, 5, 2, 1);
INSERT INTO `answer_comment` (`id`, `comment_date`, `comment_text`, `user_id`, `answer_id`, `in_reply_to_id`, `enabled`) VALUES (3, '2022-12-03 23:40:16', 'Almost', 9, 5, 2, 1);

COMMIT;

