package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  this class is a REST Controller for the window class of the application
 */
@CrossOrigin
@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {

    private final WindowDao windowDao;
    private final RoomDao roomDao;

    /**
     * Constructor
     */
    public WindowController(WindowDao windowDao, RoomDao roomDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    /**
     * Retrieve the window list via a GET
     * @return  list of all windows (in DTO format)
     */
    @GetMapping
    public List<WindowDto> findAll() {
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());
    }

    /**
     * Retrieve a particular window via a GET
     * @param id    id of the window we want to see
     * @return      the desired window in DTO format
     */
    @GetMapping(path = "/{id}")
    public WindowDto findById(@PathVariable Long id) {
        return windowDao.findById(id).map(WindowDto::new).orElse(null);
    }

    /**
     * Update a window and switch its status via a PUT
     * @param id    id of the window
     * @return      the update window in DTO format
     */
    @PutMapping(path = "/{id}/switch")
    public WindowDto switchStatus(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

    /**
     * Create or update a window via a POST
     * @param dto   the window requested by the customer
     * @return      this window created or updated in DTO format
     */
    @PostMapping
    public WindowDto create(@RequestBody WindowDto dto) {
        // WindowDto must always contain the window room
        Room room = roomDao.getById(dto.getRoomId());
        Window window = null;
        // On creation id is not defined
        // create if Window does not exist
        if (dto.getId() == null) {
            window = windowDao.save(new Window(dto.getWindowStatus(), dto.getName(), room));
        }
        // update if already exist
        else {
            window = windowDao.getById(dto.getId());
            window.setWindowStatus(dto.getWindowStatus());
            window.setName(dto.getName());
        }
        return new WindowDto(window);
    }

    /**
     * Delete a window via a DELETE
     * @param id    id of the window we want to delete
     */
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }
}