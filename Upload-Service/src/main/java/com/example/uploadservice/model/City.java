package com.example.uploadservice.model;
import javax.persistence.*;

@Entity
public class City{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;


    public City() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public City(String name, String location){
        this.name = name;
        this.location = location;
    }
}