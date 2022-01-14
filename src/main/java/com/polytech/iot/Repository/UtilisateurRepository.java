package com.polytech.iot.Repository;

import com.polytech.iot.Domain.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {
    @Query("select ut from UtilisateurEntity  ut where ut.username = ?1")
    public UtilisateurEntity rechercheNom(String login);

    public UtilisateurEntity findById(Long id);

    public UtilisateurEntity findByUsername(String surname);
}
