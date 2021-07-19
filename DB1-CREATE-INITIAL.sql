-- Tabela de usuários
CREATE TABLE tb_user(
    id_user                 BIGSERIAL PRIMARY KEY,
    email_uk                VARCHAR NOT NULL UNIQUE,
    nm_user                 VARCHAR NOT NULL,
    lastname_user           VARCHAR,
    password                VARCHAR NOT NULL,
    ds_gender               CHAR(1) NOT NULL DEFAULT 'M',
    dt_birthday             DATE NOT NULL,
    ds_about_txt            VARCHAR,
    ck_work_status          BOOLEAN DEFAULT false,
    ds_work_job             VARCHAR,
    id_gp_user              INT NOT NULL DEFAULT 1,
    cd_status_acc           VARCHAR DEFAULT 'ACTIVE'
)  

-- Tabela de grupo de usuário (Usuário Comum ou Administrador)
CREATE TABLE tb_gp_user(
    id_gp_user              SERIAL PRIMARY KEY,
    ds_go_user              VARCHAR
)

-- Tabela de Fotos dos usuários
CREATE TABLE tb_picture_user(
    id_picture_user         BIGSERIAL PRIMARY KEY,
    id_user                 BIGINT NOT NULL,
    is_main_picture         BOOLEAN DEFAULT false,
    dt_upload               DATE NOT NULL DEFAULT NOW(),
    url_picture             VARCHAR NOT NULL
)