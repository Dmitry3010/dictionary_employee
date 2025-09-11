package demo.model;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String description;
    private String cityName;
    private LocalDate date;

    public Employee(int id, String surname, String name, String patronymic, String description, String cityName, LocalDate date) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.description = description;
        this.cityName = cityName;
        this.date = date;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(surname, employee.surname) && Objects.equals(name, employee.name) && Objects.equals(patronymic, employee.patronymic) && Objects.equals(description, employee.description) && Objects.equals(cityName, employee.cityName) && Objects.equals(date, employee.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, description, cityName, date);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", description='" + description + '\'' +
                ", cityName='" + cityName + '\'' +
                ", date=" + date +
                '}';
    }
}