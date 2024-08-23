CREATE TABLE corragil (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nomecompleto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(15) COLLATE latin1_spanish_ci DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY `UK50f9s5rbq942dncbo28j0rsr7` (`email`)
);
