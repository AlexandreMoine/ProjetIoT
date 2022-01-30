package com.polytech.iot.Domain;

import javax.persistence.*;

@Entity
@Table(name = "mesure")

public class TauxGazEntity {

    private Integer id;
    private Integer quantiteGaz;
    private String dateMesure;
    private Integer seuil_id;

    public Integer getSeuil_id() {
        return seuil_id;
    }

    public void setSeuil_id(Integer seuil_id) {
        this.seuil_id = seuil_id;
    }

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
    @Column(name = "quantitegaz", nullable = false)
    public Integer getQuantiteGaz() {
        return quantiteGaz;
    }

    public void setQuantiteGaz(Integer quantiteGaz) {
        this.quantiteGaz = quantiteGaz;
    }

    @Basic
    @Column(name = "datemesure", nullable = false)
    public String getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(String dateMesure) {
        this.dateMesure = dateMesure;
    }

    @Override
    public String toString() {
        return "TauxGazEntity{" +
                "id=" + id +
                ", quantiteGaz=" + quantiteGaz +
                ", dateMesure=" + dateMesure +
                '}';
    }
}