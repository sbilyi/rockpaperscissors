CREATE TABLE IF NOT EXISTS `rounds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_move` varchar(255) NOT NULL,
  `system_move` varchar(255) NOT NULL,
  `winner` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT UC_rounds UNIQUE (`user_move`,`system_move`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) NOT NULL,
  `round_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`round_id`)  REFERENCES `rounds` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `rounds`(`id`, `user_move`, `system_move`, `winner`)
VALUES
(1, 'ROCK', 'ROCK', 'NONE'),
(2, 'PAPER', 'PAPER', 'NONE'),
(3, 'SCISSORS', 'SCISSORS', 'NONE'),

(4, 'PAPER', 'ROCK', 'USER'),
(5, 'SCISSORS', 'PAPER', 'USER'),
(6, 'ROCK', 'SCISSORS', 'USER'),

(7, 'ROCK', 'PAPER', 'SYSTEM'),
(8, 'PAPER', 'SCISSORS', 'SYSTEM'),
(9, 'SCISSORS', 'ROCK', 'SYSTEM')
;