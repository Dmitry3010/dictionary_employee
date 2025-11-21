package demo.dto;

import demo.model.Employee;
import demo.model.HrEmployee;
import demo.model.Vacancy;

import java.sql.Date;

public record JobDto (
    int id,
    Vacancy vacancy,
    HrEmployee hrEmployee,
    Employee employee,
    boolean active,
    Date startDate
){}
