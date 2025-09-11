package demo.enumVacancy;

import java.util.List;

public enum VacancyName {

    DEVELOPER("Разработчик", "IT"),
    MANAGER("Менеджер", "Управление"),
    ANALYST("Аналитик", "Аналитика"),
    TESTER("Тестировщик", "QA");

    private final String name;
    private final String department;

    VacancyName(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }
}
