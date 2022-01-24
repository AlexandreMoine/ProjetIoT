package com.polytech.iot.Domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mesure")

public class TauxGazEntity {

    private Integer id;
    private Integer quantiteGaz;
    private Date dateMesure;

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
    @Column(name = "quantiteGaz", nullable = false)
    public Integer getQuantiteGaz() {
        return quantiteGaz;
    }

    public void setQuantiteGaz(Integer quantiteGaz) {
        this.quantiteGaz = quantiteGaz;
    }

    @Basic
    @Column(name = "dateMesure", nullable = false)
    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
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
