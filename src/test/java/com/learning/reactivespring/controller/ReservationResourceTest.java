package com.learning.reactivespring.controller;

import com.learning.reactivespring.model.Reservation;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.security.RunAs;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.learning.reactivespring.controller.ReservationResource.ROOM_V_1_RESERVATION;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationResourceTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(this.context)
        .build();

        reservation = new Reservation(123l,
                LocalDate.now(), LocalDate.now().plus(10, ChronoUnit.DAYS), 150);
    }

    @Test
    void getAllReservations() {
        webTestClient.get().uri(ROOM_V_1_RESERVATION)
        .exchange().expectStatus().isOk().expectBodyList(Reservation.class);
    }

    @Test
    void createReservation() {
    }
}