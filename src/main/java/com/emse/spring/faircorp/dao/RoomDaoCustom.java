package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;

/**
 * this interface groups the particular methods used to retrieve room data from the database
 */
public interface RoomDaoCustom {
    Room findRoomByName(String name);
}
