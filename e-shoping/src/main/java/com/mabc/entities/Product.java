package com.mabc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Mark mark;

    @OneToMany(mappedBy = "category")
    private List<Category> categories = new ArrayList<>();

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "descripcion", length = 255)
    private String description;

    @Column(name = "stock", nullable = false)
    private int stock;
    
    @Column(name = "peso", nullable = false)
    private double weight;

    @Column(name = "precio_costo", nullable = false)
    private double priceCost;

    @Column(name = "precio_venta", nullable = false)
    private double priceSale;

    public Product() {
    }

    public Product(Long id, Mark mark, Category category, String name, String description, int stock, double weight, double priceCost, double priceSale) {
        this.id = id;
        this.mark = mark;
        this.categories = new ArrayList<>();
        this.categories.add(category);
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.weight = weight;
        this.priceCost = priceCost;
        this.priceSale = priceSale;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(double priceCost) {
        this.priceCost = priceCost;
    }

    public double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(double priceSale) {
        this.priceSale = priceSale;
    }

}