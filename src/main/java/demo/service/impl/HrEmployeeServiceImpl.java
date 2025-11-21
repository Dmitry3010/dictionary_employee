package demo.service.impl;

import demo.dao.HrEmployeeDao;
import demo.dto.EmployeeCreateResponse;
import demo.dto.EmployeeItemDto;
import demo.dto.HrEmployeeCreateResponse;
import demo.dto.HrEmployeeItemDto;
import demo.exception.DuplicateException;
import demo.exception.NotFoundException;
import demo.model.Employee;
import demo.model.HrEmployee;
import demo.service.HrEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrEmployeeServiceImpl implements HrEmployeeService {

    public HrEmployeeDao hrEmployeeDao;

    public HrEmployeeServiceImpl(HrEmployeeDao hrEmployeeDao) {
        this.hrEmployeeDao = hrEmployeeDao;
    }

    @Override
    public List<HrEmployeeItemDto> getAll() {
        return hrEmployeeDao.findAll();
    }

    @Override
    public HrEmployeeItemDto getById(Integer id) {
        return hrEmployeeDao.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Сотрудника таким ID: %s не нашлось в списке", id)));
    }

    @Override
    public HrEmployeeCreateResponse create(HrEmployee hrEmployee) {
        String firstName = hrEmployee.getFirstName();
        String middleName = hrEmployee.getMiddleName();
        String lastName = hrEmployee.getLastName();
        for (HrEmployeeItemDto hrEmployeeByList : getAll()){
            if (hrEmployeeByList.getFirstName().equalsIgnoreCase(firstName) &&
                    hrEmployeeByList.getMiddleName().equalsIgnoreCase(middleName) &&
                    hrEmployeeByList.getLastName().equalsIgnoreCase(lastName)){
                throw new DuplicateException(String.format("Такой сотрудник уже существует в системе ID: %s", hrEmployeeByList.getId()));
            }
        }
        HrEmployee createdHrEmployee = hrEmployeeDao.create(hrEmployee);
        return HrEmployeeCreateResponse.of(createdHrEmployee.getId());
    }

    @Override
    public void update(HrEmployee hrEmployee) {
        hrEmployeeDao.update(hrEmployee);
    }

    @Override
    public boolean deleteById(Integer id) {
        return hrEmployeeDao.deleteById(id);
    }
}
