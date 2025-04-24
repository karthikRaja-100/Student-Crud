package com.CRUD_Application.demo.controller;

import com.CRUD_Application.demo.dto.EmployeeDto;
import com.CRUD_Application.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addEmployee(@Valid @RequestBody EmployeeDto employeeDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDto employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateById(@PathVariable int id, @Valid @RequestBody EmployeeDto employeeDTO) {
        return ResponseEntity.ok(employeeService.patchEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }
    
}
