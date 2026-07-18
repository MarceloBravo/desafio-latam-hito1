package com.mabc.services.BusLayout;

import com.mabc.dto.BusLayoutDTO;
import com.mabc.entities.BusLayout;
import com.mabc.repositories.BusLayoutRepository;

import java.util.Optional;

public class BusLayoutCrudServiceImpl implements BusLayoutCrudService {
    private BusLayoutRepository busLayoutRepository;

    public BusLayoutCrudServiceImpl(BusLayoutRepository busLayoutRepository) {
        this.busLayoutRepository = busLayoutRepository;
    }

    @Override
    public Optional<BusLayoutDTO> findById(Long id) {
        Optional<BusLayout> busLayoutOptional = this.busLayoutRepository.findById(id);
        return busLayoutOptional.map(busLayout -> new BusLayoutDTO(
                busLayout.getId(),
                busLayout.getBusName(),
                busLayout.getBusModel(),
                busLayout.getTotalSeats(),
                busLayout.getRows(),
                busLayout.getColumns(),
                busLayout.getLayout(),
                busLayout.getHallwayCol(),
                busLayout.getBathroomCoord(),
                busLayout.getSpaceCoord()
        ));
    }

    @Override
    public Optional<BusLayoutDTO> save(BusLayoutDTO busLayoutDTO) {
        // Validate the bus layout data before saving
        if (!validateBusLayoutData(busLayoutDTO)) {
            throw new IllegalArgumentException("Invalid bus layout data");
        }

        // Convert DTO to entity and save
        BusLayout entity = new BusLayout();
        entity.setId(busLayoutDTO.getId());
        entity.setBusName(busLayoutDTO.getBusName());
        entity.setBusModel(busLayoutDTO.getBusModel());
        entity.setTotalSeats(busLayoutDTO.getTotalSeats());
        entity.setRows(busLayoutDTO.getRows());
        entity.setColumns(busLayoutDTO.getColumns());
        entity.setLayout(busLayoutDTO.getLayout());
        entity.setHallwayCol(busLayoutDTO.getHallwayCol());
        entity.setBathroomCoord(busLayoutDTO.getBathroomCoord());
        entity.setSpaceCoord(busLayoutDTO.getSpaceCoord());

        BusLayout saved = this.busLayoutRepository.save(entity);

        return Optional.ofNullable(saved).map(s -> new BusLayoutDTO(s.getId(), s.getBusName(), s.getBusModel(), s.getTotalSeats(), s.getRows(), s.getColumns(), s.getLayout(), s.getHallwayCol(), s.getBathroomCoord(), s.getSpaceCoord()));
    }

    @Override
    public boolean validateBusLayoutData(BusLayoutDTO busLayoutDTO) {
        if (busLayoutDTO == null || busLayoutDTO.getTotalSeats() != busLayoutDTO.getRows() * busLayoutDTO.getColumns()) {
            return false;
        }
        return true;
    }
}