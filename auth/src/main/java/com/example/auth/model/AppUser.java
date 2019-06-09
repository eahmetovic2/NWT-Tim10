package com.example.auth.model;

import javax.persistence.*;

@Entity
@Table(name="app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String username, password;
    private String role;
    
    private Integer ucenikId;
    private Integer nastavnikId;
    
    public AppUser(Integer id, String username, String password, String role, Integer ucenikId, Integer nastavnikId) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.role = role;
            this.ucenikId = ucenikId;
            this.nastavnikId = nastavnikId;
    }

    public AppUser(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }	

    public Integer getUcenikId() {
        return ucenikId;
    }

    public void setUcenikId(Integer ucenikId) {
        this.ucenikId = ucenikId;
    }

    public Integer getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(Integer nastavnikId) {
        this.nastavnikId = nastavnikId;
    }
}