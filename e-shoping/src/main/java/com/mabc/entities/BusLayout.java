package com.mabc.entities;

import com.mabc.dto.AsientoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "bus_layouts")
public class BusLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bus_name", nullable = false, length = 100)
    private String busName;

    @Column(name = "bus_model", nullable = false, length = 100)
    private String busModel;

    @Column(name = "total_seats", nullable = false)
    private int totalSeats;

    @Column(name = "rows", nullable = false)
    private int rows;

    @Column(name = "columns", nullable = false)
    private int columns;

    @Column(name = "layout", nullable = false)
    private AsientoDTO[] layout;

    @Column(name = "hallway_col", nullable = false)
    private int hallwayCol;

    @Column(name = "bathroom_coord", nullable = false)
    private int[] bathroomCoord = new int[2];

    @Column(name = "space_coord", nullable = false)
    private int[] spaceCoord = new int[2];

    public BusLayout() {
    }

    public BusLayout(Long id, String busName, String busModel, int totalSeats, int rows, int columns, AsientoDTO[] layout, int hallwayCol, int[] bathroomCoord, int[] spaceCoord) {
        this.id = id;
        this.busName = busName;
        this.busModel = busModel;
        this.totalSeats = totalSeats;
        this.rows = rows;
        this.columns = columns;
        this.layout = layout;
        this.hallwayCol = hallwayCol;
        this.bathroomCoord = bathroomCoord;
        this.spaceCoord = spaceCoord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusModel() {
        return busModel;
    }

    public void setBusModel(String busModel) {
        this.busModel = busModel;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public AsientoDTO[] getLayout() {
        return layout;
    }

    public void setLayout(AsientoDTO[] layout) {
        this.layout = layout;
    }

    public int getHallwayCol() {
        return hallwayCol;
    }

    public void setHallwayCol(int hallwayCol) {
        this.hallwayCol = hallwayCol;
    }

    public int[] getBathroomCoord() {
        return bathroomCoord;
    }

    public void setBathroomCoord(int[] bathroomCoord) {
        this.bathroomCoord = bathroomCoord;
    }

    public int[] getSpaceCoord() {
        return spaceCoord;
    }

    public void setSpaceCoord(int[] spaceCoord) {
        this.spaceCoord = spaceCoord;
    }

}