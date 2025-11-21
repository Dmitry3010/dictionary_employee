package demo.dto;

public class VacancyCreateResponse {

    private Integer id;

    public VacancyCreateResponse(Integer id) {
        this.id = id;
    }

    public VacancyCreateResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static VacancyCreateResponse of(Integer id) {
        return new VacancyCreateResponse(id);
    }
}
