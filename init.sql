CREATE TABLE news_type_entity
(
    type_id    BIGINT       NOT NULL,
    type_name  VARCHAR(255) NULL,
    type_color VARCHAR(255) NULL,
    CONSTRAINT pk_newstypeentity PRIMARY KEY (type_id)
);

CREATE TABLE news_entity
(
    news_id              BIGINT       NOT NULL,
    news_name            VARCHAR(255) NULL,
    short_description    VARCHAR(255) NULL,
    full_description     VARCHAR(255) NULL,
    news_type_id_type_id BIGINT       NULL,
    CONSTRAINT pk_newsentity PRIMARY KEY (news_id)
);

ALTER TABLE news_entity
    ADD CONSTRAINT FK_NEWSENTITY_ON_NEWSTYPEID_TYPEID FOREIGN KEY (news_type_id_type_id) REFERENCES news_type_entity (type_id);