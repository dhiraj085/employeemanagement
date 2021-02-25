/**
 * @author : Dhiraj
 */
package com.pplflw.employeemanagement.repository;

import com.pplflw.employeemanagement.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
