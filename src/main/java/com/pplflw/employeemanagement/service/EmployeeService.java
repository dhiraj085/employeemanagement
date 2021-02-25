/**
 * @author : Dhiraj
 */
package com.pplflw.employeemanagement.service;

import com.pplflw.employeemanagement.model.EmployeeEntity;
import com.pplflw.employeemanagement.helper.EmployeeServiceHelper;
import com.pplflw.employeemanagement.data.Employee;
import com.pplflw.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeServiceHelper.convertEmployeeToEmployeeEntity(employee);
        logger.info("Employee created {}", employeeEntity);
        employeeEntity = employeeRepository.save(employeeEntity);
        return EmployeeServiceHelper.convertEmployeeEntityToEmployee(employeeEntity);
    }

    public Employee findEmployee(long id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        EmployeeEntity employeeEntity = optionalEmployeeEntity.orElse(null);
        logger.info("Saved employee is {}", employeeEntity);
        if (Objects.nonNull(employeeEntity)) {
            return EmployeeServiceHelper.convertEmployeeEntityToEmployee(employeeEntity);
        }
        return null;
    }
}
