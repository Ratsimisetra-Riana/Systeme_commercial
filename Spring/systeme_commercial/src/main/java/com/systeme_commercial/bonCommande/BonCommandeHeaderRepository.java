package com.systeme_commercial.bonCommande;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BonCommandeHeaderRepository extends JpaRepository<BonCommandeHeader,Integer>{

    @Query(value="SELECT * FROM bon_commande_header WHERE id_bon_commande=(SELECT MAX(id_bon_commande) FROM bon_commande_header)",nativeQuery = true)
    public List<BonCommandeHeader> findLatest();
}
