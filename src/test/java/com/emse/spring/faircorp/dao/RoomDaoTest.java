package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class manages the tests of the methods of the implementations of the {@link RoomDao} interface
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoomDaoTest {
    @Autowired
    private RoomDao roomDao;

    @Autowired
    private BuildingDao buildingDao;

    /**
     * this unit test verifies that if we search for a room according to its ID in the database
     * i.e. with the getById() method of the {@link RoomDao} class, we get the right room
     */
    @Test
    public void shouldFindARoom() {
        Room room = roomDao.getById(-9L);
        Assertions.assertThat(room.getName()).isEqualTo("Room2");
        Assertions.assertThat(room.getFloor()).isEqualTo(1);
    }

    /**
     * this unit test verifies that if we search for a room using its name (method FindARoomByName()),
     * we will indeed find it
     */
    @Test
    public void shouldFindARoomByName(){
        Room room = roomDao.findRoomByName("Room2");
        Assertions.assertThat(room.getId()).isEqualTo(-9L);
    }
}