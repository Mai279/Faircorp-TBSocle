package com.emse.spring.faircorp.model;

import org.hibernate.mapping.List;

import javax.persistence.*;
import java.util.Set;

/**
 * this class allows the creation of a room table in the database
 */
@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable = false)
    private Integer floor;

    private Double currentTemperature;
    private Double targetTemperature;

    @OneToMany(mappedBy = "room")
    private Set<Heater> heaters;

    @OneToMany(mappedBy = "room")
    private Set<Window> windows;

    @ManyToOne
    private Building building;

    /**
     * Constructors
     */
    public Room(){
    }

    public Room(Long id, String name, Integer floor, Double currentTemperature, Double targetTemperature, Building building) {
        this.id = id;
        this.floor = floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.building = building;
    }

    public Room(Long id, String name, Integer floor, Building building){
        this.id = id;
        this.floor = floor;
        this.name = name;
        this.building = building;
    }

    public Room(Integer floor, String name, Building building) {
        this.floor = floor;
        this.name = name;
        this.building = building;
    }

    /**
     * Getters
     */
    public Long getId() {
        return id;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public Set<Heater> getHeaters() {
        return heaters;
    }

    public Set<Window> getWindows() {
        return windows;
    }

    public Building getBuilding() {
        return building;
    }

    /**
     * Setters
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
