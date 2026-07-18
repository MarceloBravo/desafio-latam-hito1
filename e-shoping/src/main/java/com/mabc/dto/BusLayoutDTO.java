package com.mabc.dto;

public class BusLayoutDTO {
    private Long id;
    private String busName;
    private String busModel;
    private int totalSeats;
    private int rows;
    private int columns;
    private AsientoDTO[] layout;
    private int hallwayCol;
    private int[] bathroomCoord = new int[2];   // Array to store the coordinates of the bathroom
    private int[] spaceCoord = new int[2];      // Array to store the coordinates of the space

    public BusLayoutDTO() {
    }

    // Constructor
    public BusLayoutDTO(Long id, String busName, String busModel, int totalSeats, int rows, int columns, AsientoDTO[] layout, int hallwayCol, int[] bathroomCoord, int[] spaceCoord) {
        this.id = id;
        this.busName = busName;
        this.busModel = busModel;
        this.totalSeats = totalSeats;
        this.rows = rows;
        this.columns = columns;
        if (layout != null) {
            this.layout = layout;
        } else {
            this.layout = new AsientoDTO[totalSeats];
        }
        if(hallwayCol < 0 || hallwayCol >= columns) {
            throw new IllegalArgumentException("hallwayCol must be between 0 and " + (columns - 1));
        }
        this.hallwayCol = hallwayCol;
        this.bathroomCoord = bathroomCoord;
        this.spaceCoord = spaceCoord;
    }

    // Getters and Setters
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

    public void setHallwayCol(int hallwayCol) {
        if(hallwayCol < 0 || hallwayCol >= columns) {
            throw new IllegalArgumentException("hallwayCol must be between 0 and " + (columns - 1));
        }
        this.hallwayCol = hallwayCol;
    }

    public int getHallwayCol() {
        return hallwayCol;
    }

    public void setBathroomCoord(int[] bathroomCoord) {
        if(bathroomCoord.length != 2 || bathroomCoord[0] < 0 || bathroomCoord[1] < 0) {
            throw new IllegalArgumentException("bathroomCoord must be an array of length 2");
        }
        this.bathroomCoord = bathroomCoord;
    }

    public int[] getBathroomCoord() {
        return bathroomCoord;
    }

    public int[] getSpaceCoord() {
        return spaceCoord;
    }

    public void setSpaceCoord(int[] spaceCoord) {
        this.spaceCoord = spaceCoord;
    }
}