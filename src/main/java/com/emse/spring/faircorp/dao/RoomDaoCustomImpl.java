package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * this class allows the implementation of the data access methods relative to the room of the database
 */
@Repository
public class RoomDaoCustomImpl implements RoomDaoCustom {

    @PersistenceContext
    private EntityManager em;

    /**
     * this method retrieves the room with the name passed as argument
     * @param name  name of the room we search
     * @return      room with the good name
     */
    @Override
    public Room findRoomByName(String name){
        return em.createQuery("select r from Room r where r.name = :nameRoom", Room.class)
                .setParameter("nameRoom", name)
                .getSingleResult();
    }
}
