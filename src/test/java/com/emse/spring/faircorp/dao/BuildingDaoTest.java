package com.emse.spring.faircorp.dao;


import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


/**
 * this class manages the tests of the methods of the implementations of the BuildingDAO interface
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BuildingDaoTest {
    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private RoomDao roomDao;

    /**
     * this unit test verifies that if we search for a building using its name (method findBuildingByName()),
     * we will indeed find it
     */
    @Test
    public void shouldFindBuildingByName(){
        Building building = buildingDao.findBuildingByName("Building1");
        Assertions.assertThat(building.getId()).isEqualTo(-10L);
    }

    /**
     * this test verifies that the findAllWindowsInBuilding() method implemented in the {@link BuildingDaoCustomImpl}
     * class returns the list of windows of the building with the id we gave as argument
     */
    @Test
    public void shouldFindAllWindowsInBuilding(){
        List<Window> result = buildingDao.findAllWindowsInBuilding(-10L);
        Assertions.assertThat(result)
                .hasSize(3)
                .extracting("id")
                .containsExactly(-10L, -9L, -8L);
    }

    /**
     * this test verifies that the findAllHeatersInBuilding() method implemented in the {@link BuildingDaoCustomImpl}
     * class returns the list of heaters of the building with the id we gave as argument
     */
    @Test
    public void shouldFindAllHeatersInBuilding(){
        List<Heater> result = buildingDao.findAllHeatersInBuilding(-10L);
        Assertions.assertThat(result)
                .hasSize(2)
                .extracting("id")
                .containsExactly(-10L, -9L);
    }

    /**
     * this test verifies that the findIdRoomsInBuilding() method implemented in the {@link BuildingDaoCustomImpl}
     * class returns the list of id of the rooms of the building
     */
    @Test
    public void shouldFindIdRoomsInBuilding(){
        List<Long> result = buildingDao.findIdRoomsInBuilding(-10L);
        Assertions.assertThat(result)
                .hasSize(2)
                .containsExactly(-10L, -9L);
    }
}

