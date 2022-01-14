package com.polytech.iot.Domain;

import javax.persistence.*;

@Entity
@Table(name="utilisateur")
public class UtilisateurEntity {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String token;

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
    @Column(name = "username", nullable = true, length = 25)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "salt", nullable = true, length = 25)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 80)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "token", nullable = true, length = 10000)
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
