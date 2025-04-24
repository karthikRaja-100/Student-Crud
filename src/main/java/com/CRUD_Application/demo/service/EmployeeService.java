package com.CRUD_Application.demo.service;

import com.CRUD_Application.demo.dto.EmployeeDto;
import java.util.Map;

public interface EmployeeService {
    Map<String, Object> findAll();
    Map<String, Object> findById(int id);
    Map<String, Object> updateEmployee(int id, EmployeeDto employeeDTO);
    Map<String, Object> deleteById(int id);
    Map<String, Object> patchEmployee(int id, EmployeeDto employeeDTO);
    Map<String, Object> addEmployee(EmployeeDto employeeDTO);
}
