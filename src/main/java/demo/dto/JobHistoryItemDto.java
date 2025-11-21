package demo.dto;

import demo.model.Employee;
import demo.model.HrEmployee;
import demo.model.Vacancy;

import java.sql.Date;

public class JobHistoryItemDto {

    private int id;
    private Vacancy vacancy;
    private HrEmployee hrEmployee;
    private Employee employee;
    private boolean active;
    private Date startDate;

    public JobHistoryItemDto(int id, Vacancy vacancy, HrEmployee hrEmployee, Employee employee, boolean active, Date startDate) {
        this.id = id;
        this.vacancy = vacancy;
        this.hrEmployee = hrEmployee;
        this.employee = employee;
        this.active = active;
        this.startDate = startDate;
    }

    public JobHistoryItemDto(int id, Vacancy vacancy, HrEmployee hrEmployee, Employee employee, boolean active) {
        this.id = id;
        this.vacancy = vacancy;
        this.hrEmployee = hrEmployee;
        this.employee = employee;
        this.active = active;
    }

    public JobHistoryItemDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public HrEmployee getHrEmployee() {
        return hrEmployee;
    }

    public void setHrEmployee(HrEmployee hrEmployee) {
        this.hrEmployee = hrEmployee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
