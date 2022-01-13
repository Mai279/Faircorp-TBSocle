package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

/**
 * this class manages the tests of the methods of the implementations of the HeaterDAO interface
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class HeaterDaoTest {
    @Autowired
    private HeaterDao heaterDao;

    @Autowired
    private RoomDao roomDao;

    /**
     * this unit test verifies that if we search for a heater according to its ID in the database
     * i.e. with the getById() method of the {@link HeaterDao} class, we get the right heater
     */
    @Test
    public void shouldFindAHeater(){
        Heater Heater = heaterDao.getById(-10L);
        org.assertj.core.api.Assertions.assertThat(Heater.getName()).isEqualTo("Heater1");
        Assertions.assertThat(Heater.getHeaterStatus()).isEqualTo(HeaterStatus.ON);
    }

    /**
     * this test checks if the use of the deleteHeatersRoom method of the {@link HeaterDaoCustomImpl} class
     * deletes all the heaters of the room with the id passed in argument
     */
    @Test
    public void shouldDeleteHeatersRoom() {
        Room room = roomDao.getById(-10L);
        List<Long> roomIds = room.getHeaters().stream().map(Heater::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds.size()).isEqualTo(2);

        heaterDao.deleteHeatersRoom(-10L);
        List<Heater> result = heaterDao.findAllById(roomIds);
        Assertions.assertThat(result).isEmpty();
    }
}
