package com.eswarproj.realhotelse.service;

import com.eswarproj.realhotelse.model.Room;
import com.eswarproj.realhotelse.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setId(1L);
        room.setRoomType("Single");
        room.setRoomPrice(BigDecimal.valueOf(100));
    }

    @Test
    void addNewRoom_shouldSaveRoom() throws java.sql.SQLException, java.io.IOException {
        // Arrange
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        // Act
        Room savedRoom = roomService.addNewRoom(null, "Single", BigDecimal.valueOf(100));

        // Assert
        assertNotNull(savedRoom);
        assertEquals("Single", savedRoom.getRoomType());
    }

    @Test
    void getAllRooms_shouldReturnListOfRooms() throws java.sql.SQLException {
        // Arrange
        when(roomRepository.findAll()).thenReturn(Collections.singletonList(room));

        // Act
        List<Room> rooms = roomService.getAllRooms();

        // Assert
        assertFalse(rooms.isEmpty());
        assertEquals(1, rooms.size());
    }

    @Test
    void getRoomById_shouldReturnRoom_whenRoomExists() {
        // Arrange
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        // Act
        Optional<Room> foundRoom = roomService.getRoomById(1L);

        // Assert
        assertTrue(foundRoom.isPresent());
        assertEquals(1L, foundRoom.get().getId());
    }
}
