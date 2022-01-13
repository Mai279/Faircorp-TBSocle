package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * this interface presents the data access methods for the rooms in the database
 */
public interface RoomDao extends JpaRepository<Room,Long>, RoomDaoCustom {
    Room getById(Long id);
}
