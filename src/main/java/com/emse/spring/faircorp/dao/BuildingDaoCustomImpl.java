package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * this class allows the implementation of the data access methods relative to the buildings of the database
 */
@Repository
public class BuildingDaoCustomImpl implements BuildingDaoCustom {

    @PersistenceContext
    private EntityManager em;

    /**
     * this method retrieves the building with the name passed as argument
     * @param name  name of the building we search
     * @return      building with the good name
     */
    @Override
    public Building findBuildingByName(String name) {
        return em.createQuery("select b from Building b where b.name = :nameBuilding", Building.class)
                .setParameter("nameBuilding", name)
                .getSingleResult();
    }


    /**
     * this method retrieves the list of windows of one building
     * @param id    id of the building where we look at the windows
     * @return      list of windows in the building
     */
    @Override
    public List<Window> findAllWindowsInBuilding(Long id) {
        return em.createQuery("select w from Window w join w.room r where r.id = w.room.id and r.building.id = :bId", Window.class)
                .setParameter("bId", id)
                .getResultList();
    }

    /**
     * this method retrieves the list of heaters of one building
     * @param id    id of the building where we look at the heaters
     * @return      list of heaters in the building
     */
    @Override
    public List<Heater> findAllHeatersInBuilding(Long id) {
        return em.createQuery("select h from Heater h join h.room r where r.id = h.room.id and r.building.id = :bId", Heater.class)
                .setParameter("bId", id)
                .getResultList();
    }

    /**
     * this method retrieves the list th id of the rooms of one building
     * @param id    id of the building where we look at the rooms
     * @return      list of the id of the the rooms of the building
     */
    @Override
    public List<Long> findIdRoomsInBuilding(Long id){
        return em.createQuery("select r.id from Room r where r.building.id = :bId", Long.class)
                .setParameter("bId", id)
                .getResultList();
    }
}
