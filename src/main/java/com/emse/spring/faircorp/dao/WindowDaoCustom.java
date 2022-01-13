package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;

import java.util.List;

/**
 * this interface groups the particular methods used to retrieve window data from the database
 */
public interface WindowDaoCustom {
    List<Window> findRoomOpenWindows(Long id);
    int deleteWindowsRoom(Long id);
}
