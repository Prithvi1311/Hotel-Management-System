package com.eswarproj.realhotelse.service;

import com.eswarproj.realhotelse.model.BookedRoom;
import com.eswarproj.realhotelse.model.Room;
import com.eswarproj.realhotelse.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private IRoomService roomService;

    @InjectMocks
    private BookingService bookingService;

    private BookedRoom bookingRequest;
    private Room room;

    @BeforeEach
    void setUp() {
        bookingRequest = new BookedRoom();
        bookingRequest.setCheckInDate(LocalDate.now().plusDays(1));
        bookingRequest.setCheckOutDate(LocalDate.now().plusDays(3));

        room = new Room();
        room.setId(1L);
        room.setBookings(new ArrayList<>());
    }

    @Test
    void saveBooking_shouldReturnConfirmationCode_whenRoomIsAvailable() {
        // Arrange
        when(roomService.getRoomById(1L)).thenReturn(Optional.of(room));
        when(bookingRepository.save(any(BookedRoom.class))).thenAnswer(invocation -> {
            BookedRoom savedBooking = invocation.getArgument(0);
            savedBooking.setBookingConfirmationCode("123456"); // Simulate DB generating ID/Code logic if needed, or
                                                               // just set it
            return savedBooking;
        });

        // Act
        BookedRoom savedBooking = bookingService.saveBooking(1L, bookingRequest);

        // Assert
        assertNotNull(savedBooking.getBookingConfirmationCode());
        verify(bookingRepository, times(1)).save(any(BookedRoom.class));
    }

    @Test
    void getAllBookings_shouldReturnListOfBookings() {
        // Arrange
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(bookingRequest));

        // Act
        var bookings = bookingService.getAllBookings();

        // Assert
        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
    }
}
