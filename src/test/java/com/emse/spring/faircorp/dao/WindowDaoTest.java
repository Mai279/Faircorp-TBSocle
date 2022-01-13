package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this class manages the tests of the methods of the implementations of the WindowDAO interface
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class WindowDaoTest {
    @Autowired
    private WindowDao windowDao;

    @Autowired
    private RoomDao roomDao;
    private BuildingDao buildingDao;

    /**
     * this unit test verifies that if we search for a window according to its ID in the database
     * i.e. with the getById() method of the {@link WindowDao} class, we get the right window
     */
    @Test
    public void shouldFindAWindow() {
        Window window = windowDao.getById(-10L);
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getWindowStatus()).isEqualTo(WindowStatus.CLOSED);
    }

    /**
     * this test verifies that if a window is open in the room where we want to retrieve the list of open windows
     * i.e. where we apply the findRoomOpenWindows() method, it does appear in the list returned by the method
     */
    @Test
    public void shouldFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-9L);
        Assertions.assertThat(result)
                .hasSize(1)
                .extracting("id", "windowStatus")
                .containsExactly(Tuple.tuple(-8L, WindowStatus.OPEN));
    }

    /**
     * this test verifies that if no window is open in the room where we want to retrieve the list of open windows
     * i.e. where we apply the findRoomOpenWindows() method, the method returns an empty list
     */
    @Test
    public void shouldNotFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-10L);
        Assertions.assertThat(result).isEmpty();
    }

    /**
     * this test checks if the use of the deleteWindowsRoom method of the {@link WindowDaoCustomImpl} class
     * deletes all the windows of the room with the id passed in argument
     */
    @Test
    public void shouldDeleteWindowsRoom() {
        Room room = roomDao.getById(-10L);
        List<Long> roomIds = room.getWindows().stream().map(Window::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds.size()).isEqualTo(2);

        windowDao.deleteWindowsRoom(-10L);
        List<Window> result = windowDao.findAllById(roomIds);
        Assertions.assertThat(result).isEmpty();
    }
}
