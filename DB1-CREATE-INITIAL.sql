BEGIN;

-- Tabela de usuários
CREATE TABLE tb_user(
    id_user                 BIGSERIAL PRIMARY KEY,
    email_user              VARCHAR NOT NULL UNIQUE,
    nm_user                 VARCHAR NOT NULL,
    lastname_user           VARCHAR,
    password                VARCHAR NOT NULL,
    ds_gender               CHAR(1) NOT NULL DEFAULT 'M',
    dt_birthday             DATE NOT NULL,
    ds_about_txt            VARCHAR,
    ck_work_status          BOOLEAN DEFAULT false,
    ds_work_job             VARCHAR,
    id_gp_user              INT NOT NULL DEFAULT 1,
    cd_status_acc           VARCHAR DEFAULT 'ACTIVE',
    dt_register             DATE NOT NULL DEFAULT NOW(),
    is_online               BOOLEAN DEFAULT TRUE
);  

-- Tabela de pendencias de Reset de Senha de Usuários
CREATE TABLE tb_user_reset_password(
    id_reset_pass           BIGSERIAL PRIMARY KEY,
    id_user                 BIGINT NOT NULL,
    dt_solicited            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    url_to_reset            VARCHAR NOT NULL,
    is_restarted            BOOLEAN DEFAULT FALSE
);  

-- Tabela de grupo de usuário (Usuário Comum ou Administrador)
CREATE TABLE tb_user_group(
    id_user_gp              SERIAL PRIMARY KEY,
    ds_user_gp              VARCHAR
);
-- Grupos iniciais: Comum, VIP e Administrador
INSERT INTO tb_user_group(ds_user_gp) VALUES ('COMMOM'), ('VIP'), ('ADMIN');

-- Tabela de Fotos dos usuários
CREATE TABLE tb_user_picture(
    id_user_picture         BIGSERIAL PRIMARY KEY,
    id_user                 BIGINT NOT NULL,
    is_main_picture         BOOLEAN DEFAULT FALSE,
    dt_upload               DATE NOT NULL DEFAULT NOW(),
    url_picture             VARCHAR NOT NULL
);

-- Tabela de Localização de Usuários
CREATE TABLE tb_user_location(
    id_user                 BIGINT NOT NULL PRIMARY KEY,
    road                    VARCHAR,
    residential             VARCHAR,          
    city_district           VARCHAR,           
    city                    VARCHAR,      
    county                  VARCHAR, 
    state_district          VARCHAR,     
    state                   VARCHAR,      
    postcode                VARCHAR,         
    country                 VARCHAR,        
    country_cd              VARCHAR,         
    region                  VARCHAR,     
    lat_nu                  VARCHAR,        
    lng_nu                  VARCHAR
);
  
-- Tabela de Mensagens de Usuários
CREATE TABLE tb_user_message(
    id_message              BIGSERIAL PRIMARY KEY,
    id_user_sender          BIGINT NOT NULL,
    id_user_receiver        BIGINT NOT NULL,
    txt_message             VARCHAR NOT NULL,
    dt_message              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    read_message            BOOLEAN DEFAULT FALSE
);

-- Tabela de Razões de Denúncias
CREATE TABLE tb_reported_reason(
    id_reported_reason      INT PRIMARY KEY,
    ds_reported_reason      VARCHAR NOT NULL
);

-- Tabela de Denúncias de Usuários
CREATE TABLE tb_user_reported(
    id_report               BIGSERIAL PRIMARY KEY,
    id_user                 BIGINT NOT NULL,
    id_user_reported        BIGINT NOT NULL,
    id_reported_reason      INT NOT NULL,
    dt_report               TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    txt_message_report      VARCHAR
);

-- Tabela de Fotos dos Eventos
CREATE TABLE tb_event_picture(
    id_event_picture        BIGSERIAL PRIMARY KEY,
    id_event                BIGINT NOT NULL,
    is_main_picture         BOOLEAN DEFAULT FALSE,
    dt_upload               DATE NOT NULL DEFAULT NOW(),
    url_picture             VARCHAR NOT NULL
);

-- Tabela dos Eventos
CREATE TABLE tb_event(
    id_event                BIGSERIAL PRIMARY KEY,
    id_picture_event        BIGINT NOT NULL,
    title_event             VARCHAR NOT NULL,
    dt_hr_event             TIMESTAMP NOT NULL,
    ds_event                VARCHAR,
    id_owner_user           BIGINT NOT NULL,
    ds_status_event         VARCHAR NOT NULL DEFAULT 'OPEN'
);

-- Tabela dos Participantes de Eventos
CREATE TABLE tb_event_participant(
    id_event                BIGINT PRIMARY KEY,
    id_user                 BIGINT NOT NULL,
    dt_hr_selected          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMIT;

-- TESTES
-- INSERT INTO tb_user VALUES(1, 'felipea@gmail.com', 'Felipe A', 'AAAA', 'AAAAA', 'M', '19950612'::date, 'Nenhuma', true, 'Nenhuma', 1, 'ACTIVE', '20211212'::date, false);

-- SELECT * FROM tb_user
