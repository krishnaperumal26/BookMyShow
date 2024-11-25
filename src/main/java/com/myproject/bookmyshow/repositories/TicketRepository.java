package com.myproject.bookmyshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.bookmyshow.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
        Ticket save(Ticket ticket);
}
