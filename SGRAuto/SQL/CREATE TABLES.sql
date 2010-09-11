
CREATE TABLE estado (
  est_cod  INTEGER      NOT NULL,
  est_desc VARCHAR2(40) UNIQUE NOT NULL,
  est_uf   VARCHAR2(2)  UNIQUE NOT NULL
)
  PCTUSED    0
/

ALTER TABLE estado
  ADD CONSTRAINT pk_est_cod PRIMARY KEY (
    est_cod
  )
/

CREATE TABLE cidade (
  cid_cod  INTEGER      NOT NULL,
  est_cod  INTEGER      NOT NULL,
  cid_desc VARCHAR2(60) UNIQUE NOT NULL
)
  PCTUSED    0
/

ALTER TABLE cidade
  ADD CONSTRAINT pk_cidade PRIMARY KEY (
    cid_cod
  )
/

ALTER TABLE cidade
  ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (
    est_cod
  ) REFERENCES estado (
    est_cod
  )
/

CREATE TABLE marca (
  mar_cod  INTEGER      NOT NULL,
  mar_desc VARCHAR2(30) UNIQUE NOT NULL
)
  PCTUSED    0
/

ALTER TABLE marca
  ADD CONSTRAINT pk_marca PRIMARY KEY (
    mar_cod
  )
/

CREATE TABLE modelo (
  mod_cod  INTEGER       NOT NULL,
  mar_cod  INTEGER       NOT NULL,
  mod_desc VARCHAR2(100) UNIQUE NOT NULL
)
  PCTUSED    0
/

ALTER TABLE modelo
  ADD CONSTRAINT pk_modelo PRIMARY KEY (
    mod_cod
  )
/

ALTER TABLE modelo
  ADD CONSTRAINT fk_modelo_marca FOREIGN KEY (
    mar_cod
  ) REFERENCES marca (
    mar_cod
  )
/


CREATE TABLE empresa (
  emp_cod           INTEGER       NOT NULL,
  emp_cnpj          VARCHAR2(18)  NOT NULL,
  cid_cod           INTEGER       NOT NULL,
  emp_tipo_emp      VARCHAR(50)   NOT NULL,
  emp_razao_social  VARCHAR2(100) NOT NULL,
  emp_nome_fantasia VARCHAR2(80)  NULL,
  emp_end           VARCHAR2(200) NULL,
  emp_bairro        VARCHAR2(60)  NULL,
  emp_cep           VARCHAR2(12)  NULL,
  emp_contato       VARCHAR2(100) NULL,
  emp_tel           VARCHAR2(13)  NULL,
  emp_ind_desat     INTEGER       NOT NULL
)
  PCTUSED    0
/

ALTER TABLE empresa
  ADD CONSTRAINT pk_empresa PRIMARY KEY (
    emp_cod
  )
/

ALTER TABLE empresa
  ADD UNIQUE (
    emp_cnpj
  )
/

ALTER TABLE empresa
  ADD CONSTRAINT fk_empresa_cidade FOREIGN KEY (
    cid_cod
  ) REFERENCES cidade (
    cid_cod
  )
/


CREATE TABLE automovel (
  aut_cod         INTEGER       NOT NULL,
  aut_placa       VARCHAR2(8)   NOT NULL,
  mod_cod         INTEGER       NOT NULL,
  emp_cod         INTEGER       NOT NULL,
  aut_desc        VARCHAR2(40)  NOT NULL,
  aut_chassi      VARCHAR2(100) NULL,
  aut_ano         INTEGER       NOT NULL,
  aut_ano_mod     INTEGER       NOT NULL,
  aut_cor         VARCHAR2(60)  NOT NULL,
  aut_combustivel VARCHAR2(20)  NOT NULL,
  aut_km_rodado   INTEGER       NULL,
  aut_kit_gas     INTEGER       NULL,
  aut_ar_cond     INTEGER       NULL,
  aut_ar_quente   INTEGER       NULL,
  aut_ar_frio     INTEGER       NULL,
  aut_vidro_elet  INTEGER       NULL,
  aut_vidro_verde INTEGER       NULL,
  aut_isulfilm    INTEGER       NULL,
  aut_air_bag     INTEGER       NULL,
  aut_abs         INTEGER       NULL,
  aut_trava_elet  INTEGER       NULL,
  aut_alarme      INTEGER       NULL,
  aut_cd_player   INTEGER       NULL,
  aut_desem_tras  INTEGER       NULL,
  aut_limp_tras   INTEGER       NULL,
  aut_dir_hidr    INTEGER       NULL,
  aut_roda_liga   INTEGER       NULL,
  aut_camb_aut    INTEGER       NULL,
  aut_outros      VARCHAR2(500) NULL,
  aut_valor_ven   NUMBER(8,2)   NOT NULL,
  aut_valor_com   NUMBER(8,2)   NOT NULL,
  aut_dt_ent      DATE          NOT NULL,
  aut_dt_sai      DATE          NULL,
  aut_ind_vendido INTEGER       NOT NULL
)
  PCTUSED    0
/

ALTER TABLE automovel
  ADD CONSTRAINT pk_automovel PRIMARY KEY (
    aut_cod
  )
/

ALTER TABLE automovel
  ADD CONSTRAINT fk_automovel_empresa FOREIGN KEY (
    emp_cod
  ) REFERENCES empresa (
    emp_cod
  )
/

