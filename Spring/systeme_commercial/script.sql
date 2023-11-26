create database _syscommercial;
\c _syscommercial;


create table _service(
    _idservice SERIAL PRIMARY KEY,
    _nom_service VARCHAR(100) NOT NULL,
    _role INTEGER NOT NULL DEFAULT 0,
    _password VARCHAR(20) CHECK (LENGTH(_password) >= 8)
);

create table _category(
    _idcategory SERIAL PRIMARY KEY,
    _libele VARCHAR(100) NOT NULL,
    _photo VARCHAR(255)
);
insert into _category values (default, 'Fourniture de bureau', 'helloworld.png');

create table _article(
    _idarticle SERIAL PRIMARY KEY,
    _idcategory INTEGER REFERENCES _category(_idcategory),
    _nom_article VARCHAR(100) UNIQUE NOT NULL,
    _photo VARCHAR(255)
);
insert into _article values (default, 1, 'Table', 'table.png');

create table _variante_article(
    _idvariante SERIAL PRIMARY KEY,
    _idarticle INTEGER REFERENCES _article(_idarticle),
    _nom_variante_article VARCHAR(100) UNIQUE NOT NULL,
    _description TEXT,
    _photo VARCHAR(255)
);
insert into _variante_article values (default, 1, 'Table basse', 'table en bois mais basse', 'hello.png');

create table _etat(
    _idetat SERIAL PRIMARY KEY,
    _libele VARCHAR(25) UNIQUE NOT NULL
);

create table _besoin_service(
    _idbesoin SERIAL PRIMARY KEY,
    _idservice INTEGER REFERENCES _service(_idservice),
    _date_demande DATE NOT NULL,
    _etat_demande INTEGER REFERENCES _etat(_idetat)
);

create table _besoin_detail(
    _iddetail SERIAL PRIMARY KEY,
    _idbesoin INTEGER REFERENCES _besoin_service(_idbesoin),
    _idvariante INTEGER REFERENCES _variante_article(_idvariante),
    _quantity DOUBLE PRECISION CHECK (_quantity >= 0)
);

create table _demande_grouper(
    _iddemande_grouper SERIAL PRIMARY KEY,
    _date_envoye DATE NOT NULL,
    _date_livraison DATE NOT NULL
);

create table _demande_grouper_detail(
    _iddemande_grouper_detail SERIAL PRIMARY KEY,
    _iddemande_grouper INTEGER REFERENCES _demande_grouper(_iddemande_grouper),
    _idvariante_article INTEGER REFERENCES _variante_article(_idvariante),
    _quantity_total DOUBLE PRECISION CHECK (_quantity_total >= 0)
);

create table _demande_grouper_besoin(
    _iddemande_grouper_besoin SERIAL PRIMARY KEY,
    _iddemande_grouper INTEGER REFERENCES _demande_grouper(_iddemande_grouper),
    _idbesoin INTEGER REFERENCES _besoin_service(_idbesoin)
);

-- Tsy creen ty vue ty fa reference fotsiny
-- create view _v_demande_grouper_detail as
--     select
--         _demande_grouper._iddemande_grouper as _iddemande_grouper,
--         _besoin_detail._idvariante as _idvariante,
--         sum(_besoin_detail._quantity) as _quantity
--     from
--         _demande_grouper
--         join _demande_grouper_besoin on _demande_grouper_besoin._iddemande_grouper = _demande_grouper._iddemande_grouper
--         join _besoin_service on _besoin_service._idbesoin = _demande_grouper_besoin._idbesoin
--         join _besoin_detail on _besoin_detail._idbesoin = _besoin_service._idbesoin
--     group by _demande_grouper._iddemande_grouper, _besoin_detail._idvariante;
-- 

create table _fournisseur(
    _idfournisseur SERIAL PRIMARY KEY,
    _nom_fournisseur VARCHAR(255) NOT NULL,
    _mail VARCHAR(255) UNIQUE NOT NULL,
    _mdp VARCHAR(255) NOT NULL,
    _address VARCHAR(255) NOT NULL
);

create table _proformat_facture(
    _idproformat SERIAL PRIMARY KEY,
    _idfournisseur INTEGER REFERENCES _fournisseur(_idfournisseur),
    _iddemande_grouper INTEGER REFERENCES _demande_grouper(_iddemande_grouper),
    _date_livraison DATE NOT NULL
);

-- table proformat detail reste à faire 
create table _proformat_detail(
    _idproformat_detail SERIAL PRIMARY KEY,
    _idvariante_article INTEGER REFERENCES _variante_article(_idvariante),
    _quantity_dispo DOUBLE PRECISION CHECK (_quantity_total >= 0),
    _prix_unitaire_ht DOUBLE PRECISION CHECK (_prix_unitaire_ht >= 0),
    _tva DOUBLE PRECISION CHECK (_tva >= 0),
    _prix_unitaire_ttc DOUBLE PRECISION CHECK (_prix_unitaire_ttc >= 0)
);