package demo.dto;

import demo.model.City;

import java.time.LocalDate;

public class EmployeeItemDto {

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String description;
    private City city;
    private LocalDate date;

    public EmployeeItemDto(int id, String surname, String name, String patronymic, String description, City city, LocalDate date) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.description = description;
        this.city = city;
        this.date = date;
    }

    public EmployeeItemDto() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
