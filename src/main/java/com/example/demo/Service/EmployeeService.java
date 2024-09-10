package com.example.demo.Service;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Entity.TaxDeductionDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        // Convert EmployeeDTO to Employee entity
        Employee employee = convertToEntity(employeeDTO);
        return employeeRepository.save(employee);
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumbers(employeeDTO.getPhoneNumbers());
        employee.setDoj(employeeDTO.getDoj());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }

    public TaxDeductionDTO calculateTaxDeductions(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        double yearlySalary = calculateYearlySalary(employee.getDoj(), employee.getSalary());
        double taxAmount = calculateTax(yearlySalary);
        double cessAmount = (yearlySalary > 2500000) ? (yearlySalary - 2500000) * 0.02 : 0;

        return new TaxDeductionDTO(employee.getEmployeeId(), employee.getFirstName(),
                employee.getLastName(), yearlySalary, taxAmount, cessAmount);
    }





    private double calculateYearlySalary(LocalDate doj, double salary) {
        int monthsWorked = calculateMonthsWorked(doj);
        return salary * monthsWorked;
    }

    private int calculateMonthsWorked(LocalDate doj) {
        YearMonth startOfYear = YearMonth.of(2023, 4);  // Financial year starting in April
        YearMonth endOfYear = YearMonth.of(2024, 3);

        YearMonth joiningYearMonth = YearMonth.from(doj);
        YearMonth currentYearMonth = YearMonth.now();

        if (joiningYearMonth.isBefore(startOfYear)) {
            joiningYearMonth = startOfYear;
        }

        return Period.between(joiningYearMonth.atEndOfMonth(), currentYearMonth.atEndOfMonth()).getMonths();
    }

    private double calculateTax(double yearlySalary) {
        double tax = 0;
        if (yearlySalary > 1000000) {
            tax += (yearlySalary - 1000000) * 0.2;
            yearlySalary = 1000000;
        }
        if (yearlySalary > 500000) {
            tax += (yearlySalary - 500000) * 0.1;
            yearlySalary = 500000;
        }
        if (yearlySalary > 250000) {
            tax += (yearlySalary - 250000) * 0.05;
        }
        return tax;
    }


}
