package demo.model;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String description;
    private int cityId;
    private LocalDate date;

    public Employee(int id, String surname, String name, String patronymic, String description, int cityId, LocalDate date) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.description = description;
        this.cityId = cityId;
        this.date = date;
    }

    public Employee(String surname, String name, String patronymic, String description, int cityId) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.description = description;
        this.cityId = cityId;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", description='" + description + '\'' +
                ", cityId=" + cityId +
                ", date=" + date +
                '}';
    }
}