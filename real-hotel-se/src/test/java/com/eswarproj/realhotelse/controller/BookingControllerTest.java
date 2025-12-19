package com.eswarproj.realhotelse.controller;

import com.eswarproj.realhotelse.model.BookedRoom;
import com.eswarproj.realhotelse.model.Room;
import com.eswarproj.realhotelse.response.BookingResponse;
import com.eswarproj.realhotelse.service.IBookingService;
import com.eswarproj.realhotelse.service.IRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private IBookingService bookingService;

    @Mock
    private IRoomService roomService;

    @InjectMocks
    private BookingController bookingController;

    private BookedRoom booking;
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setId(1L);
        room.setRoomType("Single");
        room.setRoomPrice(BigDecimal.valueOf(100));

        booking = new BookedRoom();
        booking.setBookingId(1L);
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(2));
        booking.setGuestFullName("John Doe");
        booking.setGuestEmail("john@example.com");
        booking.setBookingStatus("PENDING");
        booking.setBookingConfirmationCode("CONF123");
        booking.setRoom(room);
    }

    @Test
    void saveBooking_shouldReturnBookingResponse() {
        // Arrange
        when(bookingService.saveBooking(eq(1L), any(BookedRoom.class))).thenReturn(booking);
        when(roomService.getRoomById(1L)).thenReturn(Optional.of(room));

        // Act
        ResponseEntity<?> response = bookingController.saveBooking(1L, booking);

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody() instanceof BookingResponse);
        BookingResponse body = (BookingResponse) response.getBody();
        assertEquals("CONF123", body.getBookingConfirmationCode());
    }

    @Test
    void getBookingByConfirmationCode_shouldReturnBooking() {
        // Arrange
        when(bookingService.findByBookingConfirmationCode("CONF123")).thenReturn(booking);
        when(roomService.getRoomById(1L)).thenReturn(Optional.of(room));

        // Act
        ResponseEntity<?> response = bookingController.getBookingByConfirmationCode("CONF123");

        // Assert
        assertTrue(response.getStatusCode().is2xxSuccessful());
        BookingResponse body = (BookingResponse) response.getBody();
        assertEquals("CONF123", body.getBookingConfirmationCode());
    }
}
