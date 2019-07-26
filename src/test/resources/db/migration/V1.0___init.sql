CREATE TABLE IF NOT EXISTS `games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groups` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rounds` (
  `user_move` varchar(255) NOT NULL,
  `system_move` varchar(255) NOT NULL,
  `winner` varchar(255) NOT NULL,
  PRIMARY KEY (`user_move`, `system_move`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `rounds`(`user_move`, `system_move`, `winner`)
VALUES
('ROCK', 'ROCK', 'NONE'),
('PAPER', 'PAPER', 'NONE'),
('SCISSORS', 'SCISSORS', 'NONE'),

('PAPER', 'ROCK', 'USER'),
('SCISSORS', 'PAPER', 'USER'),
('ROCK', 'SCISSORS', 'USER'),

('ROCK', 'PAPER', 'SYSTEM'),
('PAPER', 'SCISSORS', 'SYSTEM'),
('SCISSORS', 'ROCK', 'SYSTEM')
;