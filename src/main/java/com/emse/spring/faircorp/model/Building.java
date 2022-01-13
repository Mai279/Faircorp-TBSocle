package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

/**
 * this class allows the creation of a building table in the database
 */
@Entity
@Table(name = "BUILDING")
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @OneToMany(mappedBy = "building")
    private Set<Room> room;

    /**
     * Constructors
     */
    public Building(){

    }

    public Building(Long id, String name, Set<Room> room) {
        this.id = id;
        this.name = name;
        this.room = room;
    }

    public Building(String name) {
        this.name = name;
    }

    /**
     * Getters & Setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Room> getRoom() {
        return room;
    }

    public void setRoom(Set<Room> room) {
        this.room = room;
    }
}
