CREATE TABLE IF NOT EXISTS `corragil` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nomecompleto` varchar(90) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `senha` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK50f9s5rbq942dncbo28j0rsr7` (`email`)
);
