package demo.model;

import demo.enumVacancy.VacancyName;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Vacancy {

    private int id;
    private String name;
    private String description;
    private int cityId;
    private boolean accessibility;
    private LocalDate date;

    public Vacancy(int id, String name, String description, int cityId, boolean accessibility, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityId = cityId;
        this.accessibility = accessibility;
        this.date = date;
    }

    public Vacancy(String name, String description, int cityId, boolean accessibility) {
        this.name = name;
        this.description = description;
        this.cityId = cityId;
        this.accessibility = accessibility;
    }

    public Vacancy() {
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cityId=" + cityId +
                ", accessibility=" + accessibility +
                ", date=" + date +
                '}';
    }
}