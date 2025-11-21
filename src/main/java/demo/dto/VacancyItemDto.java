package demo.dto;

import demo.model.City;

import java.time.LocalDate;

public class VacancyItemDto {

    private int id;
    private String name;
    private String description;
    private City city;
    private boolean accessibility;
    private LocalDate date;

    public VacancyItemDto(int id, String name, String description, City city, boolean accessibility, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.accessibility = accessibility;
        this.date = date;
    }

    public VacancyItemDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
