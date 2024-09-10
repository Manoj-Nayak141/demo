package com.example.demo.Controller;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Entity.TaxDeductionDTO;
import com.example.demo.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    @GetMapping("/{employeeId}/tax-deductions")
    public ResponseEntity<TaxDeductionDTO> getTaxDeductions(@PathVariable String employeeId) {
        TaxDeductionDTO taxDeduction = employeeService.calculateTaxDeductions(employeeId);
        return ResponseEntity.ok(taxDeduction);
    }

}