ALTER TABLE automovel
  ADD CONSTRAINT fk_automovel_modelo FOREIGN KEY (
    mod_cod
  ) REFERENCES modelo (
    mod_cod
  )
/

CREATE TABLE automovel_fotos (
  fot_cod    INTEGER NOT NULL,
  aut_cod    INTEGER NOT NULL,
  fot_imagem BLOB    NOT NULL
)
  PCTUSED    0
/

ALTER TABLE automovel_fotos
  ADD CONSTRAINT pk_automovel_fotos PRIMARY KEY (
    fot_cod
  )
/

ALTER TABLE automovel_fotos
  ADD CONSTRAINT fk_automovel_fotos_automovel FOREIGN KEY (
    aut_cod
  ) REFERENCES automovel (
    aut_cod
  )
/

CREATE TABLE pessoa (
  pes_cod         INTEGER       NOT NULL,
  cid_cod         INTEGER       NOT NULL,
  emp_cod         INTEGER   NOT NULL,
  pes_nome        VARCHAR2(100) NOT NULL,
  pes_rg          VARCHAR2(11)  NOT NULL,
  pes_cpf         VARCHAR2(14)  NOT NULL,
  pes_cnh         VARCHAR2(20)  NULL,
  pes_dt_nasc     DATE          NOT NULL,
  pes_sexo        CHAR(1)       NOT NULL,
  pes_cargo       VARCHAR2(60)  NOT NULL,
  pes_remuneracao NUMBER(8,2)   NULL,
  pes_end         VARCHAR2(200) NULL,
  pes_bairro      VARCHAR2(60)  NULL,
  pes_cep         VARCHAR2(12)  NULL,
  pes_fone        VARCHAR2(13)  NOT NULL,
  pes_cel         VARCHAR2(13)  NULL,
  pes_email       VARCHAR2(100) NULL,
  pes_ind_cli     INTEGER       NOT NULL,
  pes_ind_func    INTEGER       NOT NULL,
  pes_dt_entrada  DATE          NULL,
  pes_dt_saida    DATE          NULL,
  pes_login       VARCHAR2(20)  NULL,
  pes_senha       VARCHAR2(250) NULL,
  pes_ind_ger     INTEGER       NOT NULL,
  pes_ind_desat   INTEGER       NOT NULL
)
  PCTUSED    0
/

ALTER TABLE pessoa
  ADD CONSTRAINT pk_pessoa PRIMARY KEY (
    pes_cod
  )
/

ALTER TABLE pessoa
  ADD UNIQUE (
    pes_cnh
  )
/

ALTER TABLE pessoa
  ADD UNIQUE (
    pes_cpf
  )
/

ALTER TABLE pessoa
  ADD UNIQUE (
    pes_login
  )
/

ALTER TABLE pessoa
  ADD UNIQUE (
    pes_rg
  )
/

ALTER TABLE pessoa
  ADD CONSTRAINT fk_pessoa_cidade FOREIGN KEY (
    cid_cod
  ) REFERENCES cidade (
    cid_cod
  )
/

ALTER TABLE pessoa
  ADD CONSTRAINT fk_pessoa_empresa FOREIGN KEY (
    emp_cod
  ) REFERENCES empresa (
    emp_cod
  )
/


CREATE TABLE venda (
  ven_cod           INTEGER      NOT NULL,
  pes_cod_func      INTEGER      NOT NULL,
  emp_cod_fil       INTEGER      NOT NULL,
  pes_cod_cli       INTEGER      NOT NULL,
  aut_cod           INTEGER      NOT NULL,
  emp_cod_fin       INTEGER      NULL,
  ven_data_hora     DATE         NOT NULL,
  ven_a_vista       INTEGER      NOT NULL,
  ven_desc          NUMBER(8,2)  NULL,
  ven_prazo         INTEGER      NULL,
  ven_vlr_ent       NUMBER(8,2)  NULL,
  ven_tx_juros      NUMBER(3,2)  NULL,
  ven_qtd_prest     INTEGER      NULL,
  ven_valor_prest   NUMBER(8,2)  NULL,
  ven_valor_a_vista NUMBER(8,2)  NULL,
  ven_valor_a_prazo NUMBER(8,2)  NULL,
  vend_valor_prest  NUMBER(19,2) NULL,
  ven_valor_total   NUMBER(19,2) NULL
)
/


ALTER TABLE venda
  ADD CONSTRAINT pk_venda PRIMARY KEY (
    ven_cod
  )
/


ALTER TABLE venda
  ADD CONSTRAINT fk_venda_automovel FOREIGN KEY (
    aut_cod
  ) REFERENCES automovel (
    aut_cod
  )
/


ALTER TABLE venda
  ADD CONSTRAINT fk_venda_empresa_fil FOREIGN KEY (
    emp_cod_fil
  ) REFERENCES empresa (
    emp_cod
  )
/

ALTER TABLE venda
  ADD CONSTRAINT fk_venda_empresa_fin FOREIGN KEY (
    emp_cod_fin
  ) REFERENCES empresa (
    emp_cod
  )
/


ALTER TABLE venda
  ADD CONSTRAINT fk_venda_pessoa_cli FOREIGN KEY (
    pes_cod_cli
  ) REFERENCES pessoa (
    pes_cod
  )
/

ALTER TABLE venda
  ADD CONSTRAINT fk_venda_pessoa_func FOREIGN KEY (
    pes_cod_func
  ) REFERENCES pessoa (
    pes_cod
  )
/



