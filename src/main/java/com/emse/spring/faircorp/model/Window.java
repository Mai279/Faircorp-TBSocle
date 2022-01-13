package com.emse.spring.faircorp.model;

import javax.persistence.*;

/**
 * this class allows the creation of a windows table in the database
 */
@Entity //Mark this class as a JPA entity
@Table(name = "RWINDOW")//give a different name for your table. H2 can’t call a table Window because it is a reserved word.
public class Window {

    @Id
    @GeneratedValue //the table ID, ID auto generated
    private Long id;

    @Column(nullable=false) //field must be not nullable
    private String name;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING) // not nullable, an enumeration
    private WindowStatus windowStatus;

    @ManyToOne
    private Room room;

    /**
     * Constructors
     */
    public Window() {
    }

    public Window(Long id, WindowStatus windowStatus, String name, Room room) {
        this.id = id;
        this.windowStatus = windowStatus;
        this.name = name;
        this.room = room;
    }

    public Window(WindowStatus windowStatus, String name, Room room){
        this.windowStatus = windowStatus;
        this.name = name;
        this.room = room;
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

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public Room getRoom() {
        return room;
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

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
