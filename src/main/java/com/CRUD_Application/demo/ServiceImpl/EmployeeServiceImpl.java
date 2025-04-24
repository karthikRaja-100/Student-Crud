package com.CRUD_Application.demo.ServiceImpl;

import com.CRUD_Application.demo.dto.EmployeeDto;
import com.CRUD_Application.demo.entity.Employee;
import com.CRUD_Application.demo.repository.EmployeeRepo;
import com.CRUD_Application.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Map<String, Object> findAll() {
        List<Employee> employees = employeeRepo.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Data has been fetched successfully");
        response.put("data", employees);
        return response;
    }

    @Override
    public Map<String, Object> findById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (employee.isPresent()) {
            response.put("message", "Data has been fetched successfully");
            response.put("data", employee.get());
        } else {
            response.put("message", "Employee not found");
        }
        return response;
    }

    @Override
    public Map<String, Object> addEmployee(EmployeeDto employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());

        Employee savedEmployee = employeeRepo.save(employee);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee added successfully");
        response.put("data", savedEmployee);
        return response;
    }

    @Override
    public Map<String, Object> updateEmployee(int id, EmployeeDto employeeDTO) {
        Optional<Employee> existingEmployee = employeeRepo.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setAge(employeeDTO.getAge());
            employee.setEmail(employeeDTO.getEmail());
            employee.setSalary(employeeDTO.getSalary());
            employeeRepo.save(employee);
            response.put("message", "Data has been updated successfully");
            response.put("data", employee);
        } else {
            response.put("message", "Employee not found");
        }
        return response;
    }

    @Override
    public Map<String, Object> deleteById(int id) {
        Map<String, Object> response = new HashMap<>();
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            response.put("message", "Data has been deleted successfully");
        } else {
            response.put("message", "Employee not found");
        }
        return response;
    }

    @Override
    public Map<String, Object> patchEmployee(int id, EmployeeDto employeeDTO) {
        Optional<Employee> existingEmployee = employeeRepo.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            if (employeeDTO.getName() != null) {
                employee.setName(employeeDTO.getName());
            }
            if (employeeDTO.getAge() != null) {  // Corrected this line
                employee.setAge(employeeDTO.getAge());
            }
            if (employeeDTO.getSalary() != null && employeeDTO.getSalary() > 0) {
                employee.setSalary(employeeDTO.getSalary());
            }
            if (employeeDTO.getEmail() != null) {
                employee.setEmail(employeeDTO.getEmail());
            }
            employeeRepo.save(employee);
            response.put("message", "Data has been updated successfully");
            response.put("data", employee);
        } else {
            response.put("message", "Employee not found");
        }
        return response;
    }
}