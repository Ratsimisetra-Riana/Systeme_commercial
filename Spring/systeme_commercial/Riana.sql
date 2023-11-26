CREATE TABLE bon_commande_header(
    id_bonCommande SERIAL PRIMARY KEY,
    id_proforma INTEGER FOREIGN KEY,
    id_proforma REFERENCES _proformat_facture(_idproforma)
);

CREATE TABLE bon_commande_detail(
    bon_commande_detail SERIAL PRIMARY KEY,
    id_bon_commande INTEGER FOREIGN KEY,
    id_variante_article INTEGER FOREIGN KEY,
    quantite_voulu NUMERIC,
    prix_unitaire NUMERIC,
    tva NUMERIC,
    prix_unitaire_ttc NUMERIC,
    id_bon_commande REFERENCES bon_commande_header(id_bon_commande),
    id_variante_article REFERENCES _variante_article(_idvariante_article)
);

create table variante_article(
    id_variante_article SERIAL PRIMARY KEY,
    id_article INTEGER REFERENCES _article(_idarticle),
    nom_variante_article VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    photo VARCHAR(255)
);