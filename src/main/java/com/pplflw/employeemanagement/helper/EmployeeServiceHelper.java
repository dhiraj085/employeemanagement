/**
 * @author : Dhiraj
 */
package com.pplflw.employeemanagement.helper;

import com.pplflw.employeemanagement.model.EmployeeEntity;
import com.pplflw.employeemanagement.data.Employee;
import com.pplflw.employeemanagement.model.EmployeeStateEnum;
import org.springframework.beans.BeanUtils;

public final class EmployeeServiceHelper {

    public static EmployeeEntity convertEmployeeToEmployeeEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        String status = employee.getEmployeeStateEnum().name();
        employeeEntity.setEmployeeStateEnum(EmployeeStateEnum.valueOf(status));
        BeanUtils.copyProperties(employee, employeeEntity);
        return employeeEntity;
    }

    public static Employee convertEmployeeEntityToEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        String status = employeeEntity.getEmployeeStateEnum().name();
        employee.setEmployeeStateEnum(com.pplflw.employeemanagement.data.EmployeeStateEnum.valueOf(status));
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }
}
