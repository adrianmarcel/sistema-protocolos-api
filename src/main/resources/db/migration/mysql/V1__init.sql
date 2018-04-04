create database sistema_protocolos;

use sistema_protocolos;

CREATE TABLE diretoria (
    id               bigint(20)   NOT NULL,
    descricao        varchar(255) NOT NULL,
    sigla            varchar(255) NOT NULL,
    telefone         varchar(255) NOT NULL,
    email            varchar(255) NOT NULL,
    data_atualizacao datetime     NOT NULL,
    data_criacao     datetime     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE protocolo (
    id                     bigint(20)    NOT NULL,
    data                   datetime      NOT NULL,
    data_atualizacao       datetime      NOT NULL,
    data_criacao           datetime      NOT NULL,
    descricao              varchar(255)  NOT NULL,
    email                  varchar(255)  NOT NULL,
    usuario_id             bigint(20)    DEFAULT NULL,
    diretoria_id           bigint(20)    DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
    id               bigint(20)   NOT NULL,
    data_atualizacao datetime     NOT NULL,
    data_criacao     datetime     NOT NULL,
    nome             varchar(255) NOT NULL,
    perfil           varchar(255) NOT NULL,
    email            varchar(255) NOT NULL,
    login            varchar(255) NOT NULL,
    senha            varchar(255) NOT NULL,
    diretoria_id     bigint(20)    DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table 'diretoria'
--
ALTER TABLE diretoria
  ADD PRIMARY KEY (id);

--
-- Indexes for table 'protocolo'
--
ALTER TABLE protocolo
  ADD PRIMARY KEY (id),
  ADD KEY FK_USUARIO (usuario_id),
  ADD KEY FK_DIRETORIA (diretoria_id);

--
-- Indexes for table 'usuario'
--
ALTER TABLE usuario
    ADD PRIMARY KEY (id),
    ADD KEY FK_DIRETORIA (diretoria_id);
    
--
-- AUTO_INCREMENT for table 'diretoria'
--
ALTER TABLE diretoria
    MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;
    
--
-- AUTO_INCREMENT for table 'protocolo'
--
ALTER TABLE protocolo
    MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;
    
--
-- AUTO_INCREMENT for table 'usuario'
--
ALTER TABLE usuario
    MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;
    
--
-- Constraints for dumped tables
--

--
-- Constraints for table 'protocolo'
--
ALTER TABLE protocolo
    ADD CONSTRAINT FK_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario (id),
    ADD CONSTRAINT FK_DIRETORIA FOREIGN KEY (diretoria_id) REFERENCES diretoria (id);
    
--
-- Constraints for table 'usuario'
--
ALTER TABLE usuario
    ADD CONSTRAINT FK_DIRETORIA FOREIGN KEY (diretoria_id) REFERENCES diretoria (id);