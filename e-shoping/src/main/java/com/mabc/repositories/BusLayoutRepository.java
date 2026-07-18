package com.mabc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.mabc.entities.BusLayout;
import com.mabc.dto.BusLayoutDTO;

public interface BusLayoutRepository extends JpaRepository<BusLayout, Long> {
    // This interface is intended to handle data persistence for bus layouts.
}