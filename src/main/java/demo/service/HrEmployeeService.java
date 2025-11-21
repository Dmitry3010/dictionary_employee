package demo.service;

import demo.dao.HrEmployeeDao;
import demo.dto.HrEmployeeCreateResponse;
import demo.dto.HrEmployeeItemDto;
import demo.model.HrEmployee;

import java.util.List;

public interface HrEmployeeService {
    List<HrEmployeeItemDto> getAll();

    HrEmployeeItemDto getById (Integer id);

    HrEmployeeCreateResponse create (HrEmployee hrEmployee);

    void update (HrEmployee hrEmployee);

    boolean deleteById (Integer id);
}
