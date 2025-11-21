package demo.dao;

import demo.dto.HrEmployeeItemDto;
import demo.model.HrEmployee;

import java.util.List;
import java.util.Optional;

public interface HrEmployeeDao {

    List<HrEmployeeItemDto> findAll();

    Optional<HrEmployeeItemDto> findById (Integer id);

    HrEmployee create (HrEmployee hrEmployee);

    void update (HrEmployee hrEmployee);

    boolean deleteById (Integer id);
}
