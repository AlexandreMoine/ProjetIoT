package com.polytech.iot.Domain;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "seuil")
@DynamicUpdate

public class SeuilEntity {

    private Integer id;
    private Integer valeur;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "valeur", nullable = false)
    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer seuil) {
        this.valeur = seuil;
    }
}
