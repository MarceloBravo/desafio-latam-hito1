package com.mabc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mabc.dto.AsientoDTO;
import com.mabc.dto.BusLayoutDTO;
import com.mabc.entities.BusLayout;
import com.mabc.repositories.BusLayoutRepository;
import com.mabc.services.BusLayout.BusLayoutCrudServiceImpl;

import java.util.Optional;

public class BusLayoutCrudServiceImplTest {

    private BusLayoutCrudServiceImpl busLayoutCrud;
    private BusLayoutRepository busLayoutRepository;

    @BeforeEach
    public void setUp() {
        this.busLayoutRepository = mock(BusLayoutRepository.class);
        this.busLayoutCrud = new BusLayoutCrudServiceImpl(busLayoutRepository);
    }

    @Test
    @DisplayName("Should register a new bus layout")
    public void shouldRegisterANewLayoutBus() {
        // ARRANGE
        BusLayoutDTO busLayoutDTO = new BusLayoutDTO(
            null,
            "Bus 1",
            "Marcopolo G8 - Standard",
            50,
            5,
            10,
            new AsientoDTO[50],
            3,
            new int[]{10, 5},
            new int[]{5, 5}
        );
        BusLayout savedEntity = new BusLayout();
        savedEntity.setId(1L);
        savedEntity.setBusName(busLayoutDTO.getBusName());
        savedEntity.setBusModel(busLayoutDTO.getBusModel());
        savedEntity.setTotalSeats(busLayoutDTO.getTotalSeats());
        savedEntity.setRows(busLayoutDTO.getRows());
        savedEntity.setColumns(busLayoutDTO.getColumns());
        savedEntity.setLayout(busLayoutDTO.getLayout());
        savedEntity.setHallwayCol(busLayoutDTO.getHallwayCol());
        savedEntity.setBathroomCoord(busLayoutDTO.getBathroomCoord());
        savedEntity.setSpaceCoord(busLayoutDTO.getSpaceCoord());

        when(busLayoutRepository.save(any(BusLayout.class))).thenReturn(savedEntity);

        // ACT
        Optional<BusLayoutDTO> savedBusLayoutDTO = this.busLayoutCrud.save(busLayoutDTO);

        // ASSERT
        assertTrue(savedBusLayoutDTO.isPresent());
        verify(busLayoutRepository, times(1)).save(any(BusLayout.class));
        assertEquals(busLayoutDTO.getBusModel(), savedBusLayoutDTO.get().getBusModel());
        assertEquals(busLayoutDTO.getTotalSeats(), savedBusLayoutDTO.get().getTotalSeats());
        assertEquals(busLayoutDTO.getHallwayCol(), savedBusLayoutDTO.get().getHallwayCol());
        assertArrayEquals(busLayoutDTO.getBathroomCoord(), savedBusLayoutDTO.get().getBathroomCoord());
        assertArrayEquals(busLayoutDTO.getSpaceCoord(), savedBusLayoutDTO.get().getSpaceCoord());
    }

    @Test
    @DisplayName("Debe lanzar una excepción al intentar grabar un bus layout con datos inválidos")
    public void shouldThrowExceptionWhenRegisteringInvalidBusLayout() {
        // ARRANGE
        BusLayoutDTO invalidBusLayoutDTO = new BusLayoutDTO(
            null,
            "Bus 2",
            "Marcopolo G8 - Standard",
            50, // Total seats
            5,  // Rows
            9,  // Columns (invalid, should be 10 to match total seats)
            new AsientoDTO[50],
            3,
            new int[]{10, 5},
            new int[]{5, 5}
        );

        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.busLayoutCrud.save(invalidBusLayoutDTO);
        });

        assertEquals("Invalid bus layout data", exception.getMessage());
        verify(busLayoutRepository, never()).save(any(BusLayout.class));
    }

    @Test
    @DisplayName("Debe lanzar una excepción al intentar grabar un bus layout cuando recibe un DTO nulo")
    public void shouldThrowExceptionWhenRegisteringNullBusLayout() {
        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.busLayoutCrud.save(null);
        });

        assertEquals("Invalid bus layout data", exception.getMessage());
        verify(busLayoutRepository, never()).save(any(BusLayout.class));
    }

    @Test
    @DisplayName("Debe buscar y encontrar un bus Layout por su ID")
    public void shouldFindAndReturnBusLayoutById() {
        // ARRANGE
        Long busLayoutId = 1L;
        BusLayout busLayout = new BusLayout(
            busLayoutId,
            "Bus 1",
            "Marcopolo G8 - Standard",
            50,
            5,
            10,
            new AsientoDTO[50],
            3,
            new int[]{10, 5},
            new int[]{5, 5}
        );
        when(busLayoutRepository.findById(busLayoutId)).thenReturn(Optional.of(busLayout));

        // ACT
        Optional<BusLayoutDTO> foundBusLayoutDTO = this.busLayoutCrud.findById(busLayoutId);

        // ASSERT
        assertNotNull(foundBusLayoutDTO);
        verify(busLayoutRepository, times(1)).findById(busLayoutId);
        assertEquals(busLayout.getBusModel(), foundBusLayoutDTO.get().getBusModel());
        assertEquals(busLayout.getTotalSeats(), foundBusLayoutDTO.get().getTotalSeats());
        assertEquals(busLayoutId, foundBusLayoutDTO.get().getId());
    }


    @Test
    @DisplayName("Debe buscar un bus Layout por su ID y retornar un Optional vacío si no se encuentra")
    public void shouldFindAndReturnEmptyOptionalIfBusLayoutNotFound() {
        // ARRANGE
        Long busLayoutId = 99999L;
        
        when(busLayoutRepository.findById(busLayoutId)).thenReturn(Optional.empty());

        // ACT
        Optional<BusLayoutDTO> foundBusLayoutDTO = this.busLayoutCrud.findById(busLayoutId);

        // ASSERT
        assertTrue(foundBusLayoutDTO.isEmpty());
        verify(busLayoutRepository, times(1)).findById(busLayoutId);
    }
}