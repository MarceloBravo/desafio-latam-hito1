package com.mabc.services.BusLayout;

import com.mabc.dto.BusLayoutDTO;

import java.util.Optional;

public interface BusLayoutCrudService {
    // This class is intended to handle CRUD operations for bus layouts.
    // Implementation details will be added here in the future.

    Optional<BusLayoutDTO> findById(Long id);

    Optional<BusLayoutDTO> save(BusLayoutDTO busLayoutDTO);

    boolean validateBusLayoutData(BusLayoutDTO busLayoutDTO);
}