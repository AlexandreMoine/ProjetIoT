package com.polytech.iot.Service;

import com.polytech.iot.Domain.LoginParam;
import com.polytech.iot.Domain.UtilisateurEntity;
import com.polytech.iot.Repository.UtilisateurRepository;
import com.polytech.iot.mesExceptions.MonException;
import com.polytech.iot.utilitaire.MonMotPassHash;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Service
public class AuthentificationService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public AuthentificationService(UtilisateurRepository utilisateurRepostory) {
        this.utilisateurRepository = utilisateurRepostory;
    }

    public UtilisateurEntity authentification(LoginParam unUti) throws Exception {
        UtilisateurEntity unUtilisateur;
        String username = unUti.getUsername();
        String password = unUti.getPassword();
        unUtilisateur = utilisateurRepository.rechercheNom(username);
        if(unUtilisateur != null){
            try{
                String sel = unUtilisateur.getSalt();
                String mdp = unUtilisateur.getPassword();
                unUtilisateur.setToken(UUID.randomUUID().toString().toUpperCase());
                byte[] salt = MonMotPassHash.transformeEnBytes(sel);
                char[] pwd_char = MonMotPassHash.converttoCharArray(password);
                byte[] monpwdCo = MonMotPassHash.generatePasswordHash(pwd_char, salt);
                byte[] mdp_byte = MonMotPassHash.transformeEnBytes(mdp);

                if (!MonMotPassHash.verifyPassword(monpwdCo, mdp_byte)) {
                    return null;
                }
            } catch (MonException e) {
                throw e;
            } catch (Exception e) {
                throw e;
            }
        }
        return unUtilisateur;
    }

    public UtilisateurEntity ajouter(UtilisateurEntity utilisateurEntity) {
        try {
            utilisateurEntity.setSalt(MonMotPassHash.bytesToString(MonMotPassHash.GenerateSalt()));
            byte[] salt = MonMotPassHash.transformeEnBytes(utilisateurEntity.getSalt());
            char[] pwd_char = MonMotPassHash.converttoCharArray(utilisateurEntity.getPassword());
            utilisateurEntity.setPassword(MonMotPassHash.bytesToString(MonMotPassHash.generatePasswordHash(pwd_char, salt)));
            utilisateurEntity.setToken(UUID.randomUUID().toString().toUpperCase());

            return this.utilisateurRepository.save(utilisateurEntity);
        } catch (Exception e) {
            throw new MonException("Insert", "Sql", e.getMessage());
        }
    }
}
