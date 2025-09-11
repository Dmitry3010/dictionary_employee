package demo.model;

import demo.enumVacancy.VacancyName;

import java.time.LocalDate;
import java.util.Objects;

public class Vacancy {

    private int id;
    private String name;
    private String description;
    private String cityName;
    private boolean accessibility;
    private LocalDate date;

    public Vacancy(int id, String name, String description, String cityName, boolean accessibility, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityName = cityName;
        this.accessibility = accessibility;
        this.date = date;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && accessibility == vacancy.accessibility && name == vacancy.name && Objects.equals(description, vacancy.description) && Objects.equals(cityName, vacancy.cityName) && Objects.equals(date, vacancy.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, cityName, accessibility, date);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name=" + name +
                ", description='" + description + '\'' +
                ", cityName='" + cityName + '\'' +
                ", accessibility=" + accessibility +
                ", date=" + date +
                '}';
    }
}