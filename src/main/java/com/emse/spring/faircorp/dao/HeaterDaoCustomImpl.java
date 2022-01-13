package com.emse.spring.faircorp.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * this class allows the implementation of the data access methods relative to the heater of the database
 */
@Repository
public class HeaterDaoCustomImpl implements HeaterDaoCustom{

    @PersistenceContext
    private EntityManager em;

    /**
     * this method allows to delete all the heaters in a given room
     * @param id    id of the room where to delete the windows
     */
    @Override
    public int deleteHeatersRoom(Long id){
        return em.createQuery("delete from Heater r where r.room.id = :idRoom")
                .setParameter("idRoom", id)
                .executeUpdate();
    }
}
