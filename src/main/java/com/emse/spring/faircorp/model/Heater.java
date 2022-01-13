package com.emse.spring.faircorp.model;

import javax.persistence.*;

/**
 * this class allows the creation of a heating table in the database
 */
@Entity
@Table(name = "HEATER")
public class Heater {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    private Long power;

    @ManyToOne
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private HeaterStatus heaterStatus;

    /**
     * Constructors
     */
    public Heater(){
    }

    public Heater(Long id, HeaterStatus heaterStatus, String name, Long power, Room room) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.room = room;
        this.heaterStatus = heaterStatus;
    }

    public Heater(HeaterStatus heaterStatus, String name, Long power, Room room) {
        this.name = name;
        this.power = power;
        this.room = room;
        this.heaterStatus = heaterStatus;
    }


    /**
     * Getters
     */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPower() {
        return power;
    }

    public Room getRoom() {
        return room;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    /**
     * Setters
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }
}

