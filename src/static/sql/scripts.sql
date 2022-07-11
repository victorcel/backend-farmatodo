CREATE TABLE `restapi-farmatodo`.episode
(
    id   INT          NOT NULL,
    name VARCHAR(250) NOT NULL,
    CONSTRAINT pk_episode PRIMARY KEY (id)
);

CREATE TABLE `restapi-farmatodo`.`character`
(
    id          INT          NOT NULL,
    name        VARCHAR(250) NOT NULL,
    species     VARCHAR(250) NOT NULL,
    gender      VARCHAR(250) NOT NULL,
    image       VARCHAR(250) NOT NULL,
    location_id INT          NULL,
    CONSTRAINT pk_character PRIMARY KEY (id)
);

CREATE TABLE `restapi-farmatodo`.episode_character
(
    character_id INT NOT NULL,
    episode_id   INT NOT NULL,
    CONSTRAINT pk_episode_character PRIMARY KEY (character_id, episode_id)
);

ALTER TABLE `restapi-farmatodo`.episode_character
    ADD CONSTRAINT fk_epicha_on_character FOREIGN KEY (character_id) REFERENCES `restapi-farmatodo`.`character` (id);

ALTER TABLE `restapi-farmatodo`.episode_character
    ADD CONSTRAINT fk_epicha_on_episode FOREIGN KEY (episode_id) REFERENCES `restapi-farmatodo`.episode (id);

CREATE TABLE `restapi-farmatodo`.location
(
    id        INT          NOT NULL,
    name      VARCHAR(250) NOT NULL,
    type      VARCHAR(250) NOT NULL,
    dimension VARCHAR(250) NOT NULL,
    CONSTRAINT pk_location PRIMARY KEY (id)
);


ALTER TABLE `restapi-farmatodo`.`character`
    ADD CONSTRAINT FK_CHARACTER_ON_LOCATION FOREIGN KEY (location_id) REFERENCES `restapi-farmatodo`.location (id);
