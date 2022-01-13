package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import java.util.List;

/**
 * this interface groups the particular methods used to retrieve building data from the database
 */
public interface BuildingDaoCustom {
    Building findBuildingByName(String name);
    List<Window> findAllWindowsInBuilding(Long id);
    List<Heater> findAllHeatersInBuilding(Long id);
    List<Long> findIdRoomsInBuilding(Long id);
}
