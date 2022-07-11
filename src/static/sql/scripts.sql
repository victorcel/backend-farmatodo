CREATE TABLE `db-farmatodo`.episode
(
    id   INT          NOT NULL,
    name VARCHAR(250) NOT NULL,
    CONSTRAINT pk_episode PRIMARY KEY (id)
);

CREATE TABLE `db-farmatodo`.episode_character
(
    character_id INT NOT NULL,
    episode_id   INT NOT NULL,
    CONSTRAINT pk_episode_character PRIMARY KEY (character_id, episode_id)
);

ALTER TABLE `db-farmatodo`.episode_character
    ADD CONSTRAINT fk_epicha_on_character FOREIGN KEY (character_id) REFERENCES `db-farmatodo`.`character` (id);

ALTER TABLE `db-farmatodo`.episode_character
    ADD CONSTRAINT fk_epicha_on_episode FOREIGN KEY (episode_id) REFERENCES `db-farmatodo`.episode (id);

CREATE TABLE `db-farmatodo`.location
(
    id        INT          NOT NULL,
    name      VARCHAR(250) NOT NULL,
    type      VARCHAR(250) NOT NULL,
    dimension VARCHAR(250) NOT NULL,
    CONSTRAINT pk_location PRIMARY KEY (id)
);

CREATE TABLE `db-farmatodo`.`character`
(
    id          INT          NOT NULL,
    name        VARCHAR(250) NOT NULL,
    species     VARCHAR(250) NOT NULL,
    gender      VARCHAR(250) NOT NULL,
    image       VARCHAR(250) NOT NULL,
    location_id INT          NULL,
    CONSTRAINT pk_character PRIMARY KEY (id)
);

ALTER TABLE `db-farmatodo`.`character`
    ADD CONSTRAINT FK_CHARACTER_ON_LOCATION FOREIGN KEY (location_id) REFERENCES `db-farmatodo`.location (id);