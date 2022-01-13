package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * this class allows the implementation of the data access methods relative to the windows of the database
 */
@Repository
public class WindowDaoCustomImpl implements WindowDaoCustom {

    @PersistenceContext
    private EntityManager em;

    /**
     * this method retrieves the list of open windows
     * @param id    id of the room where we look at the state of the windows
     * @return      list of open windows in the id room provided
     */
    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.windowStatus= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }


    /**
     * this method allows to delete all the windows in a given room
     * @param id    id of the room where to delete the windows
     */
    @Override
    public int deleteWindowsRoom(Long id){
        return em.createQuery("delete from Window w where w.room.id = :idRoom")
                .setParameter("idRoom", id)
                .executeUpdate();
    }

}
